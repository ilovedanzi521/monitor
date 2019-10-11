import AxiosFun from "../../../api/AxiosFun";
import {MicroServiceStatusVO} from "../vo/MicroServiceStatusVO";


export default class HomeMicroServiceStatusService {

  private microServiceStatusList: Array<MicroServiceStatusVO> = [{
    "id": "2111",
    "state": "1",
    "ipAddress": "192.168.0.01111",
    "cupNum": 2,
    "memorySize": "128G",
    "diskSize": "32T",
    "cpuPer": "70%",
    "memoryPer": "30%",
    "diskPer": "60%"
  },
    {
      "id": "23311",
      "state": "1",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "23444311",
      "state": "2",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "234444444311",
      "state": "1",
      "ipAddress": "192.168.0.1",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "23444433444311",
      "state": "1",
      "ipAddress": "192.168.0.1",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "2344443343344311",
      "state": "1",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "2344443343344311",
      "state": "3",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "23444444334311",
      "state": "1",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "21131",
      "state": "1",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "21112",
      "state": "1",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "211312",
      "state": "2",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "2113412",
      "state": "2",
      "ipAddress": "192.168.0.0",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    },
    {
      "id": "2113412",
      "state": "3",
      "ipAddress": "192.168.0.1",
      "cupNum": 2,
      "memorySize": "128G",
      "diskSize": "32T",
      "cpuPer": "70%",
      "memoryPer": "30%",
      "diskPer": "60%"
    }];

  public initMicroServiceStatusList() {
    return this.microServiceStatusList;
  }

}
