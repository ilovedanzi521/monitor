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
import Machine from "../../../components2/vue/monitor/Machine.vue";
import MachineService from "../service/MachineService";
import { WinResponseData } from "../../common/vo/BaseVO";

import AxiosFun from "../../../api/AxiosFun";

@Component({ components: { Machine } })
export default class MachinePanel extends BaseController {
    machinePanelDataList = [
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "2",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "1",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "1",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2",
            state: "0",
            ipAddress: "192.168.0.0111111111",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21112",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211212",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21121332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "21123321332",
            state: "1",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "211312",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "2",
            ipAddress: "192.168.0.0",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },

        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        },
        {
            id: "2113412",
            state: "3",
            ipAddress: "192.168.0.1",
            cupNum: 2,
            memorySize: "128G",
            diskSize: "32T",
            cpuPer: "70%",
            memoryPer: "30%",
            diskPer: "60%"
        }
    ];
    ws: WebSocket;

    async mounted() {
        this.machinePanelData();
        let requestUrl =
            AxiosFun.monitorCenterWebsocketBaseUrl + "/home/machineState";
        this.establishConnection(requestUrl);
    }

    establishConnection(requestUrl) {
        this.handleClose();
        this.ws = new WebSocket(requestUrl);
        let _ = this;
        this.ws.onopen = function(e) {
            _.ws.send(
                JSON.stringify({
                    flag: requestUrl,
                    data: "i am a microServiceState WebSocket!"
                })
            );
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
        console.log("machinePanelData");
        MachineService.machinePanelData()
            .then((response: WinResponseData) => {
                console.log(response.data);
                 let object = JSON.parse(response.data);
                 this.machinePanelDataList = response.data;
            })
            .catch((ex: any) => {});
    }
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
