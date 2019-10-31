import Vue from "vue";
import Component from "vue-class-component";
import MachineService from "../service/MachineService";
import { UserReqVO } from "../vo/MachineVO";
import AddMachine from "../view/AddMachine.vue";
import EditMachine from "../view/EditMachine.vue";
import DeleteMachine from "../view/DeleteMachine.vue";
import MachineTable from "../view/MachineTable.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "win-biz";
import MachineDetailDialog from "../view/MachineDetailDialog.vue";
import { MachineInfoVO } from "../vo/MachineInfoVO";
import {MachineVO} from "../../home/vo/MachineVO";
import {MachinePanelVO} from "../vo/MachinePanelVO";


@Component({
    components: {
      MachineDetailDialog
    }
})
export default class MachinePanelController extends BaseController {

  private machinePanelDataList : Array<MachinePanelVO> = [

  ];

  private machineDetailDialogVisible: boolean = false;

  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MachineInfoVO;
  };

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.machineDetailDialogVisible = false;
  }

  onclickRow(row){
    console.log(">>>>>>>>>>>>333333333333>>>>>>>>>>>>");
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(row.item),
    };
    this.machineDetailDialogVisible = true;
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



  async mounted() {
    this.machinePanelData();
  }

  /** 机器明细数据 */
  machinePanelData() {
    console.log("machinePanelData");
    MachineService.machinePanelData()
      .then((response: WinResponseData) => {
        console.log(response.data);
        this.machinePanelDataList = response.data;
      })
      .catch((ex: any) => {});
  }
}
