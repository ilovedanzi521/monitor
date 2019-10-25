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
import AxiosFun from "../../../api/AxiosFun";
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
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "2",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "1",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "1",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2",
      state: "0",
      ipAddress: "192.168.0.0111111111",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21112",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211212",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21121332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "21123321332",
      state: "1",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "211312",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "2",
      ipAddress: "192.168.0.0",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },

    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    },
    {
      id: "2113412",
      state: "3",
      ipAddress: "192.168.0.1",
      cupNum: 2,
      memorySize: "128G",
      diskSize: "32T",
      cpuPer: "70%",
      memoryPer: "30%",
      diskPer: "60%"
    }
  ];

  ws: WebSocket;

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
    MachineService.machinePanelData()
      .then((response: WinResponseData) => {
        console.log(response.data);
        let object = JSON.parse(response.data);
        this.machinePanelDataList = response.data;
      })
      .catch((ex: any) => {});
  }
}
