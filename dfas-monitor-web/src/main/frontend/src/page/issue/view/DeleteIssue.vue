<template>
    <win-fdialog title="删除问题" :visible.sync="dialogFormVisible" @close="close" :close-on-click-modal="false" width="780px">
        <win-form :inline="true" v-testName="{'TEST_NAME':'editUser'}">
            <div class="hr">
              <win-form-item label="节点">
                <win-input placeholder="节点" v-model="userReqVo.issue.ipAddress" :disabled="true"></win-input>
              </win-form-item>
              <win-form-item label="问题描述">
                <win-input v-model="userReqVo.issue.issueDesc" :disabled="true"></win-input>
              </win-form-item>
              <win-form-item label="报警级别">
                <win-input v-model="userReqVo.issue.warnLevelDesc" :disabled="true"></win-input>
              </win-form-item>
            </div>
        </win-form>
        <div slot="footer" class="dialog-footer">
            <win-button @click="close">取 消</win-button>
            <win-button type="primary" @click="handleDeleteIssue">确 认</win-button>
        </div>
    </win-fdialog>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import { UserReqVO,MachineClass,IssueClass, UserClass } from "../vo/IssueVO";
import BaseController from "../../common/controller/BaseController";
@Component({})
export default class DeleteIssue extends BaseController {
    dialogFormVisible: boolean = true;
    issue: IssueClass = new IssueClass();
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


  @Emit("deleteissue")
  handleDeleteIssue() {
    let userParams = {
      id: this.issue.id, //ID
      ipAddress: this.issue.ipAddress, //ip
      issueDesc: this.issue.issueDesc,
      warnLevel: this.issue.warnLevel
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
