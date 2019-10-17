<template>
  <div class="table-contanier">
    <dl class="searchList">
      <dl>
        <dt></dt>
      </dl>
      <Monitor :dataList="machinePanelDataList" type="machine" :animations="['2','1']" :isTool="true" :maxRow="2" minWidth="100%"></Monitor>
    </dl>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import { Component, Prop, Emit } from "vue-property-decorator";
  import BaseController from "../../common/controller/BaseController";
  import Component from "vue-class-component";
  import { debuglog } from "util";
  import { UserReqVO, UserClass } from "../vo/MachineVO";
  import PageVO from "../../common/vo/PageVO";
  import Monitor from "../../../components2/vue/Monitor.vue"
  import machineService from "../service/machineService";
  import { WinResponseData } from "../../common/vo/BaseVO";

  import AxiosFun from "../../../api/AxiosFun";

  @Component({components:{Monitor}})
  export default class MachineDetailPanel extends BaseController {
    machinePanelDataList = [] ;
    ws: WebSocket;

    async mounted() {
      this.machinePanelData();
      let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineState";
      this.establishConnection(requestUrl);
    }

    establishConnection(requestUrl) {
      this.handleClose();
      this.ws = new WebSocket(requestUrl);
      let _ = this;
      this.ws.onopen = function (e) {
        _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a microServiceState WebSocket!"}));
      };
      this.ws.onmessage = e => this.handleWebSocketData(e);
      this.ws.onclose = () => this.handleClose();
    }

    handleWebSocketData(e) {
      let object = JSON.parse(e.data);
      this.machinePanelDataList = object;
    }

    handleClose() {
      if (this.ws != null) {
        this.ws.close();
      }
    }

    /** 机器明细数据 */
    machinePanelData() {
      console.log("machinePanelData")
      machineService
        .machinePanelData()
        .then((response: WinResponseData) => {
          console.log(response.data);
          //let object = JSON.parse(response.data);
          this.machinePanelDataList = response.data;
        })
        .catch((ex: any) => {

        });
    }

  }

</script>
<style lang="scss" scoped>
  @import "../../../assets/style/element.scss";
  // float: right;
  // width: 88%;
  .serch-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 10px;
    .input {
      @include sinput();
    }
    .showTypeGroup {
      margin-right: 30px;
      .showType {
        @include showType();
      }
      .showType2 {
        @include showType($bg: #2f4cbd);
      }
    }
  }
  .searchList {
    width: 100%;
    padding: 0 0 10px 0;
    box-sizing: border-box;
    dt {
      float: left;
      font-size: 14px;
      color: #fff;
      width: 252px;
      background: transparent;
      text-align: center;
      height: 41px;
      line-height: 41px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    dd {
      height: 41px;
      line-height: 41px;
      margin-left: 252px;
      background: #2b3451;

      .but {
        display: inline-block;
        border: none;
        padding: 8px 16px;
        background: transparent;
        font-size: 14px;
        color: #adb5bb;

        cursor: pointer;
        &:hover {
          color: #ff900d;
        }
      }
      .cop {
        display: inline-block;
        vertical-align: middle;
        color: #fff;
        font-size: 14px;
        padding-left: 24px;
        // border-left: 1px solid #fff;
        &:first-of-type {
          border-left: 1px solid transparent;
        }
      }
    }
  }
  .type1 {
    display: inline-block;
    vertical-align: middle;
    color: #ff900d;
    margin-right: 6px;
  }
  .type2 {
    display: inline-block;
    vertical-align: middle;
    color: #4d65c3;
    margin-right: 6px;
  }
  .type3 {
    display: inline-block;
    vertical-align: middle;
    color: #33cc33;
    margin-right: 6px;
  }

  .type4 {
    display: inline-block;
    vertical-align: middle;

    color: #ff6f6f;
    margin-right: 6px;
  }
  .icon {
    padding-right: 8px;
    color: #f58a0d;
  }
  .hr {
    height: 13px;
    width: 1px;
    background: #707070;
    display: inline-block;
    position: relative;
    top: 4px;
    margin-left: 9px;
  }
  .type-title {
    color: #fff;
    display: inline-block;
    vertical-align: middle;
    font-size: 14px;
    padding: 0 16px;
    position: relative;
    top: 1px;
    color: #999;
  }
  .table-contanier {
    max-height: 700px;
    // background: red;
  }
  span {
    display: inline-block;
    vertical-align: middle;
    margin-right: 6px;
    cursor: pointer;
  }
  .operation {
    display: inline-block;
    vertical-align: middle;
    margin-right: 10px;
    &:hover {
      color: #fff;
    }
  }
  .icon {
    display: inline-block;
    margin-right: 8px;
  }
  .icon1 {
    color: #f58a0d;
  }
  .icon2 {
    color: #4d65c3;
  }
  .icon3 {
    color: #33cc33;
  }

  .icon4 {
    color: #ff4c4c;
  }
</style>
