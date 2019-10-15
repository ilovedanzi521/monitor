<template>
  <win-fdialog :title="dialogTitle" :before-close="closeDialog" :visible.sync="infoDialogVisible" :close-on-click-modal="false" :close-on-press-escape="false" width="780px">
    <win-form id="#create" :model="MicroServiceInfoRepVO" :rules="rules" ref="MicroServiceInfo" v-testName="{'TEST_NAME':'microServiceInfoDialog'}" label-width="110px" :disabled="formDisabled">
      <div class="hr">
        <win-row>
          <win-col :span="spanWidth">
            <win-form-item label="微服务名称" prop="microServiceName">
              <el-input v-model.number="MicroServiceInfoRepVO.microServiceName" clearable></el-input>
            </win-form-item>
          </win-col>
        </win-row>
      </div>
    </win-form>

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
<style lang="css" scoped>
.el-button--warning {
  background-color: #ff900d;
}
.el-date-editor {
  width: 224px;
}
span font {
  text-align: left;
  margin: -30px 0 0px;
  font-size: 16px;
}
</style>
