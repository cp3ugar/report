package indi.xk.report.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author xk
 * @Date 2020/6/19 17:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class AjaxResponse {
    private boolean isOk;
    private int code;
    private String message;
    private Object data;

    public AjaxResponse(Object data){
        this.isOk = true;
        this.code = 200;
        this.message = "请求响应成功";
        this.data = data;
    }

    public AjaxResponse(String message){
        this.isOk = true;
        this.code = 200;
        this.message = message;
    }

    public AjaxResponse(int code,String message){
        this.isOk = true;
        this.code = code;
        this.message = message;
    }

    public AjaxResponse(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static AjaxResponse fail(int code){
        return new AjaxResponse(code,"请求响应失败");
    }

    public static AjaxResponse success(){
        return new AjaxResponse(200,"请求响应成功");
//        AjaxResponse ar = new AjaxResponse();
//        ar.setOk(true);
//        ar.setCode(200);
//        ar.setMessage("请求响应成功");
//        return ar;
    }

    public static AjaxResponse success(Object data){
        return new AjaxResponse(data);
//        AjaxResponse ar = new AjaxResponse();
//        ar.setOk(true);
//        ar.setCode(200);
//        ar.setMessage("请求响应成功");
//        ar.setData(data);
//        return ar;
    }

    public static AjaxResponse success(String message,Object data){
        return new AjaxResponse(200,message,data);
//        AjaxResponse ar = new AjaxResponse();
//        ar.setOk(true);
//        ar.setCode(200);
//        ar.setMessage(message);
//        ar.setData(data);
//        return ar;
    }
}
