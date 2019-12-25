package indi.xk.report.utils;

/**
 * @Author xk
 * @Date 2019/12/23 14:27
 * @Version 1.0
 */
public class ReturnObject<T> {
    public static final int IS_RIGHT = 0;
    public static final int LOGIN_TIMEOUT = 501;
    public static final int SERVICE_ERROR = 502;
    public static final int CONTROLLER_ERROR = 503;
    public static final int OTHER_ERROR = 504;
    public static final int MISS_ERROR = 500;

    private int status = 0;

    private String message;

    private T data;

    public ReturnObject() {
    }

    public ReturnObject(T data) {
        this.status = 0;
        this.message = "操作成功";
        this.data = data;
    }

    public ReturnObject(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReturnObject(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ReturnObject<T> outError(String message) {
        return new ReturnObject<T>(500, message);
    }

    public static <T> ReturnObject<T> outSuccess(String message) {
        return new ReturnObject<T>(0, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
