/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfas-monitor-center
 * 文件名称: MicroServicePanelService.ts
 * 文件描述: @Description: 微服务面板 服务类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import AxiosFun from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MicroServiceInfoReqVO } from "../vo/MicroServiceInfoVO";

/**
 * MicroServicePanelService
 * 类描述：微服务面板 服务类
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export default class MicroServicePanelService {
    

    /**
     * 微服务面板 列表查询
     * @param reqVO
     */
    public list(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/microServicePanel",
            reqVO
        );
    }

}
