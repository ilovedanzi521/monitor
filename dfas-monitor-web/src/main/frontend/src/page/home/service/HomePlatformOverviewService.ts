import AxiosFun from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";

export default class HomePlatformOverviewService {
  public platformOverview(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/home/platformOverview",
      {}
    );
  }
}
