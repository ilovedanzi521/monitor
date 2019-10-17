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
import { UserReqVO,MachineClass, UserClass } from "../vo/MachineVO";
import BaseController from "../../common/controller/BaseController";
@Component({})
export default class DeleteMachine extends BaseController {
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

    close() {
        this.userReqVo.stateController.switchFormType = "";
    }

    mounted() {
    }
}
</script>
<style lang="scss" scoped>
</style>
