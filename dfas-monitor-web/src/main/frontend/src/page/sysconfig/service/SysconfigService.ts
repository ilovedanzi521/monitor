import { WinResponseData } from "../../common/vo/BaseVO";
import { SysconfigVO } from "../vo/SysconfigVO";
import AxiosFun from "../../../api/AxiosFun";

/**
 * 系统配置页面获取数据
 */
export default class SysconfigService {
  
  static getScrapeDataList(vo): Promise<WinResponseData> {
      console.log("getScrapeDataList");
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/scrape/scrapePage",
      vo
    );
  }

  /**添加机器 */
  static addScrape(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/scrape/add",
      vo
    );
  }

  //编辑机器
  static editScrape(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/scrape/modify",
      vo
    );
  }

  //删除机器
  static deleteScrape(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/scrape/delete",
      vo
    );
  }

  /** 批量删除 */
  static delScrapeBatch(ids: string) {
    return AxiosFun.winDelete(
      AxiosFun.monitorCenterServiceName + "/scrape/batchDelete/" + ids
    );
  }

  /***********************************************************************************/
  static getAlertRuleDataList(vo): Promise<WinResponseData> {
    console.log("getAlertRuleDataList");
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/alertRule/alertRulePage",
      vo
    );
  }

  /**添加机器 */
  static addAlertRule(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/alertRule/add",
      vo
    );
  }

  //编辑机器
  static editAlertRule(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/alertRule/modify",
      vo
    );
  }

  //删除机器
  static deleteAlertRule(vo: SysconfigVO): Promise<WinResponseData> {
    return AxiosFun.post(
      AxiosFun.monitorCenterServiceName + "/alertRule/delete",
      vo
    );
  }

  /** 批量删除 */
  static delAlertRuleBatch(ids: string) {
    return AxiosFun.winDelete(
      AxiosFun.monitorCenterServiceName + "/alertRule/batchDelete/" + ids
    );
  }

}
