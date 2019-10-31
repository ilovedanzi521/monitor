/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfbp-common-basicparameter
 * 文件名称: MicroServiceInfoService.tsts
 * 文件描述: @Description: 交易对手表 服务类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import AxiosFun from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MicroServiceInfoReqVO } from "../vo/MicroServiceInfoVO";

/**
 * 类名称：BottomMachineListService
 * 类描述：微服务机器列表 服务类
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export default class BottomMachineListService {


    /**
     * 微服务机器列表 列表查询
     * @param reqVO
     */
    public list(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/microServiceMachineList",
            reqVO
        );
    }


}
