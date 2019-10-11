<template>
  <div class="homeRight">
    <div class="monitorHeaderBlock">
      <img style="width:100%;height: 100%" src="../image/rightHeader.png">
    </div>
    <HomeRightMicroServiceState></HomeRightMicroServiceState>
    <div class="homeRightBottomBlock">
      <HomeRightMachineList></HomeRightMachineList>
      <HomeRightException></HomeRightException>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component } from "vue-property-decorator";
  import HomeRightException from "./HomeRightException.vue";
  import HomeRightMachineList from "./HomeRightMachineList.vue";
  import HomeRightMicroServiceState from "./HomeRightMicroServiceState.vue";

  @Component({
    components: {HomeRightException, HomeRightMachineList,HomeRightMicroServiceState}
  })
  export default class HomeRight extends Vue {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
</style>
