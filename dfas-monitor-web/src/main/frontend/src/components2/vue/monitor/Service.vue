<template>
    <div :class="{'isheght100':row&&col}">
        <BaseMonitor v-bind="{...$props}" @clickItem="clickItem" type="service"></BaseMonitor>
    </div>
</template>
<script type="text/babel">
import BaseMonitor from "./BaseMonitor";
import baseMinxs from "./minMonitor";
export default {
    mixins: [baseMinxs],
    components: { BaseMonitor },
    methods: {
        clickItem({ ParentItem, item }) {
            this.$emit("clickItem", {
                ParentItem: ParentItem,
                item: item
            });
        }
    }
};
</script>

<style scoped lang="scss">
// @import "../../style.css/baseMonitor.scss";
.isheght100 {
    height: 100%;
}
</style>
