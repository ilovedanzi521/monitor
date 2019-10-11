import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import MicroServiceStateVO from "../vo/MicroServiceStateVO";
import HomeMicroServiceStateService from "../service/HomeMicroServiceStateService";

@Component({components:{Monitor}})
export default class HomeMicroServiceStateController extends Vue {

  ws: WebSocket;

  private homeMicroServiceStateService: HomeMicroServiceStateService = new HomeMicroServiceStateService();

  private microServiceStateList: Array<MicroServiceStateVO> = this.homeMicroServiceStateService.initMicroServiceStateList();

  mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/microServiceState";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a microServiceState WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.microServiceStateList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }
}
