<template>
  <div class="homeLeft">
    <HomeLeftPlatformOverview></HomeLeftPlatformOverview>
    <HomeLeftQps></HomeLeftQps>
    <HomeLeftMachineStatus></HomeLeftMachineStatus>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component } from "vue-property-decorator";
  import HomeLeftMachineStatus from "./HomeLeftMachineStatus.vue";
  import HomeLeftPlatformOverview from "./HomeLeftPlatformOverview.vue";
  import HomeLeftQps from "./HomeLeftQps.vue";

  @Component({
    components: {HomeLeftMachineStatus, HomeLeftPlatformOverview,HomeLeftQps}
  })
  export default class HomeLeft extends Vue {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
</style>
