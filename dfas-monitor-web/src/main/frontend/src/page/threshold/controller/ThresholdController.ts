import Vue from "vue";
import Component from "vue-class-component";
import ThresholdService from "../service/ThresholdService";
import { UserReqVO } from "../vo/ThresholdVO";
import AddThreshold from "../view/AddThreshold.vue";
import EditThreshold from "../view/EditThreshold.vue";
import DeleteThreshold from "../view/DeleteThreshold.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "../../../api/AxiosFun";
@Component({
    components: {
      AddThreshold,
      EditThreshold,
      DeleteThreshold,
    }
})
export default class ThresholdController extends BaseController {
    $set;
    $refs;
    $forceUpdate;
    filterText = "";
    userReqVo: UserReqVO = new UserReqVO();
    ws: WebSocket;

    /** 批量删除 */
    private delThresholdBatch(multipleSelection,userReqVo) {
      console.log(">>>>>>>>delThresholdBatch>>>>>>>>>>>>")
      //const multipleSelection = this.multipleSelection;
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
          ThresholdService
            .delBatch(idsStr)
            .then((response: WinResponseData) => {
              this.success("删除阈值成功");
              this.syncThreshold({},userReqVo);
              //this.message(response);
            })
            .catch((ex: any) => {
              this.win_message_error("批量删除失败");
            });
        });
      } else {

      }
    }

  /*** 同步threshold表格*/
  async syncThreshold(params,userReqVo) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    userReqVo.reqParam = params;

    ThresholdService
      .getThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              indicatorBody: item.indicatorBody,
              indicatorName: item.indicatorName,
              indicatorSymbol: item.indicatorSymbol,
              threshold: item.threshold + ''
            };
          });
          console.log(">>>>>>>>syncThreshold>>>>>>>>>>")
          userReqVo.userPageVO = winResponseData.data;
          console.log(userReqVo.userPageVO)
          userReqVo.thresholdArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }
  /***添加机器页面*/
  addThresholdPage() {
    this.userReqVo.stateController.switchFormType = "AddThreshold";
  }

  editThresholdPage(row) {
    this.userReqVo.threshold = row;
    this.userReqVo.stateController.switchFormType = "EditThreshold";
  }

  deleteThresholdPage(row) {
    this.userReqVo.threshold = row;
    this.userReqVo.stateController.switchFormType = "DeleteThreshold";
  }


  /***添加阈值*/
  async httpAddThreshold(params) {
    ThresholdService
      .addThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC === winResponseData.winRspType) {
          this.userReqVo.threshold.id = winResponseData.data.id;
          this.success("添加阈值成功");
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  /***编辑阈值*/
  httpEditThreshold(res) {
    console.log(">>>>>>>>>>httpEditThreshold>>>>>>>>>>>>>")
    let id = this.userReqVo.threshold.id;
    let indicatorBody = this.userReqVo.threshold.indicatorBody;
    let indicatorName = this.userReqVo.threshold.indicatorName;
    let indicatorSymbol = this.userReqVo.threshold.indicatorSymbol;
    let threshold = this.userReqVo.threshold.threshold;
    let params = {
      ...res,
      id: id,
      indicatorBody: indicatorBody,
      indicatorName: indicatorName,
      indicatorSymbol: indicatorSymbol,
      threshold: threshold + '',
    };
    ThresholdService
      .editThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("修改阈值成功");
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }

  /***编辑机器*/
  httpEditMachine(res) {
    let id = this.userReqVo.machine.id;
    let ipAddress = this.userReqVo.machine.ipAddress;
    let name = this.userReqVo.machine.name;
    let params = {
      ...res,
      id: id,
      ipAddress: ipAddress,
      name: name
    };
    ThresholdService
      .editThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("修改机器成功");
          let params = {
          };
          this.getThreshold(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }

  /***删除阈值*/
  httpDeleteThreshold(res) {
    let id = this.userReqVo.threshold.id;
    let indicatorBody = this.userReqVo.threshold.indicatorBody;
    let indicatorName = this.userReqVo.threshold.indicatorName;
    let indicatorSymbol = this.userReqVo.threshold.indicatorSymbol;
    let threshold = this.userReqVo.threshold.threshold;

    let params = {
      ...res,
      id: id,
      indicatorBody: indicatorBody,
      indicatorName: indicatorName,
      indicatorSymbol: indicatorSymbol,
      threshold: threshold,
    };
    ThresholdService
      .deleteThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.success("删除阈值成功");
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }


    /***首次加载机器列表数据 */
    async fristGetCompent() {

        /*let params = {
            companyId: this.userReqVo.company.companyId,
            userStates: this.userStates
        };*/
        console.log("fristGetCompent")
        this.getThreshold({})
    }


    /***加载公司树结构和用户数据 */
    async getCompent() {
        let params = {  };
        this.getThreshold(params);
    }


  /**
   * 机器分页查询
   * @param vo
   */
  httpMachinePageQuery(vo: PageVO) {
    console.log("httpUserPageQuery")
    var params = {
      ...this.userReqVo.reqParam,
      ...{ reqPageNum: vo.pageNum, reqPageSize: vo.pageSize }
    };

    this.getThreshold(params);
  }

    /**
     * 用户搜索
     */
    public searchMachine() {
        let params = {
            //companyId: this.userReqVo.company.companyId,
        };
        this.getThreshold(params);
    }

  /***加载查询threshold表格*/
  async getThreshold(params) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    ThresholdService
      .getThreshold(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              indicatorBody: item.indicatorBody,
              indicatorName: item.indicatorName,
              indicatorSymbol: item.indicatorSymbol,
              threshold: item.threshold
            };
          });
          console.log(">>>>>>>>getThreshold>>>>>>>>>>")
          this.userReqVo.userPageVO = winResponseData.data;
          console.log(this.userReqVo.userPageVO)
          this.userReqVo.thresholdArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

    filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
    }

    /***添加,删除，编辑成功 */
    success(string: string): void {
        this.successMessage(string);
        //this.compayArray = [this.userReqVo.company.companyId];
        this.userReqVo.stateController.switchFormType = "";
        this.getCompent();
    }

    async mounted() {
        this.fristGetCompent();
        Tool.frameToMessage("tab", {
            menuName: "业务开关控制",
            menuAddress:
                "/dfbp-common-basicparameter/dfbp/dfbp-common-basicparameter/index.html#/switch"
        });
    }

}
