<template>
  <div id="pie-doughnut" style="width:50%; height:100%" >
  </div>
</template>

<script lang="ts">
  import CPUController from "../../controller/CPUController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class CPUChart extends CPUController {
  }
</script>

<style lang="scss" scoped>
  #pie-doughnut {
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
