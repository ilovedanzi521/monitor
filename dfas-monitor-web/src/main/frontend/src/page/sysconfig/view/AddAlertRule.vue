<template>
  <win-fdialog title="新增预警规则配置" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="800px" noDrag>
    <win-form :model="alertRule" :rules="rules" ref="scrape" label-width="185px" v-testName="{'TEST_NAME':'userPage'}">
      <div class="hr">
        <win-form-item label="阈值类型" prop="alertRuleType" class="dialogItem">
          <win-select v-model="alertRule.alertRuleType" filterable placeholder="请选择" :disabled="editDisabled" @change="changeAlertRuleType" clearable>
            <win-option v-for="item in userReqVo.alertTypeSelect" :search="item.alertRuleType" :key="item.alertRuleType" :label="item.value" :value="item.alertRuleType">
              <!--<span style="float: left">{{ item.type }}</span>-->
              <span>{{ "&nbsp;"+item.value }}</span>
            </win-option>
          </win-select>
        </win-form-item>
        <win-form-item label="IP地址" prop="ipAddress" class="dialogItem">
          <win-select v-model="alertRule.ipAddress" filterable placeholder="请选择" :disabled="editDisabled" @change="changeIpaddressType" clearable>
            <win-option v-for="item2 in userReqVo.ipAddressSelect" :search="item2.code" :key="item2.code" :label="item2.code" :value="item2.value">
              <!--<span style="float: left">{{ item.type }}</span>-->
              <span>{{ "&nbsp;"+item2.code }}</span>
            </win-option>
          </win-select>
        </win-form-item>
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
        <!--<win-form-item label="告警级别" prop="labelsSeverity">
          <win-input placeholder="请填写告警级别" v-model="alertRule.labelsSeverity" maxlength="120"></win-input>
        </win-form-item>-->
        <win-form-item label="类型" prop="labelsSeverity" class="dialogItem">
          <win-select v-model="alertRule.labelsSeverity" filterable placeholder="请选择" :disabled="editDisabled"  clearable>
            <win-option v-for="item in levels" :search="item.code" :key="item.code" :label="item.name" :value="item.code">
              <!--<span style="float: left">{{ item.type }}</span>-->
              <span>{{ "&nbsp;"+item.name }}</span>
            </win-option>
          </win-select>
        </win-form-item>

        <!--<win-form-item label="摘要" prop="annotationsSummary">
          <win-input placeholder="请填写摘要" v-model="alertRule.annotationsSummary" maxlength="120"></win-input>
        </win-form-item>-->
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
    startX;
    startY;
    isDrag: Boolean = false;

    private levels: any[] = [{
      code: '0',
      name: '低',
    },{
      code: '1',
      name: '中',
    },{
      code: '2',
      name: '高',
    }];

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
        ipAddress: this.alertRule.ipAddress,
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

    private changeAlertRuleType(alertRuleType: string) {
      console.log(alertRuleType);
    }

    private changeIpaddressType(ipAddressType: string) {
      console.log(ipAddressType);
    }

    mounted() {
      SysconfigService.initAlertTypeSelect(this.userReqVo);
      SysconfigService.initIpAddressSelect(this.userReqVo);
    }
    close() {
      this.userReqVo.stateController.switchFormType = "";
    }
  }
</script>
<style lang="scss" scoped>
</style>
