import { WinResponseData } from "../../common/vo/BaseVO";
import { UserReqVO } from "../vo/ThresholdVO";
import AxiosFun from "win-biz";

/**
 * 机器页面获取数据
 */
export default class ThresholdService {
  
  static getThreshold(vo): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/threshold/thresholdPage",
      vo
    );
  }

  /**添加机器 */
  static addThreshold(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/threshold/add",
      vo
    );
  }

  //编辑机器
  static editThreshold(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/threshold/modify",
      vo
    );
  }

  //删除机器
  static deleteThreshold(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/threshold/delete",
      vo
    );
  }

  /** 批量删除 */
  static delBatch(ids: string) {
    return AxiosFun.winDelete(
      AxiosFun.monitorCenterServiceName + "/threshold/batchDelete/" + ids
    );
  }

}
