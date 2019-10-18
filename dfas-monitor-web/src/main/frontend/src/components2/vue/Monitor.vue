<template>
    <div>
        <ul class="monitor-container" :style="{'width':minWidth}" ref="monitor">
            <li class="monitor-wrapper" v-for="(monitor,index) in getFormatData" :key="index">
                <div v-for="aitem in monitor" :key="aitem.id" :class="['monitor-item-js',`monitor-item-${type}`]" @click="clickItem(monitor,aitem)" :style="{'width':minWidth}">
                    <div :class="['img-container',`img-container-${type}`] ">
                        <img src="../style.css/img/as.svg" v-if="type=='machine'" :style="{'width':parseInt(minWidth)-20+'px'}" />
                        <img src="../style.css/img/bs.svg" v-else :style="{'width':parseInt(minWidth)-30+'px'}" />

                        <img src="../style.css/img/0.png" :class="[`icon-${type}`,{'animation3':animations.includes(aitem.state)}]" v-if="aitem.state==0" />
                        <img src="../style.css/img/1.png" :class="[`icon-${type}`,{'animation3':animations.includes(aitem.state)}]" v-if="aitem.state==1" />
                        <img src="../style.css/img/2.png" :class="[`icon-${type}`,{'animation3':animations.includes(aitem.state)}]" v-if="aitem.state==2" />
                        <img src="../style.css/img/3.png" :class="[`icon-${type}`,{'animation3':animations.includes(aitem.state)}]" v-if="aitem.state==3" />

                        <p class="monitor-title" :style="{'width':parseInt(minWidth)-20+'px'}" v-if="isShowTitel">{{aitem.ipAddress||aitem.microServiceName}}</p>
                    </div>

                    <!-- <div class="tool-container" v-if="isTool">
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
                    </div> -->
                </div>
            </li>
        </ul>
    </div>
</template>
<script type="text/babel">
export default {
    data() {
        return {
            monitorList: []
        };
    },
    props: {
        minWidth: {
            type: String
        },
        /**渲染数据 0：档机，1：危险，2：警告，3：正常 */
        dataList: {
            type: Array,
            default() {
                return [];
            }
        },
        /**渲染数据类型，machine：机器，service：服务 */
        isShowTitel: {
            type: Boolean,
            default: false
        },
        type: {
            type: String,
            default: "service", //service,machine
            validator: function(value) {
                return ["machine", "service"].indexOf(value) !== -1;
            }
        },
        /**是否展示提示面板 默认true,展示面板，fasle不展示面板*/
        isTool: {
            type: Boolean,
            default: true
        },
        /**那个状态需要用动画，["1","2"]代表危险的与警告的需要动画*/
        animations: {
            type: Array,
            default() {
                return ["2", "1"];
            },
            validator: function(value) {
                let isContainArray = false;
                value.forEach(item => {
                    if (["1", "2", "3", "4"].indexOf(item) !== -1) {
                        isContainArray = true;
                    } else {
                        isContainArray = false;
                    }
                });
                return isContainArray;
            }
        },
        /**最多显示多少行*/
        row: {
            type: Number,
            default: 0
        },
        /**最多显示多少列*/
        col: {
            type: Number,
            default: 0
        }
    },
    computed: {
        getFormatData() {
            let monitorList = this.dataList.reduce((monitor, item) => {
                if (!monitor[item.state]) {
                    monitor[item.state] = [];
                    monitor[item.state].push(item);
                } else {
                    monitor[item.state].push(item);
                }

                return monitor;
            }, {});

            if (this.row && this.col) {
                monitorList = this.renderRow(monitorList);
            }

            return monitorList;
        }
    },
    methods: {
        clickItem(monitor, item) {
            this.$emit("clickItem", { ParentItem: monitor, item });
        },
        /****渲染多少行多少列 */
        renderRow(mb) {
            let array = Object.keys(mb).map(item => {
                return mb[item];
            });
            if (this.row && this.col) {
                let computedNum = 0;
                let rows = [];
                let rememberState;
                for (let i = 0; i < array.length; i++) {
                    if (computedNum < this.row) {
                        rememberState = i;
                        let row;
                        if (array[i].length % this.col === 0) {
                            row = array[i].length / this.col;
                        } else {
                            row = Math.floor(array[i].length / this.col) + 1;
                        }
                        computedNum += row;
                        rows.push(row);
                    }
                }
                // console.log(rows);
                // console.log(rememberState);
                let currentIndex = 0;
                let maxItemNum = 0;
                let arrayItem = [];
                let maxElememt = this.row * this.col;
                let formatArray = array
                    .map((item, index) => {
                        if (index <= rememberState) {
                            let formatRendreItem = item
                                .map(element => {
                                    currentIndex++;
                                    if (currentIndex <= maxElememt) {
                                        // return {...element,isRendre:true}
                                        return { ...element };
                                    } else {
                                        return "";
                                    }
                                })
                                .filter(item => item.id);
                            maxItemNum += rows[index] * this.col;
                            arrayItem.push(rows[index] * this.col);
                            return formatRendreItem;
                        }

                        // maxItemNum += row[index] * this.col;
                        // console.log(maxItemNum);
                    })
                    .filter(item => Array.isArray(item));

                if (maxItemNum > maxElememt) {
                    let diff =
                        maxElememt -
                        (maxItemNum - arrayItem[arrayItem.length - 1]);
                    formatArray[formatArray.length - 1].splice(diff);
                }

                return formatArray;
            }
        },
        getUiWidth() {
            let monitorUi = this.$refs.monitor;
            let monitorItem = monitorUi.querySelectorAll(".monitor-item-js")[0];
            monitorUi.style.width = monitorItem.offsetWidth * this.col + "px";
        }
    },
    mounted() {
        if (this.row && this.col && this.dataList.length) {
            this.getUiWidth();
            // let MaxMonitorItemHeight = (monitorUi.style.height =
            //     monitorItem.offsetHeight * this.maxRow + 20 + "px");
        }
    }
};
</script>

<style scoped lang="scss">
@import "../style.css/monitor.scss";
</style>
