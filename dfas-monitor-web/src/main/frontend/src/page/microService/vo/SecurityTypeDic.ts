import { DicRepVO } from "../../dictionary/vo/DicVO";
/**
 * 页面加载前需要准备的数据
 */
export default class SecurityTypeDic {
    /* 交易市场 */
    public marketDic: DicRepVO[] = [];
    /* 交易平台 */
    public platformDic: DicRepVO[] = [];
    /* 市场类型 */
    public marketTypeDic: DicRepVO[] = [];
    /* 证券类型 */
    public securityTypeDic: DicRepVO[] = [];
    /* 证券大类 */
    public assetTypeDic: DicRepVO[] = [];
    /* 网上网下 */
    public nettradeTypeDic: DicRepVO[] = [];
    /* 证券类别单位 */
    public securityTypeUnitDic: DicRepVO[] = [];
    /* 申报单位 */
    public declarationUnitDic: DicRepVO[] = [];
    /* 资产冻结方式 */
    public blockFundsTypeDic: DicRepVO[] = [];
    /* 买卖最小数量控制方式 */
    public minControlTypeDic: DicRepVO[] = [];
    /* 交收方式 */
    public guaranteeFlag: DicRepVO[] = [];
    /* 清算速度 */
    public countFlag: DicRepVO[] = [];
    /* 清算时点 */
    public countTime: DicRepVO[] = [];
    /* 资金可用预留 */
    public cashUseReserveDic: DicRepVO[] = [];
    /* 交易日类型 */
    public tradeDayTypeDic: DicRepVO[] = [];
    /* 国家 */
    public countryDic: DicRepVO[] = [];
    /* 时区 */
    public timeZoneDic: DicRepVO[] = [];
    /* 申报方向 */
    public declarePathDic: DicRepVO[] = [];
    /* 资金方向 */
    public fundPathDic: DicRepVO[] = [];
    /* 证券方向 */
    public securityPathDic: DicRepVO[] = [];
    /* 交易方向 */
    public tradeDircets: DicRepVO[] = [];
    /* 交易方向 */
    public tradeDircetsMap: any = {};
    /* 影响证券 */
    public effectSecurityDic: DicRepVO[] = [];
    /* 关联变动方向 */
    public relationChangePathDic: DicRepVO[] = [];
    /* 关联影响证券 */
    public relationEffectSecurityDic: DicRepVO[] = [];
}
