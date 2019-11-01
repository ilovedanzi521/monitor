import Vue from "vue";
import Component from "vue-class-component";
import Machine from "../../../components2/vue/monitor/Machine.vue";
import Service from "../../../components2/vue/monitor/Service.vue";
import { MachineStateVO } from "../vo/MachineStateVO";
import HomeMachineStateService from "../service/HomeMachineStateService";
import { AxiosFun, BaseController } from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";
import MachineDetailDialog from "../../machine/view/MachineDetailDialog.vue";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import {MachineInfoVO} from "../../machine/vo/MachineInfoVO";

@Component({ components: { Machine, Service,MachineDetailDialog } })
export default class HomeMachineStateController extends BaseController {

  intervalId: NodeJS.Timer | null;

  constructor() {
    super();
    this.intervalId = null;
  }

  setCountDown() {
    this.intervalId = setInterval(() => {
      this.machinePanelData();
    }, 10 * 1000);
  }

  private homeMachineStateService: HomeMachineStateService = new HomeMachineStateService();

  private machineStateList: Array<MachineStateVO> = [];

  private machineDetailDialogVisible: boolean = false;

  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MachineInfoVO;
  };

  onclickRow(row){
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(row.item),
    };
    this.machineDetailDialogVisible = true;
    console.log(this.cardNumber.data);
  }

  /**对象复制 */
  copy(row) {

    let machineInfoVo = {
      ipAddress : row.ipAddress ,
      balance : row.balance ,
      cpuInfo : row.cpuPer ,
      diskInfo : row.diskPer ,
      memoryInfo : row.memoryPer ,
      balanceInfo : row.balanceInfo
    } ;
    return JSON.parse(JSON.stringify(machineInfoVo));
  }

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.machineDetailDialogVisible = false;
  }

  mounted() {
    this.machinePanelData();
    this.setCountDown();

  }

  machinePanelData() {
    AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/machinePanelData",
      {}
    )
      .then((response: WinResponseData) => {
        console.log(response.data);
        //let object = JSON.parse(response.data);
        this.machineStateList = response.data;
        alert(JSON.stringify(this.machineStateList));
      })
      .catch((ex: any) => {});
  }
}
