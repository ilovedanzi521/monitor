/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 文件名称: MicroServiceInfoVO.ts
 * 文件描述: @Description: 微服务 返回VO
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/
import { BaseRepVO, BaseReqVO } from "../../common/vo/BaseVO";

/**
 * 类名称：MicroServiceInfoRepVO
 * 类描述：交易对手表 返回VO
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export class MachineInfoVO extends BaseRepVO {
    /**
     * 对手序号
     */
    public microServiceAlias: String;

    /**
     * 对手编号
     */
    public ipAddress: string;

    /**
     * 对手名称
     */
    public microServiceName: string;

    /**
     * 对手简称
     */
    public rivalShortName: string;

    /**
     * 对手方评级
     */
    public appraise: string;

    /**
     * 优质对手方
     */
    public golden: string;

    /**
     * 首期结算方式
     */
    public firstSettleType: string;

    /**
     * 到期结算方式
     */
    public secondSettleType: string;

    /**
     * 备注
     */
    public remark: string;
}

/**
 * MicroServiceInfoReqVO
 * 类描述：交易对手表 请求VO
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export class MicroServiceInfoReqVO extends BaseReqVO {
    /**
     * 对手序号
     */
    public microServiceAlias: string;

    /**
     * 对手编号
     */
    public ipAddress: string;

    /**
     * 对手名称
     */
    public microServiceName: string;

    /**
     * 对手简称
     */
    public rivalShortName: string;

    /**
     * 对手方评级
     */
    public appraise: string;

    /**
     * 优质对手方
     */
    public golden: string;

    /**
     * 首期结算方式
     */
    public firstSettleType: string;

    /**
     * 到期结算方式
     */
    public secondSettleType: string;

    /**
     * 备注
     */
    public remark: string;
}
