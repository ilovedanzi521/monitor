<template>
  <div class="platformOverviewBlock">
    <div class="platformOverviewTitle">
      <img src="../image/groupMin.png">&nbsp;&nbsp;平台概况
    </div>
    <div class="platformOverview-container">
      <div id="leftDiv">
        <div id="totalNodeDiv">
          <div id="totalNodeTextDiv">总节点数&nbsp;&nbsp;</div>
          <div id="totalNodeNumberDiv">10</div>
        </div>
        <div id="currentRequestDiv">
          <div id="currentRequestTextDiv">今日请求数&nbsp;&nbsp;</div>
          <div id="currentRequestNumberDiv">1,203,608</div>
        </div>
      </div>
      <div id="rightDiv">
        <div id="totalServiceDiv">
          <div id="totalServiceTextDiv">总服务数&nbsp;&nbsp;</div>
          <div id="totalServiceNumberDiv">1,403,201</div>
        </div>
        <div id="qpsDiv">
          <div id="qpsTextDiv">Qps&nbsp;&nbsp;</div>
          <div id="qpsNumberDiv">68</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";

  @Component({})
  export default class HomePlatformOverview extends Vue {
  }
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

      #currentRequestTextDiv{
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
</style>
