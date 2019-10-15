/****************************************************
 * 创建人：     @author zoujian
 * 创建时间: 2019年7月5日/下午5:52:56
 * 项目名称：  FRONTEND
 * 文件名称: MicroServiceInfoDialogController
 * 文件描述: @Description: (结算账户增删改页面Controller)
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/
import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import { WinResponseData } from "../../common/vo/BaseVO";
import { BaseConst } from "../../common/const/BaseConst";
import { WinRspType } from "../../common/enum/BaseEnum";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import MicroServiceInfoDialogService from "../service/MicroServiceInfoDialogService";
import { MicroServiceInfoRepVO } from "../vo/MicroServiceInfoVO";
import MicroServiceInfoDicDataVO from "../vo/MicroServiceInfoDicDataVO";
import { MicroServiceValidateConst } from "../const/MicroServiceValidateConst";
@Component
export default class MicroServiceInfoDialogController extends BaseController {
    public $refs: {
        validate: HTMLFormElement;
    };

    /** 父子组件通信 */
    @Prop({
        type: Object,
        required: false,
        default: () => ({})
    })
    private fromFatherMsg!: {
        type: OperationTypeEnum;
        data: MicroServiceInfoRepVO;
        rivalInfoDicData: MicroServiceInfoDicDataVO;
    };

    private microServiceInfoDialogService: MicroServiceInfoDialogService = new MicroServiceInfoDialogService();
    private MicroServiceInfoRepVO: MicroServiceInfoRepVO = new MicroServiceInfoRepVO();
    private rivalInfoDicData: MicroServiceInfoDicDataVO = new MicroServiceInfoDicDataVO();
    private infoDialogVisible: boolean = true;
    private dialogLoading: boolean = false;
    private createLoading: boolean = false;
    private formDisabled: boolean = false;
    /** 是否显示操作（确定、取消）按钮 */
    private operationShow: boolean = true;
    private rivalDisabled: boolean = true;
    private dialogSumbitText: string = "";
    /** dialog标题 */
    private dialogTitle: string = "";
    private spanWidth: number = 12;
    /** 控制交易对手方序号样式 */
    private rivalNoWidth: number = 12;

    /** form表单校验规则 */
    private rules = {
        rivalName: [
            {
                required: true,
                message: MicroServiceValidateConst.RIVAL_NAME_NOT_NULL,
                trigger: "blur"
            }
        ],
        appraise: [
            {
                required: true,
                message: MicroServiceValidateConst.APPRAISE_NOT_NULL,
                trigger: "blur"
            }
        ]
    };
    /** 页面初始化 */
    private mounted() {
        this.MicroServiceInfoRepVO = new MicroServiceInfoRepVO();
        this.MicroServiceInfoRepVO = this.fromFatherMsg.data;
        if (this.fromFatherMsg.type === OperationTypeEnum.ADD) {
            this.dialogTitle = MicroServiceInfoConst.CREATETITLE;
            this.dialogSumbitText = BaseConst.CONFIRM;
            this.operationShow = true;
            this.rivalNoWidth = 0;
            this.rivalDisabled = false;
        }
        if (this.fromFatherMsg.type === OperationTypeEnum.DELETE) {
            this.dialogTitle = MicroServiceInfoConst.DELETETITLE;
            this.dialogSumbitText = BaseConst.DELETE;
            this.formDisabled = true;
            this.operationShow = true;
        }
        if (this.fromFatherMsg.type === OperationTypeEnum.UPDATE) {
            this.dialogTitle = MicroServiceInfoConst.MODIFYTITLE;
            this.dialogSumbitText = BaseConst.CONFIRM;
            this.operationShow = true;
        }
        if (this.fromFatherMsg.type === OperationTypeEnum.VIEW) {
            this.dialogTitle = MicroServiceInfoConst.VIEWTITLE;
            this.operationShow = false;
            this.formDisabled = true;
        }
        this.rivalInfoDicData = this.fromFatherMsg.rivalInfoDicData;
        this.infoDialogVisible = true;
    }

    /** 取消 */
    private dialogCancel() {
        this.send(WinRspType.CANCEL);
    }

    /** 关闭 */
    private closeDialog() {
        this.send(WinRspType.SUCC);
    }

    /** 提交按钮 */
    private submitDialog() {
        const MicroServiceInfo = "MicroServiceInfo";
        const el: any = this.$refs[MicroServiceInfo];
        el.validate((valid: boolean) => {
            if (valid) {
                this.dialogLoading = true;
                if (this.fromFatherMsg.type === OperationTypeEnum.ADD) {
                    this.microServiceInfoDialogService.insert(this.MicroServiceInfoRepVO).then((response: WinResponseData) => {
                        this.dialogMessage(response);
                    });
                }
                if (this.fromFatherMsg.type === OperationTypeEnum.UPDATE) {
                    this.microServiceInfoDialogService.update(this.MicroServiceInfoRepVO).then((response: WinResponseData) => {
                        this.dialogMessage(response);
                    });
                }
                if (this.fromFatherMsg.type === OperationTypeEnum.DELETE) {
                    this.microServiceInfoDialogService.del(this.MicroServiceInfoRepVO.id).then((response: WinResponseData) => {
                        this.dialogMessage(response);
                    });
                }
            }
        });
    }

    private dialogMessage(response: WinResponseData) {
        this.dialogLoading = false;
        if (response.winRspType === WinRspType.ERROR) {
            this.win_message_error(response.msg);
        } else {
            this.win_message_success(response.msg);
            this.send(WinRspType.SUCC);
        }
    }

    /** 回调给父组件函数 */
    @Emit("bindSend")
    private send(msg: WinRspType) {}
}
export const MicroServiceInfoConst = {
    /** 新增基本信息 */
    CREATETITLE: "新增微服务基本信息",
    /** 查看基本信息 */
    VIEWTITLE: "查看微服务基本信息",
    /** 修改基本信息 */
    MODIFYTITLE: "修改微服务基本信息",
    /** 删除基本信息 */
    DELETETITLE: "删除微服务基本信息"
};
