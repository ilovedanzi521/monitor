import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import HomeMachineStatusService from "../service/HomeMachineStatusService";
import MicroServiceStatusVO from "../vo/MicroServiceStatusVO";
import HomeMicroServiceStatusService from "../service/HomeMicroServiceStatusService";

@Component({components:{Monitor}})
export default class HomeMicroServiceStatusController extends Vue {

  ws: WebSocket;

  private homeMicroServiceStatusService: HomeMicroServiceStatusService = new HomeMicroServiceStatusService();

  private microServiceStatusList: Array<MicroServiceStatusVO> = this.homeMicroServiceStatusService.initMicroServiceStatusList();

  mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/microServiceStatus";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a microServiceStatus WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.microServiceStatusList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }
}
