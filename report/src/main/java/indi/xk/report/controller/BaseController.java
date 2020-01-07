package indi.xk.report.controller;

import indi.xk.report.constance.Constance;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.ReturnObject;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xk
 */
@ControllerAdvice
public class BaseController {
    /**
     * 异常捕获
     * @param
     * @return
     * @author xk
     * @date 2019/12/31  9:51
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ReturnObject<String> exception(HttpServletRequest request, HttpServletResponse response, RuntimeException e) {
            e.printStackTrace();
            if (e instanceof BaseRuntimeException || e.getClass().getName().contains(Constance.BRE)) {
                BaseRuntimeException be = (BaseRuntimeException) e;
                return new ReturnObject<>(be.getErrorCode(), be.getMessage());
            }
            if (e instanceof DataIntegrityViolationException) {
                if (e instanceof DuplicateKeyException) {
                    return ReturnObject.outError("数据不能重复！");
                } else {
                    return ReturnObject.outError("数据错误！");
                }
            }
        return ReturnObject.outError(null == e.getMessage() ? "操作异常！" : "操作异常:" + e.getMessage());
    }


    /**
     * 表单提交对象参数验证
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ReturnObject<String> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        for (ObjectError err : errors) {
            errorMsg.append(err.getDefaultMessage());
        }
        return ReturnObject.outError(errorMsg.toString());
    }

    /**
     * shiro异常捕获
     * @param
     * @return
     * @author xk
     * @date 2020/1/7  17:38
     */
    @ExceptionHandler(ShiroException.class)
    public String shiroException(HttpServletRequest request, HttpServletResponse response, ShiroException e) {
        e.printStackTrace();
        if (e instanceof UnauthorizedException) {
            return "noAuth";
        }
        return "toError";
    }
}
