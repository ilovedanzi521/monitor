import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import {MachineStateVO} from "../vo/MachineStateVO";
import HomeMachineStateService from "../service/HomeMachineStateService";
import AxiosFun from "../../../api/AxiosFun";

@Component({components: {Monitor}})
export default class HomeMachineStateController extends Vue {

  ws: WebSocket;

  private homeMachineStateService: HomeMachineStateService = new HomeMachineStateService();

  private machineStateList: Array<MachineStateVO> = this.homeMachineStateService.initMachineStateList();

  mounted() {
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineState";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a machineState WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.machineStateList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

}
