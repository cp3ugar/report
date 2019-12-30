package com.picc.insurance.controller;

import com.assist.excel.bean.Utils;
import com.picc.insurance.bean.dto.LoginToken;
import com.picc.insurance.utils.BaseRuntimeException;
import com.picc.insurance.utils.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    
    /**
     * @param e
     * @return
     * @throws RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ReturnObject<String> exception(HttpServletRequest request, HttpServletResponse response, RuntimeException e) {
        log.error(this.getClass() + " is errory, errorType=" + e.getClass(), e);
        e.printStackTrace();
        if (e instanceof BaseRuntimeException || e.getClass().getName().contains("BaseRuntimeException")) {
            log.error("e", e);
            BaseRuntimeException be = (BaseRuntimeException) e;
            return new ReturnObject<>(be.getErrorCode(), be.getMessage());
        }
        if (e instanceof DataIntegrityViolationException) {
            log.error("e", e);
            if (e instanceof DuplicateKeyException) {
                return new ReturnObject(ReturnObject.OTHER_ERROR, "数据不能重复");
            } else {
                return new ReturnObject<>(ReturnObject.OTHER_ERROR, "数据错误!");
            }
        }
        return ReturnObject.outError(null == e.getMessage() ? "操作异常" : "操作异常:" + e.getMessage());
    }


    /**
     * 表单提交对象参数验证
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ReturnObject<String> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        for (ObjectError err : errors) {
            errorMsg.append(err.getDefaultMessage()).append(";");
        }
        return ReturnObject.outError(errorMsg.toString());
    }

//    /**
//     * 表单提交对象参数验证
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ReturnObject<String> methodArgumentNotValidException(BindException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        List<ObjectError> errors = bindingResult.getAllErrors();
//        StringBuffer errorMsg = new StringBuffer();
//        for (ObjectError err : errors) {
//            errorMsg.append(err.getDefaultMessage()).append(",");
//        }
//        return ReturnObject.outError(errorMsg.toString());
//    }

    /**
     * 根据token获取用户登录信息
     *
     * @param token
     * @return
     */
    protected LoginToken getLoginInfoByToken(String token) {
        if (Utils.isNotEmpty(token)) {
            return (LoginToken) redisTemplate.opsForValue().get(token);
        }
        return null;
    }

    /**
     * 获取token信息
     *
     * @return
     */
    protected LoginToken getLoginInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (Utils.isNotEmpty(token)) {
            return (LoginToken) redisTemplate.opsForValue().get(token);
        }
        return null;
    }

    /**
     * 获取token
     *
     * @return
     */
    protected String getRequestToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        return token;
    }

}
