import { BaseReqVO, BaseRepVO, WinResponseData } from "../../common/vo/BaseVO";
import PageVO from "../../common/vo/PageVO";
/**
 *
 *
 * 公司数据接口
 *
 *
 * */
interface Company {
    companyId: string; //公司Id
    companyName: string; //公司全称
    companySimpleName: string; //公司简称
    companyCode: string; //公司编码,
    companyRoleArray: any[]; //公司下所有的角色
    companyUserArray: any[]; //公司下所有的用户
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
 *
 * 部门数据接口
 *
 *
 * */
interface Department {
    departmentId: string; //部门Id
    departmentname: string; //部门名称
    departmentArray: any[]; //公司下的所有部门
    departmentCode: string; //部门编码
}

/*
 *
 * *角色数据接口
 *
 *
 *
 * */
interface Role {
    companyId: string; //角色所属公司ID
    departmentId: string; //角色所属部门ID
    roleId: string; //角色Id
    role: string; //角色名称
    roleCode: string; //角色编码
    roleType: Array<RoleType>; //角色类型
    roleStatus: string[]; //角色状态
    roleMutex: string; //互斥角色
    roleCopy: string; //复制权限
    roleInfo: string; //权限描述
    rolesArray: any[]; //部门下所有角色
    roleType1: string;
    mutexRoleId: string;
}

interface RoleType {
    id: string;
    name: string;
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

/*
 *
 *
 * *用户查询接口
 *
 *
 * */

interface UserQuery {
    serialnumber: number; //表格序号
    id: string;
    userCode: string; //用户编码
    userName: string; //用户名称
    roleNames: [{}]; //隶属角色
    contactWay: string; //用户手机
    phoneNumber: string; //用户邮箱
    mailAddress: string; //用户类型
    departmentName: string; //所属部门名称
    departmentId: string; //所属部门ID
    status: string;
}

interface UserState {
    userStateText: string;
    userStateType: string;
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

/*
 *
 *
 * *用户查询接口
 *
 *
 * */

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

export interface CheckedItem {
    id: string;
    name: string;
}

export class UserReqVO extends BaseReqVO {
    public userSerachParam: string;
    machinePanelDataList: any[];
    machine: Machine = {
      id: "",
      ipAddress: "",
      name: ""
    };
    company: Company = {
        companyId: "",
        companyName: "",
        companySimpleName: "",
        companyCode: "",
        companyRoleArray: [],
        companyUserArray: []
    };
    department: Department = {
        departmentId: "",
        departmentname: "",
        departmentArray: [],
        departmentCode: ""
    };
    roleInfo: {};
    role: Role = {
        companyId: "",
        departmentId: "",
        roleId: "",
        role: "",
        roleCode: "",
        roleType: [
            { id: "1", name: "内部员工" },
            { id: "2", name: "外部员工" },
            { id: "3", name: "后台员工" }
        ], //角色类型
        roleStatus: ["正常"], //角色状态
        roleMutex: "", //互斥角色
        roleCopy: "", //复制权限
        roleInfo: "", //权限描述
        rolesArray: [],
        roleType1: "1",
        mutexRoleId: ""
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

    userArray: UserQuery[] = [];
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

/**
 *
 *
 * 添加公司模块继承公司接口
 *
 *
 *  */
export class CompanyClass implements Company {
    companyId: "";
    companyName: "请输入公司名称";
    companySimpleName: "请输入公司简称";
    companyCode: "请输入公司编码";
    companyRoleArray: [];
    companyUserArray: [];
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
 * 添加部门模块继承部门接口
 *
 *
 * */
export class DepartmentClass implements Department {
    departmentId: "请输入部门ID";
    departmentname: "请输入部门名称";
    departmentArray: any[];
    departmentCode: "请输入部门编码";
}

/**
 *
 *
 *
 * 添加角色模块继承角色接口
 *
 *
 *
 *  */
export class RoleClass implements Role {
    companyId: "";
    departmentId: "";
    roleId: "";
    role: "";
    roleCode: "";
    roleType: [];
    roleStatus: [];
    roleMutex: "";
    roleCopy: "";
    roleInfo: "";
    rolesArray: [];
    roleType1: "";
    mutexRoleId: "";
}

/**
 *
 *
 *
 * 添加用户模块继承用户接口
 *
 *
 *
 *  */
export class UserClass {
    id: ""; //用户Id
    userCode: "请输入用户Code"; //用户编码
    username: string; //用户名称
    tel: "请输入用户电话"; //用户联系方式
    pho: "请输入用户手机号码"; //用户手机
    email: "请输入用户邮箱"; //用户邮箱
    userType: string; //用户类型
    userState: number; // 用户状态
    departmentId: number; // 部门ID
    roleIds: Array<number>; // 用户角色
}
