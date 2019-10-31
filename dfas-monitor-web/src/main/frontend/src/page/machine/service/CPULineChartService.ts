import AxiosFun from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MachineInfoVO } from "../vo/MachineInfoVO";

/**
 * 类名称：CPUChartService
 * 类描述：CPU 服务类
 * 创建人：@author lj
 * 创建时间：2019-07-10 11:35:35
 */
export default class CPULineChartService {
    /**
     * cpu
     * @param reqVO
     * @Date:  2019-07-10 11:35:35
     */
    public info(reqVO: MachineInfoVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/machineDetail/cpuLineUsage",
            reqVO
        );
    }

}
