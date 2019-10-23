import {BaseReqVO} from "../../common/vo/BaseVO";
import {DicRepVO} from "../../dictionary/vo/DicVO";

/**
 * CPU信息
 */
export default class MachineCPUVO {

    legendData:Array<string>;
    xaxisData:Array<string>;
    colorData:Array<string>;
    seriesData:Array<CpuSeriesData[]>;
}

export class CpuSeriesData {
  name: string;
  value: number;
}
