import AxiosFun from "win-biz";
import { MicroServiceInfoRepVO } from "../vo/MachineInfoVO";

export default class MachineInfoDialogService {

    public insert(MicroServiceInfoRepVO: MicroServiceInfoRepVO) {
        return AxiosFun.post(
            AxiosFun.monitorCenterServiceName + "/microService/insertMicroService",
            MicroServiceInfoRepVO
        );
    }


    public update(MicroServiceInfoRepVO: MicroServiceInfoRepVO) {
        return AxiosFun.put(
            AxiosFun.monitorCenterServiceName + "/microService/updateMicroService",
            MicroServiceInfoRepVO
        );
    }


    public del(id: number) {
        return AxiosFun.winDelete(
            AxiosFun.monitorCenterServiceName + "/microService/deleteMicroService/" + id,
            {}
        );
    }
}
