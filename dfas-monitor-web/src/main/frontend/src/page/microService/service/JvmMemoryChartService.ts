/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfbp-common-basicparameter
 * 文件名称: JvmMemoryChartService.ts
 * 文件描述: @Description: 微服务折线图 服务类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import AxiosFun from "../../../api/AxiosFun";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MicroServiceInfoReqVO } from "../vo/MicroServiceInfoVO";

/**
 * 类名称：JvmMemoryChartService
 * 类描述：交易对手表 服务类
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export default class JvmMemoryChartService {
    /**
     * jvm内存变化 列表查询
     * @param reqVO
     * @Date:  2019-07-10 11:35:35
     */
    public info(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/jvmMemory",
            reqVO
        );
    }

}
