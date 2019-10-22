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
            id: "12",
            state: "1",
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
            id: "12",
            state: "1",
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
            id: "12",
            state: "1",
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
            id: "12",
            state: "1",
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
    .monitor-container .tool-container {
        top: 0;
    }
    // .monitor-item-service:nth-of-type(13n) .tool-container {
    //     left: -200px;
    //     &::after {
    //         position: absolute;
    //         top: 20px;
    //         left: 251px;
    //         width: 0;
    //         height: 0;
    //         border-right: 14px solid #0e2e5d;
    //         border-bottom: 30px solid transparent;
    //         border-top: 20px solid transparent;
    //         -webkit-transform: rotate(360deg);
    //         transform: rotate(-42deg);
    //     }
    // }

    .monitor-item-service:nth-of-type(15n) .tool-container {
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
    .monitor-item-service:nth-of-type(15n -1) .tool-container {
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
}
</style>
