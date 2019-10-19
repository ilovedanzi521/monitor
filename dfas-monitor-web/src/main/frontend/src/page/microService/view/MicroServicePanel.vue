<template>
  <div class="microServiceStatus-container">
    <Monitor
      :dataList="microServiceStateList"
      :animations="['1','2']"
      :col="10"
      :row="4"
      minWidth="126px"
      type="service"
      @clickItem="operation"
    ></Monitor>
    <MicroServiceDetailDialog
      :fromFatherMsg="cardNumber"
      @bindSend="toFatherMsg"
      v-if="detailDialogVisible"
    ></MicroServiceDetailDialog>
  </div>
</template>

<script lang="ts">
import MicsoServicePanelController from "../controller/MicsoServicePanelController";
import { Component } from "vue-property-decorator";


@Component({  })
export default class MicroServicePanel extends MicsoServicePanelController {}
</script>

<style lang="scss" scoped>
.microServiceStatus-container {
  margin: 10px;
  width: 100%;
  height: 95%;
}
</style>
