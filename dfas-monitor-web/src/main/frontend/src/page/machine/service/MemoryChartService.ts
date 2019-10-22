import AxiosFun from "../../../api/AxiosFun";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MachineInfoVO } from "../vo/MachineInfoVO";

/**
 * 类名称：MemoryChartService
 * 类描述：memory 服务类
 * 创建人：@author lj
 * 创建时间：2019-07-10 11:35:35
 */
export default class MemoryChartService {
    /**
     * cpu
     * @param reqVO
     * @Date:  2019-07-10 11:35:35
     */
    public info(reqVO: MachineInfoVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/machineDetail/memoryUsage",
            reqVO
        );
    }

}
