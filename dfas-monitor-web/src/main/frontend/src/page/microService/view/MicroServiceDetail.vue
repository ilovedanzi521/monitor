<template>
  <div class="detailContainer">
    <div class="topContainer">
      <div class="leftContainer">
        <div class="title1">JVM内存数据变化折线图</div>
        <JvmMemoryChart></JvmMemoryChart>
      </div>
      <div class="rightContainer">
        <MicroServiceBaseInfo></MicroServiceBaseInfo>
      </div>
    </div>
    <div class="bottomContainer">
      <div class="title2">微服务机器列表</div>
      <BottomMachineList></BottomMachineList>
    </div>
  </div>
</template>
<script lang="ts">
  import MicroServiceDetailController from '../controller/MicroServiceDetailController';
  import JvmMemoryChart from './detail/JvmMemory.vue';
  import MicroServiceBaseInfo from './detail/MicroServiceBaseInfo.vue';
  import Component from "vue-class-component";
  import BottomMachineList from './detail/BottomMachineList.vue';

  @Component({components: {JvmMemoryChart,MicroServiceBaseInfo,BottomMachineList}})
  export default class MicroServiceDetail extends MicroServiceDetailController {
  };
</script>

<style lang="scss" scoped>
  .detailContainer {
    width: 100%;
    height: 100%;

    .topContainer {
      width: 98%;
      height: 48%;
      margin: 20px;
      opacity: 1;

      .leftContainer {
        float: left;
        width: 80%;
        height: 100%;
        background: rgba(12, 22, 63, 1);
        opacity: 1;
        .title1 {
          height: 21px;
          font-size: 16px;
          font-family: Microsoft YaHei;
          font-weight: 400;
          line-height: 21px;
          color: rgba(255, 255, 255, 1);
          opacity: 1;
        }
      }

      .rightContainer {
        float: left;
        margin-left: 30px;
        width: 18%;
        height: 100%;
        background: rgba(12, 22, 63, 1);
        opacity: 1;
      }
    }

    .bottomContainer {
      width: 98%;
      height: 44%;
      margin: 20px;
      background: rgba(12, 22, 63, 1);
      opacity: 1;
      .title2 {
        width: 100%;
        height: 19px;
        font-size: 14px;
        font-family: Microsoft YaHei;
        font-weight: 400;
        line-height: 19px;
        color: rgba(255, 255, 255, 1);
        opacity: 1;
      }
    }
  }
</style>
