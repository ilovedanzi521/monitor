<template>
  <win-fdialog :title="dialogTitle" :before-close="closeDialog" :visible.sync="detailDialogVisible"
               :close-on-click-modal="false" :close-on-press-escape="false" height="100%" width="100%">
    <div class="detailContainer">
      <div class="topContainer">
        <div class="leftContainer">
          <div class="title1">JVM内存数据变化折线图</div>
          <JvmMemoryChart></JvmMemoryChart>
        </div>
        <div class="rightContainer">
          <MicroServiceBaseInfo></MicroServiceBaseInfo>
        </div>
      </div>
      <div class="bottomContainer">
        <div class="title2">微服务机器列表</div>
        <BottomMachineList></BottomMachineList>
      </div>
    </div>
  </win-fdialog>
</template>
<script lang="ts">
  import MicroServiceDetailDialogController from '../controller/MicroServiceDetailDialogController';

  export default class MicroServiceDetailDialog extends MicroServiceDetailDialogController {
  };
</script>

<style lang="scss" scoped>
  .detailContainer {
    width: 100%;
    height: 100%;

    .topContainer {
      width: 98%;
      height: 48%;
      margin: 20px;
      opacity: 1;

      .leftContainer {
        float: left;
        width: 80%;
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
      height: 44%;
      margin: 20px;
      background: rgba(12, 22, 63, 1);
      opacity: 1;

      .title2 {
        width: 100%;
        height: 19px;
        font-size: 14px;
        font-family: Microsoft YaHei;
        font-weight: 400;
        line-height: 19px;
        color: rgba(255, 255, 255, 1);
        opacity: 1;
      }
    }
  }
</style>
