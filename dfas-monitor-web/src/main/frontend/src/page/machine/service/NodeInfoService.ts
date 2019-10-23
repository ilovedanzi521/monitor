/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfas-monitor-center
 * 文件名称: MicroServiceBaseInfoService.ts
 * 文件描述: @Description: 交易对手表 服务类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import AxiosFun from "../../../api/AxiosFun";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MachineInfoVO } from "../vo/MachineInfoVO";

/**
 * 类名称：NodeInfoService
 * 类描述：节点基本信息 服务类
 * 创建人：@author lj
 * 创建时间：2019-07-10 11:35:35
 */
export default class NodeInfoService {


    /**
     * 交易对手表 列表查询
     * @param reqVO
     */
    public async info(reqVO: MachineInfoVO): Promise<WinResponseData> {
        return await AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/machineDetail/nodeBaseInfo",
            reqVO
        );
    }

}
