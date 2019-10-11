/****************************************************
 * 创建人：  @author zoujian
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
import MicroServiceInfoDicDataVO from "../vo/MicroServiceInfoDicDataVO";
import DicService from "../../dictionary/service/DicService";
import { DicReqVO, DicRepVO } from "../../dictionary/vo/DicVO";
import dateUtils from "../../common/util/DateUtils";
import { WinRspType } from "../../common/enum/BaseEnum";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import MicroServiceInfoDialog from "../view/MicroServiceInfoDialog.vue";
import { BaseConst } from "../../common/const/BaseConst";
import { mapState } from "vuex";
/**
 * 类名称：MicroServiceInfoController
 * 类描述：交易对手表 前端控制器
 * 创建人：@author zoujian
 * 创建时间：2019-07-10 11:35:35
 */
@Component({
  components: { MicroServiceInfoDialog },
  computed: {
    ...mapState({
      showMore: (state: any) => state.rival.showMore
    })
  },
  watch: {
    showMore: {
      handler(newValue, oldValue) {
        // 当关联信息点击修改
        if (newValue.flag) {
          this.tableHeight = newValue.offsetTop - this.$refs.rivalInfoTable.$el.offsetTop - 70;
        }
      }
    }
  }
})



export default class MicsoServiceListController extends BaseController {
  /** service */
  private microServiceInfoService: MicroServiceInfoService = new MicroServiceInfoService();
  /** 数据字典service */
  private dicService: DicService = new DicService();
  /** 选中信息 */
  private multipleSelection: any = [];
  /** 请求VO */
  private reqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  /** 数据字典 */
  private rivalInfoDicData: MicroServiceInfoDicDataVO = new MicroServiceInfoDicDataVO();
  /** dialog显示控制 */
  private dialogVisible: boolean = false;
  // 表格默认高度
  private tableHeight: number = 400;
  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MicroServiceInfoRepVO;
    rivalInfoDicData: MicroServiceInfoDicDataVO;
  };

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    if (msg === WinRspType.SUCC) {
      this.query();
    }
    this.dialogVisible = false;
  }

  /**
   * 交易对手表  数据准备
   * @Title: mounted
   * @throws
   * @author: zoujian
   * @Date:   2019-07-10 11:35:35
   */
  private mounted() {
    const dic = new DicReqVO();
    const dicCodeArray = ["1000271", "1000272", "1000267"];
    dic.parentDicCodeList = dicCodeArray;
    this.dicService.dicMultipleAllSubList(dic).then((res: WinResponseData) => {
      /** 是否优质对手方 */
      this.rivalInfoDicData.goldenDic = res.data["1000267"];
      /** 结算方式 */
      this.rivalInfoDicData.settleTypeDic = res.data["1000271"];
      /** 对手方评级 */
      this.rivalInfoDicData.appraiseDic = res.data["1000272"];
    });
    this.$nextTick(() => {
      this.query();
    });
  }

  /**
   * 交易对手表列表查询
   * @Title: query
   * @author: zoujian
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.microServiceInfoService.pageList(this.reqVO).then((res: WinResponseData) => {
      if (res.winRspType === "ERROR") {
        this.win_message_error(res.msg);
      }
      this.pageVO = res.data;
      this.$nextTick(() => {
        if (this.$refs.rivalInfoTable !== undefined && this.$refs.rivalInfoTable.data.length > 0) {
          // 第一行选中
          this.$refs.rivalInfoTable.setCurrentRow(this.$refs.rivalInfoTable.data[0]);
          // 同步
          this.syncRivalState(this.$refs.rivalInfoTable.data[0]);
        } else {
          this.syncRivalState(null);
        }
      });
    });
  }

  /**
   * 交易对手表 分页列表查询
   * @Title: pageQuery
   * @param pageVO
   * @author: zoujian
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
   * @author: zoujian
   * @Date:   2019-07-10 11:35:35
   */
  private reset(): void {
    this.reqVO = new MicroServiceInfoReqVO();
  }

  /** 数据字典映射 */
  private formatDic({ row, column }) {
    if (column.property === "appraise") {
      return this.getLabelFromDic(row.appraise, this.rivalInfoDicData.appraiseDic);
    }
    if (column.property === "golden") {
      return this.getLabelFromDic(row.golden, this.rivalInfoDicData.goldenDic);
    }
    if (column.property === "firstSettleType") {
      return this.getLabelFromDic(row.firstSettleType, this.rivalInfoDicData.settleTypeDic);
    }
    if (column.property === "secondSettleType") {
      return this.getLabelFromDic(row.secondSettleType, this.rivalInfoDicData.settleTypeDic);
    }
    if (column.property === "createUserId") {
      return row.updateUserId ? row.updateUserId : row.createUserId;
    }
    if (column.property === "createTime") {
      const date = new Date(row.updateTime ? row.updateTime : row.createTime);
      return dateUtils.dateFtt("yyyy-MM-dd hh:mm:ss", date);
    }
  }

  private getLabelFromDic(str: string, dicArray: DicRepVO[]) {
    let value = "";
    for (const dic of dicArray) {
      if (dic.dicCode === str) {
        value = dic.dicExplain;
        break;
      }
    }
    return value;
  }

  /** 对手方序号查询 */
  private rivalNoSelect(queryString: string, cb: any) {
    const rivalInfoReqVO = new MicroServiceInfoReqVO();
    // tslint:disable-next-line: radix
    rivalInfoReqVO.rivalNo = parseInt(queryString);
    // 查询前10条数据展示
    rivalInfoReqVO.reqPageSize = 10;
    this.microServiceInfoService.list(rivalInfoReqVO).then((response: WinResponseData) => {
      if (response.winRspType === WinRspType.ERROR) {
        this.win_message_error(response.data);
      } else {
        const list = response.data;
        this.filter(list, cb, queryString, "rivalNo");
      }
    });
  }

  /** 对手方名称查询 */
  private rivalNameSelect(queryString: string, cb: any) {
    const rivalInfoReqVO = new MicroServiceInfoReqVO();
    rivalInfoReqVO.rivalName = queryString;
    // 查询前10条数据展示
    rivalInfoReqVO.reqPageSize = 10;
    this.microServiceInfoService.list(rivalInfoReqVO).then((response: WinResponseData) => {
      if (response.winRspType === WinRspType.ERROR) {
        this.win_message_error(response.data);
      } else {
        const list = response.data;
        this.filter(list, cb, queryString, "rivalName");
      }
    });
  }

  private filter(list: MicroServiceInfoRepVO[], cb: any, queryString: string, name: string) {
    const array = [];
    if (list.length > 0) {
      list.forEach((element: MicroServiceInfoRepVO) => {
        const ob: any = { key: "", value: "" };
        if (name === "rivalNo") {
          ob.key = element.rivalNo;
          ob.value = element.rivalNo.toString();
        }
        if (name === "rivalName") {
          ob.key = element.rivalName;
          ob.value = element.rivalName;
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
    this.dialogVisible = true;
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(row),
      rivalInfoDicData: this.rivalInfoDicData
    };
  }

  /**
   * 新增、修改、删除弹框
   * @param row
   * @param type
   */
  private operation(row: MicroServiceInfoRepVO, type: string) {
    this.dialogVisible = true;
    if (type === OperationTypeEnum.ADD) {
      row = new MicroServiceInfoRepVO();
      this.cardNumber = {
        type: OperationTypeEnum.ADD,
        data: this.copy(row),
        rivalInfoDicData: this.rivalInfoDicData
      };
    }
    if (type === OperationTypeEnum.DELETE) {
      this.cardNumber = {
        type: OperationTypeEnum.DELETE,
        data: this.copy(row),
        rivalInfoDicData: this.rivalInfoDicData
      };
    }
    if (type === OperationTypeEnum.UPDATE) {
      this.cardNumber = {
        type: OperationTypeEnum.UPDATE,
        data: this.copy(row),
        rivalInfoDicData: this.rivalInfoDicData
      };
    }
  }

  /**
   * 选中当前行触发事件
   * @param row 行数据
   */
  private handleCurrentChange({ row }) {
    this.syncRivalState(row);
  }

  /**
   * 同步选中行
   * @param row 行数据
   */
  private syncRivalState(row: any) {
    if (row !== null) {
      // 同步一行
      this.$store.commit("setRivalInfo", {
        rivalNo: row.rivalNo,
        rivalName: row.rivalName
      });
    } else {
      // 同步默认
      this.$store.commit("setRivalInfo", {
        rivalNo: -1,
        rivalName: ""
      });
    }
  }
}
