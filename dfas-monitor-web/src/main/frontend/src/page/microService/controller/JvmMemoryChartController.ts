import BaseController from "../../common/controller/BaseController";
import { Component, Prop } from "vue-property-decorator";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import {
  MicroServiceInfoRepVO,
  MicroServiceInfoReqVO
} from "../vo/MicroServiceInfoVO";
import echarts from "echarts";
import JvmMemoryChartService from "../service/JvmMemoryChartService";
import { WinResponseData } from "../../common/vo/BaseVO";
import MicroServiceJvmMemoryVO from "../vo/MicroServiceJvmMemoryVO";

@Component({})
export default class JvmMemoryChartController extends BaseController {
  public $refs: {
    validate: HTMLFormElement;
  };

  /** 父子组件通信 */
  @Prop({
    type: Object,
    required: false,
    default: () => ({})
  })
  private fromFatherMsg!: {
    type: OperationTypeEnum;
    data: MicroServiceInfoRepVO;
  };

  private microServiceInfoReqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  private microServiceInfoRepVO: MicroServiceInfoRepVO = new MicroServiceInfoRepVO();
  private jvmMemoryChartService: JvmMemoryChartService = new JvmMemoryChartService();
  private microServiceJvmMemoryVO: MicroServiceJvmMemoryVO = new MicroServiceJvmMemoryVO();

  private legendData: string[] = [
    "192.168.0.55",
    "192.168.0.56",
    "192.168.0.57"
  ];
  private xAxisData: string[] = [
    "08:00",
    "09:00",
    "10:00",
    "11:00",
    "12:00",
    "13:00",
    "14:00"
  ];
  private colorData: string[] = ["#33CC33", "#FF4D4D", "#00BAF3"];
  private seriesData: number[][] = [
    [220, 232, 201, 234, 290, 230, 220],
    [120, 200, 150, 80, 70, 110, 130],
    [320, 220, 190, 80, 170, 310, 230]
  ];

  private getSeriesAxis(legendData, colorData, seriesData) {
    let objArr: Array<any> = [];
    for (let i = 0; i < legendData.length; i++) {
      let obj: any = {
        name: legendData[i],
        data: seriesData[i],
        type: "line", // 类型为折线图
        smooth: true,
        lineStyle: {
          // 线条样式 => 必须使用normal属性
          normal: {
            color: colorData[i]
          }
        }
      };
      objArr.push(obj);
    }
    return objArr;
  }

  private seriesAxis: any[] = this.getSeriesAxis(
    this.legendData,
    this.colorData,
    this.seriesData
  );

  /** 页面初始化 */
  private mounted() {
    this.microServiceInfoRepVO = this.fromFatherMsg.data;
    this.microServiceInfoReqVO.microServiceName = this.microServiceInfoRepVO.microServiceName;
/*     this.renderChart(
      this.legendData,
      this.xAxisData,
      this.colorData,
      this.seriesAxis
    ); */
    this.$nextTick(() => {
      this.query();
    });
  }

  private renderChart(legendData, xAxisData, colorData, seriesAxis) {
    let boxID = document.getElementById("chartLineBox");
    this.chartLine = echarts.init(boxID);
    // 使用刚指定的配置项和数据显示图表。
    this.chartLine.setOption(
      this.getOption(legendData, xAxisData, colorData, seriesAxis)
    );
    window.onresize = this.chartLine.resize;
  }

  name: "chartLineBox";

  chartLine: any;

  private getOption(legendData, xAxisData, colorData, seriesAxis) {
    // 指定图表的配置项和数据
    let option = {
      tooltip: {
        //设置tip提示
        trigger: "axis"
      },
      legend: {
        //设置区分（哪条线属于什么）
        data: legendData
      },
      color: colorData, //设置区分（每条线是什么颜色，和 legend 一一对应）
      xAxis: {
        //设置x轴
        type: "category",
        boundaryGap: false, //坐标轴两边不留白
        data: xAxisData,
        name: "时间", //X轴 name
        nameTextStyle: {
          //坐标轴名称的文字样式
          color: "#818DA3",
          fontSize: 16,
          padding: [0, 0, 0, 20]
        },
        axisLine: {
          //坐标轴轴线相关设置。
          lineStyle: {
            color: "#818DA3"
          }
        }
      },
      yAxis: {
        name: "使用率",
        splitLine: {
          show: true,
          lineStyle: {
            color: "rgba(29, 50, 81, 1)",
            width: 1
          }
        },
        nameTextStyle: {
          color: "#818DA3",
          fontSize: 16,
          padding: [0, 0, 10, 0]
        },
        axisLine: {
          lineStyle: {
            color: "#818DA3"
          }
        },
        type: "value"
      },
      series: seriesAxis
    };
    return option;
  }

  /**
   * 微服务JVM使用率查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.jvmMemoryChartService
      .info(this.microServiceInfoReqVO)
      .then((res: WinResponseData) => {
        if (res.winRspType === "ERROR") {
          this.win_message_error(res.msg);
        }
        this.microServiceJvmMemoryVO = res.data;
        this.seriesAxis = this.getSeriesAxis(
          this.microServiceJvmMemoryVO.legendData,
          this.microServiceJvmMemoryVO.colorData,
          this.microServiceJvmMemoryVO.seriesData
        );
        this.legendData = this.microServiceJvmMemoryVO.legendData;
        this.xAxisData = this.microServiceJvmMemoryVO.xaxisData;
        this.colorData = this.microServiceJvmMemoryVO.colorData;
        this.renderChart(
          this.legendData,
          this.xAxisData,
          this.colorData,
          this.seriesAxis
        );
      });
  }
}
