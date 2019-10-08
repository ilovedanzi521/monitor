<template>
  <div class="machineListBlock">
    <div class="machineListTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;机器列表</div>
    <div class="table">
      <div class="table-tr-title">
        <div class="table-th">节点</div>
        <div class="table-th">状态</div>
        <div class="table-th">负载</div>
        <div class="table-th">CPU</div>
        <div class="table-th">内存</div>
        <div class="table-th">磁盘</div>
      </div>
      <div class="table-tr-data-one">
        <div class="table-td">192.168.0.55</div>
        <div class="table-td-online">在线</div>
        <div class="table-td">8.0%</div>
        <div class="table-td">8.0%</div>
        <div class="table-td">8.0%</div>
        <div class="table-td">8.0%</div>
      </div>
      <div class="table-tr-data-two">
        <div class="table-td">192.168.0.56</div>
        <div class="table-td-offline">离线</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
      </div>
      <div class="table-tr-data-one">
        <div class="table-td">192.168.0.57</div>
        <div class="table-td-online">在线</div>
        <div class="table-td">8.5%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
      </div>
      <div class="table-tr-data-two">
        <div class="table-td">192.168.0.58</div>
        <div class="table-td-online">在线</div>
        <div class="table-td">8.5%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
      </div>
      <div class="table-tr-data-one">
        <div class="table-td">192.168.0.59</div>
        <div class="table-td-online">在线</div>
        <div class="table-td">8.5%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
        <div class="table-td">8.3%</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class HomeRightMachineList extends Vue {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

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
    margin:2% auto;
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
    color: rgba(247, 42, 42, 1);
  }

</style>
