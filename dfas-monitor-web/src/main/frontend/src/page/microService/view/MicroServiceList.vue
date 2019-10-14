<template>
  <div class="container">
    <!--产品详情查询表单:Begin -->
    <div class="formInline" style="width:100%;height:50px;margin-top:5px;">
      <win-form v-model="reqVO" :inline="true" v-testName="{'TEST_NAME':'rivalInfo'}">
        <win-row>
          <win-col :span="3">
            <div style="margin-top: 5px;">
              <el-button-group>
                <win-button type="info" icon="el-icon-plus" @click="operation('','ADD')" round>新增</win-button>
                <win-button type="info" icon="el-icon-delete" :disabled="multipleSelection.length == 0" @click="delBatch" round>删除</win-button>
              </el-button-group>
            </div>
          </win-col>
          <win-col :span="3">
            <div style="margin-top: 5px;">
              <el-button-group>
                <win-button type="info" icon="win win-rizhichaxun" @click="operation('','SYN')" round>一键同步</win-button>
              </el-button-group>
            </div>
          </win-col>
          <win-col :span="5">
            <win-form-item label="微服务名称">
              <el-autocomplete v-model="reqVO.name" :fetch-suggestions="rivalNameSelect" clearable placeholder="请输入内容">
              </el-autocomplete>
            </win-form-item>
          </win-col>

          <win-col :span="3">
            <div style="margin-top: 5px;">
              <win-button type="primary" icon="el-icon-search" @click="query">查询</win-button>
              <win-button icon="el-icon-refresh" @click="reset">重置</win-button>
            </div>
          </win-col>
        </win-row>
      </win-form>
    </div>
    <!--产品查询表单:End -->
    <!--产品表格数据:Begin -->
    <div class="microServiceDataTable">
      <win-table :height="tableHeight" :data="pageVO.list" ref="rivalInfoTable2" @cell-dblclick="dblclick" @select-change="tableSelectionChange" @select-all="tableSelectionChange" @cell-click="handleCurrentChange">
        <win-table-column prop="microServiceName" label="微服务名称" ></win-table-column>
        <win-table-column prop="state" label="状态" ></win-table-column>
        <win-table-column prop="warn" label="告警数" ></win-table-column>
        <win-table-column prop="error" label="错误数" ></win-table-column>
        <win-table-column label="操作" width="200">
          <template slot-scope="scope">
            <win-button type="text" size="small" icon="el-icon-edit-outline" @click="operation(scope.row,'UPDATE')">修改</win-button>
            <win-button type="text" size="small" icon="el-icon-view" @click="operation(scope.row,'VIEW')">查看</win-button>
            <win-button type="text" size="small" style="color:#FF4D4D" icon="el-icon-delete" @click="operation(scope.row,'DELETE')">删除</win-button>
          </template>
        </win-table-column>
      </win-table>
      <!-- 分页组件 -->
      <win-pagination v-bind:pageInfo="pageVO" @pageInfoChange="pageQuery"></win-pagination>
      <!-- 创建/修改/删除 dialog -->
      <MicroServiceInfoDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="dialogVisible">
      </MicroServiceInfoDialog>
    </div>
  </div>
</template>
<script lang="ts">
  import MicsoServiceListController from "../controller/MicsoServiceListController";
  import Component from "vue-class-component";
  @Component({})
  export default class MicsoServiceList extends MicsoServiceListController {}
</script>
<style lang="scss" scoped>
  .formInline {
    .el-form-item {
      margin-bottom: 10px;
    }
  }
  .left {
    margin-left: 160px;
  }
  .microServiceDataTable {
    margin-right: 10px;
  }
</style>
