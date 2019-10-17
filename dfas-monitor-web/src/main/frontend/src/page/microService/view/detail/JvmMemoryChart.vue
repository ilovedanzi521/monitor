<template>
  <div id="chartLineBox" style="width:100%; height:100%" >
  </div>
</template>

<script lang="ts">
  import JvmMemoryChartController from "../../controller/JvmMemoryChartController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class JvmMemoryChart extends JvmMemoryChartController {
  }
</script>

<style lang="scss" scoped>
  #chartLineBox {
    width: 100%;
    height: 100%;
    font-size: 18px;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
  }
</style>
