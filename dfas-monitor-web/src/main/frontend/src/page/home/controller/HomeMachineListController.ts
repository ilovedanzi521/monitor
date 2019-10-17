import Vue from "vue";
import Component from "vue-class-component";
import HomeMachineListService from "../service/HomeMachineListService";
import {MachineVO} from "../vo/MachineVO";
import AxiosFun from "../../../api/AxiosFun";
import {WinResponseData} from "../../common/vo/BaseVO";

@Component({})
export default class HomeMachineListController extends Vue {

  private homeMachineListService: HomeMachineListService = new HomeMachineListService();

  private ws: WebSocket;

  private machine: MachineVO = new MachineVO();

  private machineList: Array<MachineVO> = [];

  //private machineList: Array<MachineVO> = this.homeMachineListService.initMachineList();

  mounted() {
    this.initMachineList();
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineList";
    this.establishConnection(requestUrl);
  }

  generateClassName(index) {
    if (index % 2 == 0) {
      return "table-tr-data-one";
    } else {
      return "table-tr-data-two";
    }
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

  initMachineList() {
    AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/homePageMachineTop5",
      null
    ).then((response: WinResponseData) => {
      console.log(response.data);
      //let object = JSON.parse(response.data);
      this.machineList = response.data;

    })
      .catch((ex: any) => {

      });
  }

}
