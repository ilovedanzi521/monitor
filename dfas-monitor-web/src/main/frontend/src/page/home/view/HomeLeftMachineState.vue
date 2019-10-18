<template>
  <div class="machineStateBlock">
    <div class="rect">
      <div class="machineStateTitle">
        <div class="table-tr-title">
          <div class="titleArea">
            <img src="../image/groupMin.png">&nbsp;&nbsp;机器状态
          </div>
          <div class="tipArea">
            <img src="../image/tip.png">
          </div>
          <div class="moreArea">
            <a href="/#/machinePanel" target="_blank"><img src="../image/more.png"></a>
          </div>
        </div>
      </div>
      <div class="machineState-container">
        <Monitor :dataList="machineStateList" :col="5" :row="4" type="machine" minWidth="60px"></Monitor>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import HomeMachineStateController from '../controller/HomeMachineStateController';

  export default class HomeLeftMachineState extends HomeMachineStateController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .machineState-container {
    //margin: 10px;
    padding-top: 10px;
    width: 100%;
    height: 90.5%;
    /*overflow: hidden;*/
  }

  .table-tr-title {
    display: table-row;
    vertical-align: middle;
  }


  .td {
    display: table-cell;
    vertical-align: middle;
  }

  .titleArea{
    @extend .td;
    text-align:left;
  }

  .tipArea{
    @extend .td;
    text-align:center;
  }

  .moreArea{
    @extend .td;
    text-align:right;
    padding-right: 30px;
    color:rgba(139,162,191,1);
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
    background-size: 2px 16px, 16px 2px, 2px 16px, 16px 2px;
  }
</style>
