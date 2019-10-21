import Vue from "vue";
import Component from "vue-class-component";
import BaseController from "../../common/controller/BaseController";
import MicroServicePanelService from "../service/MicroServicePanelService";
import { WinResponseData } from "../../common/vo/BaseVO";
import {
  MicroServiceInfoReqVO,
  MicroServiceInfoRepVO
} from "../vo/MicroServiceInfoVO";
import { MicroServiceStateVO } from "../../home/vo/MicroServiceStateVO";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import { Prop } from "vue-property-decorator";
import Service from "../../../components2/vue/monitor/Service.vue";
import MicroServiceDetailDialog from "../view/MicroServiceDetailDialog.vue";

@Component({ components: { Service, MicroServiceDetailDialog } })
export default class MicsoServicePanelController extends BaseController {
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
  /** 请求VO */
  private reqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();

  private microServicePanelService: MicroServicePanelService = new MicroServicePanelService();

  private microServiceStateList: Array<MicroServiceStateVO> = [];

  /** dialog显示控制 */
  private detailDialogVisible: boolean = false;
  // 表格默认高度
  private tableHeight: number = 400;
  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MicroServiceInfoRepVO;
  };

  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.detailDialogVisible = false;
  }
  /**
   * 微服务面板  数据准备
   * @Title: mounted
   * @throws
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private mounted() {
    this.$nextTick(() => {
      this.query();
    });
  }

  /**
   * 微服务列表查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.microServicePanelService
      .list(this.reqVO)
      .then((res: WinResponseData) => {
        if (res.winRspType === "ERROR") {
          this.win_message_error(res.msg);
        }
        this.microServiceStateList = res.data;
      });
  }

  /**
   * 新增、修改、删除弹框
   * @param row
   * @param type
   */
  private operation({ item }) {
    this.detailDialogVisible = true;
    this.cardNumber = {
      type: OperationTypeEnum.VIEW,
      data: this.copy(item)
    };
  }
}
