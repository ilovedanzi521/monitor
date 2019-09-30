<template>
  <div>
    <div class="homeLeft">
    <div class="platformOverview">
      <div style="width:72px;
           height:24px;
           font-size:18px;
           font-family:Microsoft YaHei;
           font-weight:400;
           line-height:24px;
           color:rgba(255,255,255,1);
           opacity:1;">平台概况
      </div>
    </div>
    <div class="qps">
      <div style="width:90px;
height:24px;
font-size:18px;
font-family:Microsoft YaHei;
font-weight:400;
line-height:24px;
color:rgba(255,255,255,1);
opacity:1;">Qps趋势图
      </div>
    </div>
    <div class="machineStatus">
      <div style="width:72px;
height:24px;
font-size:18px;
font-family:Microsoft YaHei;
font-weight:400;
line-height:24px;
color:rgba(255,255,255,1);
opacity:1;">机器状态</div>
    </div>
    </div>
    <div class="homeRight">
      <div class="monitorTitle">
          <div style="width:309px;
height:39px;
background:linear-gradient(180deg,rgba(176,227,254,1) 0%,rgba(26,167,241,1) 100%);
opacity:1;">自动化运维监控平台</div>
      </div>
    <div class="microServiceStatus">
      <div style="width:90px;
height:24px;
font-size:18px;
font-family:Microsoft YaHei;
font-weight:400;
line-height:24px;
color:rgba(255,255,255,1);
opacity:1;">微服务状态</div>
    </div>
      <div>
        <div class="machineList">
          <div style="width:72px;
height:24px;
font-size:18px;
font-family:Microsoft YaHei;
font-weight:400;
line-height:24px;
color:rgba(255,255,255,1);
opacity:1;">机器列表</div>
        </div>
        <div class="exception">
          <div style="width:36px;
height:24px;
font-size:18px;
font-family:Microsoft YaHei;
font-weight:400;
line-height:24px;
color:rgba(255,255,255,1);
opacity:1;">异常</div>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts">
  import HomeController from '../controller/HomeController';
  import Component from "vue-class-component";

  @Component({})
  export default class Home extends HomeController {
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .homeLeft {
    float:left;
    margin-left:20px
  }
  .homeRight {
    float:left;
    margin-left:30px;
  }
  .platformOverview {
    width: 538px;
    height: 194px;
    background: rgba(9, 27, 65, 1);
    border: 1px solid rgba(11, 37, 77, 1);
    opacity: 1;
  }

  .qps{
    width:538px;
    height:253px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }

  .machineStatus {
    width:538px;
    height:553px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }

  .monitorTitle {
    width:1310px;
    height:50px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }

  .microServiceStatus {
    width:1310px;
    height:600px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }

  .machineList{
    float:left;
    width:766px;
    height:237px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }

  .exception{
    float:left;
    margin-left:30px;
    width:512px;
    height:237px;
    background:rgba(9,27,65,1);
    border:1px solid rgba(11,37,77,1);
    opacity:1;
  }
</style>
