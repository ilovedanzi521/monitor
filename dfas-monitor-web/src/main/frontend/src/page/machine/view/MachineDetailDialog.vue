<template>
  <div id="detailContainer" class="detailContainer">
  <win-fdialog :title="dialogTitle" :before-close="closeDialog" :visible.sync="detailDialogVisible"
               :close-on-click-modal="false" :close-on-press-escape="false" height="100%" width="100%">
      <div class="topContainer">
        <div class="leftContainer">
          <div class="title1">资源使用率</div>
         <!--<CPUChart :fromFatherMsg="fromFatherMsg"></CPUChart>-->
          <MemoryChart :MemoryChartfromFatherMsg="fromFatherMsg"></MemoryChart>
        </div>
        <div class="rightContainer">
          <NodeBaseInfo :fromFatherMsg="fromFatherMsg"></NodeBaseInfo>
        </div>
      </div>
      <div class="bottomContainer">
        <div class="title2">CPU使用变化率</div>
        <!--<CPULineChart :fromFatherMsg="fromFatherMsg"></CPULineChart>-->
        <DiskBar :fromFatherMsg="fromFatherMsg"></DiskBar>
      </div>
  </win-fdialog>
  </div>
</template>
<script lang="ts">
  import MachineDetailDialogController from '../controller/MachineDetailDialogController';

  export default class MachineDetailDialog extends MachineDetailDialogController {




  };
</script>

<style lang="scss" scoped>
  .detailContainer {
    //background: red !important;
    align-items: center !important;
   /deep/ .el-dialog__wrapper {
     height: 80% !important;
     width: 100% !important;
     top: 10% !important;
     right: 0 !important;
     bottom: 0 !important;
     left: 0 !important;
     overflow: auto;
     margin: 0 !important;
     display: flex;
     //background: yellow;
     justify-content: center !important;
     align-items: center !important;
    .el-dialog {
       height: 100% !important;
       margin: 0 !important;
       background: #090f26ff !important;
       border-radius: 12px !important;
       text-align: left;
       //background: green !important;
     }
      .el-dialog__body {
        padding: 0 36px !important;
        height: 100% !important;
        max-height: 100% !important;
        box-sizing: border-box !important;
        overflow-y: auto !important;
        //background: blueviolet !important;
      }
  }

  .topContainer {
    width: 98%;
    height: 48%;
    margin: 20px;
    opacity: 1;

    .leftContainer {
      float: left;
      width: 50%;
      height: 100%;
      background: rgba(12, 22, 63, 1);
      opacity: 1;

      .title1 {
        height: 21px;
        font-size: 16px;
        font-family: Microsoft YaHei;
        font-weight: 400;
        line-height: 21px;
        color: rgba(255, 255, 255, 1);
        opacity: 1;
      }
    }

    .rightContainer {
      float: left;
      margin-left: 30px;
      width: 18%;
      height: 100%;
      background: rgba(12, 22, 63, 1);
      opacity: 1;
    }
  }

  .bottomContainer {
    width: 98%;
    height: 38%;
    margin: 20px;
    background: rgba(12, 22, 63, 1);
    opacity: 1;

    .title2 {
      width: 100%;
      height: 19px;
      font-size: 14px;
      font-family: Microsoft YaHei;
      vertical-align: middle;
      font-weight: 400;
      line-height: 19px;
      color: rgba(255, 255, 255, 1);
      opacity: 1;
    }
  }
  }
</style>
