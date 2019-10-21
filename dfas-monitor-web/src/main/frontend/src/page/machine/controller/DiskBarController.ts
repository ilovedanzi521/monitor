import BaseController from "../../common/controller/BaseController";
import { Component, Prop } from "vue-property-decorator";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import {
  MachineInfoVO,
  MicroServiceInfoReqVO
} from "../vo/MachineInfoVO";
import echarts from "echarts";
import CPUChartService from "../service/CPUChartService";
import { WinResponseData } from "../../common/vo/BaseVO";
import MachineCPUVO from "../vo/MachineCPUVO";

@Component({})
export default class DiskBarController extends BaseController {
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
    data: MachineInfoVO;
  };

  private microServiceInfoReqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  private microServiceInfoRepVO: MachineInfoVO = new MachineInfoVO();
  private jvmMemoryChartService: CPUChartService = new CPUChartService();
  private microServiceJvmMemoryVO: MachineCPUVO = new MachineCPUVO();

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
    //this.microServiceInfoReqVO.id = this.microServiceInfoRepVO.id;
    //this.microServiceInfoReqVO.microServiceName = this.microServiceInfoRepVO.microServiceName;
     this.renderChart(
      this.legendData,
      this.xAxisData,
      this.colorData,
      this.seriesAxis
    );
   /* this.$nextTick(() => {
      this.query();
    });*/
  }

  private renderChart(legendData, xAxisData, colorData, seriesAxis) {
    let boxID = document.getElementById("pie-doughnut");
    this.chartLine = echarts.init(boxID);
    // 使用刚指定的配置项和数据显示图表。
    this.chartLine.setOption(
      this.getOption(legendData, xAxisData, colorData, seriesAxis)
    );
    window.onresize = this.chartLine.resize;
  }

  name: "pie-doughnut";

  chartLine: any;

  private getOption(legendData, xAxisData, colorData, seriesAxis) {


    let option = {
      title: {
        text: '磁盘使用占比图',
        //subtext: '数据来自网络'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['已使用', '未使用']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
      },
      yAxis: {
        type: 'category',
        data: ['C盘','D盘','E盘']
      },
      series: [
        {
          name: '已使用',
          type: 'bar',
          data: [1820, 2348, 2903]
        },
        {
          name: '未使用',
          type: 'bar',
          data: [19325, 23438, 31000]
        }
      ]
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
