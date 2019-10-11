import AxiosFun from "../../../api/AxiosFun";
import {MachineVO} from "../vo/MachineVO";


export default class HomeMachineListService {

  private machineList: Array<MachineVO> = [];

  public initMachineList() {
    for (let i = 0; i < 5; i++) {
      this.machineList.push({
        ip: "",
        status: "",
        balance: "",
        cpu: "",
        memory: "",
        disk: ""
      })
    }
    return this.machineList;
  }

}
