<template>
  <win-fdialog title="删除刮取配置" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="800px" noDrag>
    <win-form :inline="true" v-testName="{'TEST_NAME':'userPage'}">
      <div class="hr">
        <win-form-item label="抓取指标的job名称" prop="jobName">
          <win-input placeholder="请填写抓取指标的job名称" v-model="userReqVo.scrape.jobName" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="请求的协议方案" prop="scheme">
          <win-input placeholder="请填写请求的协议方案" v-model="userReqVo.scrape.scheme" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="抓取目标的频率" prop="scrapeInterval">
          <win-input placeholder="请填写抓取目标的频率" v-model="userReqVo.scrape.scrapeInterval" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="从目标获取指标的HTTP资源路径" prop="metricsPath">
          <win-input placeholder="请填写从目标获取指标的HTTP资源路径" v-model="userReqVo.scrape.metricsPath" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="静态配置指定的目标" prop="staticConfigsTargets">
          <win-input placeholder="请填写静态配置指定的目标" v-model="userReqVo.scrape.staticConfigsTargets" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="抓取的所有指标实例标签" prop="staticConfigsLabelsInstance">
          <win-input placeholder="抓取的所有指标实例标签" v-model="userReqVo.scrape.staticConfigsLabelsInstance" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="注册中心地址" prop="consulSdConfigsServer">
          <win-input placeholder="注册中心地址" v-model="userReqVo.scrape.consulSdConfigsServer" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="服务名称" prop="consulSdConfigsServername">
          <win-input placeholder="服务名称" v-model="userReqVo.scrape.consulSdConfigsServername" maxlength="120"></win-input>
        </win-form-item>
      </div>
    </win-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="_deleteScrape('scrape')">确 认</el-button>
    </div>
  </win-fdialog>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  import {  MachineClass,UserReqVO,ScrapeClass } from "../vo/SysconfigVO";

  @Component({})
  export default class DeleteScrape extends BaseController {
    dialogFormVisible: boolean = true;
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

    _deleteScrape(formName) {
      const comParams = {
        id: this.userReqVo.scrape.id,
        jobName: this.userReqVo.scrape.jobName,
        scheme: this.userReqVo.scrape.scheme,
        scrapeInterval: this.userReqVo.scrape.scrapeInterval,
        metricsPath: this.userReqVo.scrape.metricsPath,
        staticConfigsTargets: this.userReqVo.scrape.staticConfigsTargets,
        staticConfigsLabelsInstance: this.userReqVo.scrape.staticConfigsLabelsInstance,
        consulSdConfigsServer: this.userReqVo.scrape.consulSdConfigsServer,
        consulSdConfigsServername: this.userReqVo.scrape.consulSdConfigsServername,
        consulSdConfigsScheme: this.userReqVo.scrape.consulSdConfigsScheme,
      };
      console.log("_deleteScrape>>>>>start");
      console.log(this.userReqVo);
      console.log("_deleteScrape>>>>>end");

      this.$emit("deleteScrape", comParams,this.userReqVo);
    }

    close() {
      this.userReqVo.stateController.switchFormType = "";
    }
  }
</script>
<style lang="scss" scoped>
</style>
