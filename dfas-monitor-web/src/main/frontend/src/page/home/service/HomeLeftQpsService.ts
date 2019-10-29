import AxiosFun from "../../../api/AxiosFun";
import {MachineVO} from "../vo/MachineVO";
import { WinResponseData } from "../../common/vo/BaseVO";


export default class HomeLeftQpsService {

  private hourList: string[] = [];

  private dataList: number[] = [];

  public initHourList() {
    this.hourList = [];
    let date = new Date();
    let hour = date.getHours();
    let suffix = ":00";
    for (let i = 0; i <= hour + 1; i++) {
      if (i < 10) {
        this.hourList.push('0' + i + suffix);
      } else {
        this.hourList.push(i + suffix);
      }
    }
    return this.hourList;
  }


  public inityAxisDataList() {
    this.dataList = [];
    let date = new Date();
    let hour = date.getHours();
    for (let i = 0; i <= hour + 1; i++) {
      this.dataList.push(Math.ceil(Math.random() * 1000) + 1);
    }
    return this.dataList;
  }

  public qps(): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/home/qps",
      {}
    );
  }

}
