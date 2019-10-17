<template>
    <div class="table">
      <div class="table-tr-title">
        <div class="table-th">节点</div>
        <div class="table-th">状态</div>
        <div class="table-th">负载</div>
        <div class="table-th">JVM</div>
        <div class="table-th">告警数</div>
        <div class="table-th">错误数</div>
      </div>
      <div v-for="(item, index) in microServiceMachineRepList"  :class="generateClassName(index)" >
        <div class="table-td">{{item.ipAddr}}</div>
        <div v-if="item.state == 'UP' " class="table-td-online">在线</div>
        <div v-else-if="item.state == 'DOWN' " class="table-td-offline">离线</div>
        <div class="table-td">{{item.balance}}</div>
        <div class="table-td">{{item.jvm}}</div>
        <div class="table-td">{{item.warn}}</div>
        <div class="table-td">{{item.error}}</div>
      </div>
    </div>
</template>

<script lang="ts">
  import BottomMachineListController from "../../controller/BottomMachineListController";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class BottomMachineList extends BottomMachineListController {
  }
</script>

<style lang="scss" scoped>

  .tr {
    display: table-row;
    vertical-align: middle;
    height: 28px;
  }

  .td {
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    font-size: 14px;
    font-family: Microsoft YaHei;
    font-weight: 400;
    opacity: 1;
  }

  .table {
    display: table;
    width: 100%;
    margin-top:10px;
    border-collapse: collapse;
  }

  .table-tr-title {
    display: table-row;
    vertical-align: middle;
    height: 32px;
    background: rgba(68,78,105,1);;
  }

  .table-tr-data-one {
    @extend .tr;
    background: rgba(29, 50, 81, 1);
  }

  .table-tr-data-two {
    @extend .tr;
    background: rgba(12, 36, 74, 1);
  }

  .table-th {
    @extend .td;
    color: rgba(255, 255, 255, 1);
  }

  .table-td {
    @extend .td;
    color: rgba(139, 162, 191, 1);
  }

  .table-td-online {
    @extend .td;
    color: rgba(51, 204, 51, 1);
  }

  .table-td-offline {
    @extend .td;
    color: rgba(247, 42, 42, 1);
  }

</style>
