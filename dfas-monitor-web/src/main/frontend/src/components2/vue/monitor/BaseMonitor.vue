<template>
    <div class="isheight100_contanier">
        <ul class="monitor-container" ref="monitor">
            <li class="monitor-wrapper" v-for="(monitor,index) in getFormatData" :key="index">
                <div v-for="aitem in monitor" :key="aitem.id" :class="['monitor-item-js',`monitor-item-${type}`]" @click="clickItem(monitor,aitem)">
                    <div :class="['img-container',`img-container-${type}`] " :style="width?{'width':width,'height':width}:''">
                        <span :class="['icon',`icon-${aitem.state}`,{'animation3':animations.includes(aitem.state)}]" />
                    </div>
                    <p class="monitor-title" v-if="isShowTitle">{{aitem.ipAddress||aitem.microServiceName}}</p>
                    <div class="tool-container" v-if="isTool">
                        <p v-if="aitem.ipAddress||aitem.microServiceName">
                            <label>{{aitem.ipAddress?"ip地址:":"微服务名称:"}}</label>
                            <span>{{aitem.ipAddress||aitem.microServiceName}}</span>
                        </p>
                        <p v-if="aitem.cpuNum||aitem.warn">
                            <label>{{aitem.cpuNum?"cpu个数：:":"日志告警数:"}}</label>
                            <span>{{aitem.cpuNum||aitem.warn}}</span>
                        </p>
                        <p v-if="aitem.memorySize||aitem.error">
                            <label>{{aitem.memorySize?"内存大小：":"日志错误数:"}}</label>
                            <span>{{aitem.memorySize||aitem.error}}</span>
                        </p>
                        <p v-if="aitem.diskSize">
                            <label>磁盘总大小：</label>
                            <span>{{aitem.diskSize}}</span>
                        </p>
                        <p v-if="aitem.cpuPer">
                            <label>CPU使用率：</label>
                            <span>{{aitem.cpuPer}}</span>
                        </p>
                        <p v-if="aitem.memoryPer">
                            <label>内存使用率：</label>
                            <span>{{aitem.memoryPer}}</span>
                        </p>
                        <p v-if="aitem.diskPer">
                            <label>磁盘使用率：</label>
                            <span>{{aitem.diskPer}}</span>
                        </p>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>
<script type="text/babel">
import baseMinxs from "./minMonitor";
export default {
    mixins: [baseMinxs]
};
</script>

<style scoped lang="scss">
@import "../../style.css/baseMonitor.scss";
.isheight100_contanier {
    height: 100%;
}
</style>
