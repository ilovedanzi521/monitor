<template>
  <div class="microServiceStatusBlock">
    <div class="microServiceStatusTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;微服务状态</div>
    <div class="microServiceStatus-container"><img style="width:100%;height: 100%" src="../image/microServiceStatus.png"></div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component } from "vue-property-decorator";

  @Component({})
  export default class HomeRightMicroServiceStatus extends Vue {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
  .microServiceStatus-container {
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
    height:90.5%;
  }
</style>
