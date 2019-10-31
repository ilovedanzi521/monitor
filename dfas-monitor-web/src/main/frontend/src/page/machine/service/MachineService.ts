import { WinResponseData } from "../../common/vo/BaseVO";
import { UserReqVO } from "../vo/MachineVO";
import AxiosFun from "win-biz";

/**
 * 机器页面获取数据
 */
export default class MachineService {
  
  static getMachine(vo): Promise<WinResponseData> {
      console.log("getMachine");
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/machinePage",
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

  //删除机器
  static deleteMachine(vo: UserReqVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/machine/delete",
      vo
    );
  }

  /** 批量删除 */
  static delBatch(ids: string) {
    return AxiosFun.winDelete(
      AxiosFun.monitorCenterServiceName + "/machine/batchDelete/" + ids
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
