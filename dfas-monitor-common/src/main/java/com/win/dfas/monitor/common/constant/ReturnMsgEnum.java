package com.win.dfas.monitor.common.constant;

public enum ReturnMsgEnum {
	
	Default("操作成功!"), 
	Add("新增成功!"), 
	Edit("更新成功!"), 
	Remove("删除成功!"), 
	Check("审核成功!"), 
	Uncheck("反审核成功!"),
	Import("导入成功!"),
	Reset("重置成功!"),
	Upload("上传成功!"),
	ServerError("服务器异常!"),
	Reload("重新加载成功!"),
	Syn("同步成功!");
	
	ReturnMsgEnum(String msg) {
		this.msg = msg;
	}
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
