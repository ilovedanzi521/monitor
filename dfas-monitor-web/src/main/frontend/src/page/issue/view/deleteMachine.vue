<template>
    <win-fdialog title="删除机器" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="780px">
        <win-form :inline="true" v-testName="{'TEST_NAME':'editUser'}">
            <div class="hr">
                <win-form-item label="机器IP">
                    <win-input placeholder="机器IP" v-model="userReqVo.machine.ipAddress" :disabled="true"></win-input>
                </win-form-item>
                <win-form-item label="机器名称">
                    <win-input v-model="userReqVo.machine.name" :disabled="true"></win-input>
                </win-form-item>
            </div>
        </win-form>
        <div slot="footer" class="dialog-footer">
            <win-button @click="close">取 消</win-button>
            <win-button type="primary" @click="handleDeleteMachine">确 认</win-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import { UserReqVO,MachineClass, UserClass } from "../vo/IssueVO";
import BaseController from "../../common/controller/BaseController";
@Component({})
export default class AddUser extends BaseController {
    dialogFormVisible: boolean = true;
    machine: MachineClass = new MachineClass();
    ipAddress: string = "";
    machineName: string = "";
    user: UserClass = new UserClass();
    userType: string = "";
    userTypeId: string = "";
    roleIds: any[] = [];
    roleName: any[] = [];
    depName: string = "";
    depId: string = "";
    @Prop()
    userReqVo: UserReqVO;

    @Emit("edituser")
    handleEditUser() {
        let userParams = {
            id: this.user.id, //用户ID
            userId: this.user.userCode, //用户编吗
            userName: this.user.userName, //用户名称
            mailAddress: this.user.mailAddress, //邮箱地址
            phoneNumber: this.user.phoneNumber, //角色电话
            contactWay: this.user.contactWay, //角色联系方式
            userType: this.userTypeId, //角色类型 1代表后台2代表普通3临时
            departmentId: this.depId,
            userState: 1, // 角色状态：1-代表正常
            roleIds: this.roleIds
        };
        return userParams;
    }

    @Emit("editmachine")
    handleEditMachine() {
      let userParams = {
        id: this.machine.id, //ID
        ipAddress: this.machine.ipAddress, //ip
        name: this.machine.name//机器名称
      };
      return userParams;
    }

  @Emit("deletemachine")
  handleDeleteMachine() {
    let userParams = {
      id: this.machine.id, //ID
      ipAddress: this.machine.ipAddress, //ip
      name: this.machine.name//机器名称
    };
    return userParams;
  }

    @Emit("changeDep")
    /**改变部门 */
    changeDep(res) {
        console.log(res);
        this.roleIds = [];
        this.depId = res.id;
        return res;
    }

    /**改变角色状态 */

    /**改变用户状态 */
    changeUserType(res) {
        this.userType = res.userTypeName;
        this.userTypeId = res.userType;
    }
    close() {
        this.userReqVo.stateController.switchFormType = "";
    }

    mounted() {
        console.log("?????????????");
        console.log(this.userReqVo.machine)
        console.log(this.userReqVo.user);
        this.user = Object.assign({}, this.userReqVo.user);
        this.depName = this.userReqVo.user.departmentName;
        this.depId = this.userReqVo.user.departmentId;
        //加载角色
        this.userReqVo.user.roleNames.forEach(element => {
            this.roleIds.push(element.roleId);
            this.roleName.push(element.roleName);
        });
        this.userReqVo.userTypeArray.forEach(element => {
            if (element.userType == this.userReqVo.user.userType) {
                this.userType = element.userTypeName;
                this.userTypeId = element.userType;
            }
        });
        //初始化部门下的角色
        let depInit = this.userReqVo.department.departmentArray.filter(
            element => element.id == this.depId
        );
        this.$emit("changeDep", depInit[0]);
    }
}
</script>
<style lang="scss" scoped>
</style>
