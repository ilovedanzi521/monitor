<template>
  <win-tooltip ref="tooltip" :placement="placement" :disabled="true" effect="light" :open-delay="100">
    <el-select ref="select" @clear="handleClear" @visible-change="handelVisibleChange" :class="tooltip?'input-overflow':''" :filter-method="filterMethod||handleFilterMethod" v-bind="{...$attrs,...$props}" v-on="$listeners">
      <template #default>
        <slot name="default"></slot>
      </template>
      <template #prefix>
        <slot name="prefix"></slot>
      </template>
      <template #empty>
        <slot name="empty"></slot>
      </template>
    </el-select>
  </win-tooltip>
</template>
<script lang="ts">
import Vue from "vue";
import { Select } from "element-ui";
import { WinTooltip } from "../win-tooltip";
import { Component, Prop, Emit, Model, Watch } from "vue-property-decorator";
import mixModifyEvent from "../mixins/mixModifyEvent";
import Pinyin from "pinyin";
import Emitter from "element-ui/src/mixins/emitter";
@Component({
  components: {
    ElSelect: Select,
    WinTooltip
  },
  props: {
    ...Select.props,
    collapseTags: {
      type: Boolean,
      default: true
    },
    filterable: {
      type: Boolean,
      default: true
    },
    clearable: {
      type: Boolean,
      default: true
    }
  },
  mixins: [Emitter, mixModifyEvent("visibleChange", "clear")]
})
export default class WinSelect extends Vue {
  private pinyins: Array<any> = [];

  private options: Array<any> = [];

  private cachedOptions: Array<any> = [];

  private isTextOverflow: boolean = false;

  @Prop(Boolean)
  private tooltip: boolean;
  @Prop({ type: String, default: "top" })
  private placement: string;

  public focus() {
    this.$refs.select.focus();
  }
  public blur() {
    this.$refs.select.blur();
  }

  private handleClear() {
    this.blur();
    this.$emit("clear");
  }

  private handelVisibleChange(v) {
    if (v) {
      this.handleFilterMethod();
      this.broadcast("WinOption", "optionsVisible");
    }
    this.$emit("visibleChange", v);
  }

  private handleTextOverflow() {
    let $input = this.$el.getElementsByClassName("el-input__inner")[0];
    let isTextOverflow = $input.scrollWidth > $input.clientWidth;
    if (this.isTextOverflow !== isTextOverflow) {
      this.isTextOverflow = isTextOverflow;
      this.$refs.tooltip.updatePopper();
    }
  }

  private chineseCharsToPinyins(values) {
    let pinyins = values.map(v =>
      Pinyin(v, {
        style: Pinyin.STYLE_FIRST_LETTER
      }).join("")
    );
    this.pinyins = pinyins;
  }
  private getOptionsLabel(options) {
    let labels = (options || []).map(item => item.label);
    return labels;
  }

  private mounted() {
    this.cachedOptions = this.$refs.select.cachedOptions;
    this.options = this.$refs.select.options;
  }
  private handleFilterMethod(v) {
    let options = this.cachedOptions || [];
    let filteredOptionsCount = options.length;
    if (v) {
      this.$refs.select.options = options.map((option, index) => {
        let search = option.search;
        let visible =
          (search && search.toLowerCase().includes(v.toLowerCase())) ||
          this.pinyins[index].toLowerCase().includes(v.toLowerCase()) ||
          option.label.toLowerCase().includes(v.toLowerCase());
        if (!visible) filteredOptionsCount--;
        option.visible = visible;
        return option;
      });
    } else {
      this.$refs.select.options = options.map(option => {
        option.visible = true;
        return option;
      });
    }
    this.$refs.select.filteredOptionsCount = filteredOptionsCount;
  }
  @Watch("cachedOptions", { immediate: true })
  watchCachedOptions(options) {
    let labels = this.getOptionsLabel(options);
    this.chineseCharsToPinyins(labels);
  }

  @Watch("value", { immediate: true })
  watchValue() {
    this.$nextTick(() => {
      // this.handleTextOverflow();
    });
  }
}
</script>
<style scoped>
.input-overflow >>> .el-input__inner {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>