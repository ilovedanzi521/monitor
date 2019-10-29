<template>
  <div class="container">
    <!--产品详情查询表单:Begin -->
    <div class="formInline" style="width:100%;height:50px;margin-top:5px;">
      <win-form v-model="reqVO" :inline="true" v-testName="{'TEST_NAME':'microServiceInfo'}">
        <win-row>
          <win-col :span="5">
                <win-button-group>
                <win-button type="info" icon="win win-add" permit-ref="ADD" @click="operation('','ADD')" round>新增</win-button>
                <win-button type="info" icon="win win-del" :disabled="multipleSelection.length == 0" @click="delBatch" round>删除</win-button>
                <win-button type="info" icon="win win-shougongjiaoyichuli" @click="synchronize" round>一键同步</win-button>
                </win-button-group>
          </win-col> 

          <win-col :span="5">
            <win-form-item label="微服务名称">
              <el-autocomplete v-model="reqVO.microServiceName" :fetch-suggestions="microServiceNameSelect" clearable placeholder="请输入内容">
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
      <win-table :height="tableHeight" :data="pageVO.list" ref="microServiceInfoTable" @cell-dblclick="dblclick" @select-change="tableSelectionChange" @select-all="tableSelectionChange" @cell-click="handleCurrentChange">
        <win-table-column prop="microServiceName" label="微服务名称"></win-table-column>
        <win-table-column prop="microServiceAlias" label="微服务描述" > 
          <template slot-scope="scope">
              <span v-if="scope.row.microServiceAlias === ''">-</span>
              <span v-else-if="scope.row.microServiceAlias === null ">-</span>
              <span v-else-if="scope.row.microServiceAlias != ''">{{scope.row.microServiceAlias}}</span> 
              <span v-else-if="scope.row.microServiceAlias != null ">{{scope.row.microServiceAlias}}</span> 
          </template>
        </win-table-column> 
        <win-table-column prop="state" label="状态">
          <template slot-scope="scope">
              <span class="content_0" v-if="scope.row.state === '0'">离线</span>
              <span class="content_1" v-else-if="scope.row.state === '1'">异常</span>
              <span class="content_2" v-else-if="scope.row.state === '2'">告警</span>
              <span class="content_3" v-else-if="scope.row.state === '3'">在线</span>
              <span  v-else-if="scope.row.state === ''">-</span>
              <span  v-else-if="scope.row.state === '-'">-</span>
          </template>
        </win-table-column>
        <win-table-column prop="warn" label="告警数"></win-table-column>
        <win-table-column prop="error" label="错误数"></win-table-column>
        <win-table-column prop="operation" align="center" fixed="right" label="操作" width="480">
          <template slot-scope="scope">
            <win-button type="text" size="small" icon="el-icon-edit-outline" @click="operation(scope.row,'UPDATE')">修改</win-button>
            <win-button type="text" size="small" icon="el-icon-view" @click="operation(scope.row,'VIEW')">查看</win-button>
            <win-button type="text" size="small" style="color:#FF4D4D" icon="el-icon-delete" @click="operation(scope.row,'DELETE')">删除</win-button>
          </template>
        </win-table-column>
      </win-table>
      <!-- 分页组件 -->
    <!--   <win-pagination name="microService" v-bind:childMsg="pageVO" @callFather="pageQuery"></win-pagination> -->
      <win-pagination v-bind:pageInfo="pageVO" @pageInfoChange="pageQuery"></win-pagination>
      <!-- 创建/修改/删除 dialog -->
      <MicroServiceInfoDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="infoDialogVisible">
      </MicroServiceInfoDialog>
      <!-- 创建/修改/删除 dialog -->
      <MicroServiceDetailDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="detailDialogVisible">
      </MicroServiceDetailDialog>
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
 .content_0 {
      color:rgba(95,92,94,1);
  }
  .content_1 {
      color:rgba(245,12,28,1);
  }
  .content_2 {
      color:rgba(245,179,0,1);
  }
   .content_3 {
      color:rgba(51,204,51,1);
  }
</style>
