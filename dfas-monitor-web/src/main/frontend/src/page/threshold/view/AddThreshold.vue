<template>
    <win-fdialog title="新增阈值" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="380px" noDrag>
        <win-form :model="threshold" :rules="rules" ref="threshold" label-width="70px" v-testName="{'TEST_NAME':'userPage'}">
            <div class="hr">
                <win-form-item label="指标主体" prop="indicatorBody">
                    <win-input placeholder="请填写指标主体" v-model="threshold.indicatorBody" maxlength="50"></win-input>

                </win-form-item>

                <win-form-item label="指标名称" prop="indicatorName">
                    <win-input placeholder="请填写指标名称" v-model="threshold.indicatorName" maxlength="50"></win-input>
                </win-form-item>

              <win-form-item label="指标符号" prop="indicatorSymbol">
                <win-input placeholder="请填写指标符号" v-model="threshold.indicatorSymbol" maxlength="50"></win-input>
              </win-form-item>

              <win-form-item label="阈值" prop="threshold">
                <win-input placeholder="阈值" v-model="threshold.threshold" maxlength="50"></win-input>
              </win-form-item>

            </div>

        </win-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="saveThreshold('threshold')">确 认</el-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import { CompanyClass,MachineClass,ThresholdClass, UserReqVO } from "../vo/ThresholdVO";

@Component({})
export default class AddThreshold extends BaseController {
    dialogFormVisible: boolean = true;
    threshold: ThresholdClass = new ThresholdClass();
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
    /*compan: CompanyClass = new CompanyClass();

    handleAddCom(formName) {
        const comParams = {
            companyFullName: this.compan.companyName,
            companySimpleName: this.compan.companySimpleName,
            companyCode: this.compan.companyCode
        };

        this.$emit("addCom", comParams);
    }*/

  machine: MachineClass = new MachineClass();

  saveThreshold(formName) {
      const comParams = {
        indicatorBody: this.threshold.indicatorBody,
        indicatorName: this.threshold.indicatorName,
        indicatorSymbol: this.threshold.indicatorSymbol,
        threshold: this.threshold.threshold
      };

      this.$emit("addThreshold", comParams);
    }

    close() {
        this.userReqVo.stateController.switchFormType = "";
    }
}
</script>
<style lang="scss" scoped>
</style>
