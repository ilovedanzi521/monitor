/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-07-10 11:35:35
 * 文件名称: MicroServiceInfoVO.ts
 * 文件描述: @Description: 微服务 返回VO
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2019
 *
 ********************************************************/

import {BaseRepVO} from "../../common/vo/BaseVO";

/**
 * 类名称：MicroServiceInfoRepVO
 * 类描述：交易对手表 返回VO
 * 创建人：@author wangyaoheng
 * 创建时间：2019-07-10 11:35:35
 */
export class MicroServiceMachineRepVO extends BaseRepVO {

  /**
   * IP地址
   */
  public ipAddr: string;

  /**
   * 状态
   */
  public state: string;

  /**
   * 负载
   */
  public balance: string;

  /**
   * jvm
   */
  public jvm: string;

  /**
   * 告警数
   */
  public warn:string;

  /**
   * 错误数
   */
  public error:string;
}
