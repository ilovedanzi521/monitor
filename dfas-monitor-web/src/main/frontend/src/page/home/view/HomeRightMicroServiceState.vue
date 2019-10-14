<template>
  <div class="microServiceStateBlock">
    <div class="rect">
      <div class="microServiceStateTitle">
        <div style="float: left">
          <img src="../image/groupMin.png">&nbsp;&nbsp;微服务状态
        </div>
        <div class="tipArea">
          <img src="../image/tip.png">
        </div>
      </div>
      <div class="microServiceState-container">
        <Monitor  :dataList="microServiceStateList" :animations="['1','2']" :col="18" :row="6" type="service"></Monitor>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMicroServiceStateController from "../controller/HomeMicroServiceStateController";

  export default class HomeRightMicroServiceState extends HomeMicroServiceStateController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .microServiceState-container {
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
