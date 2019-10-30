<template>
  <win-fdialog title="修改预警规则配置" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="800px" noDrag>
    <win-form :inline="true" v-testName="{'TEST_NAME':'userPage'}">
      <div class="hr">
        <win-form-item label="规则分组名称" prop="groupsName">
          <win-input placeholder="请填写规则分组名称" v-model="userReqVo.alertRule.groupsName" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="告警规则的名称" prop="name">
          <win-input placeholder="请填写告警规则的名称" v-model="userReqVo.alertRule.name" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="告警PromQL表达式" prop="expr">
          <win-input placeholder="请填写告警PromQL表达式" v-model="userReqVo.alertRule.expr" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="评估等待时间" prop="fortime">
          <win-input placeholder="请填写评估等待时间" v-model="userReqVo.alertRule.fortime" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="告警级别" prop="labelsSeverity">
          <win-input placeholder="请填写告警级别" v-model="userReqVo.alertRule.labelsSeverity" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="摘要" prop="annotationsSummary">
          <win-input placeholder="请填写摘要" v-model="userReqVo.alertRule.annotationsSummary" maxlength="120"></win-input>
        </win-form-item>
        <win-form-item label="描述" prop="annotationsDescription">
          <win-input placeholder="请填写描述" v-model="userReqVo.alertRule.annotationsDescription" maxlength="120"></win-input>
        </win-form-item>
      </div>
    </win-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="_editAlertRule('scrape')">确 认</el-button>
    </div>
  </win-fdialog>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  import {  MachineClass,UserReqVO,ScrapeClass,AlertRuleClass } from "../vo/SysconfigVO";

  @Component({})
  export default class EditAlertRule extends BaseController {
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

    alertRule : AlertRuleClass = new AlertRuleClass();

    _editAlertRule(formName) {
      const comParams = {
        id: this.userReqVo.alertRule.id,
        groupsName: this.userReqVo.alertRule.groupsName,
        name: this.userReqVo.alertRule.name,
        expr: this.userReqVo.alertRule.expr,
        fortime: this.userReqVo.alertRule.fortime + '',
        labelsSeverity: this.userReqVo.alertRule.labelsSeverity,
        annotationsSummary: this.userReqVo.alertRule.annotationsSummary,
        annotationsDescription: this.userReqVo.alertRule.annotationsDescription
      };
      console.log("_editAlertRule>>>>>start");
      console.log(this.userReqVo);
      console.log("_editAlertRule>>>>>end");

      this.$emit("editAlertRule", comParams,this.userReqVo);
    }

    close() {
      this.userReqVo.stateController.switchFormType = "";
    }
  }
</script>
<style lang="scss" scoped>
</style>
