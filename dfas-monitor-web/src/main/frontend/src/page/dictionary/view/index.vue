<template>
    <div class="container" v-testName="{'DICTIONARY':'dictionary'}">
        <div class="content-fr">
            <div class="search-contanier">
                <win-form>
                    <win-input type="text" placeholder="请输入查询条件" maxlength="64" v-model="dicReqVO.commonParam" @input="search()"></win-input>
                </win-form>
            </div>
            <div class="content-fr-warp">
                <win-table :data="dicList" ref="dicTable" stripe :showSelection="false" height="612" :index-width="10" :style="{'width': '100%'}" @cell-click="handleDicRow">
                    <win-table-column prop="dicCode" :min-width="120" label="字典项编码" sortable></win-table-column>
                    <win-table-column prop="dicExplain" :min-width="180" label="字典项说明" sortable></win-table-column>
                </win-table>
                <div class="paginatio-contanier">
                    <win-pagination name="dic" :isEasy="true" v-bind:pageInfo="dicPageVO" @pageInfoChange="dicPageQuery"></win-pagination>
                </div>
            </div>
        </div>

        <div class="content-fl">
            <div class="search-contanier">
            </div>
            <div class="content-fl-warp">
                <win-table :data="dicSubList" :highlight-current-row="true" :showSelection="false" height="600" :index-width="20" :style="{'width': '100%'}">
                    <win-table-column prop="dicCode" :min-width="80" label="字典子项编码" sortable></win-table-column>
                    <win-table-column prop="dicExplain" :min-width="100" label="字典子项说明"></win-table-column>
                    <win-table-column prop="param1" :min-width="100" label="字典子项参数1"></win-table-column>
                    <win-table-column prop="param2" :min-width="100" label="字典子项参数2"></win-table-column>
                    <win-table-column prop="param3" :min-width="100" label="字典子项参数3"></win-table-column>
                    <win-table-column prop="paramExplain" :min-width="150" label="字典子项参数说明"></win-table-column>
                </win-table>
                <div class="paginatio-contanier">
                    <win-pagination name="subDic" v-bind:pageInfo="dicSubPageVO" @pageInfoChange="dicSubPageQuery"></win-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import DicController from "../controller/DicController";

@Component({})
export default class Index extends DicController {}
</script>

<style lang="scss" scoped>
.container {
    padding: 16px 18px;
    overflow: hidden;
    .content-fr {
        position: relative;
        box-sizing: border-box;
        float: left;
        width: 27%;
        box-sizing: border-box;
        .content-fr-warp {
            background: #0b1431ff;
            height: 720px;
            overflow-x: hidden;
        }
    }
    .content-fl {
        position: relative;
        width: 72%;
        box-sizing: border-box;
        top: 28px;
        float: right;
        .content-fl-warp {
            border: 1px solid #f58b0eff;
            padding: 2px;
            background: #38415bff;
            box-sizing: border-box;
        }
    }
    .page-contanier {
        position: absolute;
        width: 100%;
        right: 10px;
    }

    .search-contanier {
        margin-bottom: 14px;
        overflow: hidden;
        .add-botton {
            display: inline-block;
            width: 132px;
            height: 36px;
            font-size: 14px;
            line-height: 36px;
            border-radius: 20px;
            background: #2b3551ff;
            color: #fff;
            text-align: center;
        }
    }
    .paginatio-contanier {
        margin-top: 5px;
    }
}
</style>
