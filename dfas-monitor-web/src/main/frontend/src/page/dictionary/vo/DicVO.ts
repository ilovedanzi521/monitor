import { BaseReqVO, BaseRepVO } from "../../common/vo/BaseVO";
export class DicReqVO extends BaseReqVO {
    commonParam: string;
    dicCode: string;
    dicExplain: string;
    parentDicCode: string;
    parentDicCodeList: Array<string>;
    param1: string;
    param2: string;
    param3: string;
    paramExplain: string;
}

export class DicRepVO extends BaseReqVO {
    dicCode: string;
    dicExplain: string;
    parentDicCode: string;
    param1: string;
    param2: string;
    param3: string;
    paramExplain: string;
}
