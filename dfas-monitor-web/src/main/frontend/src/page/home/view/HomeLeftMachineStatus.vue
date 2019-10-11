<template>
  <div class="machineStatusBlock">
    <div class="rect">
      <div class="machineStatusTitle">
        <div style="float: left">
          <img src="../image/groupMin.png">&nbsp;&nbsp;机器状态
        </div>
        <div class="tipArea">
          <img src="../image/tip.png">
        </div>
      </div>
      <div class="machineStatus-container">
        <Monitor :dataList="machineStatusList"></Monitor>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMachineStatusController from '../controller/HomeMachineStatusController';

  export default class HomeLeftMachineStatus extends HomeMachineStatusController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .machineStatus-container {
    margin: 10px;
    width: 98%;
    height: 90.5%;
  }

  .tipArea{
    margin-right: 10%;
    float: right;
  }

  .rect {
    width: 100%;
    height: 100%;
    background: linear-gradient(to left, #195091, #195091) left top no-repeat,
    linear-gradient(to bottom, #195091, #195091) left top no-repeat,
    linear-gradient(to left, #195091, #195091) right top no-repeat,
    linear-gradient(to bottom, #195091, #195091) right top no-repeat,
    linear-gradient(to left, #195091, #195091) left bottom no-repeat,
    linear-gradient(to bottom, #195091, #195091) left bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat;
    background-size: 2px 16px, 16px 2px, 2px 16px, 16px 2px;
  }
</style>
