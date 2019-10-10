import Vue from "vue";
import Component from "vue-class-component";
import PlatformOverviewService from "../service/PlatformOverviewService";

@Component({})
export default class PlatformOverviewController extends Vue {

  private platformOverviewService:PlatformOverviewService = new PlatformOverviewService();

  ws: WebSocket;

  private totalNode: string = "400";

  private totalHttpRequest:string="1,234,239";

  private totalMicroService:string="1,689";

  private qps:string="68";

  mounted() {
    console.log("mounted");
    let requestUrl = "ws://localhost:8080/monitor/home/qps";
    this.establishConnection(requestUrl);
  }


  establishConnection(requestUrl) {
    console.log("establishConnection begin");
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      console.log("establishConnection success");
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a PlatformOverview WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    console.log(JSON.stringify(object));
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
      console.log("关闭连接！");
    }else{
      console.log("ws is null");
    }
  }

}
