<template>
  <div class="baseInfoContainer">


    <div class="flex-contanier">
      <div class="outDivStyle">
        <div class="innerDivStyle">
          <div class="title">节点</div>
          <div class="name">{{machineInfoVO.ipAddress}}</div>
        </div>
      </div>
      <div class="outDivStyle">
        <div class="innerDivStyle">
          <div class="title">负载</div>
          <div class="name" v-if="parseFloat(machineInfoVO.balanceInfo.replace('%','')) > 80" style="color: #f72a2a">{{machineInfoVO.balanceInfo}}</div>
          <div class="name" v-else-if="parseFloat(machineInfoVO.balanceInfo.replace('%','')) > 60 && parseFloat(machineInfoVO.balanceInfo.replace('%','')) < 80 " style="color:#e2d34f">{{machineInfoVO.balanceInfo}}</div>
          <div class="name" v-else-if="parseFloat(machineInfoVO.balanceInfo.replace('%','')) < 60" style="color:#33cc33">{{machineInfoVO.balanceInfo}}</div>
        </div>
      </div>
    </div>

    <div class="flex-contanier">
      <div class="outDivStyle">
        <div class="innerDivStyle">
          <div class="title">CPU</div>
          <div class="name">{{machineInfoVO.cpuInfo}}</div>
        </div>
      </div>
      </div>

      <div class="flex-contanier">

        <div class="innerDivStyle">
          <div class="title">磁盘</div>
          <div class="name">{{machineInfoVO.diskInfo}}</div>
        </div>

        <div>
          <div class="innerDivStyle">
            <div class="title">内存</div>
            <div class="name">{{machineInfoVO.memoryInfo}}</div>
          </div>
        </div>
      </div>


    </div>
</template>

<script lang="ts">
  import NodeInfoController from "../../controller/NodeInfoController";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class NodeBaseInfo extends NodeInfoController {
  }
</script>

<style lang="scss" scoped>
  .baseInfoContainer {
    width: 100%;
    height: 100%;
    padding: 0 16px;
    box-sizing: border-box;
    .flex-contanier {
      display: flex;
      width: 100%;
      padding: 10px 20px;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      box-sizing: border-box;
      border-bottom: 1px solid #f5f5f5;
    }

    .flex-contanier:last-of-type {
      border-bottom: 1px solid transparent;
    }

    .outDivStyle {
    }

    .innerDivStyle {

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
      height: 26px;
      font-size: 20px;
      font-family: Microsoft YaHei;
      font-weight: bold;
      line-height: 26px;
      color: rgba(51, 204, 51, 1);
      opacity: 1;
    }

    .warn {
      height: 26px;
      font-size: 20px;
      font-family: Microsoft YaHei;
      font-weight: bold;
      line-height: 26px;
      color: rgba(255, 144, 13, 1);
      opacity: 1;
    }

    .error {
      height: 26px;
      font-size: 20px;
      font-family: Microsoft YaHei;
      font-weight: bold;
      line-height: 26px;
      color: rgba(255, 77, 77, 1);
      opacity: 1;
    }
  }
</style>
}
