import AxiosFun from "win-biz";
import {MachineStateVO} from "../vo/MachineStateVO";
import {WinResponseData} from "../../common/vo/BaseVO";
import {MachineVO} from "../vo/MachineVO";


export default class HomeMachineStateService {

  private machine: MachineVO = new MachineVO();

  initMachineList(machineList) {
    AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/homePageMachineTop5",
      null
    ).then((response: WinResponseData) => {
      console.log(response.data);
      //let object = JSON.parse(response.data);
      machineList.length = 0 ;
      //this.machineList = response.data;
      let object = response.data;
      if (object != null) {
        console.log("length" + object.length);
        if (object.length <= 5) {
          //this.machineList = object;
          let length = object.length;
          for (let i = 0; i < length; i++) {
            machineList.push(object[i]);
          }
          console.log(machineList.length);
          for (let i = length; i <= 5 - length; i++) {
            this.machine = {
              ip: "",
              state: "",
              balance: "",
              cpu: "",
              memory: "",
              disk: ""
            };
            machineList.push(this.machine);
          }

        } else if (object.length > 5) {
          machineList = [];
          for (let i = 0; i < 5; i++) {
            machineList.push(object[i]);
          }
        }
      }

    })
      .catch((ex: any) => {

      });
  }

}
