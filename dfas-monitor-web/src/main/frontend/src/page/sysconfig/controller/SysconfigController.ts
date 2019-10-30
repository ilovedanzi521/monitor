import Vue from "vue";
import Component from "vue-class-component";
import SysconfigService from "../service/SysconfigService";
import AddMachine from "../view/AddMachine.vue";
import EditMachine from "../view/EditScrape.vue";
import DeleteMachine from "../view/DeleteScrape.vue";
import MachineTable from "../view/MachineTable.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "../../../api/AxiosFun";
import MachineDetailDialog from "../view/MachineDetailDialog.vue";
import {MachineVO} from "../../home/vo/MachineVO";
import {UserReqVO,SysconfigVO} from "../vo/SysconfigVO";
//import {UserReqVO} from "../../machine/vo/MachineVO";
import AddScrape from "../view/AddScrape.vue";
import MachineService from "../../machine/service/MachineService";
import EditScrape from "../view/EditScrape.vue";
import DeleteScrape from "../view/DeleteScrape.vue";
import {MachineInfoVO} from "../../machine/vo/MachineInfoVO";

import AddAlertRule from "../view/AddAlertRule.vue";
import EditAlertRule from "../view/EditAlertRule.vue";
import DeleteAlertRule from "../view/DeleteAlertRule.vue";

@Component({
    components: {
      AddScrape,EditScrape,DeleteScrape,
      AddAlertRule,EditAlertRule,DeleteAlertRule
    }
})
export default class SysconfigController extends BaseController {


  userReqVo: UserReqVO = new UserReqVO();

  private scrapeDataList : Array<SysconfigVO> = [] ;

  private sysConfigForm: SysconfigVO = new SysconfigVO();

  private multipleScrapeSelection: any = [];

  ws: WebSocket;

  /***添加刮取配置*/
  async httpAddScrape(params,userReqVo) {
    SysconfigService
      .addScrape(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC === winResponseData.winRspType) {
          this.userReqVo.scrape.id = winResponseData.data.id;
          this.scrape_success("添加刮取配置成功");
          this.syncScrapeDataList(params,userReqVo);
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  /***添加刮取配置页面*/
  addScrapePage(userReqVo) {
    console.log(">>>>ScrapeController addScrapePage >>>");
    this.userReqVo = userReqVo ;
    this.userReqVo.stateController.switchFormType = "AddScrape";
  }

  /***添加刮取配置页面*/
  editScrapePage(row) {
    console.log(">>>>ScrapeController editScrapePage >>>");
    this.userReqVo.scrape = row ;
    this.userReqVo.stateController.switchFormType = "EditScrape";
  }

  /***添加刮取配置页面*/
  deleteScrapePage(row) {
    console.log(">>>>ScrapeController editScrapePage >>>");
    this.userReqVo.scrape = row ;
    this.userReqVo.stateController.switchFormType = "DeleteScrape";
  }

  // 选中
  tableScrapSelectionChange(val: any) {
    this.multipleScrapeSelection = val.selection;
    console.log(this.multipleScrapeSelection);
  }

  /***删除刮取*/
  httpDeleteScrape(res,userReqVo) {
    let id = this.userReqVo.scrape.id;
    let params = {
      ...res,
      id: id,
    };
    SysconfigService.deleteScrape(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("删除刮取配置成功");
          let params = {
          };
          this.getScrapeDataList(params);
          this.syncScrapeDataList(params,userReqVo);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }

  /***编辑机器*/
  httpEditScrape(res) {
    let id = this.userReqVo.scrape.id;
    let jobName = this.userReqVo.scrape.jobName;
    let scrapeInterval = this.userReqVo.scrape.scrapeInterval;
    let metricsPath = this.userReqVo.scrape.metricsPath;
    let staticConfigsTargets = this.userReqVo.scrape.staticConfigsTargets;
    let staticConfigsLabelsInstance = this.userReqVo.scrape.staticConfigsLabelsInstance;
    let consulSdConfigsServer = this.userReqVo.scrape.consulSdConfigsServer;
    let consulSdConfigsServername = this.userReqVo.scrape.consulSdConfigsServername;
    let consulSdConfigsScheme = this.userReqVo.scrape.consulSdConfigsScheme;
    let params = {
      ...res,
      id: id,
      jobName: jobName,
      scrapeInterval: scrapeInterval + '',
      metricsPath: metricsPath,
      staticConfigsTargets: staticConfigsTargets,
      staticConfigsLabelsInstance: staticConfigsLabelsInstance,
      consulSdConfigsServer: consulSdConfigsServer,
      consulSdConfigsServername: consulSdConfigsServername,
      consulSdConfigsScheme: consulSdConfigsScheme,
    };
    SysconfigService
      .editScrape(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("修改刮取成功");
          let params = {
          };
          this.getScrapeDataList(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }


  /** 批量删除刮取配置 */
  private delBatchScrape() {
    const multipleSelection = this.multipleScrapeSelection;
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
        SysconfigService
          .delScrapeBatch(idsStr)
          .then((response: WinResponseData) => {
            this.scrape_success("删除刮取成功");
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
  scrape_success(string: string): void {
    this.successMessage(string);
    //this.compayArray = [this.userReqVo.company.companyId];
    this.userReqVo.stateController.switchFormType = "";
    this.getScrapeCompent();
  }

  /***加载公司树结构和用户数据 */
  async getScrapeCompent() {
    let params = {  };
    this.getScrapeDataList(params);
  }

  /***加载查询刮取配置表格*/
  async getScrapeDataList(params) {
    console.log(">>>getScrapeDataList>>>>");
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    SysconfigService
      .getScrapeDataList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              jobName: item.jobName,
              scheme: item.scheme,
              scrapeInterval: item.scrapeInterval,
              metricsPath: item.metricsPath,
              staticConfigsTargets: item.staticConfigsTargets,
              staticConfigsLabelsInstance: item.staticConfigsLabelsInstance,
              consulSdConfigsServer: item.consulSdConfigsServer,
              consulSdConfigsServername: item.consulSdConfigsServername,
              consulSdConfigsScheme: item.consulSdConfigsScheme
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          this.userReqVo.scrapeArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  async syncScrapeDataList(params,userReqVO) {
    console.log(">>>getScrapeDataList>>>>");
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    SysconfigService
      .getScrapeDataList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              jobName: item.jobName,
              scheme: item.scheme,
              scrapeInterval: item.scrapeInterval,
              metricsPath: item.metricsPath,
              staticConfigsTargets: item.staticConfigsTargets,
              staticConfigsLabelsInstance: item.staticConfigsLabelsInstance,
              consulSdConfigsServer: item.consulSdConfigsServer,
              consulSdConfigsServername: item.consulSdConfigsServername,
              consulSdConfigsScheme: item.consulSdConfigsScheme
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          userReqVO.userArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  /****************************************************************************************/


  private multipleAlertRuleSelection: any = [];

  /***添加刮取配置*/
  async httpAddAlertRule(params,userReqVo) {
    SysconfigService
      .addAlertRule(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC === winResponseData.winRspType) {
          this.userReqVo.scrape.id = winResponseData.data.id;
          this.alertrule_success("添加预警配置成功");
          //this.syncAlertRuleDataList(params,userReqVo);
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  /***添加刮取配置页面*/
  addAlertRulePage(userReqVo) {
    console.log(">>>>AlertRuleController addAlertRulePage >>>");
    this.userReqVo = userReqVo ;
    this.userReqVo.stateController.switchFormType = "AddAlertRule";
  }

  /***添加刮取配置页面*/
  editAlertRulePage(row) {
    console.log(">>>>SysconfigController editAlertRulePage >>>");
    this.userReqVo.alertRule = row ;
    this.userReqVo.stateController.switchFormType = "EditAlertRule";
  }

  /***添加刮取配置页面*/
  deleteAlertRulePage(row) {
    console.log(">>>>SysconfigController deleteAlertRulePage >>>");
    this.userReqVo.alertRule = row ;
    this.userReqVo.stateController.switchFormType = "DeleteAlertRule";
  }

  // 选中
  tableAlertRuleSelectionChange(val: any) {
    this.multipleAlertRuleSelection = val.selection;
    console.log(this.multipleAlertRuleSelection);
  }

  /***删除刮取*/
  httpDeleteAlertRule(res,userReqVo) {
    let id = this.userReqVo.alertRule.id;
    let params = {
      ...res,
      id: id,
    };
    SysconfigService.deleteAlertRule(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("删除预警配置成功");
          let params = {
          };
          this.getAlertRuleDataList(params);
          this.syncAlertRuleDataList(params,userReqVo);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }

  /***编辑机器*/
  httpEditAlertRule(res) {
    let id = this.userReqVo.scrape.id;
    let jobName = this.userReqVo.scrape.jobName;
    let scrapeInterval = this.userReqVo.scrape.scrapeInterval;
    let metricsPath = this.userReqVo.scrape.metricsPath;
    let staticConfigsTargets = this.userReqVo.scrape.staticConfigsTargets;
    let staticConfigsLabelsInstance = this.userReqVo.scrape.staticConfigsLabelsInstance;
    let consulSdConfigsServer = this.userReqVo.scrape.consulSdConfigsServer;
    let consulSdConfigsServername = this.userReqVo.scrape.consulSdConfigsServername;
    let consulSdConfigsScheme = this.userReqVo.scrape.consulSdConfigsScheme;
    let params = {
      ...res,
      id: id,
      jobName: jobName,
      scrapeInterval: scrapeInterval + '',
      metricsPath: metricsPath,
      staticConfigsTargets: staticConfigsTargets,
      staticConfigsLabelsInstance: staticConfigsLabelsInstance,
      consulSdConfigsServer: consulSdConfigsServer,
      consulSdConfigsServername: consulSdConfigsServername,
      consulSdConfigsScheme: consulSdConfigsScheme,
    };
    SysconfigService
      .editAlertRule(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("修改预警成功");
          let params = {
          };
          this.getAlertRuleDataList(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }


  /** 批量删除刮取配置 */
  private delBatchAlertRule() {
    const multipleSelection = this.multipleAlertRuleSelection;
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
        SysconfigService
          .delAlertRuleBatch(idsStr)
          .then((response: WinResponseData) => {
            this.alertrule_success("删除预警成功");
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
  alertrule_success(string: string): void {
    this.successMessage(string);
    //this.compayArray = [this.userReqVo.company.companyId];
    this.userReqVo.stateController.switchFormType = "";
    this.getAlertRuleCompent();
  }

  /***加载公司树结构和用户数据 */
  async getAlertRuleCompent() {
    let params = {  };
    this.getAlertRuleDataList(params);
  }

  /***加载查询刮取配置表格*/
  async getAlertRuleDataList(params) {
    console.log(">>>getAlertRuleDataList>>>>");
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    SysconfigService
      .getAlertRuleDataList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              groupsName: item.groupsName,
              name: item.name,
              expr: item.expr,
              fortime: item.fortime,
              labelsSeverity: item.labelsSeverity,
              annotationsSummary: item.annotationsSummary,
              annotationsDescription: item.annotationsDescription
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          this.userReqVo.alertRuleArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  async syncAlertRuleDataList(params,userReqVO) {
    console.log(">>>syncAlertRuleDataList>>>>");
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    SysconfigService
      .getAlertRuleDataList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              jobName: item.jobName,
              scheme: item.scheme,
              scrapeInterval: item.scrapeInterval,
              metricsPath: item.metricsPath,
              staticConfigsTargets: item.staticConfigsTargets,
              staticConfigsLabelsInstance: item.staticConfigsLabelsInstance,
              consulSdConfigsServer: item.consulSdConfigsServer,
              consulSdConfigsServername: item.consulSdConfigsServername,
              consulSdConfigsScheme: item.consulSdConfigsScheme
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          userReqVO.alertRuleArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }


  async mounted() {
    this.getScrapeCompent();
    this.getAlertRuleCompent();
  }

}
