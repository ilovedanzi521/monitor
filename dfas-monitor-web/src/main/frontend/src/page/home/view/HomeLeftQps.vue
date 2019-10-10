<template>
  <div class="qpsBlock">
    <div class="rect">
      <div class="qpsTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;Qps趋势图
      </div>
      <div id="chartLineBox"></div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";
  import echarts from "echarts";

  @Component({})
  export default class HomeLeftQps extends Vue {


    name: 'chartLineBox';

    chartLine;

    private mounted() {
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));

      // 指定图表的配置项和数据
      var option = {
        xAxis: {
          name: '时间',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: '#818DA3',
            fontSize: 16,
            padding: [0, 0, 0, 0]
          },
          type: 'category',
          boundaryGap: false,
          data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00'],
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
            fontSize: 16,
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
          data: [820, 932, 901, 934, 1290, 1330, 1320],
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
      this.chartLine.setOption(option);
      window.onresize = this.chartLine.resize;
    }


  }
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";

  #chartLineBox {
    width: 100%;
    height: 98%;
    font-size: 18px;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
  }
  .rect {
    width: 100%;
    height: 100%;
    background: linear-gradient(to left, #195091, #195091) left top no-repeat,
    linear-gradient(to bottom, #195091, #195091) left top no-repeat,
    linear-gradient(to left, #195091, #195091) right top no-repeat,
    linear-gradient(to bottom, #195091, #195091) right top no-repeat,
    linear-gradient(to left, #195091, #195091) left bottom no-repeat,
    linear-gradient(to bottom, #195091, #195091) left bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat,
    linear-gradient(to left, #195091, #195091) right bottom no-repeat;
    background-size: 1px 10px, 10px 1px, 2px 10px, 10px 1px;
  }
</style>
