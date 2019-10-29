<template>
  <div class="baseInfoContainer">
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">微服务名称</div>
        <div class="name">{{microServiceInfoRepVO.microServiceName}}</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">微服务描述</div>
        <div class="name">{{microServiceInfoRepVO.microServiceAlias}}</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">状态</div>
        <div v-if="microServiceInfoRepVO.state == '0' " class="status_0">离线</div>
        <div v-else-if="microServiceInfoRepVO.state == '1' " class="status_1">异常</div>
        <div v-else-if="microServiceInfoRepVO.state == '2' " class="status_2">告警</div>
        <div v-else-if="microServiceInfoRepVO.state == '3' " class="status_3">在线</div>
        <div v-else-if="microServiceInfoRepVO.state == '' " class="status">-</div>
        <div v-else-if="microServiceInfoRepVO.state == '-' " class="status">-</div>
      </div>
    </div>
    <div class="outDivStyle">
      <div class="innerDivStyle">
        <div class="title">告警数</div>
        <div class="warn">{{microServiceInfoRepVO.warn}}</div>
      </div>
    </div>
    <div>
      <div class="innerDivStyle">
        <div class="title">错误数</div>
        <div class="error">{{microServiceInfoRepVO.error}}</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import MicroServiceBaseInfoController from "../../controller/MicroServiceBaseInfoController";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class MicroServiceBaseInfo extends MicroServiceBaseInfoController {
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
      color:rgba(255,255,255,1);
      opacity:1;
    }

 .status_0 {
      height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      opacity:1;
      color:rgba(95,92,94,1);
  }
  .status_1 {
          height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      opacity:1;
      color:rgba(245,12,28,1);
  }
  .status_2 {
          height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      opacity:1;
      color:rgba(245,179,0,1);
  }
   .status_3 {
           height:26px;
      font-size:20px;
      font-family:Microsoft YaHei;
      font-weight:bold;
      line-height:26px;
      opacity:1;
      color:rgba(51,204,51,1);
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
