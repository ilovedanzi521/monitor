import { WinResponseData } from "../../common/vo/BaseVO";
import { UserReqVO } from "../vo/IssueVO";
import AxiosFun from "../../../api/AxiosFun";

/**
 * user页面获取数据
 */
export default class issueService {
    /**获取树形公司菜单 */
    static getCompany(): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/api/menuTree/cdrTree",
            {}
        );
    }

    static getUsers(vo): Promise<WinResponseData> {
      console.log("getUsers")
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/api/multiQuery/userCompanyPage",
            vo
        );
    }

  static getIssueList(vo): Promise<WinResponseData> {
      console.log("getIssueList");
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/issue/issuePage",
      vo
    );
  }

  /**添加机器 */
  static addMachine(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/add",
      vo
    );
  }

  //编辑机器
  static editMachine(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/modify",
      vo
    );
  }

  //删除问题
  static deleteIssue(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/issue/delete",
      vo
    );
  }

  /** 批量删除 */
  static delBatch(ids: string) {
    return AxiosFun.winDelete(
      AxiosFun.monitorCenterServiceName + "/issue/batchDelete/" + ids
    );
  }

  //一键同步
  static onKeySync(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/onKeySync",
      null
    );
  }

  //机器明细数据
  static machinePanelData(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/machinePanelData",
      null
    );
  }



}
