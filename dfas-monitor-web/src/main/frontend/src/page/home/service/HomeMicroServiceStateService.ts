import AxiosFun from "../../../api/AxiosFun";
import {MicroServiceStateVO} from "../vo/MicroServiceStateVO";


export default class HomeMicroServiceStateService {

  private microServiceStateList: Array<MicroServiceStateVO> = [{
    "id": "1",
    "state": "1",
    "microServiceName": "订单服务",
    "microServiceAlias": "订单服务",
    "warn": 2,
    "error": 2
  },
    {
      "id": "2",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "3",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "4",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "5",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "6",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "7",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "8",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "9",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "10",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "11",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "12",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    },
    {
      "id": "13",
      "state": "1",
      "microServiceName": "订单服务",
      "microServiceAlias": "订单服务",
      "warn": 2,
      "error": 2
    }];

  public initMicroServiceStateList() {
    return this.microServiceStateList;
  }

}
