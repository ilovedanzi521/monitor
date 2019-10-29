import AxiosFun from "../../../api/AxiosFun";
import { MicroServiceStateVO } from "../vo/MicroServiceStateVO";
import { WinResponseData } from "../../common/vo/BaseVO";

export default class HomeMicroServiceStateService {

  private microServiceStateList: Array<MicroServiceStateVO> = [];

  public microServiceState(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterWebsocketBaseUrl + "/home/microServiceState",
      {}
    );
  }

  public initMicroServiceStateList() {
    return this.microServiceStateList;
  }
}
