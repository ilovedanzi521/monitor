<template>
  <win-fdialog title="新增预警规则配置" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="800px" noDrag>
    <win-form :model="alertRule" :rules="rules" ref="scrape" label-width="185px" v-testName="{'TEST_NAME':'userPage'}">
      <div class="hr">
        <win-form-item label="规则分组名称" prop="groupsName">
          <win-input placeholder="请填写规则分组名称" v-model="alertRule.groupsName" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="告警规则的名称" prop="name">
          <win-input placeholder="请填写告警规则的名称" v-model="alertRule.name" maxlength="120"></win-input>
        </win-form-item>
        <!--<win-form-item label="告警PromQL表达式" prop="expr">
          <win-input placeholder="请填写告警PromQL表达式" v-model="alertRule.expr" maxlength="120"></win-input>
        </win-form-item>-->
        <win-form-item label="评估等待时间" prop="fortime">
          <win-input placeholder="请填写评估等待时间" v-model="alertRule.fortime" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="告警级别" prop="labelsSeverity">
          <win-input placeholder="请填写告警级别" v-model="alertRule.labelsSeverity" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="摘要" prop="annotationsSummary">
          <win-input placeholder="请填写摘要" v-model="alertRule.annotationsSummary" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="描述" prop="annotationsDescription">
          <win-input placeholder="请填写描述" v-model="alertRule.annotationsDescription" maxlength="120"></win-input>
        </win-form-item>
      </div>
    </win-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="_addAlertRule('alertRule')">确 认</el-button>
    </div>
  </win-fdialog>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  import {  MachineClass,UserReqVO,AlertRuleClass } from "../vo/SysconfigVO";
  import SysconfigService from "../service/SysconfigService";

  @Component({})
  export default class AddAlertRule extends BaseController {
    dialogFormVisible: boolean = true;
    private editDisabled: boolean = false;
    private ipAddressSelect: any[] = [];
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

    alertRule : AlertRuleClass = new AlertRuleClass();

    _addAlertRule(formName) {
      const comParams = {
        groupsName: this.alertRule.groupsName,
        alertRuleType: this.alertRule.alertRuleType,
        name: this.alertRule.name,
        expr: this.alertRule.expr,
        fortime: this.alertRule.fortime,
        labelsSeverity: this.alertRule.labelsSeverity,
        annotationsSummary: this.alertRule.annotationsSummary,
        annotationsDescription: this.alertRule.annotationsDescription
      };
      console.log("_addAlertRule>>>>>start");
      console.log(this.userReqVo);
      console.log("_addAlertRule>>>>>end");

      this.$emit("addAlertRule", comParams,this.userReqVo);
    }

    mounted() {
      SysconfigService.initIpAddressSelect(this.ipAddressSelect);
    }
    close() {
      this.userReqVo.stateController.switchFormType = "";
    }
  }
</script>
<style lang="scss" scoped>
</style>
