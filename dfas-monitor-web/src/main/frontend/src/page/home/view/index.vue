<template>
  <div class="homeContainer">
    <div class="homeLeft">
      <div class="platformOverviewBlock">
        <div class="platformOverviewTitle">
          <img src="../image/groupMin.png">&nbsp;&nbsp;平台概况
        </div>
        <div></div>
      </div>
      <div class="qpsBlock">
        <div class="qpsTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;Qps趋势图
        </div>
      </div>
      <div class="machineStatusBlock">
        <div class="machineStatusTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;机器状态</div>
      </div>
    </div>
    <div class="homeRight">
      <div class="monitorHeaderBlock">
        <img style="width:100%;height: 100%" src="../image/rightHeader.png">
      </div>
      <div class="microServiceStatusBlock">
        <div class="microServiceStatusTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;微服务状态</div>
      </div>
      <div class="homeRightBottomBlock">
        <div class="machineListBlock">
          <div class="machineListTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;机器列表</div>
        </div>
        <div class="exceptionBlock">
          <div class="exceptionTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;异常</div>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts">
  import HomeController from '../controller/HomeController';
  import Component from "vue-class-component";

  @Component({})
  export default class Home extends HomeController {
  };
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
</style>
