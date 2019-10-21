<template>
  <div id="pie-simple" style="width:50%; height:100%" >
  </div>
</template>

<script lang="ts">
  import MemoryController from "../../controller/MemoryController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class MemoryChart extends MemoryController {
  }
</script>

<style lang="scss" scoped>
  #pie-simple {
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
