<template>
    <win-select ref="select" v-bind="{...$attrs,...$props}" v-on="$listeners">
        <template #default>
            <win-option ref="option" class="option" v-for="item in dicDataSelect" :search="item.dicCode" :key="item.dicCode" :label="item.dicExplain" :value="item.dicCode" v-if="!$slots.default">
                <span style="float: left">{{ item.dicCode }}</span>
                <span>{{ "&nbsp;"+item.dicExplain }}</span>
            </win-option>
            <slot name="default"></slot>
        </template>
        <template #prefix>
            <slot name="prefix"></slot>
        </template>
        <template #empty>
            <slot name="empty"></slot>
        </template>
    </win-select>
</template>
<script lang="ts">
import Vue from "vue";
import { WinSelect, WinOption } from "../win-select";
import { Component, Prop, Emit, Model } from "vue-property-decorator";
import { DicReqVO, DicRepVO } from "../../common/vo/DicVO";
import DicServiceService from "../../common/service/DicService";

@Component({
    components: {
        WinSelect,
        WinOption
    },
    props: {
        collapseTags: {
            type: Boolean,
            default: true
        },
        filterable: {
            type: Boolean,
            default: true
        }
    }
})
export default class WinSelectDic extends WinSelect {
    @Prop(String)
    private parentDicCode: string;

    // 字典数据
    private dicDataSelect: DicRepVO[] = [];

    private async mounted() {
        if (this.parentDicCode) {
            const vo = new DicReqVO();
            vo.parentDicCodeList = [this.parentDicCode];
            const result = await DicServiceService.dicMultipleAllSubList(vo);
            this.dicDataSelect = result.data[this.parentDicCode];
        }
    }

    public focus() {
        this.$refs.select.focus();
    }
    public blur() {
        this.$refs.select.blur();
    }
}
</script>
<style scoped lang="scss">
</style>