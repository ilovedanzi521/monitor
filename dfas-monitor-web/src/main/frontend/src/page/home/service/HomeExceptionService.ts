import AxiosFun from "win-biz";
import { MicroServiceStateVO } from "../vo/MicroServiceStateVO";
import { WinResponseData } from "../../common/vo/BaseVO";

export default class HomeExceptionService {


  public exception(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/home/exception",
      {}
    );
  }

}
