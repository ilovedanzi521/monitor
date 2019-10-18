<template>
    <win-fdialog title="删除阈值" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="780px">
        <win-form :inline="true" v-testName="{'TEST_NAME':'editUser'}">
            <div class="hr">
              <win-form-item label="指标主体">
                <win-input v-model="userReqVo.threshold.indicatorBody" :disabled="true"></win-input>
              </win-form-item>
              <win-form-item label="指标名称">
                <win-input v-model="userReqVo.threshold.indicatorName" :disabled="true"></win-input>
              </win-form-item>
              <win-form-item label="指标符号">
                <win-input v-model="userReqVo.threshold.indicatorSymbol" :disabled="true"></win-input>
              </win-form-item>
              <win-form-item label="阈值">
                <win-input v-model="userReqVo.threshold.threshold" :disabled="true"></win-input>
              </win-form-item>
            </div>
        </win-form>
        <div slot="footer" class="dialog-footer">
            <win-button @click="close">取 消</win-button>
            <win-button type="primary" @click="deleteThreshold">确 认</win-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import { UserReqVO,MachineClass, UserClass } from "../vo/ThresholdVO";
import BaseController from "../../common/controller/BaseController";
@Component({})
export default class DeleteThreshold extends BaseController {
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



  deleteThreshold(formName) {
    const comParams = {

    };

    this.$emit("deleteThreshold", comParams);
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
