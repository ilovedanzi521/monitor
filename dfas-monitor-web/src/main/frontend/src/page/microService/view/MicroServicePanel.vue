<template>
    <div class="microServiceStatus-container">
        <Service :dataList="sa" :animations="['1','2']" @clickItem="operation" width="99px" :isShowTitle="true"></Service>
        <MicroServiceDetailDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="detailDialogVisible"></MicroServiceDetailDialog>
    </div>
</template>

<script lang="ts">
import MicsoServicePanelController from "../controller/MicsoServicePanelController";
import { Component } from "vue-property-decorator";

@Component({})
export default class MicroServicePanel extends MicsoServicePanelController {
    sa = [
        {
            id: "1",
            state: "0",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
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
            id: "1",
            state: "0",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },

        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "3",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "xxxxx",
            microServiceAlias: "别名",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "2",
            microServiceName: "xxxxxxx",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "2",
            microServiceName: "xxxxx",
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "xxxxxxx",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "12",
            state: "1",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "1",
            state: "0",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        },
        {
            id: "12",
            state: "1",
            microServiceName: "192.168.0.0111111111",
            cupNum: 2,
            warn: "128G",
            error: "32T"
        }
    ];
}
</script>

<style lang="scss" scoped>
.microServiceStatus-container >>> {
    margin: 10px 0 0;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    overflow-y: scroll;
    overflow-x: hidden;
    padding-bottom: 30px;
    .monitor-container {
        .monitor-item-service {
            padding: 7px 14px !important;
        }
    }
}
</style>
