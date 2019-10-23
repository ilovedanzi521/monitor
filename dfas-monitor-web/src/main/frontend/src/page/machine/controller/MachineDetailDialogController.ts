import { Component, Prop, Emit } from "vue-property-decorator";
import BaseController from "../../common/controller/BaseController";
import { WinResponseData } from "../../common/vo/BaseVO";
import { BaseConst } from "../../common/const/BaseConst";
import { WinRspType } from "../../common/enum/BaseEnum";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import MicroServiceInfoDialogService from "../service/MachineInfoDialogService";
import { MachineInfoVO } from "../vo/MachineInfoVO";
import { MachineValidateConst } from "../const/MachineValidateConst";
import JvmMemoryChart from "../../microService/view/detail/JvmMemoryChart.vue";
import MicroServiceBaseInfo from "../../microService/view/detail/MicroServiceBaseInfo.vue";
import BottomMachineList from "../../microService/view/detail/BottomMachineList.vue";
import CPUChart from "../view/detail/CPUChart.vue";
import MemoryChart from "../view/detail/MemoryChart.vue";
import NodeBaseInfo from "../view/detail/NodeBaseInfo.vue";
import CPULineChart from "../view/detail/CPULineChart.vue";
import DiskBar from "../view/detail/DiskBar.vue";



@Component({
  components: { JvmMemoryChart, MicroServiceBaseInfo,NodeBaseInfo, BottomMachineList,CPUChart,MemoryChart,CPULineChart,DiskBar}
})
export default class MachineDetailDialogController extends BaseController {
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
    data: MachineInfoVO;
  };

  private microServiceInfoDialogService: MicroServiceInfoDialogService = new MicroServiceInfoDialogService();
  private machineInfoRepVO: MachineInfoVO = new MachineInfoVO();
  private detailDialogVisible: boolean = true;
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
        message: MachineValidateConst.RIVAL_NAME_NOT_NULL,
        trigger: "blur"
      }
    ],
    appraise: [
      {
        required: true,
        message: MachineValidateConst.APPRAISE_NOT_NULL,
        trigger: "blur"
      }
    ]
  };
  /** 页面初始化 */
  private mounted() {

    this.machineInfoRepVO = this.fromFatherMsg.data;
    console.log(">?????????????>");
    //console.log(this.machineInfoRepVO);
    console.log(this.fromFatherMsg.data)
    console.log(">?????????????>");
    //alert(JSON.stringify(this.microServiceInfoRepVO));
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
    this.detailDialogVisible = true;
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
          this.microServiceInfoDialogService
            .insert(this.machineInfoRepVO)
            .then((response: WinResponseData) => {
              this.dialogMessage(response);
            });
        }
        if (this.fromFatherMsg.type === OperationTypeEnum.UPDATE) {
          this.microServiceInfoDialogService
            .update(this.machineInfoRepVO)
            .then((response: WinResponseData) => {
              this.dialogMessage(response);
            });
        }
        if (this.fromFatherMsg.type === OperationTypeEnum.DELETE) {
          this.microServiceInfoDialogService
            .del(this.machineInfoRepVO.id)
            .then((response: WinResponseData) => {
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
  CREATETITLE: "新增微服务详细信息",
  /** 查看基本信息 */
  VIEWTITLE: "查看微服务详细信息",
  /** 修改基本信息 */
  MODIFYTITLE: "修改微服务详细信息",
  /** 删除基本信息 */
  DELETETITLE: "删除微服务详细信息"
};
