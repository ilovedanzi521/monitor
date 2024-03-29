import Vue from "vue";
import Component from "vue-class-component";
import echarts from "echarts";
import HomeLeftQpsService from "../service/HomeLeftQpsService";
import AxiosFun from "win-biz";
import BaseController from "../../common/controller/BaseController";
import { WinResponseData } from "../../common/vo/BaseVO";

@Component({})
export default class HomeLeftQpsController extends BaseController {

  private homeLeftQpsService: HomeLeftQpsService = new HomeLeftQpsService();

  ws: WebSocket;

  name: 'qpsChartLineBox';

  qpsChartLine;

  //private xAxisData: string[] = this.homeLeftQpsService.initHourList();

  //private yAxisData: number[] = this.homeLeftQpsService.inityAxisDataList();

  private xAxisData: string[] = [];

  private yAxisData: number[] = [];

  intervalId: NodeJS.Timer | null;

  constructor() {
    super();
    this.intervalId = null;
  }

  setCountDown() {
    this.intervalId = setInterval(() => {
      this.query();
    }, 10000);
  }

  resetCountDown() {
    clearInterval(this.intervalId);
    this.intervalId = null;
  }

  mounted() {
    this.initChart(this.xAxisData, this.yAxisData);
    this.$nextTick(() => {
      this.query();
      this.setCountDown();
    });
  }
  
  private query(): void {
    this.homeLeftQpsService
      .qps()
      .then((res: WinResponseData) => {
        if (res.winRspType === "ERROR") {
          this.win_message_error(res.msg);
        }else{
         // alert(JSON.stringify(res.data));
          this.xAxisData = res.data.xaxisData;
          this.yAxisData = res.data.yaxisData;
          this.initChart(this.xAxisData, this.yAxisData);
        }
      });
  }

  webSocketOpen() {
    //let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/qps";
    //this.establishConnection(requestUrl);
  }


  initChart(xAxisData, yAxisData) {
   // let qpsChartLineBox = document.getElementById('qpsChartLineBox') as HTMLElement;
    this.qpsChartLine = echarts.init(document.getElementById('qpsChartLineBox'));
    // 指定图表的配置项和数据
    let option = {
      tooltip: {              //设置tip提示
        trigger: 'axis'
      },
      xAxis: {
        name: '时间',           //X轴 name
        nameTextStyle: {        //坐标轴名称的文字样式
          color: '#818DA3',
          fontSize: 12,
          padding: [0, 0, 0, 5]
        },
        type: 'category',
        boundaryGap: false,
        data: xAxisData,
        axisLine: {             //坐标轴轴线相关设置。
          lineStyle: {
            color: '#818DA3',
          }
        }
      },
      yAxis: {
        name: '请求量',
        nameTextStyle: {
          color: '#818DA3',
          fontSize: 12,
          padding: [0, 0, 5, 0]
        },
        type: 'value',
        splitLine: {show: false},
        scale: true,
        axisLine: {
          lineStyle: {
            color: '#818DA3',
          }
        },
      },
      series: [{
        data: yAxisData,
        type: 'line',
        smooth: true,
        lineStyle: {                // 线条样式 => 必须使用normal属性
          normal: {
            color: '#00BAF3'
          }
        },
        areaStyle: {
          normal: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [{
                offset: 0, color: '#FFFFFF' // 0% 处的颜色
              }, {
                offset: 1, color: '#00BAF3' // 100% 处的颜色
              }],
              global: false // 缺省为 false
            }
          }
        }
      }]
    };

    // 使用刚指定的配置项和数据显示图表。
    this.qpsChartLine.setOption(option);
    window.onresize = this.qpsChartLine.resize;
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a qps WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.xAxisData = object.xaxisData;
    this.yAxisData = object.yaxisData;
    this.initChart(this.xAxisData, this.yAxisData);
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }

}
