<template>
  <div>
    <div>
      <ScrapeTable :userReqVo="userReqVo" @addScrapePage="addScrapePage"></ScrapeTable>
      <AlertRuleTable :userReqVo="userReqVo" @addScrapePage="addScrapePage"></AlertRuleTable>
    </div>
    <component :is="userReqVo.stateController.switchFormType" :userReqVo="userReqVo"  @addMachine="httpAddMachine" @deletemachine="httpDeleteMachine" @editmachine="httpEditMachine">
    </component>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  //   import Component from "vue-class-component";
  import { debuglog } from "util";
  import { UserReqVO } from "../vo/SysconfigVO";
  import PageVO from "../../common/vo/PageVO";
  import SysconfigService from "../service/SysconfigService";
  import { WinResponseData } from "../../common/vo/BaseVO";
  import Machine from "../../../components2/vue/monitor/Machine.vue";
  import SysconfigController from "../controller/SysconfigController";
  import AxiosFun from "../../../api/AxiosFun";
  import MachineTable from "../view/MachineTable.vue";
  import ScrapeTable from "../view/ScrapeTable.vue";
  import AlertRuleTable from "../view/AlertRuleTable.vue";


  @Component({ components: {Machine,ScrapeTable,AlertRuleTable} })
  export default class SysConfig extends SysconfigController {

    @Emit("addScrapePage")
    /**新增刮取配置 */
    handleAddScrape() {
      console.log("addScrapePage");
    }
  }
</script>
<style lang="scss" scoped>
  @import "../../../assets/style/element.scss";
  // float: right;
  // width: 88%;
  .serch-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 10px;
    .input {
      @include sinput();
    }
    .showTypeGroup {
      margin-right: 30px;
      .showType {
        @include showType();
      }
      .showType2 {
        @include showType($bg: #2f4cbd);
      }
    }
  }
  .searchList {
    width: 100%;
    padding: 0 0 10px 0;
    box-sizing: border-box;
    dt {
      float: left;
      font-size: 14px;
      color: #fff;
      width: 252px;
      background: transparent;
      text-align: center;
      height: 41px;
      line-height: 41px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    dd {
      height: 41px;
      line-height: 41px;
      margin-left: 252px;
      background: #2b3451;

      .but {
        display: inline-block;
        border: none;
        padding: 8px 16px;
        background: transparent;
        font-size: 14px;
        color: #adb5bb;

        cursor: pointer;
        &:hover {
          color: #ff900d;
        }
      }
      .cop {
        display: inline-block;
        vertical-align: middle;
        color: #fff;
        font-size: 14px;
        padding-left: 24px;
        // border-left: 1px solid #fff;
        &:first-of-type {
          border-left: 1px solid transparent;
        }
      }
    }
  }
  .type1 {
    display: inline-block;
    vertical-align: middle;
    color: #ff900d;
    margin-right: 6px;
  }
  .type2 {
    display: inline-block;
    vertical-align: middle;
    color: #4d65c3;
    margin-right: 6px;
  }
  .type3 {
    display: inline-block;
    vertical-align: middle;
    color: #33cc33;
    margin-right: 6px;
  }

  .type4 {
    display: inline-block;
    vertical-align: middle;

    color: #ff6f6f;
    margin-right: 6px;
  }
  .icon {
    padding-right: 8px;
    color: #f58a0d;
  }
  .hr {
    height: 13px;
    width: 1px;
    background: #707070;
    display: inline-block;
    position: relative;
    top: 4px;
    margin-left: 9px;
  }
  .type-title {
    color: #fff;
    display: inline-block;
    vertical-align: middle;
    font-size: 14px;
    padding: 0 16px;
    position: relative;
    top: 1px;
    color: #999;
  }
  .table-contanier {
    max-height: 700px;
    // background: red;
  }
  span {
    display: inline-block;
    vertical-align: middle;
    margin-right: 6px;
    cursor: pointer;
  }
  .operation {
    display: inline-block;
    vertical-align: middle;
    margin-right: 10px;
    &:hover {
      color: #fff;
    }
  }
  .icon {
    display: inline-block;
    margin-right: 8px;
  }
  .icon1 {
    color: #f58a0d;
  }
  .icon2 {
    color: #4d65c3;
  }
  .icon3 {
    color: #33cc33;
  }

  .icon4 {
    color: #ff4c4c;
  }
</style>
