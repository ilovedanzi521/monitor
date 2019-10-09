import { BaseReqVO, BaseRepVO } from "../../common/vo/BaseVO";

export class TradeRuleReqVO extends BaseReqVO {
    /**
     * 市场编码
     */
    public marketCode: string;
    /**
     * 交易平台
     */
    public platformCode: string;
    /**
     * 证券类别
     */
    public securityType: string;
    /**
     * 资产类别
     */
    public assetType: string;
    /**
     * 申报单位
     */
    public declarationUnit: string;
    /**
     * 资产冻结方式
     */
    public blockFundsType: string;
}

export class SecurityTypeRepVO extends BaseReqVO {
    /**
     * 交易市场编码
     */
    public marketCode: string;
    /**
     * 交易平台
     */
    public platformCode: string;
    /**
     * 市场类型
     */
    public marketType: string;
    /**
     * 证券类别
     */
    public securityType: string;
    /**
     * 证券类别名称
     */
    public securityTypeName: string;
    /**
     * 资产类别
     */
    public assetType: string;
    /**
     * 面值
     */
    public faceValue: number;
    /**
     * 证券类别单位
     */
    public securityTypeUnit: string;
    /**
     * 每手数量
     */
    public boardNumber: number;

    /**
     * 买入最小数量
     */
    public minBuyVolume: number;
    /**
     * 买入最大数量
     */
    public maxBuyVolume: number;
    /**
     * 买入变动单位数量
     */
    public buyEventNumber: number;

    /**
     * 卖出最小数量
     */
    public minSaleVolume: number;
    /**
     * 卖出最大数量
     */
    public maxSaleVolume: number;
    /**
     * 卖出变动单位数量
     */
    public saleEventNumber: number;

    /**
     * 数量最小单位
     */
    public minNumberUnit: number;
    /**
     * 买入最小金额
     */
    public minBuyAmount: number;
    /**
     * 买入最大金额
     */
    public maxBuyAmount: number;
    /**
     * 买入变动单位金额
     */
    public buyEventAmount: number;
    /**
     * 卖出最小金额
     */
    public minSaleAmount: number;
    /**
     * 卖出最大金额
     */
    public maxSaleAmount: number;
    /**
     * 卖出变动单余额
     */
    public saleEventAmount: number;
    /**
     * 最小差价
     */
    public minPriceTrade: number;
    /**
     * 申报单位
     */
    public declarationUnit: string;

    /**
     * 交易方向权限
     */
    public directionList: string[];
    /**
     * 资产冻结方式
     */
    public blockFundsType: string;
    /**
     * 买卖最小数量控制方式
     */
    public minControlType: string;

    /**
     *  交易方向
     */
    public transactionDirections: string;
}
export class SecurityCodeRepVO extends BaseReqVO {
    /**
     * 证券类别
     */
    public securityType: string;
    /**
     * 证券类别名称
     */
    public securityTypeName: string;
    /**
     * 资产类别
     */
    public assetType: string;
}
