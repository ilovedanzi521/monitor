<template>
    <div class="table-contanier">
      <dl class="searchList">
        <dt>问题管理</dt>
        <dd>
            <span class="btngrounp">
                    <button class="but"  @click="delIssueBatch">
                        <i class="icon el-icon-delete"></i>
                        <span>删除问题</span>
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
            <win-table-column prop="ipAddress" label="节点" width="150"></win-table-column>
            <win-table-column prop="issueType" label="问题类型" width="150"></win-table-column>
            <win-table-column prop="issueDesc" label="问题描述" width="150"></win-table-column>
            <!--<win-table-column :formatter="formatRoleName" label="状态" width="200"></win-table-column>-->
            <win-table-column prop="warnLevel" label="告警级别" width="90">
              <template slot-scope="scope">
                <span v-if="scope.row.warnLevel === '0'">低</span>
                <span v-if="scope.row.warnLevel === '1'">中</span>
                <span v-if="scope.row.warnLevel === '2'">高</span>
              </template>
            </win-table-column>
           <win-table-column prop="operation" align="center" fixed="right" label="操作" width="480">
             <template slot-scope="scope">
                    <span class="operation" @click="handleViewIssue(scope.row)">
                          <i class="icon icon2 el-icon-edit"></i>查看
                    </span>
                    <span class="operation" @click="handleDeleteIssue(scope.row,1)">
                              <i class="icon icon1 el-icon-delete"></i>删除
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
import IssueController from "../controller/IssueController";
import Component from "vue-class-component";
import { debuglog } from "util";
import { UserReqVO, UserClass } from "../vo/IssueVO";
import PageVO from "../../common/vo/PageVO";
import issueService from "../service/IssueService";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import { BaseConst } from "../../common/const/BaseConst";
@Component({})
export default class IssueTable extends BaseController {

    private multipleSelection: any = [];

    @Prop()
    userReqVo: UserReqVO;


  //@Emit("editMachinePage")
  handleViewIssue(row, column) {
    this.userReqVo.issue = row;
    this.$emit('viewIssuePage', row);
    //this.userReqVo.stateController.switchFormType = "EditMachine";
  }



  handleDeleteIssue(row, column) {
    this.userReqVo.issue = row;
    this.$emit('deleteIssuePage', row);
    /*this.userReqVo.machine = row;
    this.userReqVo.stateController.switchFormType = "DeleteMachine";*/
  }
  
  @Emit("delBatch")
  handledelBatch() {
    console.log("del batch");

  }

  async mounted() {
    this.getIssueList({});
  }

  /** 批量删除 */
  private delIssueBatch() {
    const multipleSelection = this.multipleSelection;
    console.log(multipleSelection);
    console.log(multipleSelection.length);
    if (multipleSelection.length >= 1) {
      this.win_message_box_warning(
        "此操作将永久删除" +
        multipleSelection.length +
        " 条信息, 是否继续?",
        BaseConst.WARNING,
        false
      ).then((res: any) => {
        const ids = [];
        multipleSelection.forEach((element: UserReqVO) => {
          ids.push(element.id);
        });
        const idsStr: string = ids.join(",");
        issueService
          .delBatch(idsStr)
          .then((response: WinResponseData) => {
            this.success("删除机器成功");
            //this.message(response);
          })
          .catch((ex: any) => {
            this.win_message_error("批量删除失败");
          });
      });
    } else {

    }
  }

  /***添加,删除，编辑成功 */
  success(string: string): void {
    this.successMessage(string);
    //this.compayArray = [this.userReqVo.company.companyId];
    this.userReqVo.stateController.switchFormType = "";
    this.getIssueList({});
  }

  // 选中
  tableSelectionChange(val: any) {
    this.multipleSelection = val.selection;
    console.log(this.multipleSelection);
  }

  /***加载查询issue表格*/
  async getIssueList(params) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    issueService
      .getIssueList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              ipAddress: item.ipAddress,
              issueType: item.issueType,
              issueDesc: item.issueDesc,
              warnLevel: item.warnLevel,
              createTime: item.createTime,
              updateTime: item.updateTime
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          this.userReqVo.userArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
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
