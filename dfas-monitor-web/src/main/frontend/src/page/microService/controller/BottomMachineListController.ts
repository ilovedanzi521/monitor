import BaseController from "../../common/controller/BaseController";
import {Component, Prop} from "vue-property-decorator";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import {MicroServiceInfoReqVO, MicroServiceInfoRepVO} from "../vo/MicroServiceInfoVO";
import BottomMachineListService from "../service/BottomMachineListService";
import {WinResponseData} from "../../common/vo/BaseVO";
import {MicroServiceMachineRepVO} from "../vo/MicroServiceMachineRepVO";

@Component({})
export default class BottomMachineListController extends BaseController {

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
  private bottomMachineListService: BottomMachineListService = new BottomMachineListService();
  private microServiceMachineRepList: Array<MicroServiceMachineRepVO> = [];

  /** 页面初始化 */
  private mounted() {
    this.microServiceInfoRepVO = this.fromFatherMsg.data;
    this.microServiceInfoReqVO.id = this.microServiceInfoRepVO.id;
    this.microServiceInfoReqVO.microServiceName = this.microServiceInfoRepVO.microServiceName;
    this.$nextTick(() => {
      this.query();
    });
  }

  generateClassName(index) {
    if (index % 2 == 0) {
      return "table-tr-data-one";
    } else {
      return "table-tr-data-two";
    }
  }

  /**
   * 微服务机器列表查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private query(): void {
    this.bottomMachineListService.list(this.microServiceInfoReqVO).then((res: WinResponseData) => {
      if (res.winRspType === "ERROR") {
        this.win_message_error(res.msg);
      }
      this.microServiceMachineRepList = res.data;
    });
  }

}
