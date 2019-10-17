import Vue from "vue";
import Component from "vue-class-component";
import issueService from "../service/IssueService";
import { UserReqVO } from "../vo/IssueVO";
import ViewIssue from "../view/ViewIssue.vue";
import DeleteIssue from "../view/DeleteIssue.vue";
import IssueTable from "../view/IssueTable.vue";
import BaseController from "../../common/controller/BaseController";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
import Tool from "../../../mixin/mm";
import { BaseConst } from "../../common/const/BaseConst";
import { OperationTypeEnum } from "../../common/enum/OperationTypeEnum";
import AxiosFun from "../../../api/AxiosFun";
@Component({
    components: {
        ViewIssue,
      DeleteIssue,
        IssueTable
    }
})
export default class IssueController extends BaseController {
    $set;
    $refs;
    $forceUpdate;
    filterText = "";
    userReqVo: UserReqVO = new UserReqVO();
    ws: WebSocket;

   private multipleSelection: any = [];

    // 选中
   tableSelectionChange(val: any) {
       this.multipleSelection = val.selection;
       console.log(this.multipleSelection);
    }

    /** 批量删除 */
    private delIssueBatch() {
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
          issueService
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

  viewIssuePage(row) {
    this.userReqVo.issue = row;
    if(this.userReqVo.issue.warnLevel == "0"){
      this.userReqVo.issue.warnLevelDesc = "低" ;
    }
    if(this.userReqVo.issue.warnLevel == "1"){
      this.userReqVo.issue.warnLevelDesc = "中" ;
    }
    if(this.userReqVo.issue.warnLevel == "2"){
      this.userReqVo.issue.warnLevelDesc = "高" ;
    }
    this.userReqVo.stateController.switchFormType = "ViewIssue";
  }

  deleteIssuePage(row) {
    this.userReqVo.issue = row;
    if(this.userReqVo.issue.warnLevel == "0"){
      this.userReqVo.issue.warnLevelDesc = "低" ;
    }
    if(this.userReqVo.issue.warnLevel == "1"){
      this.userReqVo.issue.warnLevelDesc = "中" ;
    }
    if(this.userReqVo.issue.warnLevel == "2"){
      this.userReqVo.issue.warnLevelDesc = "高" ;
    }
    this.userReqVo.stateController.switchFormType = "DeleteIssue";
  }

  /***删除问题*/
  httpDeleteIssue(res) {
    let id = this.userReqVo.issue.id;
    let ipAddress = this.userReqVo.issue.ipAddress;
    let warnLevel = this.userReqVo.issue.warnLevel;
    let params = {
      ...res,
      id: id,
      ipAddress: ipAddress,
      warnLevel: warnLevel
    };
    issueService
      .deleteIssue(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          this.userReqVo.stateController.switchFormType = "";
          this.win_message_success("删除问题成功");
          let params = {
          };
          this.getIssueList(params);
        } else {
          this.win_message_error(winResponseData.msg);
        }
      });
  }


    /***首次加载问题列表数据 */
    async fristGetCompent() {

        /*let params = {
            companyId: this.userReqVo.company.companyId,
            userStates: this.userStates
        };*/
        console.log("fristGetCompent")
        this.getIssueList({})
    }


    /***加载公司树结构和用户数据 */
    async getCompent() {
        let params = {  };
        this.getIssueList(params);
    }


  /**
   * 机器分页查询
   * @param vo
   */
  httpIssuePageQuery(vo: PageVO) {
    console.log("httpIssuePageQuery")
    var params = {
      ...this.userReqVo.reqParam,
      ...{ reqPageNum: vo.pageNum, reqPageSize: vo.pageSize }
    };

    this.getIssueList(params);
  }

    /**
     * 用户搜索
     */
    public searchMachine() {
        let params = {
            //companyId: this.userReqVo.company.companyId,
        };
        this.getIssueList(params);
    }

  /***加载查询issue表格*/
  async getIssueList(params) {
    params.reqPageNum = params.reqPageNum || 1;
    params.reqPageSize = params.reqPageSize || 10;

    this.userReqVo.reqParam = params;

    issueService
      .getIssueList(params)
      .then((winResponseData: WinResponseData) => {
        if (WinRspType.SUCC == winResponseData.winRspType) {
          let userArray = winResponseData.data.list.map(item => {
            return {
              id: item.id,
              ipAddress: item.ipAddress,
              issueDesc: item.issueDesc,
              warnLevel: item.warnLevel,
              createTime: item.createTime,
              updateTime: item.updateTime
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
