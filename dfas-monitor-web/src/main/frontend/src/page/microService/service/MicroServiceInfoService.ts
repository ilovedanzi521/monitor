/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 项目名称：dfbp-common-basicparameter
 * 文件名称: MicroServiceInfoService.tsts
 * 文件描述: @Description: 微服务列表 服务类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/
import AxiosFun from "win-biz";
import { WinResponseData } from "../../common/vo/BaseVO";
import { MicroServiceInfoReqVO } from "../vo/MicroServiceInfoVO";

/**
 * 类名称：MicroServiceInfoService
 * 类描述：微服务列表 服务类
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export default class MicroServiceInfoService {
    /**
     * 微服务列表 分页列表查询
     * @param reqVO
     * @Date:  2019-07-10 11:35:35
     */
    public pageList(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/getMicroServiceList",
            reqVO
        );
    }

    /**
     * 微服务列表 列表查询
     * @param reqVO
     */
    public list(reqVO: MicroServiceInfoReqVO): Promise<WinResponseData> {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/searchMicroService",
            reqVO
        );
    }

    public synchronize(): Promise<WinResponseData> {
      return AxiosFun.put(
        AxiosFun.monitorCenterServiceName +
        "/microService/synchronizeMicroService",
        {}
      );
    }

    /**
     * 批量删除交易对手
     * @param ids
     */
    public delBatch(ids: string): Promise<WinResponseData> {
        return AxiosFun.winDelete(
            AxiosFun.monitorCenterServiceName +
                "/microService/batDeleteMicroService/" +
                ids,
            {}
        );
    }
}
