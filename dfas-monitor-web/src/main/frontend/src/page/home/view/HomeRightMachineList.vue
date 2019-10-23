<template>
  <div class="machineListBlock">
    <div class="rect">
      <div class="machineListTitle">
        <div class="table-tr-title2">
          <div class="titleArea">
            <img src="../image/groupMin.png">&nbsp;&nbsp;资源
          </div>
          <div class="moreArea">
            <a href="/#/machineList" target="_blank"><img src="../image/more.png"></a>
          </div>
        </div>
      </div>
      <div class="table">
        <div class="table-tr-title">
          <div class="table-th">节点</div>
          <div class="table-th">状态</div>
          <div class="table-th">负载</div>
          <div class="table-th">CPU</div>
          <div class="table-th">内存</div>
          <div class="table-th">磁盘</div>
        </div>

        <div v-for="(item, index) in machineList"  :class="generateClassName(index)" @click='onclickRow(item)'>
          <div class="table-td">{{item.ip}}</div>
          <div v-if="item.state == '0' " class="table-td-offline">离线</div>
          <div v-else-if="item.state == '1' " class="table-td-offline">异常</div>
          <div v-else-if="item.state == '2' " class="table-td-offline">告警</div>
          <div v-else-if="item.state == '3' " class="table-td-online">在线</div>
          <div v-else-if="item.state == '' " class="table-td-offline"></div>
          <div class="table-td">{{item.balance}}</div>
          <div class="table-td">{{item.cpu}}</div>
          <div class="table-td">{{item.memory}}</div>
          <div class="table-td">{{item.disk}}</div>
        </div>
        <MachineDetailDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="machineDetailDialogVisible">
        </MachineDetailDialog>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMachineListController from '../controller/HomeMachineListController';
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class HomeRightMachineList extends HomeMachineListController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .table-tr-title2 {
    display: table-row;
    vertical-align: middle;
  }

  .titleArea{
    display: table-cell;
    vertical-align: middle;
    text-align:left;
  }

  .moreArea{
    display: table-cell;
    vertical-align: middle;
    text-align:right;
    padding-right: 30px;
    color:rgba(139,162,191,1);
  }

  .tr {
    display: table-row;
    vertical-align: middle;
    height: 15%;
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
    height: 75%;
    display: table;
    width: 92%;
    margin: 2% auto;
    border-collapse: collapse;
  }


  .table-tr-title {
    display: table-row;
    vertical-align: middle;
    height: 17%;
    background: rgba(12, 36, 74, 1);
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
    color: rgba(139, 162, 191, 1);
  }

  .rect {
    width: 100%;
    height: 100%;
    background: linear-gradient(to left, #195091, #195091) left top no-repeat,
    linear-gradient(to bottom, #195091, #195091) left top no-repeat,
    linear-gradient(to left, #195091, #195091) right top no-repeat,
    linear-gradient(to bottom, #195091, #195091) right top no-repeat,
    linear-gradient(to left, #195091, #195091) left bottom no-repeat,
    linear-gradient(to bottom, #195091, #195091) left bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat;
    background-size: 1px 10px, 10px 1px, 2px 10px, 10px 1px;
  }

</style>
