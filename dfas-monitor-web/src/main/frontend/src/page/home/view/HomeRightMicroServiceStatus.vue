<template>
  <div class="microServiceStatusBlock">
    <div class="rect">
      <div class="microServiceStatusTitle">
        <div style="float: left">
          <img src="../image/groupMin.png">&nbsp;&nbsp;微服务状态
        </div>
        <div class="tipArea">
          <img src="../image/tip.png">
        </div>
      </div>
      <div class="microServiceStatus-container">
        <Monitor type="service" :dataList="microServiceStatusList" :animations="['1','2']"></Monitor>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMicroServiceStatusController from "../controller/HomeMicroServiceStatusController";

  export default class HomeRightMicroServiceStatus extends HomeMicroServiceStatusController {

  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .microServiceStatus-container {
    margin: 10px;
    width: 98.5%;
    height: 90.5%;
  }

  .tipArea {
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
    background-size: 3px 20px, 20px 3px, 3px 20px, 20px 3px;
  }

</style>
