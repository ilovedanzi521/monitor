import {BaseReqVO} from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";


export class SysconfigVO {
  scrapeInterval : string;
  evaluationInterval : string;
  userArray: UserQuery[] = [];
}

interface UserQuery {
  serialnumber: number; //表格序号
  id: string;
  userCode: string; //用户编码
  userName: string; //用户名称
  roleNames: [{}]; //；隶属角色
  contactWay: string; //用户手机
  phoneNumber: string; //用户邮箱
  mailAddress: string; //用户类型
  departmentName: string; //用户状态
  status: string;
}

export class UserReqVO extends BaseReqVO {
  public userSerachParam: string;
  machinePanelDataList: any[];
  ipAddressSelect: any[] = [];
  alertTypeSelect: any[] = [];

  //dataRow : Object ;

  scrape: Scrape = {
    id: "",
    scrapeType : "" ,
    jobName: "",
    scheme: "",
    scrapeInterval: "",
    metricsPath: "",
    staticConfigsTargets: "",
    staticConfigsLabelsInstance: "",
    consulSdConfigsServer: "",
    consulSdConfigsServername: "",
    consulSdConfigsScheme: "",
  };

  alertRule: AlertRule = {
    id: "",
    groupsName: "",
    alertRuleType:"",
    ipAddress:"",
    name: "",
    expr: "",
    fortime: "",
    labelsSeverity: "",
    annotationsSummary: "",
    annotationsDescription: "",
  };



  user: User = {
    id: "",
    userCode: "",
    username: "",
    tel: "",
    pho: "",
    email: "",
    type: "",
    state: "",
    departmentName: "",
    stateArray: ["后台", "普通", "临时"]
  };
  formType: string = "";
  changeType: number = 1; //切换增加部门，增加公司，增加角色类型
  showTab: string = ""; //展示第一个公司信息的控制数据
  dialogFormVisible: boolean = false;
  isUserTypeDelete: string = "";

  //加载，刷新展示树结构的公司，部门，角色数据
  companArray: any[] = [];

  scrapeArray: UserQuery[] = [];
  alertRuleArray: UserQuery[] = [];
  userStateArray: UserState[] = [
    {
      userStateText: "显示正常用户",
      userStateType: "1"
    },
    {
      userStateText: "显示被冻结用户",
      userStateType: "2"
    },
    {
      userStateText: "显示已注销用户",
      userStateType: "3"
    }
  ];

  userTypeArray: Array<UserType> = [
    { userType: "1", userTypeName: "后台" },
    { userType: "2", userTypeName: "普通" },
    { userType: "3", userTypeName: "临时" }
  ];

  stateController: StateController = {
    switchingUserState: "1",
    switchFormType: "",
    switchCompany: true,
    switchDepartment: true,
    swichRoleRight: false,
    swicheRoleRight: false
  };

  roleRightArray: any[] = []; //加载权限
  checkArray: any[] = []; //选择权限
  changeRole: {}; //选择修改的角色

  reqParam: any = {};

  userPageVO: PageVO = new PageVO();
}

/*
 *
 *
 * *切换选中控制器
 *
 *
 * */

interface StateController {
  switchFormType: string;
  switchingUserState: string;
  switchCompany: boolean;
  switchDepartment: boolean;
  swichRoleRight: boolean;
  swicheRoleRight: boolean;
}

/**
 * 用户类别
 */
interface UserType {
  userType: string;
  userTypeName: string;
}


/*
 *
 *
 * *用户数据接口
 *
 *
 * */
interface User {
  id: string; //用户Id
  userCode: string; //用户编码
  username: string; //用户名称
  tel: string; //用户联系方式
  pho: string; //用户手机
  email: string; //用户邮箱
  type: string; //用户类型
  state: string; //用户类型索引
  stateArray: string[]; //用户状态
  departmentName: string;
}

interface UserState {
  userStateText: string;
  userStateType: string;
}


/**
 *
 *
 * 添加公司模块继承公司接口
 *
 *
 *  */
export class MachineClass implements Machine {
  id:"";
  ipAddress: "";
  name: "请输入机器名称";
}

/**
 *
 *
 * 机器数据接口
 *
 *
 * */
interface Machine {
  id: string; //机器Id
  ipAddress: string; //机器Ip
  name: string; //机器名称
}

/**
 *
 *
 * 添加公司模块继承公司接口
 *
 *
 *  */
export class ScrapeClass implements Scrape {
  id:"";
  scrapeType: "";
  jobName: "";
  scheme: "";
  scrapeInterval: "";
  metricsPath: "";
  staticConfigsTargets: "";
  staticConfigsLabelsInstance: "";
  consulSdConfigsServer: "";
  consulSdConfigsServername: "";
  consulSdConfigsScheme: "";
}

export class AlertRuleClass implements AlertRule {
  id:"";
  groupsName: "";
  alertRuleType : "";
  ipAddress:"";
  name: "";
  expr: "";
  fortime: "";
  labelsSeverity: "";
  annotationsSummary: "";
  annotationsDescription: "";
}

/**
 *
 *
 * 刮取数据接口
 *
 *
 * */
interface Scrape {
  id: string;
  scrapeType : string;
  jobName: string;
  scheme: string;
  scrapeInterval: string;
  metricsPath: string;
  staticConfigsTargets: string;
  staticConfigsLabelsInstance: string;
  consulSdConfigsServer: string;
  consulSdConfigsServername: string;
  consulSdConfigsScheme: string;
}

/**
 *
 *
 * 预警数据接口
 *
 *
 * */

interface AlertRule {
  id: string;
  groupsName: string;
  alertRuleType:string;
  ipAddress:string;
  name: string;
  expr: string;
  fortime: string;
  labelsSeverity: string;
  annotationsSummary: string;
  annotationsDescription: string;
}
