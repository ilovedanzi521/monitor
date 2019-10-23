<template>
  <div id="cpu-linechart" style="width:50%; height:100%" >
  </div>
</template>

<script lang="ts">
  import CPULineController from "../../controller/CPULineController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class CPULineChart extends CPULineController {
  }
</script>

<style lang="scss" scoped>
  #cpu-linechart {
    width: 100%;
    height: 100%;
    font-size: 18px;
    position: absolute;
    left: 0;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
  }
</style>
