import Vue from "vue";
import Component from "vue-class-component";

export default class HomeController extends Vue {
  ws: WebSocket;

  private mounted() {
    let requestUrl = "ws://localhost:8080/monitor/home/qps";
    this.establishConnection(requestUrl);
  }

  establishConnection(requestUrl) {
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function(e) {
      console.log("建立连接");
      _.ws.send(JSON.stringify({ flag: requestUrl, data: "Hello WebSocket!" }));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    console.log(JSON.stringify(object));
  }

  handleClose() {
    console.log("关闭连接！");
    if (this.ws != null) {
      this.ws.close();
    }
  }
}
