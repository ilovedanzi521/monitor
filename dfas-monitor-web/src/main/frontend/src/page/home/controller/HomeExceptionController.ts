import Vue from "vue";
import Component from "vue-class-component";
import PlatformOverviewService from "../service/HomePlatformOverviewService";
import AxiosFun from "win-biz";
import BaseController from "../../common/controller/BaseController";
import HomeExceptionService from "../service/HomeExceptionService";
import { WinResponseData } from "../../common/vo/BaseVO";
import HomeExceptionVO from "../vo/HomeExceptionVO";

@Component({})
export default class HomeExceptionController extends BaseController {
  
  ws: WebSocket;
  private homeExceptionService: HomeExceptionService = new HomeExceptionService();

  private homeExceptionVO: HomeExceptionVO = new HomeExceptionVO();

  mounted() {
    this.$nextTick(() => {
      this.query();
      this.setCountDown();
    });
  }

  intervalId: NodeJS.Timer | null;

  constructor() {
    super();
    this.intervalId = null;
  }

  setCountDown() {
    this.intervalId = setInterval(() => {
      this.query();
    }, 1000*30);
  }

  resetCountDown() {
    clearInterval(this.intervalId);
    this.intervalId = null;
  }

  private query(): void {
    this.homeExceptionService.exception().then((res: WinResponseData) => {
      if (res.winRspType === "ERROR") {
        this.win_message_error(res.msg);
      } else {
        this.homeExceptionVO = res.data;
      }
    });
  }

  webSocketOpen() {
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/exception";
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
          data: "i am a exception WebSocket!"
        })
      );
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    //  let object = JSON.parse(e.data);
    this.homeExceptionVO = e.data;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }
}
