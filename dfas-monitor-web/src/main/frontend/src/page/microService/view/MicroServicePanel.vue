<template>
  <div class="microServiceStatus-container"><img style="width:100%;height: 100%"
                                                 src="../image/microServiceStatus.png">
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";

  @Component({})
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
