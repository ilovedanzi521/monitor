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
    }
})
export default class MachinePanelController extends BaseController {

  private machinePanelDataList : Array<MachinePanelVO> = [

  ];
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
