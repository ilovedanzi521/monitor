<template>
    <div class="microServiceStatus-container">
        <Service :dataList="sa" :animations="['1','2']" @clickItem="operation" width="103px"></Service>
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
        }
    ];
}
</script>

<style lang="scss" scoped>
.microServiceStatus-container {
    margin: 10px;
    width: 100%;
    height: 95%;
}
</style>
