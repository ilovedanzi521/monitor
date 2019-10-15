<template>
  <div class="qpsBlock">
    <div class="rect">
      <div class="qpsTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;Qps趋势图
      </div>
      <div id="qpsChartLineBox"></div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeLeftQpsController from "../controller/HomeLeftQpsController";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class HomeLeftQps extends HomeLeftQpsController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  #qpsChartLineBox {
    width: 100%;
    height: 98%;
    font-size: 18px;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
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
    background-size: 1px 10px, 10px 1px, 2px 10px, 10px 1px;
  }
</style>
