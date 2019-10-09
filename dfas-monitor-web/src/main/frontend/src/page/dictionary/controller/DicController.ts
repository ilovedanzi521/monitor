import Component from "vue-class-component";
import { WinRspType } from "../../common/enum/BaseEnum";
import { WinResponseData } from "../../common/vo/BaseVO";
import { DicReqVO, DicRepVO } from "../vo/DicVO";
import DicService from "../service/DicService";
import BaseController from "../../common/controller/BaseController";
import PageVO from "../../common/vo/PageVO";

@Component({ components: {} })
export default class DicController extends BaseController {
    private dicService: DicService = new DicService();

    private dicReqVO: DicReqVO = new DicReqVO();
    private dicSubReqVO: DicReqVO = new DicReqVO();

    private dicList: DicRepVO[] = [];
    private dicSubList: DicRepVO[] = [];

    private dicPageVO: PageVO = new PageVO();
    private dicSubPageVO: PageVO = new PageVO();

    /**
     * 搜索(按钮点击)
     */
    public search() {
        this.dicReqVO.reqPageNum = 1;
        this.serachDicList(this.dicReqVO);
    }

    /**
     * 查询字典项信息
     *
     */
    public serachDicList(vo: DicReqVO) {
        this.dicService.diclist(vo).then((winResponseData: WinResponseData) => {
            if (WinRspType.SUCC === winResponseData.winRspType) {
                this.dicList = winResponseData.data.list;
                this.dicPageVO = winResponseData.data;

                if (this.dicList && this.dicList.length > 0) {
                    const dicTableObj: any = this.$refs.dicTable;

                    dicTableObj.setCurrentRow(this.dicList[0]);

                    this.dicSubReqVO = new DicReqVO();
                    this.dicSubReqVO.parentDicCode = this.dicList[0].dicCode;
                }

                this.dicSubReqVO.commonParam = this.dicReqVO.commonParam;
                this.dicSubReqVO.reqPageSize = this.dicSubPageVO.pageSize;
                this.serachDicSubList(this.dicSubReqVO);
            } else {
                this.errorMessage(winResponseData.msg);
            }
        });
    }

    /**
     * 查询字典子项信息
     *
     * @param vo
     */
    public serachDicSubList(vo: DicReqVO) {
        this.dicService
            .dicSubList(vo)
            .then((winResponseData: WinResponseData) => {
                if (WinRspType.SUCC === winResponseData.winRspType) {
                    this.dicSubList = winResponseData.data.list;
                    this.dicSubPageVO = winResponseData.data;
                } else {
                    this.errorMessage(winResponseData.msg);
                }
            });
    }

    /**
     * 点击字典项查询字典子项信息
     *
     * @param item
     */
    public handleDicRow({ row }) {
        this.dicSubReqVO = new DicReqVO();
        this.dicSubReqVO.parentDicCode = row.dicCode;
        this.dicSubReqVO.reqPageSize = this.dicSubPageVO.pageSize;

        this.serachDicSubList(this.dicSubReqVO);
    }

    /**
     * 字典项分页查询
     *
     * @param vo
     */
    public dicPageQuery(vo: PageVO) {
        this.dicReqVO.reqPageNum = vo.pageNum;
        this.dicReqVO.reqPageSize = vo.pageSize;

        this.serachDicList(this.dicReqVO);
    }

    /**
     * 字典子项分页查询
     *
     * @param vo
     */
    public dicSubPageQuery(vo: PageVO) {
        this.dicSubReqVO.reqPageNum = vo.pageNum;
        this.dicSubReqVO.reqPageSize = vo.pageSize;

        this.serachDicSubList(this.dicSubReqVO);
    }

    private mounted() {
        this.dicReqVO.reqPageSize = 20;
        this.dicSubPageVO.pageSize = 20;
        this.serachDicList(this.dicReqVO);
    }
}
