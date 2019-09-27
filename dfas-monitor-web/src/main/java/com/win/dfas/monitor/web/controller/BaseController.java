package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.ReturnMsgEnum;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.MessageUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
	* web层通用数据处理
	*
	* @author ruoyi
	*/

public class BaseController {
	/**
	* 将前台传递过来的日期格式的字符串，自动转化为Date类型
	*/

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
		* 封装返回数据
		* @param data
		* @return
		*/

	public String successData(Object data) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("data", data);
		returnMessage.put("code", 0);
		returnMessage.put("msg", "success");
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 封装返回数据
		* @param data
		* @return
		*/

	public String successData(Object data, String message) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("data", data);
		returnMessage.put("code", 0);
		returnMessage.put("msg", message);
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 封装返回数据
		* @param data
		* @return
		*/

	public String successData(Object data, ReturnMsgEnum returnMsg) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("data", data);
		returnMessage.put("code", 0);
		returnMessage.put("msg", returnMsg.getMsg());
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 封装异常数据
		* @param e
		* @return
		*/

	public String failData(Exception e) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("code", 1);
		returnMessage.put("msg", e.getMessage());
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 封装异常数据
		* @param message
		* @return
		*/

	public String failData(String message) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("code", 1);
		returnMessage.put("msg", message);
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 	封装异常数据
		* @param code
		* @return
		*/

	public String failData(String code, Object[] args) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("code", 1);
		String message = MessageUtils.message(code, args);
		returnMessage.put("msg", message);
		return JsonUtil.toJson(returnMessage);
	}

	/**
		* 封装异常数据
		* @param returnMsg
		* @return
		*/
	public String failData(ReturnMsgEnum returnMsg) {
		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("code", 1);
		returnMessage.put("msg", returnMsg.getMsg());
		return JsonUtil.toJson(returnMessage);
	}

}
