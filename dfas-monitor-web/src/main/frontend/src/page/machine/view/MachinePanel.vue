<template>
    <div class="table-contanier">

        <Machine :dataList="machinePanelDataList" :animations="['0','1','2','3']" :isTool="true" width="60px" :isShowTitle="true"></Machine>

    </div>
</template>

<script lang="ts">
import Vue from "vue";
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
//   import Component from "vue-class-component";
import { debuglog } from "util";
import { UserReqVO, UserClass } from "../vo/MachineVO";
import PageVO from "../../common/vo/PageVO";
import MachineService from "../service/MachineService";
import { WinResponseData } from "../../common/vo/BaseVO";
import Machine from "../../../components2/vue/monitor/Machine.vue";
import MachinePanelController from "../controller/MachinePanelController";
import AxiosFun from "../../../api/AxiosFun";
import {MachinePanelVO} from "../vo/MachinePanelVO";

@Component({ components: {Machine} })
export default class MachinePanel extends MachinePanelController {

}
</script>
<style lang="scss" scoped>
@import "../../../assets/style/element.scss";
.table-contanier >>> {
    margin: 10px 0 0;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    overflow-y: scroll;
    overflow-x: hidden;
    padding-bottom: 30px;
    .monitor-container
        .img-container.img-container-machine
        .icon[data-v-7e424f22] {
        position: absolute;
        width: 30px;
        height: 30px;
        top: 62%;
        left: 50%;
        margin-left: -15px;
    }

    .monitor-container .monitor-item-machine {
        padding: 17px 26px;
    }

    .monitor-item-machine:nth-of-type(17n) .tool-container {
        left: -200px;
        &::after {
            position: absolute;
            top: 20px;
            left: 251px;
            width: 0;
            height: 0;
            border-right: 14px solid #0e2e5d;
            border-bottom: 30px solid transparent;
            border-top: 20px solid transparent;
            -webkit-transform: rotate(360deg);
            transform: rotate(-42deg);
        }
    }
    .monitor-item-machine:nth-of-type(17n-1) .tool-container {
        left: -200px;
        &::after {
            position: absolute;
            top: 20px;
            left: 251px;
            width: 0;
            height: 0;
            border-right: 14px solid #0e2e5d;
            border-bottom: 30px solid transparent;
            border-top: 20px solid transparent;
            -webkit-transform: rotate(360deg);
            transform: rotate(-42deg);
        }
    }
    .monitor-container .tool-container {
        top: 0;
    }
}
</style>
