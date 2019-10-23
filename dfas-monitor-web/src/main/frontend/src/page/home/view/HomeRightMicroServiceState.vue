<template>
    <div class="microServiceStateBlock">
        <div class="rect">
            <div class="microServiceStateTitle">
                <div class="table-tr-title">
                    <div class="titleArea">
                        <img src="../image/groupMin.png">&nbsp;&nbsp;微服务状态
                    </div>
                    <div class="tipArea">
                        <img src="../image/tip.png">
                    </div>
                    <div class="moreArea">
                        <a href="/#/microServicePanel" target="_blank"><img src="../image/more.png"></a>
                    </div>
                </div>
            </div>
            <div class="microServiceState-container">
                <Service :dataList="microServiceStateList" :animations="['1','2']" :col="10" :row="4" @clickItem="operation" width="98px"></Service>
            </div>
            <MicroServiceDetailDialog :fromFatherMsg="cardNumber" @bindSend="toFatherMsg" v-if="detailDialogVisible">
            </MicroServiceDetailDialog>
        </div>
    </div>
</template>

<script lang="ts">
import HomeMicroServiceStateController from "../controller/HomeMicroServiceStateController";

export default class HomeRightMicroServiceState extends HomeMicroServiceStateController {
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
@import "../style/home.scss";

.microServiceState-container {
    margin: 10px;
    width: 98.5%;
    height: 90.5%;
}

.table-tr-title {
    display: table-row;
    vertical-align: middle;
}

.td {
    display: table-cell;
    vertical-align: middle;
}

.titleArea {
    @extend .td;
    text-align: left;
}

.tipArea {
    @extend .td;
    text-align: right;
}

.moreArea {
    @extend .td;
    text-align: right;
    padding-right: 30px;
    color: rgba(139, 162, 191, 1);
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
    background-size: 3px 20px, 20px 3px, 3px 20px, 20px 3px;
}
</style>
