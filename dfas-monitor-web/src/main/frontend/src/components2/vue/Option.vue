<template>
    <li @mouseenter="hoverItem" @click.stop="selectOptionClick" class="el-select-dropdown__item" v-show="visible" :class="{
        'selected': itemSelected,
        'is-disabled': disabled || groupDisabled || limitReached,
        'hover': hover
      }">
        <win-tooltip ref="tooltip" :enterable="false" effect="light" placement="top" :content="textContent" :disabled="!(tooltip&&state)">
            <div>
                <slot>
                    <span> {{currentLabel }}</span>
                </slot>
            </div>
        </win-tooltip>
    </li>
</template>

<script type="text/babel">
import Emitter from "element-ui/src/mixins/emitter";
import { WinTooltip } from "../win-tooltip";
import { getValueByPath, escapeRegexpString } from "element-ui/src/utils/util";

export default {
    mixins: [Emitter],

    name: "WinOption",

    componentName: "WinOption",

    inject: ["select"],

    props: {
        value: {
            required: true
        },
        label: [String, Number],
        created: Boolean,
        disabled: {
            type: Boolean,
            default: false
        },
        tooltip: {
            type: Boolean,
            default: true
        },
        search: [String, Number]
    },

    components: {
        WinTooltip
    },

    data() {
        return {
            index: -1,
            groupDisabled: false,
            visible: true,
            hitState: false,
            hover: false,
            state: false,
            textContent: ""
        };
    },

    computed: {
        isObject() {
            return (
                Object.prototype.toString.call(this.value).toLowerCase() ===
                "[object object]"
            );
        },

        currentLabel() {
            return this.label || (this.isObject ? "" : this.value);
        },

        currentValue() {
            return this.value || this.label || "";
        },

        itemSelected() {
            if (!this.select.multiple) {
                return this.isEqual(this.value, this.select.value);
            } else {
                return this.contains(this.select.value, this.value);
            }
        },

        limitReached() {
            if (this.select.multiple) {
                return (
                    !this.itemSelected &&
                    (this.select.value || []).length >=
                        this.select.multipleLimit &&
                    this.select.multipleLimit > 0
                );
            } else {
                return false;
            }
        }
    },

    watch: {
        currentLabel() {
            if (!this.created && !this.select.remote)
                this.dispatch("ElSelect", "setSelected");
        },
        value(val, oldVal) {
            const { remote, valueKey } = this.select;
            if (!this.created && !remote) {
                if (
                    valueKey &&
                    typeof val === "object" &&
                    typeof oldVal === "object" &&
                    val[valueKey] === oldVal[valueKey]
                ) {
                    return;
                }
                this.dispatch("ElSelect", "setSelected");
            }
        }
    },

    methods: {
        isEqual(a, b) {
            if (!this.isObject) {
                return a === b;
            } else {
                const valueKey = this.select.valueKey;
                return (
                    getValueByPath(a, valueKey) === getValueByPath(b, valueKey)
                );
            }
        },

        contains(arr = [], target) {
            if (!this.isObject) {
                return arr && arr.indexOf(target) > -1;
            } else {
                const valueKey = this.select.valueKey;
                return (
                    arr &&
                    arr.some(item => {
                        return (
                            getValueByPath(item, valueKey) ===
                            getValueByPath(target, valueKey)
                        );
                    })
                );
            }
        },

        handleGroupDisabled(val) {
            this.groupDisabled = val;
        },

        hoverItem() {
            if (!this.disabled && !this.groupDisabled) {
                this.select.hoverIndex = this.select.options.indexOf(this);
            }
        },

        selectOptionClick() {
            if (this.disabled !== true && this.groupDisabled !== true) {
                this.dispatch("ElSelect", "handleOptionClick", [this, true]);
            }
        },

        queryChange(query) {
            this.visible =
                new RegExp(escapeRegexpString(query), "i").test(
                    this.currentLabel
                ) || this.created;
            if (!this.visible) {
                this.select.filteredOptionsCount--;
            }
        },
        handleOptionsVisible() {
            let $el = this.$children[0].$el;
            this.$nextTick().then(() => {
                let state = $el.scrollWidth > $el.clientWidth;
                if (this.state !== state) this.state = state;
                if (state) {
                    this.textContent = $el.textContent;
                }
            });
        }
    },

    created() {
        this.select.options.push(this);
        this.select.cachedOptions.push(this);
        this.select.optionsCount++;
        this.select.filteredOptionsCount++;

        this.$on("queryChange", this.queryChange);
        this.$on("handleGroupDisabled", this.handleGroupDisabled);
        this.$on("optionsVisible", this.handleOptionsVisible);
    },

    mounted() {
        this.$nextTick(() => {
            let $input = this.select.$children[0].$el;
            let selectWidth = getComputedStyle($input).width;
            let style = this.$refs.tooltip.$refs.popper.style;
            style.maxWidth = selectWidth;
            style.boxSizing = "border-box";
        });
    },

    beforeDestroy() {
        let index = this.select.cachedOptions.indexOf(this);
        if (index > -1) {
            this.select.cachedOptions.splice(index, 1);
        }
        this.select.onOptionDestroy(this.select.options.indexOf(this));
    }
};
</script>
