<template>
  <div class="platformOverviewBlock">
    <div class="rect">
      <div class="platformOverviewTitle">
        <img src="../image/groupMin.png">&nbsp;&nbsp;平台概况
      </div>
      <div class="platformOverview-container">
        <div id="leftDiv">
          <div id="totalNodeDiv">
            <div id="totalNodeTextDiv">总节点数&nbsp;&nbsp;</div>
            <div id="totalNodeNumberDiv">{{totalNode}}</div>
          </div>
          <div id="currentRequestDiv">
            <div id="currentRequestTextDiv">今日请求数&nbsp;&nbsp;</div>
            <div id="currentRequestNumberDiv">{{totalHttpRequest}}</div>
          </div>
        </div>
        <div id="rightDiv">
          <div id="totalServiceDiv">
            <div id="totalServiceTextDiv">总服务数&nbsp;&nbsp;</div>
            <div id="totalServiceNumberDiv">{{totalMicroService}}</div>
          </div>
          <div id="qpsDiv">
            <div id="qpsTextDiv">Qps&nbsp;&nbsp;</div>
            <div id="qpsNumberDiv">{{qps}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import PlatformOverviewController from '../controller/PlatformOverviewController';
  import {Component,Prop,Emit,Watch} from "vue-property-decorator";

  @Component({})
  export default class HomePlatformOverview extends PlatformOverviewController {}
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  .divStyle {
    width: 100%;
    padding-top: 5%;
  }

  .divTextStyle {
    font-size: 14px;
    font-family: Microsoft YaHei;
    font-weight: 400;
    line-height: 35px;
    color: rgba(139, 162, 191, 1);
    opacity: 1;
    width: 50%;
    text-align: right;
    float: left;
    vertical-align: middle;
  }

  .divNumberStyle {
    font-size: 26px;
    font-family: Microsoft YaHei;
    font-weight: bold;
    line-height: 35px;
    color: rgba(145, 255, 6, 1);
    opacity: 1;
    float: left;
    text-align: left;
    width: 50%;
    vertical-align: middle;
  }

  .platformOverview-container {
    height: 100%;


    #leftDiv {
      float: left;
      width: 50%;


      #totalNodeDiv {
        @extend .divStyle;
      }

      #currentRequestDiv {
        @extend .divStyle;
      }

      #totalNodeTextDiv {
        @extend .divTextStyle;
      }

      #currentRequestTextDiv {
        @extend .divTextStyle;
      }

      #totalNodeNumberDiv {
        @extend .divNumberStyle;
      }

      #currentRequestNumberDiv {
        @extend .divNumberStyle;
      }

    }

    #rightDiv {
      float: left;
      width: 50%;

      #totalServiceDiv {
        @extend .divStyle;
      }

      #qpsDiv {
        @extend .divStyle;
      }

      #totalServiceTextDiv {
        @extend .divTextStyle;
      }

      #qpsTextDiv {
        @extend .divTextStyle;
      }

      #totalServiceNumberDiv {
        @extend .divNumberStyle;
      }

      #qpsNumberDiv {
        @extend .divNumberStyle;
      }
    }
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
