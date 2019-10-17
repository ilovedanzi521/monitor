<template>
    <win-fdialog title="修改密码" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="380px">
        <win-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" v-testName="{'TEST_NAME':'resetPass'}" label-width="70px">
            <div class="hr">
                <win-form-item label="用户编码">
                    <win-input placeholder="用户名编码" :disabled="true" v-model="userReqVo.user.userCode"></win-input>
                </win-form-item>
                <win-form-item label="用户姓名">
                    <win-input placeholder="用户姓名" :disabled="true" v-model="userReqVo.user.userName"></win-input>
                </win-form-item>
                <win-form-item label="重置密码" prop="pass">
                    <win-input placeholder="请输入密码" autocomplete="off" type="password" v-model="ruleForm.pass" maxlength="30"></win-input>
                </win-form-item>
                <win-form-item label="确认密码" prop="checkPass">
                    <win-input placeholder="请确认密码" v-model="ruleForm.checkPass" autocomplete="off" type="password" maxlength="30"></win-input>
                </win-form-item>
            </div>
        </win-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="handleSubmitPassWord('ruleForm')">确 认</el-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import { UserReqVO, UserClass } from "../vo/MachineVO";
@Component({})
export default class Password extends BaseController {
    dialogFormVisible: boolean = true;
    ruleForm = {
        pass: "",
        checkPass: ""
    };

    @Prop()
    userReqVo: UserReqVO;

    validatePass2 = (rule, value, callback) => {
        if (value === "") {
            callback("请输入确认密码");
        } else if (value !== this.ruleForm.pass) {
            callback("两次输入密码不一致!");
        } else {
            callback();
        }
    };

    rules = {
        pass: [{ required: true, message: "请输入密码", trigger: "blur" }],
        checkPass: [
            { required: true, validator: this.validatePass2, trigger: "blur" }
        ]
    };

    handleSubmitPassWord(formName) {
        let form: any = this.$refs[formName];
        form.validate(valid => {
            if (valid) {
                let params = {
                    id: this.userReqVo.user.id,
                    loginPassword: this.ruleForm.checkPass
                };
                this.$emit("httpResetUserPassword", params);
            } else {
                return false;
            }
        });
    }

    close() {
        this.userReqVo.stateController.switchFormType = "";
    }
}
</script>
<style lang="scss" scoped>
</style>
