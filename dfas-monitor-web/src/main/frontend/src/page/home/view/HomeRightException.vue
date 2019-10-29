<template>
  <div class="exceptionBlock">
    <div class="rect">
      <div class="exceptionTitle">
        <div class="table-tr-title">
          <div class="titleArea">
            <img src="../image/groupMin.png">&nbsp;&nbsp;异常
          </div>
          <div class="moreArea">
            <a href="/#/issueList" target="_blank"><img src="../image/more.png"></a>
          </div>
        </div>
      </div>
      <div class="exceptionContainer">
        <div class="machineException">
          <ul class="contentTitle">
            <li>机器CPU告警数</li>
            <li>机器内存告警数</li>
            <li>机器磁盘告警数</li>
          </ul>
          <ul class="machineNumber">
            <li>{{homeExceptionVO.machineCpu}}</li>
            <li>{{homeExceptionVO.machineMemory}}</li>
            <li>{{homeExceptionVO.machineDisk}}</li>
          </ul>
        </div>
        <div class="microServiceException">
          <ul class="contentTitle">
            <li>微服务内存告警数</li>
            <li>微服务日志告警数</li>
            <li>微服务日志错误数</li>
          </ul>
          <ul class="microServiceNumber">
            <li>{{homeExceptionVO.microServiceMemory}}</li>
            <li>{{homeExceptionVO.microServiceWarnLog}}</li>
            <li>{{homeExceptionVO.microServiceErrorLog}}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";
  import HomeExceptionController from '../controller/HomeExceptionController';

  @Component({})
  export default class HomeRightException extends HomeExceptionController {
  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

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

  .exceptionContainer {
    height: 100%;
  }

  .machineException {
    margin: 5% auto;
  }

  .microServiceException {
  }

  .contentTitle {
    list-style: none;
    height: 20px;
    font-size: 12px;
    text-align: center;
  }

  .contentTitle li {
    display: block;
    float: left;
    width: 33%;
    height: 19px;
    font-size: 14px;
    font-family: Microsoft YaHei;
    font-weight: 400;
    color: rgba(139, 162, 191, 1);
    opacity: 1;
    text-align: center;
  }

  .contentNumber {
    display: block;
    float: left;
    width: 33%;
    font-size: 28px;
    font-family: Microsoft YaHei;
    font-weight: bold;
    color: rgba(227, 227, 28, 1);
    opacity: 1;
    text-align: center;
  }

  .machineNumber li {
    @extend .contentNumber;
    height: 37px;
    padding-bottom: 2%;
  }

  .microServiceNumber li {
    @extend .contentNumber;
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
