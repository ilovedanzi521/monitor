import BaseController from "../../common/controller/BaseController";
import { Component, Prop } from "vue-property-decorator";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import {
  MachineInfoVO
} from "../vo/MachineInfoVO";
import echarts from "echarts";
import CPUChartService from "../service/CPUChartService";
import { WinResponseData } from "../../common/vo/BaseVO";

@Component({})
export default class CPUController extends BaseController {
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

  private machineInfoVO: MachineInfoVO = new MachineInfoVO();
  private cpuChartService: CPUChartService = new CPUChartService();
  /** 页面初始化 */
  private mounted() {
    this.machineInfoVO = this.fromFatherMsg.data;
    this.$nextTick(() => {
      this.query();
    });
  }

  private renderChart(legendData, xAxisData, colorData, seriesAxis,seriesData) {
    let boxID = document.getElementById("pie-doughnut");
    this.chartLine = echarts.init(boxID);
    // 使用刚指定的配置项和数据显示图表。
    this.chartLine.setOption(
      this.getOption(legendData, xAxisData, colorData, seriesAxis,seriesData)
    );
    window.onresize = this.chartLine.resize;
  }

  name: "pie-doughnut";

  chartLine: any;

  private getOption(legendData, xAxisData, colorData, seriesAxis,seriesData){
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        x: 'left',
        data:legendData
      },
      series: [
        {
          name:'CPU使用率',
          type:'pie',
          radius: ['50%', '70%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data:seriesData,
          color:colorData,
          title : {
            text: 'CPU使用率',
            x:'center',
            y: 'bottom',
          },
        }
      ]
    };
    return option;
  }

  /**
   * CPU使用率查询
   * @Title: query
   * @author: lj
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.cpuChartService
      .info(this.machineInfoVO)
      .then((res: WinResponseData) => {
        if (res.winRspType === "ERROR") {
          this.win_message_error(res.msg);
        }

        let respData = res.data;
        this.renderChart(
          respData.legendData,
          respData.xAxisData,
          respData.colorData,
          respData.seriesAxis,
          respData.seriesData
        );
      });
  }
}
