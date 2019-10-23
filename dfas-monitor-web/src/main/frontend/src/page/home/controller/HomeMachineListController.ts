import Vue from "vue";
import Component from "vue-class-component";
import HomeMachineListService from "../service/HomeMachineListService";
import {MachineVO} from "../vo/MachineVO";
import AxiosFun from "../../../api/AxiosFun";
import {WinResponseData} from "../../common/vo/BaseVO";
import MachineDetailDialog from "../../machine/view/MachineDetailDialog.vue";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import {MachineInfoVO} from "../../machine/vo/MachineInfoVO";

@Component(
  {
    components: {
       MachineDetailDialog
    }
  }
  )
export default class HomeMachineListController extends Vue {

  private homeMachineListService: HomeMachineListService = new HomeMachineListService();

  private ws: WebSocket;

  private machineList: Array<MachineVO> = [];

  private machineDetailDialogVisible: boolean = false;

  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MachineInfoVO;
  };

  mounted() {
    this.homeMachineListService.initMachineList(this.machineList);
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineList";
    this.establishConnection(requestUrl);
  }

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.machineDetailDialogVisible = false;
  }

  onclickRow(row){
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(row),
    };
    this.machineDetailDialogVisible = true;
    console.log("machinecontroller mclick >>>>row-start>>>>>");
    console.log(row);
    console.log("machinecontroller mclick >>>>row-end>>>>>");

    console.log("machinecontroller mclick >>>>start>>>>>");
    console.log(this.cardNumber.data);
    console.log("machinecontroller mclick >>>>end>>>>>");
  }
  generateClassName(index) {
    if (index % 2 == 0) {
      return "table-tr-data-one";
    } else {
      return "table-tr-data-two";
    }
  }

  /**对象复制 */
  copy(row) {
    let machineInfoVo = {
      ipAddress : row.ip ,
      balance : row.balance ,
      cpuInfo : row.cpuInfo ,
      diskInfo : row.diskInfo ,
      memoryInfo : row.memoryInfo ,
      balanceInfo : row.balanceInfo
    } ;
    return JSON.parse(JSON.stringify(machineInfoVo));
  }


  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a machineList WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    if (object != null) {
      if (object.length <= 5) {
        this.machineList = object;
        let length = object.length;
        for (let i = 0; i <= 5 - length; i++) {
          this.machine = {
            ip: "",
            state: "",
            balance: "",
            cpu: "",
            memory: "",
            disk: ""
          };
          this.machineList.push(this.machine);
        }
      } else if (object.length > 5) {
        this.machineList = [];
        for (let i = 0; i < 5; i++) {
          this.machineList.push(object[i]);
        }
      }
    }
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }



}
