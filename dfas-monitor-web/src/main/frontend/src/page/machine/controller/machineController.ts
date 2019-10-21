import Vue from "vue";
import Component from "vue-class-component";
import MachineService from "../service/MachineService";
import { UserReqVO } from "../vo/MachineVO";
import AddMachine from "../view/AddMachine.vue";
import EditMachine from "../view/EditMachine.vue";
import DeleteMachine from "../view/DeleteMachine.vue";
import MachineTable from "../view/MachineTable.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "../../../api/AxiosFun";
import MachineDetailDialog from "../view/MachineDetailDialog.vue";
import { MachineInfoVO } from "../vo/MachineInfoVO";

@Component({
    components: {
        AddMachine,
        EditMachine,
        DeleteMachine,
        MachineTable,
        MachineDetailDialog
    }
})
export default class MachineController extends BaseController {
    $set;
    $refs;
    $forceUpdate;
    filterText = "";
    userReqVo: UserReqVO = new UserReqVO();
    ws: WebSocket;

  /** 子组件显示的信息 */
  private cardNumber: {
    type: OperationTypeEnum;
    data: MachineInfoVO;
  };

  private machineDetailDialogVisible: boolean = false;


  /**
   * 子组件回调函数
   * @param msg
   */
  private toFatherMsg(msg: string) {
    this.machineDetailDialogVisible = false;
  }

  private mclick(){
    console.log("mclick");
    this.machineDetailDialogVisible = true;
    console.log(this.machineDetailDialogVisible);
  }

   private multipleSelection: any = [];

    // 选中
   tableSelectionChange(val: any) {
       this.multipleSelection = val.selection;
       console.log(this.multipleSelection);
    }

    /** 批量删除 */
    private delBatch() {
      const multipleSelection = this.multipleSelection;
      console.log(multipleSelection);
      console.log(multipleSelection.length);
      if (multipleSelection.length >= 1) {
        this.win_message_box_warning(
          "此操作将永久删除" +
          multipleSelection.length +
          " 条信息, 是否继续?",
          BaseConst.WARNING,
          false
        ).then((res: any) => {
          const ids = [];
          multipleSelection.forEach((element: UserReqVO) => {
            ids.push(element.id);
          });
          const idsStr: string = ids.join(",");
          MachineService
            .delBatch(idsStr)
            .then((response: WinResponseData) => {
              this.success("删除机器成功");
              //this.message(response);
            })
            .catch((ex: any) => {
              this.win_message_error("批量删除失败");
            });
        });
      } else {

      }
    }

  /** 一键同步 */
  private onKeySync() {
    console.log("onKeySync")
    MachineService
      .onKeySync()
      .then((response: WinResponseData) => {
        this.success("一键同步成功");
        //this.message(response);
      })
      .catch((ex: any) => {
        this.win_message_error("一键同步失败");
      });
  }

  /***添加机器页面*/
  addMachinePage() {
    this.userReqVo.stateController.switchFormType = "AddMachine";
  }

  editMachinePage(row) {
    this.userReqVo.machine = row;
    this.userReqVo.stateController.switchFormType = "EditMachine";
  }

  /***添加机器*/
  async httpAddMachine(params) {
    MachineService
      .addMachine(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC === winResponseData.winRspType) {
          this.userReqVo.machine.id = winResponseData.data.id;
          this.success("添加机器成功");
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

  /***编辑机器*/
  httpEditMachine(res) {
    let id = this.userReqVo.machine.id;
    let ipAddress = this.userReqVo.machine.ipAddress;
    let name = this.userReqVo.machine.name;
    let params = {
      ...res,
      id: id,
      ipAddress: ipAddress,
      name: name
    };
    MachineService
      .editMachine(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("修改机器成功");
          let params = {
          };
          this.getMachine(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }

  /***删除机器*/
  httpDeleteMachine(res) {
    let id = this.userReqVo.machine.id;
    let ipAddress = this.userReqVo.machine.ipAddress;
    let name = this.userReqVo.machine.name;
    let params = {
      ...res,
      id: id,
      ipAddress: ipAddress,
      name: name
    };
    MachineService
      .deleteMachine(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("删除机器成功");
          let params = {
          };
          this.getMachine(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }


    /***首次加载机器列表数据 */
    async fristGetCompent() {

        /*let params = {
            companyId: this.userReqVo.company.companyId,
            userStates: this.userStates
        };*/
        console.log("fristGetCompent")
        this.getMachine({})
    }


    /***加载公司树结构和用户数据 */
    async getCompent() {
        let params = {  };
        this.getMachine(params);
    }


  /**
   * 机器分页查询
   * @param vo
   */
  httpMachinePageQuery(vo: PageVO) {
    console.log("httpUserPageQuery")
    var params = {
      ...this.userReqVo.reqParam,
      ...{ reqPageNum: vo.pageNum, reqPageSize: vo.pageSize }
    };

    this.getMachine(params);
  }

    /**
     * 用户搜索
     */
    public searchMachine() {
        let params = {
            //companyId: this.userReqVo.company.companyId,
        };
        this.getMachine(params);
    }

  /***加载查询machine表格*/
  async getMachine(params) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    MachineService
      .getMachine(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              name: item.name,
              alias: item.alias,
              ipAddress: item.ipAddress,
              userName: item.userName,
              port: item.port,
              osType: item.osType,
              status: item.status,
              description: item.description,
              createTime: item.createTime,
              updateTime: item.updateTime,
              cpu : item.cpu,
              memory : item.memory,
              disk : item.disk
            };
          });
          this.userReqVo.userPageVO = winResponseData.data;
          this.userReqVo.userArray = userArray;
        } else {
          this.errorMessage(winResponseData.msg);
        }
      });
  }

    filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
    }

    /***添加,删除，编辑成功 */
    success(string: string): void {
        this.successMessage(string);
        //this.compayArray = [this.userReqVo.company.companyId];
        this.userReqVo.stateController.switchFormType = "";
        this.getCompent();
    }

    async mounted() {
        this.fristGetCompent();
        Tool.frameToMessage("tab", {
            menuName: "业务开关控制",
            menuAddress:
                "/dfbp-common-basicparameter/dfbp/dfbp-common-basicparameter/index.html#/switch"
        });
    }

}
