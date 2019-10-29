import AxiosFun from "../../../api/AxiosFun";
import { WinResponseData } from "../../common/vo/BaseVO";

export default class HomePlatformOverviewService {
  public platformOverview(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/home/platformOverview",
      {}
    );
  }
}
