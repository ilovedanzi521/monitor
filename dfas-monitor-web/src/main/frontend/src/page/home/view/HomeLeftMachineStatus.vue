<template>
    <div class="machineStatusBlock">
      <div class="machineStatusTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;机器状态</div>
      <div class="machineStatus-container"><img style="width:100%;height: 100%" src="../image/machineStatus.png"></div>
    </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component } from "vue-property-decorator";

  @Component({})
  export default class HomeLeftMachineStatus extends Vue {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
  .machineStatus-container {
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
    height:90.5%;
  }
</style>
