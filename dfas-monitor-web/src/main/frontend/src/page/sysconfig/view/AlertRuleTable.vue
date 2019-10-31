<template>
  <div class="table-contanier">
    <!--<dl class="searchList">
      <dt>预警规则配置</dt>
      <dd>
            <span class="btngrounp">
                    <button class="but" @click="handAddAlertRule">
                        <i class="icon el-icon-plus"></i>
                        <span>新增预警规则</span>
                    </button>
                    <button class="but"  @click="delBatchAlertRule">
                        <i class="icon el-icon-delete"></i>
                        <span>删除预警规则</span>
                    </button>
                </span>
      </dd>
    </dl>-->
    <div class="formInline" style="width:100%;height:50px;margin-top:5px;">
      <win-form v-model="reqVO" :inline="true" v-testName="{'TEST_NAME':'microServiceInfo'}">
        <win-row>
          <win-col :span="5">
            <win-button-group>
              <win-button type="info" icon="win win-add" permit-ref="ADD" @click="handAddAlertRule" round>新增预警规则</win-button>
              <win-button type="info" icon="win win-del" @click="delBatchAlertRule" round>删除预警规则</win-button>
            </win-button-group>
          </win-col>

          <win-col :span="5">
            <win-form-item label="告警规则名称">
              <!--<el-autocomplete v-model="userReqVo.scrape.jobName" :fetch-suggestions="microServiceNameSelect" clearable placeholder="请输入内容">
              </el-autocomplete>-->
              <win-input placeholder="请输入内容" v-model="userReqVo.alertRule.name"></win-input>
            </win-form-item>
          </win-col>

          <win-col :span="3">
            <div style="margin-top: 5px;">
              <win-button type="primary" icon="el-icon-search" @click="alertRuleQuery">查询</win-button>
              <win-button icon="el-icon-refresh" @click="reset">重置</win-button>
            </div>
          </win-col>
        </win-row>
      </win-form>
    </div>

    <win-table ref="multipleTable" style="width: 100%" :data="userReqVo.alertRuleArray" :showSelection="true" @select-change="tableAlertRuleSelectionChange" @select-all="tableAlertRuleSelectionChange"  max-height="600">
      <win-table-column prop="groupsName" label="规则分组名称" width="150"></win-table-column>
      <!--<win-table-column :formatter="formatRoleName" label="状态" width="200"></win-table-column>-->
      <win-table-column prop="name" label="告警规则的名称" width="150"></win-table-column>
      <win-table-column prop="expr" label="告警PromQL表达式" width="150"></win-table-column>
      <win-table-column prop="fortime" label="评估等待时间" width="150"></win-table-column>
      <win-table-column prop="labelsSeverity" label="告警级别" :formatter="formatLabelsSeverity" width="150"></win-table-column>
      <!--<win-table-column prop="annotationsSummary" label="摘要" width="150"></win-table-column>-->
      <win-table-column prop="annotationsDescription" label="描述" width="150"></win-table-column>
      <win-table-column prop="operation" align="center" fixed="right" label="操作" width="200">
        <template slot-scope="scope">
                  <span class="operation" @click="handleEditAlertRule(scope.row)">
                        <i class="icon icon2 el-icon-edit"></i>修改预警配置
                  </span>
          <span class="operation" @click="handleDeleteAlertRule(scope.row,1)">
                            <i class="icon icon1 el-icon-delete"></i>删除预警配置
                  </span>
        </template>
      </win-table-column>
    </win-table>
    <win-pagination name="scrape" v-bind:childMsg="userReqVo.userPageVO" @callFather="alertRulePageQuery"></win-pagination>
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

  @Component({ components: {Machine} })
  export default class AlertRuleTable extends SysconfigController {

    //@Emit("addScrapePage")
    /**新增刮取配置 */
    handAddAlertRule() {
      console.log("addAlertRulePage");
      this.$emit("addAlertRulePage",this.userReqVo);
    }

    handleEditAlertRule(row, column) {
      console.log(">>>>>>>>>handleEditAlertRule>>>>>>>>>>>>>>");
      this.userReqVo.alertRule = row
      //this.userReqVo.stateController.switchFormType = "EditScrape";
      this.$emit('editAlertRulePage', row);
    }

    formatLabelsSeverity({ cellValue, row, rowIndex, column, columnIndex }) {
      let labelsSeverity = row.labelsSeverity.split("_")[1];
      if("0" == labelsSeverity){
        return "低" ;
      }else  if("1" == labelsSeverity){
        return "中" ;
      }else if("2" == labelsSeverity){
        return "高" ;
      }
    }

    handleDeleteAlertRule(row, column) {
      this.userReqVo.alertRule = row;
      this.$emit('deleteAlertRulePage', row);
      //this.userReqVo.stateController.switchFormType = "DeleteScrape";
    }

    @Emit("alertRulePageQuery")
    alertRulePageQuery(vo: PageVO) {
      return vo;
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
