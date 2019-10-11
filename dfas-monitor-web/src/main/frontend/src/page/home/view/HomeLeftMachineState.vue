<template>
  <div class="machineStateBlock">
    <div class="rect">
      <div class="machineStateTitle">
        <div style="float: left">
          <img src="../image/groupMin.png">&nbsp;&nbsp;机器状态
        </div>
        <div class="tipArea">
          <img src="../image/tip.png">
        </div>
      </div>
      <div class="machineState-container">
        <Monitor :dataList="machineStateList"></Monitor>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMachineStateController from '../controller/HomeMachineStateController';

  export default class HomeLeftMachineState extends HomeMachineStateController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .machineState-container {
    //margin: 10px;
    width: 100%;
    height: 90.5%;
    /*overflow: hidden;*/

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
