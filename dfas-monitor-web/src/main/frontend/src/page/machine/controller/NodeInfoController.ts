import BaseController from "../../common/controller/BaseController";
import {Component, Prop} from "vue-property-decorator";
import {OperationTypeEnum} from "../../common/enum/OperationTypeEnum";
import {MachineInfoVO, MicroServiceInfoReqVO} from "../vo/MachineInfoVO";
import NodeInfoService from "../service/NodeInfoService";
import { WinResponseData } from "../../common/vo/BaseVO";
import AxiosFun from "../../../api/AxiosFun";

@Component({})
export default class NodeInfoController extends BaseController {
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

  private machineInfoVO: MachineInfoVO = new MachineInfoVO();
  private microServiceInfoReqVO: MicroServiceInfoReqVO = new MicroServiceInfoReqVO();
  private nodeInfoService: NodeInfoService = new NodeInfoService();

  /** 页面初始化 */
  private mounted() {
    this.machineInfoVO = this.fromFatherMsg.data;
    /*this.$nextTick(() => {
      this.query();
    });*/
  }

    /**
   * 微服务基本信息查询
   * @Title: query
   * @author: wangyaoheng
   * @Date:   2019-07-10 11:35:35
   */
  private  query(): void {
      this.nodeInfoService
        .info(this.machineInfoVO)
        .then((res: WinResponseData) => {
          if (res.winRspType === "ERROR") {
            this.win_message_error(res.msg);
          }
          let respData = res.data;
          this.machineInfoVO.balance = respData.balance;
          this.machineInfoVO.cpuInfo = respData.cpuInfo;
          this.machineInfoVO.diskInfo = respData.diskInfo;
          this.machineInfoVO.memoryInfo = respData.memoryInfo;
          console.log(">>>>nodeindocontroller >>> query >>>>");
          console.log(this.machineInfoVO);
        });

  }

}
