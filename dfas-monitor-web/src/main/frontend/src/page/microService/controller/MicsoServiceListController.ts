/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfbp-common-basicparameter
 * 文件名称: MicroServiceInfoController.ts
 * 文件描述: @Description: 交易对手表 前端控制器
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import { Component } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import MicroServiceInfoService from "../service/MicroServiceInfoService";
import PageVO from "../../common/vo/PageVO";
import { MicroServiceInfoReqVO, MicroServiceInfoRepVO } from "../vo/MicroServiceInfoVO";
import { WinResponseData } from "../../common/vo/BaseVO";
import DicService from "../../dictionary/service/DicService";
import { DicReqVO, DicRepVO } from "../../dictionary/vo/DicVO";
import dateUtils from "../../common/util/DateUtils";
import { WinRspType } from "../../common/enum/BaseEnum";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import MicroServiceInfoDialog from "../view/MicroServiceInfoDialog.vue";
import MicroServiceDetailDialog from "../view/MicroServiceDetailDialog.vue";
import { BaseConst } from "../../common/const/BaseConst";
import { mapState } from "vuex";
/**
 * 类名称：MicroServiceInfoController
 * 类描述：交易对手表 前端控制器
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
@Component({
  components: { MicroServiceInfoDialog,MicroServiceDetailDialog },
  computed: {
    ...mapState({
      showMore: (state: any) => state.microService.showMore
    })
  },
  watch: {
    showMore: {
      handler(newValue, oldValue) {
        // 当关联信息点击修改
        if (newValue.flag) {
          this.tableHeight = newValue.offsetTop - this.$refs.microServiceInfoTable.$el.offsetTop - 70;
        }
      }
    }
  }
})
export default class MicsoServiceListController extends BaseController {
  /** service */
  private microServiceInfoService: MicroServiceInfoService = new MicroServiceInfoService();
  /** 选中信息 */
  private multipleSelection: any = [];
  /** 请求VO */
  private reqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  /** dialog显示控制 */
  private infoDialogVisible: boolean = false;
  /** dialog显示控制 */
  private detailDialogVisible: boolean = false;
  // 表格默认高度
  private tableHeight: number = 400;
  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MicroServiceInfoRepVO;
  };

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    if (msg === WinRspType.SUCC) {
      this.query();
    }
    this.infoDialogVisible = false;
    this.detailDialogVisible = false;
  }

  /**
   * 交易对手表  数据准备
   * @Title: mounted
   * @throws
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private mounted() {
    this.$nextTick(() => {
      this.query();
    });
  }

  /**
   * 交易对手表列表查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.microServiceInfoService.pageList(this.reqVO).then((res: WinResponseData) => {
      if (res.winRspType === "ERROR") {
        this.win_message_error(res.msg);
      }
      this.pageVO = res.data;
      this.$nextTick(() => {
        if (this.$refs.microServiceInfoTable !== undefined && this.$refs.microServiceInfoTable.data.length > 0) {
          // 第一行选中
          this.$refs.microServiceInfoTable.setCurrentRow(this.$refs.microServiceInfoTable.data[0]);
          // 同步
          this.syncMicroServiceState(this.$refs.microServiceInfoTable.data[0]);
        } else {
          this.syncMicroServiceState(null);
        }
      });
    });
  }

  /**
   * 交易对手表 分页列表查询
   * @Title: pageQuery
   * @param pageVO
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private pageQuery(pageVO: PageVO) {
    this.reqVO.reqPageNum = pageVO.pageNum;
    this.reqVO.reqPageSize = pageVO.pageSize;
    this.query();
  }

  /**
   * 交易对手表 分页列表查询
   * @Title: pageQuery
   * @param reqVO
   * @return com.win.dfas.common.vo.WinResponseData
   * @throws
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private reset(): void {
    this.reqVO = new MicroServiceInfoReqVO();
  }


  /** 对手方名称查询 */
  private microServiceNameSelect(queryString: string, cb: any) {
    const microServiceInfoReqVO = new MicroServiceInfoReqVO();
    microServiceInfoReqVO.microServiceName = queryString;
    // 查询前10条数据展示
    microServiceInfoReqVO.reqPageSize = 10;
    this.microServiceInfoService.list(microServiceInfoReqVO).then((response: WinResponseData) => {
      if (response.winRspType === WinRspType.ERROR) {
        this.win_message_error(response.data);
      } else {
        const list = response.data;
        this.filter(list, cb, queryString, "microServiceName");
      }
    });
  }

  private filter(list: MicroServiceInfoRepVO[], cb: any, queryString: string, name: string) {
    const array = [];
    if (list.length > 0) {
      list.forEach((element: MicroServiceInfoRepVO) => {
        const ob: any = { key: "", value: "" };
        if (name === "microServiceAlias") {
          ob.key = element.microServiceAlias;
          ob.value = element.microServiceAlias.toString();
        }else if (name === "microServiceName") {
          ob.key = element.microServiceName;
          ob.value = element.microServiceName;
        }
        array.push(ob);
      });
    }
    const results = queryString ? array.filter(this.createFilter(queryString)) : array;
    // 调用 callback 返回建议列表的数据
    cb(results);
  }

  private createFilter(queryString: string) {
    return (res: any) => {
      return res.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
    };
  }

  /**
   * checkbox选中
   * @param val
   */
  private tableSelectionChange(val: any) {
    this.multipleSelection = val.selection;
  }

  private synchronize(){
    this.win_message_box_warning(
      "此操作将重置微服务数据, 是否继续?",
      BaseConst.WARNING,
      false
    )
      .then((res: any) => {
        this.microServiceInfoService.synchronize().then((response: WinResponseData) => {
          this.message(response);
        });
      })
      .catch(() => {});
  }

  /**
   * 批量删除
   */
  private delBatch() {
    const multipleSelection = this.multipleSelection;
    if (multipleSelection.length > 1) {
      this.win_message_box_warning(
        "此操作将永久删除" + multipleSelection.length + " 条信息, 是否继续?",
        BaseConst.WARNING,
        false
      )
        .then((res: any) => {
          const ids = [];
          multipleSelection.forEach((element: MicroServiceInfoRepVO) => {
            ids.push(element.id);
          });
          const idsStr: string = ids.join("_");
          this.microServiceInfoService.delBatch(idsStr).then((response: WinResponseData) => {
            this.message(response);
          });
        })
        .catch(() => {});
    } else {
      const row = multipleSelection[0];
      this.$nextTick(() => {
        this.operation(row, OperationTypeEnum.DELETE);
      });
    }
  }

  private message(resoponse: WinResponseData) {
    if (resoponse.winRspType === WinRspType.ERROR) {
      this.win_message_error(resoponse.msg);
    } else {
      this.win_message_success(resoponse.msg);
      this.query();
    }
  }

  /** 双击查看 */
  private dblclick({ row }, event: Event) {
    this.detailDialogVisible = true;
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(row),
    };
  }

  /**
   * 新增、修改、删除弹框
   * @param row
   * @param type
   */
  private operation(row: MicroServiceInfoRepVO, type: string) {
    if (type === OperationTypeEnum.ADD) {
      this.infoDialogVisible = true;
      row = new MicroServiceInfoRepVO();
      this.cardNumber = {
        type: OperationTypeEnum.ADD,
        data: this.copy(row),
      };
    } else if (type === OperationTypeEnum.DELETE) {
      this.infoDialogVisible = true;
      this.cardNumber = {
        type: OperationTypeEnum.DELETE,
        data: this.copy(row),
      };
    } else if (type === OperationTypeEnum.SYN) {
      this.infoDialogVisible = true;
      this.cardNumber = {
        type: OperationTypeEnum.SYN,
        data: this.copy(row),
      };
    }else if (type === OperationTypeEnum.UPDATE) {
      this.infoDialogVisible = true;
      this.cardNumber = {
        type: OperationTypeEnum.UPDATE,
        data: this.copy(row),
      };
    } else if (type === OperationTypeEnum.VIEW) {
      this.detailDialogVisible = true;
      this.cardNumber = {
        type: OperationTypeEnum.VIEW,
        data: this.copy(row),
      };
    }
  }

  /**
   * 选中当前行触发事件
   * @param row 行数据
   */
  private handleCurrentChange({ row }) {
    this.syncMicroServiceState(row);
  }

  /**
   * 同步选中行
   * @param row 行数据
   */
  private syncMicroServiceState(row: any) {
    if (row !== null) {
      // 同步一行
      this.$store.commit("setMicroServiceInfo", {
        microServiceAlias: row.microServiceAlias,
        microServiceName: row.microServiceName
      });
    } else {
      // 同步默认
      this.$store.commit("setMicroServiceInfo", {
        microServiceAlias: -1,
        microServiceName: ""
      });
    }
  }
}
