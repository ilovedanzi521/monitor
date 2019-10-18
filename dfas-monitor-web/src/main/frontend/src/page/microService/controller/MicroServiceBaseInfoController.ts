import BaseController from "../../common/controller/BaseController";
import {Component, Prop} from "vue-property-decorator";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import {MicroServiceInfoRepVO, MicroServiceInfoReqVO} from "../vo/MicroServiceInfoVO";
import MicroServiceBaseInfoService from "../service/MicroServiceBaseInfoService";
import { WinResponseData } from "../../common/vo/BaseVO";

@Component({})
export default class MicroServiceBaseInfoController extends BaseController {
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

  private microServiceInfoRepVO: MicroServiceInfoRepVO = new MicroServiceInfoRepVO();
  private microServiceInfoReqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  private microServiceBaseInfoService: MicroServiceBaseInfoService = new MicroServiceBaseInfoService();

  /** 页面初始化 */
  private mounted() {
    this.microServiceInfoRepVO = this.fromFatherMsg.data;
    this.microServiceInfoReqVO.id = this.microServiceInfoRepVO.id;
    this.microServiceInfoReqVO.microServiceName = this.microServiceInfoRepVO.microServiceName;
    this.$nextTick(() => {
      this.query();
    });
  }

    /**
   * 微服务基本信息查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.microServiceBaseInfoService.info(this.microServiceInfoReqVO).then((res: WinResponseData) => {
      if (res.winRspType === "ERROR") {
        this.win_message_error(res.msg);
      }
      this.microServiceInfoRepVO = res.data;
    });
  }

}
