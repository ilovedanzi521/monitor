<template>
  <div v-testName="{'TEST_NAME':'userPage'}">
    <div class="table-content" style="width: 100%">
      <div class="content">
        <div class="fl"></div>
      </div>
      <div>
        <IssueTable :userReqVo="userReqVo" @machinePageQuery="httpIssuePageQuery" @viewIssuePage="viewIssuePage" @deleteIssuePage="deleteIssuePage"  @delBatch="delIssueBatch"></IssueTable>
      </div>
    </div>
    <component :is="userReqVo.stateController.switchFormType" :userReqVo="userReqVo"  @addMachine="httpAddMachine" @deleteissue="httpDeleteIssue" @editmachine="httpEditMachine">
    </component>

  </div>
</template>

<script lang="ts">
  import IssueController from "../controller/IssueController";
  import "../style/machine.scss";
  import { symlink } from "fs";
  import { Watch } from "vue-property-decorator";
  export default class IssueList extends IssueController {
    activeName: string = "organizational";
    switchingUserState;
    filterText = "";
    inputval: string = "";
    @Watch("filterText", { immediate: true, deep: true })
    onChildChanged(val: string, oldVal: string) {}



  }
</script>
<style lang="scss" scoped>
  @import "../../../assets/style/element.scss";

  .tree-content /deep/ {
    position: absolute;
    width: 212px;
    left: 0;
    top: 0;
    bottom: 0;
    padding: 10px 0;
    background: #273049;
    .tree-wrapp {
      height: 800px;
    }
    .el-input {
      width: 200px;
    }
  }

  .table-content {
    left: 224px;
    right: 0;
    top: 0;
  }
  .content {
    .fl {
      float: left;
    }
    .rl {
      // float: right;
      // width: 88%;
      .serch-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-bottom: 10px;

        .input {
          @include sinput();
          background: #495473;
          font-size: 16px;
        }
        .showTypeGroup {
          margin-right: 30px;
          .showType0 {
            @include showType();
            &.active + em {
              color: #ff900d;
            }
          }
          .showType1 {
            @include showType($bg: #ff4d4dff);
            &.active + em {
              color: #ff900d;
            }
          }
          .showType2 {
            @include showType($bg: #546fd8ff);
            &.active + em {
              color: #ff900d;
            }
          }
          em,
          i {
            display: inline-block;
            vertical-align: middle;
            color: #8d959a;
            cursor: pointer;
            &.marginRight16 {
              margin-right: 46px;
            }
          }

          i {
            margin-right: 12px;
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
    }
  }
</style>
