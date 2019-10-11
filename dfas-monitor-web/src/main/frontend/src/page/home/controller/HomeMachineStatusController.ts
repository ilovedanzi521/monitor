import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import MachineStatusVO from "../vo/MachineStatusVO";
import HomeMachineStatusService from "../service/HomeMachineStatusService";

@Component({components: {Monitor}})
export default class HomeMachineStatusController extends Vue {

  ws: WebSocket;

  private homeMachineStatusService: HomeMachineStatusService = new HomeMachineStatusService();

  private machineStatusList: Array<MachineStatusVO> = this.homeMachineStatusService.initMachineStatusList();

  mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/machineStatus";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a machineStatus WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.machineStatusList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

}
