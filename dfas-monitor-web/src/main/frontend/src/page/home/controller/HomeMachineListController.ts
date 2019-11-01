import Vue from "vue";
import Component from "vue-class-component";
import HomeMachineListService from "../service/HomeMachineListService";
import {MachineVO} from "../vo/MachineVO";
import AxiosFun, { BaseController } from "win-biz";
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
export default class HomeMachineListController extends BaseController {

  private homeMachineListService: HomeMachineListService = new HomeMachineListService();

  intervalId: NodeJS.Timer | null;

  constructor() {
    super();
    this.intervalId = null;
  }

  setCountDown() {
    this.intervalId = setInterval(() => {
      this.homeMachineListService.initMachineList(this.machineList);
    }, 5000);
  }

  private machineList: Array<MachineVO> = [];

  private machineDetailDialogVisible: boolean = false;

  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MachineInfoVO;
  };

  mounted() {
    this.homeMachineListService.initMachineList(this.machineList);
    this.setCountDown();
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


}
