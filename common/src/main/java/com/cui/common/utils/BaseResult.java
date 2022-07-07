package com.cui.common.utils;


import java.io.Serializable;
import java.util.List;

/**
 * <p>Tile:控制层消息返回基类</p>
 * <p>Description:</p>
 * <p>Email:babyluckyday@163.com</p>
 * @author 吴轶雷
 * @date 2016-3-11 下午9:12:58
 * @version 1.0
 */
public class BaseResult<T>  implements Serializable{
	
	/** 序列号 */
	private static final long serialVersionUID = -6274137768408671754L;

	/** 是否成功标识 */
	protected boolean success = false;
	
	/** 错误时的错误代码 */
	protected String errorCode;
	
	/** 错误时的错误信息 */
	protected String errorMsg;
	
	/** 返回的结果数据 */
	protected  T data;

	protected int code;
	
	/** 每页显示行数 */
	protected Integer rows = 10;
	
	/** 当前页数 */
	protected Object page = 1;
	
	/** 总记录数 */
	protected Object total;
	
	/** pc为了保持长效链接，前端当用户登陆后访问每个接口都应该带上uid和token，然后后台当方法执行结束后，应该重新设置token返回给前端的值*/
	protected String token;
	
	private Integer totalPage=0;
	
	private final static String ERRORCODE="500";
	
	public BaseResult(){}

	public BaseResult(boolean success,int code, T data) {
		super();
		this.success = success;
		this.code = code;
		this.data=data;
	}
	
	public BaseResult(boolean success,int code) {
		super();
		this.success = success;
		this.code = code;

	}
	
	public BaseResult(boolean success, T data) {
		super();
		this.success = success;

		this.data=data;
	}
	
	public BaseResult(boolean success, T data, Integer rows, Integer page,
                      Integer total) {
		super();
		this.success = success;
		this.data = data;
		this.rows = rows;
		this.page = page;
		this.total = total;
	}
	/**
	 * 直接设置错误信息，错误代码默认600
	 * @param errorMsg
	 */
	public BaseResult(String errorMsg) {
		super();
		this.errorCode = com.cui.common.utils.BaseResult.ERRORCODE;
		this.errorMsg = errorMsg;
	}
	
	/**
	 * 自己设置错误代码和错误信息
	 * @param errorCode
	 * @param errorMsg
	 */
	public BaseResult(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public BaseResult(int code, String errorMsg) {
		super();
		this.code = code;
		this.errorMsg = errorMsg;
	}

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(T data) {
		this.data = data;
		if(data instanceof List && rows ==null) {
			this.rows = 10;
		}
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Object getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Object getTotal() {
		return total;
	}

	public void setTotal(Object total) {
		this.total = total;
	}

	/** 重写toString */
	@Override
	public String toString() {
		return String.format("Result [errorCode=%s, errorMsg=%s, object=%s]",errorCode, errorMsg, data);
	}

	/**
	 * 
	 * <p>Description:失败处理</p>
	 * @date 2016年10月25日 下午12:56:29
	 * @author 吴轶雷
	 * @param errorCode 错误代码
	 * @param errorMsg 错误信息
	 * @return
	 */
	public static <T> com.cui.common.utils.BaseResult<T> fail(String errorCode, String errorMsg){
		 return new com.cui.common.utils.BaseResult<T>(errorCode,errorMsg);
	}

	public static <T> com.cui.common.utils.BaseResult<T> fail(int code, String errorMsg){
		return new com.cui.common.utils.BaseResult<T>(code,errorMsg);
	}

	/**
	 * 
	 * <p>Description:成功处理</p>
	 * @date 2016年10月25日 下午2:30:16
	 * @author 吴轶雷
	 * @param data
	 * @return
	 */

	public static <T> com.cui.common.utils.BaseResult<T> success(T data){
		 return new com.cui.common.utils.BaseResult<T>(true,data);
	}
	public static <T> com.cui.common.utils.BaseResult<T> success(int code,T data){
		return new com.cui.common.utils.BaseResult<T>(true,200,data);
	}

	public static <T> com.cui.common.utils.BaseResult<T> success(){
		 return new com.cui.common.utils.BaseResult<T>(true,null);
	}

	public Integer getTotalPage() {
		if(null!=total && !total.toString().equals("0")&& null!=rows && !rows.toString().equals("0")){
			Integer r = Integer.parseInt(rows.toString());
			Integer t = Integer.parseInt(total.toString());
			if(t%r==0){
				totalPage = t/r;
			}else{
				totalPage = t/r+1;
			}
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
