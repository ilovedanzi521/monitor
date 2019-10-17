<template>
    <div class="dialog_section">
        <win-fdialog title="修改待上市证券" :visible="true" :close-on-click-modal="false" width="1173px">
            <win_form :inline="true" :model="compan" :rules="rules" label-width="126px">
                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券简称" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券类别" prop="companyName">
                        <win_input unit="元"></win_input>
                    </win_form_item>
                    <win_form_item label="债券期限" prop="companyName">
                        <win_input unit="万元"></win_input>
                    </win_form_item>
                    <win_form_item label="债券期次" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券面值" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="内部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="外部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="是否有调整票面利率选择权" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="是否有回售选择权" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                </div>

                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券简称" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券期次" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券面值" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="内部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="外部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                </div>

                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券简称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券期次">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券面值">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="内部信用评级">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="外部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                </div>

                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券简称" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券期次" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券面值" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="内部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="外部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                </div>

                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券简称" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券期次" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="债券面值" prop="companyName">
                        <win_input unit="万元"></win_input>
                    </win_form_item>
                    <win_form_item label="内部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                    <win_form_item label="外部信用评级" prop="companyName">
                        <win_input></win_input>
                    </win_form_item>
                </div>

                <div>
                    <win_form_item label="证券代码" prop="companyName">
                        <win_input unit="元"></win_input>
                    </win_form_item>
                    <win_form_item label="债券全称">
                        <win_input></win_input>
                    </win_form_item>
                </div>

            </win_form>
            <div slot="footer" class="dialog-footer">
                <el-button>取 消</el-button>
                <el-button type="primary">确 认</el-button>
            </div>
        </win-fdialog>
    </div>
</template>
<script>
import {
    win_dialog,
    win_button,
    win_form,
    win_form_item,
    win_tabs,
    win_tab,
    win_select,
    win_option,
    win_input,
    win_table,
    win_table_column,
    win_checkbox,
    win_checkboxButton,
    win_checkboxGroup
} from "@win-frond-frameworks/biz-common";
export default {
    data() {
        return {
          /*  compan: {
                companyName: ""
            },*/
          machine: {
            id : "" ,
            ipAddress: "",
            name: ""
          },
            rules: {
              /*  companyName: [
                    {
                        required: true,
                        message: "请输入活动名称",
                        trigger: "blur"
                    }
                ],*/
              ip: [
                {
                  required: true,
                  message: "请输入机器IP",
                  trigger: "blur"
                }
              ],
              name: [
                {
                  required: true,
                  message: "请输入机器名称",
                  trigger: "blur"
                }
              ]
            }
        };
    },
    components: {
        win_dialog,
        win_button,
        win_form,
        win_form_item,
        win_tabs,
        win_tab,
        win_select,
        win_option,
        win_input,
        win_table,
        win_table_column,
        win_checkbox,
        win_checkboxButton,
        win_checkboxGroup
    },
    created() {},
    mounted() {},
    watch: {},
    computed: {},
    methods: {}
};
</script>
<style lang="scss" scoped>
</style>
