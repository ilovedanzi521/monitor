<template>
    <div class="table-contanier">
      <dl class="searchList">
        <dt>{{setTitle}}管理</dt>
        <dd>
            <span class="btngrounp">
                    <button class="but" @click="handleAddMachine">
                        <i class="icon el-icon-plus"></i>
                        <span>新增机器</span>
                    </button>
                    <button class="but"  @click="delBatch">
                        <i class="icon el-icon-delete"></i>
                        <span>删除机器</span>
                    </button>
                   <button class="but" @click="onKeySync" >
                        <i class="icon el-icon-time"></i>
                        <span>一键同步</span>
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
       <win-table ref="multipleTable" style="width: 100%" :data="userReqVo.userArray" :showSelection="true" @select-change="tableSelectionChange" @select-all="tableSelectionChange"  max-height="600">

            <win-table-column prop="ipAddress" label="IP" width="150"></win-table-column>
            <win-table-column prop="name" label="主机名" width="150"></win-table-column>
            <!--<win-table-column :formatter="formatRoleName" label="状态" width="200"></win-table-column>-->
            <win-table-column prop="status" label="状态" width="90">
              <template slot-scope="scope">
                <span v-if="scope.row.status === 0" style="color: rgba(139, 162, 191, 1)">离线</span>
                <span v-if="scope.row.status === 1" style="color: rgba(247, 42, 42, 1)">异常</span>
                <span v-if="scope.row.status === 2" style="color: rgba(139, 162, 191, 1)">告警</span>
                <span v-if="scope.row.status === 3" style="color: rgba(51, 204, 51, 1)">在线</span>
              </template>
            </win-table-column>
            <win-table-column prop="cpu" label="CPU使用率(%)" width="150"></win-table-column>
            <win-table-column prop="memory" label="内存使用率(%)" width="200"></win-table-column>
            <win-table-column prop="disk" label="磁盘使用率(%)" width="200"></win-table-column>
            <win-table-column prop="operation" align="center" fixed="right" label="操作" width="480">
                <template slot-scope="scope">
                  <span class="operation" @click="handleEditMachine(scope.row)">
                        <i class="icon icon2 el-icon-edit"></i>修改机器
                  </span>
                  <span class="operation" @click="handleDeleteMachine(scope.row,1)">
                            <i class="icon icon1 el-icon-delete"></i>删除机器
                  </span>
                </template>
            </win-table-column>
        </win-table>
        <win-pagination name="machine" v-bind:childMsg="userReqVo.userPageVO" @callFather="machinePageQuery"></win-pagination>
    </div>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import MachineController from "../controller/MachineController";
import Component from "vue-class-component";
import { debuglog } from "util";
import { UserReqVO, UserClass } from "../vo/MachineVO";
import PageVO from "../../common/vo/PageVO";
@Component({})
export default class MachineTable extends MachineController {
    @Prop()
    userReqVo: UserReqVO;

  //@Emit("editMachinePage")
  handleEditMachine(row, column) {
    this.userReqVo.machine = row;
    this.$emit('editMachinePage', row);
    //this.userReqVo.stateController.switchFormType = "EditMachine";
  }

  handleDeleteMachine(row, column) {
    this.userReqVo.machine = row;
    this.userReqVo.stateController.switchFormType = "DeleteMachine";
  }

    @Emit("machinePageQuery")
    machinePageQuery(vo: PageVO) {
      return vo;
    }
  @Emit("addMachinePage")
  /**增加系 */
  handleAddMachine() {
    console.log("addMachinePage");
  }

  @Emit("delBatch")
  handledelBatch() {
    console.log("del batch");

  }

  /**删除系 */

  get setTitle() {
    return "机器"
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
