<template>
  <div class="homeLeft">
    <HomeLeftPlatformOverview></HomeLeftPlatformOverview>
    <HomeLeftQps></HomeLeftQps>
    <HomeLeftMachineState></HomeLeftMachineState>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component } from "vue-property-decorator";
  import HomeLeftMachineState from "./HomeLeftMachineState.vue";
  import HomeLeftPlatformOverview from "./HomeLeftPlatformOverview.vue";
  import HomeLeftQps from "./HomeLeftQps.vue";

  @Component({
    components: {HomeLeftMachineState, HomeLeftPlatformOverview,HomeLeftQps}
  })
  export default class HomeLeft extends Vue {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
</style>
