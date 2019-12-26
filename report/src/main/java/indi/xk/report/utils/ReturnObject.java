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

    private int code = 0;

    private String msg;

    private long count;

    private T data;

    public ReturnObject() {
    }

    public ReturnObject(T data) {
        this.code = 0;
        this.msg = "操作成功";
        this.count = 10;
        this.data = data;
    }

    public ReturnObject(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnObject(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnObject(int code, String msg, long count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static <T> ReturnObject<T> outError(String msg) {
        return new ReturnObject<T>(500, msg);
    }

    public static <T> ReturnObject<T> outSuccess(String msg) {
        return new ReturnObject<T>(0, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnObject{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
