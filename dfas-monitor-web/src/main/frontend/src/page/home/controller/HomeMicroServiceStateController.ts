import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import {MicroServiceStateVO} from "../vo/MicroServiceStateVO";
import HomeMicroServiceStateService from "../service/HomeMicroServiceStateService";
import AxiosFun from "../../../api/AxiosFun";
import {MicroServiceInfoRepVO} from "../../microService/vo/MicroServiceInfoVO";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import MicroServiceInfoDicDataVO from "../../microService/vo/MicroServiceInfoDicDataVO";
import BaseController from "../../common/controller/BaseController";
import MicroServiceDetailDialog from "../../microService/view/MicroServiceDetailDialog.vue";
import {Prop} from "vue-property-decorator";
import {WinRspType} from "../../common/enum/BaseEnum";

@Component({components: {Monitor, MicroServiceDetailDialog}})
export default class HomeMicroServiceStateController extends BaseController {

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
  };


  ws: WebSocket;

  private homeMicroServiceStateService: HomeMicroServiceStateService = new HomeMicroServiceStateService();

  private microServiceStateList: Array<MicroServiceStateVO> = this.homeMicroServiceStateService.initMicroServiceStateList();

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

  /** dialog显示控制 */
  private detailDialogVisible: boolean = false;
  // 表格默认高度
  private tableHeight: number = 400;
  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MicroServiceInfoRepVO;
  };

  mounted() {
    let requestUrl = AxiosFun.monitorCenterWebsocketBaseUrl + "/home/microServiceState";
    this.establishConnection(requestUrl);
  }

  /**
   * 新增、修改、删除弹框
   * @param row
   * @param type
   */
  private operation({item}) {
    console.log(item)
    this.detailDialogVisible = true;
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(item),
    };
  }

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.detailDialogVisible = false;
  }

  establishConnection(requestUrl) {
    this.handleClose();
    this.ws = new WebSocket(requestUrl);
    let _ = this;
    this.ws.onopen = function (e) {
      _.ws.send(JSON.stringify({flag: requestUrl, data: "i am a microServiceState WebSocket!"}));
    };
    this.ws.onmessage = e => this.handleWebSocketData(e);
    this.ws.onclose = () => this.handleClose();
  }

  handleWebSocketData(e) {
    let object = JSON.parse(e.data);
    this.microServiceStateList = object;
  }

  handleClose() {
    if (this.ws != null) {
      this.ws.close();
    }
  }


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
