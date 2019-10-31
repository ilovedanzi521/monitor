<template>
  <win-fdialog title="新增刮取配置" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="800px" noDrag>
    <win-form :model="scrape" :rules="rules" ref="scrape" label-width="185px" v-testName="{'TEST_NAME':'userPage'}">
      <div class="hr">
        <win-form-item label="类型" prop="scrapeType" class="dialogItem">
          <win-select v-model="scrape.scrapeType" filterable placeholder="请选择" :disabled="editDisabled" @change="changeScrapeType" clearable>
            <win-option v-for="item in ars" :search="item.type" :key="item.type" :label="item.name" :value="item.type">
              <!--<span style="float: left">{{ item.type }}</span>-->
              <span>{{ "&nbsp;"+item.name }}</span>
            </win-option>
          </win-select>
        </win-form-item>
        <win-form-item label="作业名称" prop="jobName">
          <win-input placeholder="作业名称" v-model="scrape.jobName" maxlength="120"></win-input>
        </win-form-item>
       <!-- <win-form-item label="请求的协议方案" prop="scheme">
          <win-input placeholder="请填写请求的协议方案" v-model="scrape.scheme" maxlength="120"></win-input>
        </win-form-item>-->
        <win-form-item label="频率" prop="scrapeInterval">
          <win-input placeholder="频率" v-model="scrape.scrapeInterval" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="指标URL" prop="metricsPath" v-if="metricsPathVisible">
          <win-input placeholder="请填写指标URL" v-model="scrape.metricsPath" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="目标地址" prop="staticConfigsTargets" v-if="staticConfigsTargetsVisible">
          <win-input placeholder="请填写目标地址" v-model="scrape.staticConfigsTargets" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="抓取的所有指标实例标签" prop="staticConfigsLabelsInstance" v-if="staticConfigsLabelsInstanceVisible">
          <win-input placeholder="抓取的所有指标实例标签" v-model="scrape.staticConfigsLabelsInstance" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="注册中心地址" prop="consulSdConfigsServer" v-if="consulSdConfigsServerVisible">
          <win-input placeholder="注册中心地址" v-model="scrape.consulSdConfigsServer" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="服务名称" prop="consulSdConfigsServername" v-if="consulSdConfigsServernameVisible">
          <win-input placeholder="服务名称" v-model="scrape.consulSdConfigsServername" maxlength="120"></win-input>
        </win-form-item>
      </div>
    </win-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="_addScrape('scrape')">确 认</el-button>
    </div>
  </win-fdialog>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  import {  MachineClass,UserReqVO,ScrapeClass } from "../vo/SysconfigVO";

  @Component({})
  export default class AddScrape extends BaseController {
    dialogFormVisible: boolean = true;

    metricsPathVisible : boolean = false;
    staticConfigsTargetsVisible : boolean = false;
    staticConfigsLabelsInstanceVisible : boolean = false;
    consulSdConfigsServerVisible : boolean = false;
    consulSdConfigsServernameVisible : boolean = false;


    startX;
    startY;
    isDrag: Boolean = false;
    style = {
      position: "absolute",
      left: "400px",
      top: "20px",
      width: "422px",
      height: "665px",
      overflow: "hidden",
      cursor: "move"
    };
    private ars: any[] = [{
      type: 'machine',
      name: '机器',
    },{
      type: 'microService',
      name: '微服务',
    },{
      type: 'gateway',
      name: '网关',
    }];
    $ref;
    rules = {
      companyName: [
        {
          required: true,
          message: "公司名称未填写不可提交",
          trigger: "blur"
        }
      ],
      companySimpleName: [
        {
          required: true,
          message: "公司简称未填写不可提交",
          trigger: "blur"
        }
      ],
      companyCode: [
        {
          required: true,
          message: "公司编码未填写不可提交",
          trigger: "blur"
        }
      ]
    };

    @Prop()
    userReqVo: UserReqVO;

    scrape: ScrapeClass = new ScrapeClass();

    private changeScrapeType(scrapeType: string) {
      if('machine' == scrapeType || 'gateway' == scrapeType){
         this.staticConfigsTargetsVisible = true;
         this.staticConfigsLabelsInstanceVisible = true;
         this.metricsPathVisible = false;
         this.consulSdConfigsServerVisible = false;
      }else if('microService' == scrapeType){
         this.staticConfigsTargetsVisible = false;
         this.staticConfigsLabelsInstanceVisible = false;
         this.metricsPathVisible = true;
         this.consulSdConfigsServerVisible = true;
      }
      console.log(scrapeType);
    }

    _addScrape(formName) {
      const comParams = {
        jobName: this.scrape.jobName,
        scheme: this.scrape.scheme,
        scrapeInterval: this.scrape.scrapeInterval,
        metricsPath: this.scrape.metricsPath,
        staticConfigsTargets: this.scrape.staticConfigsTargets,
        staticConfigsLabelsInstance: this.scrape.staticConfigsLabelsInstance,
        consulSdConfigsServer: this.scrape.consulSdConfigsServer,
        consulSdConfigsServername: this.scrape.consulSdConfigsServername,
        consulSdConfigsScheme: this.scrape.consulSdConfigsScheme,
      };
      console.log("_addScrape>>>>>start");
      console.log(this.userReqVo);
      console.log("_addScrape>>>>>end");

      this.$emit("addScrape", comParams,this.userReqVo);
    }

    close() {
      this.userReqVo.stateController.switchFormType = "";
    }
  }
</script>
<style lang="scss" scoped>
</style>
