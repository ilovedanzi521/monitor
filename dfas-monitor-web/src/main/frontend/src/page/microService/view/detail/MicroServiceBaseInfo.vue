<template>
  <div class="baseInfoContainer">
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">微服务名称</div>
        <div class="name">订单服务</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">微服务描述</div>
        <div class="name">这是一个在线订单服务</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">状态</div>
        <div class="status">在线</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">告警数</div>
        <div class="warn">300</div>
      </div>
    </div>
    <div>
      <div class="innerDivStyle">
        <div class="title">错误数</div>
        <div class="error">20</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class MicroServiceBaseInfo extends Vue {
  }
</script>

<style lang="scss" scoped>
  .baseInfoContainer {
    width: 100%;
    height: 100%;

    .outDivStyle {
      height: 20%;
      border-bottom: 1px solid rgba(73, 84, 116, 1);
    }

    .innerDivStyle {
      margin-left: 10%;
    }

    .title {
      height: 21px;
      font-size: 16px;
      font-family: Microsoft YaHei;
      font-weight: 400;
      line-height: 21px;
      color: rgba(199, 205, 214, 1);
      opacity: 1;
      padding-top: 10px;
      padding-bottom: 8px;
    }

    .name {
      height: 26px;
      font-size: 20px;
      font-family: Microsoft YaHei;
      font-weight: bold;
      line-height: 26px;
      color: rgba(255, 255, 255, 1);
      opacity: 1;
    }

    .status {
      height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      color:rgba(51,204,51,1);
      opacity:1;
    }

    .warn{
      height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      color:rgba(255,144,13,1);
      opacity:1;
    }

    .error{
      height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      color:rgba(255,77,77,1);
      opacity:1;
    }
  }
</style>
}
