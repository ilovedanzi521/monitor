<template>
  <div id="pie-diskbar" style="width:50%; height:100%" >
  </div>
</template>

<script lang="ts">
  import DiskBarController from "../../controller/DiskBarController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class DiskBar extends DiskBarController {
  }
</script>

<style lang="scss" scoped>
  #pie-diskbar {
    width: 100%;
    height: 100%;
    font-size: 18px;
    position: absolute;
    right: 0;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
  }
</style>
