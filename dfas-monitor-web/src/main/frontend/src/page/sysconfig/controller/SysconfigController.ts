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
import {SysconfigVO} from "../vo/SysconfigVO";
import {UserReqVO} from "../../machine/vo/MachineVO";
import AddScrape from "../view/AddScrape.vue";
import MachineService from "../../machine/service/MachineService";
import EditScrape from "../view/EditScrape.vue";
import DeleteScrape from "../view/DeleteScrape.vue";

@Component({
    components: {
      AddScrape,EditScrape,DeleteScrape
    }
})
export default class SysconfigController extends BaseController {


  userReqVo: UserReqVO = new UserReqVO();

  private machinePanelDataList : Array<SysconfigVO> = [] ;

  private sysConfigForm: SysconfigVO = new SysconfigVO();

  private multipleScrapeSelection: any = [];

  ws: WebSocket;

  /***添加刮取配置页面*/
  addScrapePage() {
    console.log(">>>>sysconfigcontroller addScrapePage >>>")
    this.userReqVo.stateController.switchFormType = "AddScrape";
  }

  // 选中
  tableScrapSelectionChange(val: any) {
    this.multipleScrapeSelection = val.selection;
    console.log(this.multipleScrapeSelection);
  }

  /** 批量删除刮取配置 */
  private delScrapeBatch() {
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
        MachineService
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
    this.getCompent();
  }

  /***加载公司树结构和用户数据 */
  async getCompent() {
    let params = {  };
    this.getMachine(params);
  }

  /***加载查询machine表格*/
  async getMachine(params) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    MachineService
      .getMachine(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              name: item.name,
              alias: item.alias,
              ipAddress: item.ipAddress,
              userName: item.userName,
              port: item.port,
              osType: item.osType,
              status: item.status,
              description: item.description,
              createTime: item.createTime,
              updateTime: item.updateTime,
              cpu : item.cpu,
              memory : item.memory,
              disk : item.disk,
              cpuInfo : item.cpuInfo,
              diskInfo : item.diskInfo,
              memoryInfo : item.memoryInfo,
              balanceInfo : item.balanceInfo
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          this.userReqVo.userArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  async mounted() {
    this.machinePanelData();
    let requestUrl =
      AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineState";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function(e) {
      _.ws.send(
        JSON.stringify({
          flag: requestUrl,
          data: "i am a microServiceState WebSocket!"
        })
      );
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.machinePanelDataList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

  /** 机器明细数据 */
  machinePanelData() {
    console.log("machinePanelData");
    SysconfigService.machinePanelData()
      .then((response: WinResponseData) => {
        console.log(response.data);
        let object = JSON.parse(response.data);
        this.machinePanelDataList = response.data;
      })
      .catch((ex: any) => {});
  }
}
