/****************************************************
 * 创建人：     @author zoujian
 * 创建时间: 2019年7月11日/下午5:52:56
 * 项目名称：  FRONTEND
 * 文件名称: ProdFundsAccountInfoDialogService
 * 文件描述: @Description: (交易对手方Service)
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/
import AxiosFun from "../../../api/AxiosFun";
import { MicroServiceInfoRepVO } from "../vo/MicroServiceInfoVO";

export default class MicroServiceInfoDialogService {
    /**
     * 新增交易对手
     * @param MicroServiceInfoRepVO
     */
    public insert(MicroServiceInfoRepVO: MicroServiceInfoRepVO) {
        return AxiosFun.post(
            AxiosFun.basicParameterServiceName + "/rival/rivalInfo/",
            MicroServiceInfoRepVO
        );
    }

    /**
     * 修改交易对手
     * @param MicroServiceInfoRepVO
     */
    public update(MicroServiceInfoRepVO: MicroServiceInfoRepVO) {
        return AxiosFun.put(
            AxiosFun.basicParameterServiceName + "/rival/rivalInfo/",
            MicroServiceInfoRepVO
        );
    }

    /**
     * 单个删除交易对手
     * @param id
     */
    public del(id: number) {
        return AxiosFun.winDelete(
            AxiosFun.basicParameterServiceName + "/rival/rivalInfo/" + id,
            {}
        );
    }
}