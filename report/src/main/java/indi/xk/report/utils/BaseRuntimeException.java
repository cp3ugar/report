package indi.xk.report.utils;


/**
 * @Author xk
 * @Date 2019/12/23 14:27
 * @Version 1.0
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 8702302085609579029L;

	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	public ReturnObject outError(){
		return ReturnObject.outError(super.getMessage());
	}
}
