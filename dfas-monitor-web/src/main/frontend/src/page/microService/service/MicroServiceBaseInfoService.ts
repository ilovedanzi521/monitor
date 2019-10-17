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
import { MicroServiceInfoReqVO } from "../vo/MicroServiceInfoVO";

/**
 * 类名称：MicroServiceInfoService
 * 类描述：微服务基本信息 服务类
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export default class MicroServiceBaseInfoService {


    /**
     * 交易对手表 列表查询
     * @param reqVO
     */
    public info(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/microServiceInfo",
            reqVO
        );
    }

}
