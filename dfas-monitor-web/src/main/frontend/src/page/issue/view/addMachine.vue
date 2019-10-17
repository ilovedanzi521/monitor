<template>
    <win-fdialog title="新增机器" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="380px" noDrag>
        <win-form :model="machine" :rules="rules" ref="machine" label-width="70px" v-testName="{'TEST_NAME':'userPage'}">
            <div class="hr">
                <win-form-item label="机器IP" prop="ipAddress">
                    <win-input placeholder="请填写机器IP" v-model="machine.ipAddress" maxlength="50"></win-input>

                </win-form-item>

                <win-form-item label="机器名称" prop="name">
                    <win-input placeholder="请填写机器名称" v-model="machine.name" maxlength="50"></win-input>
                </win-form-item>

            </div>

        </win-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="handleAddMachine('machine')">确 认</el-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import { CompanyClass,MachineClass, UserReqVO } from "../vo/IssueVO";

@Component({})
export default class AddMachine extends BaseController {
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

    handleAddMachine(formName) {
      const comParams = {
        ipAddress: this.machine.ipAddress,
        name: this.machine.name
      };

      this.$emit("addMachine", comParams);
    }

    close() {
        this.userReqVo.stateController.switchFormType = "";
    }
}
</script>
<style lang="scss" scoped>
</style>
