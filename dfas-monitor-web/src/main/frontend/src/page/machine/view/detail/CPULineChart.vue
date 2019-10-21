<template>
  <div id="cpu-linechart" style="width:50%; height:100%" >
  </div>
</template>

<script lang="ts">
  import CPULineController from "../../controller/CPULineController";
  import {Component} from "vue-property-decorator";
  @Component({})
  export default class CPULineChart extends CPULineController {
  }
</script>

<style lang="scss" scoped>

</style>
