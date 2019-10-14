<template>
  <div class="microServiceStatus-container">
    <Monitor></Monitor>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";
  import Monitor from "../../../components2/vue/Monitor.vue";

  @Component({components: {Monitor}})
  export default class MicroServicePanel extends Vue {
  }
</script>

<style lang="scss" scoped>

  .microServiceStatus-container {
    margin: 10px;
    width: 100%;
    height: 95%;
  }


</style>
