<template>
  <win-fdialog :title="dialogTitle" :before-close="closeDialog" :visible.sync="infoDialogVisible" :close-on-click-modal="false" :close-on-press-escape="false" width="780px">
    <el-form id="#create" :model="microServiceInfoRepVO" :rules="rules" ref="microServiceInfo" v-testName="{'TEST_NAME':'microServiceInfoDialog'}" label-width="110px" :disabled="formDisabled">
      <div class="hr">
        <el-row>
          <el-col :span="spanWidth">
            <el-form-item label="微服务名称" prop="microServiceName">
              <win-input v-model="microServiceInfoRepVO.microServiceName" clearable></win-input>
            </el-form-item>
          </el-col>
           <el-col :span="spanWidth">
            <el-form-item label="微服务描述" prop="microServiceAlias">
              <el-input v-model="microServiceInfoRepVO.microServiceAlias" clearable></el-input>
            </el-form-item>
          </el-col>
            <el-form-item label="微服务节点" prop="ipAddress">
              <el-input  type="textarea" v-model="microServiceInfoRepVO.ipAddress" :rows="2" clearable></el-input><span :style="{'paddingLeft':'10px','position':'absolute','marginTop':'10px','width':'307px'}">（提示：多个IP之间请用","分开）</span>
            </el-form-item> 
        </el-row>
      </div>
    </el-form>

    <div slot="footer" class="dialog-footer" v-if="operationShow">
      <win-button @click="dialogCancel">取消</win-button>
      <win-button type="primary" :loading="createLoading" @click="submitDialog">{{ dialogSumbitText }}</win-button>
    </div>
  </win-fdialog>
</template>
<script lang="ts">
import MicroServiceInfoDialogController from "../controller/MicroServiceInfoDialogController";
export default class MicroServiceInfoDialog extends MicroServiceInfoDialogController {}
</script>
<style lang="scss" scoped>
  /deep/ .el-textarea__inner{
     width: 202px !important;
  }
</style>
