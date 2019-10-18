<template>
    <div class="table-contanier">
      <dl class="searchList">
        <dt>{{setTitle}}管理</dt>
        <dd>
            <span class="btngrounp">
                    <button class="but" @click="handleAddThreshold">
                        <i class="icon el-icon-plus"></i>
                        <span>新增阈值</span>
                    </button>
                    <button class="but"  @click="handleDelThresholdBatch">
                        <i class="icon el-icon-delete"></i>
                        <span>删除阈值</span>
                    </button>
                </span>
        </dd>
      </dl>
      <div class="rl">
        <div class="serch-container">
          <win-input v-model="userReqVo.userSerachParam" placeholder="请动态进行模糊搜索" @keyup.enter.native="searchMachine"></win-input>
        </div>
        <div class="test"></div>
      </div>
       <win-table ref="multipleTable" style="width: 100%" :data="userReqVo.thresholdArray" :showSelection="true" @select-change="thresholdTableSelectionChange" @select-all="thresholdTableSelectionChange"  max-height="600">
            <win-table-column prop="indicatorBody" label="指标主体" width="150"></win-table-column>
            <win-table-column prop="indicatorName" label="指标名称" width="150"></win-table-column>
            <win-table-column prop="indicatorSymbol" label="指标符号" width="150"></win-table-column>
            <win-table-column prop="threshold" label="阈值" width="200"></win-table-column>
            <win-table-column prop="operation" align="center" fixed="right" label="操作" width="480">
                <template slot-scope="scope">
                  <span class="operation" @click="handleEditThreshold(scope.row)">
                        <i class="icon icon2 el-icon-edit"></i>修改阈值
                  </span>
                  <span class="operation" @click="handleDeleteThreshold(scope.row,1)">
                            <i class="icon icon1 el-icon-delete"></i>删除阈值
                  </span>
                </template>
            </win-table-column>
        </win-table>
        <win-pagination name="machine" v-bind:childMsg="userReqVo.userPageVO" @callFather="machinePageQuery"></win-pagination>
    </div>
</template>

<script lang="ts">

import { Component, Prop, Emit } from "vue-property-decorator";
import ThresholdController from "../controller/ThresholdController";
import Component from "vue-class-component";
import { debuglog } from "util";
import { UserReqVO, UserClass } from "../vo/ThresholdVO";
import Vue from "vue";
import Component from "vue-class-component";
import ThresholdService from "../service/ThresholdService";
import { UserReqVO } from "../vo/ThresholdVO";
import AddThreshold from "../view/AddThreshold.vue";
import EditThreshold from "../view/EditThreshold.vue";
import DeleteThreshold from "../view/DeleteThreshold.vue";
import ThresholdTable from "../view/ThresholdTable.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "../../../api/AxiosFun";

@Component({
})
export default class ThresholdTable extends ThresholdController {

  @Prop()
  userReqVo: UserReqVO;

  private multipleSelection: any = [];

  // 选中
  thresholdTableSelectionChange(val: any) {
    console.log(">>>>>>>thresholdTableSelectionChange>>>>>>>")
    this.multipleSelection = val.selection;
    console.log(this.multipleSelection);
  }


  //@Emit("editMachinePage")
  handleEditThreshold(row, column) {
    this.userReqVo.threshold = row;
    this.$emit('editThresholdPage', row);
  }

  handleSaveEditThreshold() {
    this.$emit('saveEditThreshold');
  }

  handleDeleteThreshold(row, column) {
    this.userReqVo.threshold = row;
    this.$emit('deleteThresholdPage', row);
  }


    @Emit("machinePageQuery")
    machinePageQuery(vo: PageVO) {
      return vo;
    }
  @Emit("addThresholdPage")
  /**增加系 */
  handleAddThreshold() {
    console.log("addThresholdPage");
  }

  //@Emit("delThresholdBatch")
  handleDelThresholdBatch() {
    this.$emit('delThresholdBatch', this.multipleSelection,this.userReqVo);
    console.log("del batch");

  }

  /**删除系 */

  get setTitle() {
    return "阈值"
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
