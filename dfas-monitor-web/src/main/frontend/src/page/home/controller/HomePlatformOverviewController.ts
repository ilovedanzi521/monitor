import Vue from "vue";
import Component from "vue-class-component";
import PlatformOverviewService from "../service/HomePlatformOverviewService";
import AxiosFun from "../../../api/AxiosFun";
import { WinResponseData } from "../../common/vo/BaseVO";
import HomePlatformOverviewVO from "../../microService/vo/HomePlatformOverviewVO";
import BaseController from "../../common/controller/BaseController";

@Component({})
export default class HomePlatformOverviewController extends BaseController {
  
  private platformOverviewService: PlatformOverviewService = new PlatformOverviewService();

  ws: WebSocket;

  private homePlatformOverviewVO :HomePlatformOverviewVO = new HomePlatformOverviewVO();

  intervalId: NodeJS.Timer | null;

  constructor() {
    super();
    this.intervalId = null;
  }

  setCountDown() {
    this.intervalId = setInterval(() => {
      this.query();
    }, 1000);
  }

  resetCountDown() {
    clearInterval(this.intervalId);
    this.intervalId = null;
  }

  mounted() {
    this.$nextTick(() => {
      this.query();
      this.setCountDown();
    });
  }



  private query(): void {
    this.platformOverviewService
      .platformOverview()
      .then((res: WinResponseData) => {
        if (res.winRspType === "ERROR") {
          this.win_message_error(res.msg);
        }else{
          this.homePlatformOverviewVO = res.data;
        }
      });
  }

  webSocketOpen() {
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/platformOverview";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function(e) {
      _.ws.send(
        JSON.stringify({
          flag: requestUrl,
          data: "i am a PlatformOverview WebSocket!"
        })
      );
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    //let object = JSON.parse(e.data);
    this.homePlatformOverviewVO = e.data;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }
}
