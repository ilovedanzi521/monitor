import { WinResponseData } from "../../common/vo/BaseVO";
import { UserReqVO } from "../vo/IssueVO";
import AxiosFun from "../../../api/AxiosFun";

/**
 * user页面获取数据
 */
export default class issueService {

  static getIssueList(vo): Promise<WinResponseData> {
      console.log("getIssueList");
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/issue/issuePage",
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

}
