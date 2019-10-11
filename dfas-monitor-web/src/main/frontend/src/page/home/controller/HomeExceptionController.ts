import Vue from "vue";
import Component from "vue-class-component";
import PlatformOverviewService from "../service/HomePlatformOverviewService";

@Component({})
export default class HomeExceptionController extends Vue {

  ws: WebSocket;

  private machineCpu: string = "0";
  private machineMemory: string = "0";
  private machineDisk: string = "0";
  private microServiceMemory: string = "0";
  private microServiceWarnLog: string = "0";
  private microServiceErrorLog: string = "0";

  mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/exception";
    this.establishConnection(requestUrl);
  }


  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a exception WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.machineCpu = object.machineCpu;
    this.machineMemory = object.machineMemory;
    this.machineDisk = object.machineDisk;
    this.microServiceMemory = object.microServiceMemory;
    this.microServiceWarnLog = object.microServiceWarnLog;
    this.microServiceErrorLog = object.microServiceErrorLog;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

}
