package cn.duyunzhi.security.bean;

import java.io.Serializable;
import java.util.Map;

/**
* @Author: DuYunZhi
* @Description: JsonAsynResult
* @Date: 2019/9/2 16:49
*/

public class JsonAsynResult implements Serializable{
	/**  @Fields SUCCESS_CODE: 成功响应吗 **/ 
	public static Integer SUCCESS_OPERATER_CODE = 200;
	
	/**  @Fields SUCCESS_CODE: 成功响应吗 **/ 
	public static Integer SUCCESS_EXCEPTION_CODE = 100;

	public static String SUCCESS_MAP = "success";

	public static String FAILURES_MAP = "failures";

	private static final long serialVersionUID = 1L;
	
	/** 
	* @Title: createSucResult 
	* @Description: 创建success result 
	* @param @return 
	* @return JsonAsynResult 
	* @throws 
	*/
	public static JsonAsynResult createSucResult() {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_OPERATER_CODE);
		result.setMessage("操作成功！");
		
		return result;
	}
	/** 
	* @Title: createSucResult 
	* @Description: 创建success result 
	* @param @return 
	* @return JsonAsynResult 
	* @throws 
	*/
	public static JsonAsynResult createSucResult(Object obj) {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_OPERATER_CODE);
		result.setMessage("操作成功！");
		result.setData(obj);
		return result;
	}
	
	/** 
	* @Title: createSucResult 
	* @Description:
	* @param @return 
	* @return JsonAsynResult 
	* @throws 
	*/
	public static JsonAsynResult createSucResult(String msg) {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_OPERATER_CODE);
		result.setMessage(msg);
		
		return result;
	}
	
	
	/** 
	* @Title: createErrorResult 
	* @Description:
	* @param @return 
	* @return JsonAsynResult 
	* @throws 
	*/
	public static JsonAsynResult createErrorResult() {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_EXCEPTION_CODE);
		result.setMessage("操作失败！");
		
		return result;
	}
	
	
	/** 
	* @Title: createErrorResult 
	* @Description:
	* @param @return 
	* @return JsonAsynResult 
	* @throws 
	*/
	public static JsonAsynResult createErrorResult(String error) {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_EXCEPTION_CODE);
		result.setMessage(error);
		
		return result;
	}

	public static JsonAsynResult createSucResult(Map<String, Integer> map) {
		JsonAsynResult result = new JsonAsynResult();
		result.setCode(SUCCESS_OPERATER_CODE);
		map.putIfAbsent(SUCCESS_MAP, 0);
		map.putIfAbsent(FAILURES_MAP, 0);
		result.setMessage("成功"+map.get(SUCCESS_MAP)+"条，失败"+map.get(FAILURES_MAP)+"条");
		return result;
	}

	/**  @Fields code: 异步返回响应吗 **/ 
	private Integer code;
	
	/**  @Fields message: 异步返回响应信息 **/ 
	private String message;

	/**  @Fields message: 异步返回数据 **/ 
	private Object data;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
