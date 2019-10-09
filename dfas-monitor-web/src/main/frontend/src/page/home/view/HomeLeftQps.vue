<template>
  <div class="qpsBlock">
    <div class="qpsTitle"><img src="../image/groupMin.png">&nbsp;&nbsp;Qps趋势图
    </div>
    <div id="chartLineBox"> </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import {Component} from "vue-property-decorator";
  import echarts from "echarts";

  @Component({})
  export default class HomeLeftQps extends Vue {


    name:'chartLineBox';

    chartLine;

    private mounted() {
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));

      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data:['QPS']
        },
        color: ['#8AE09F', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data: ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00',],
          name: '',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: '#FA6F53',
            }
          }
        },
        yAxis: {
          name: '',
          nameTextStyle: {
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: '#FA6F53',
            }
          },
          type: 'value'
        },
        series: [
          {
            name: 'Qps',
            data:  [220, 232, 201, 234, 290, 230, 220],
            type: 'line',               // 类型为折线图
            lineStyle: {                // 线条样式 => 必须使用normal属性
              normal: {
                color: '#8AE09F',
              }
            },
          }
          /*,
          {
            name: '学生成绩',
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'line',
            lineStyle: {
              normal: {
                color: '#FA6F53',
              }
            },
          }*/
        ]
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
    height: 100%;
    font-size: 18px;
    font-family: Microsoft YaHei;
    color: rgba(255, 255, 255, 1);
    opacity: 1;
    box-sizing: border-box;
    display: inline-block;
  }
</style>
