import Vue from "vue";
import Component from "vue-class-component";
import PlatformOverviewService from "../service/HomePlatformOverviewService";

@Component({})
export default class HomePlatformOverviewController extends Vue {

  private platformOverviewService:PlatformOverviewService = new PlatformOverviewService();

  ws: WebSocket;

  private totalNode: string = "0";

  private totalHttpRequest:string="0";

  private totalMicroService:string="0";

  private qps:string="0";

  mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/platformOverview";
    this.establishConnection(requestUrl);
  }


  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a PlatformOverview WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.totalNode = object.totalNode;
    this.qps = object.qps;
    this.totalMicroService = object.totalMicroService;
    this.totalHttpRequest = object.totalHttpRequest;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

}
