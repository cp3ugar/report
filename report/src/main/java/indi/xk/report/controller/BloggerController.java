package indi.xk.report.controller;

import indi.xk.report.pojo.AjaxResponse;
import indi.xk.report.pojo.Blogger;
import indi.xk.report.pojo.User;
import indi.xk.report.service.BloggerService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.ImageVerificationCode;
import indi.xk.report.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author xk
 * @Date 2020/8/20 17:55
 * @Version 1.0
 */
@Controller
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;

    @RequestMapping("getVerifyCode")
    @ResponseBody
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //用我们的验证码类，生成验证码类对象
        ImageVerificationCode ivc = new ImageVerificationCode();
        //获取验证码
        BufferedImage image = ivc.getImage();
        //将验证码的文本存在session中
        request.getSession().setAttribute("text", ivc.getText());
        //将验证码图片响应给客户端
        ImageVerificationCode.output(image, response.getOutputStream());
    }

    @RequestMapping("getCode")
    @ResponseBody
    public String Login_authentication(HttpServletRequest request) throws IOException{
        request.setCharacterEncoding("utf-8");
        //从session中获取真正的验证码
        String code = (String) request.getSession().getAttribute("text");
        return code;
    }

    @PostMapping("/register")
    @ResponseBody
    private ReturnObject register(@Validated Blogger blogger,HttpServletRequest request){
        String code = (String) request.getSession().getAttribute("text");
        if(!code.equals(blogger.getCode())){
            throw new BaseRuntimeException(500,"验证码不正确");
        }
        bloggerService.register(blogger);
        return ReturnObject.outSuccess("注册成功");
    }

    @RequestMapping("/blogLogin")
    @ResponseBody
    private ReturnObject login(Blogger blogger, HttpSession session){
        User user = bloggerService.login(blogger);
        if(user == null){
            return ReturnObject.outError("账号密码不正确");
        }
        session.setAttribute("userId",user.getUserId());
        return ReturnObject.outSuccess("登录成功");
    }

    @RequestMapping("/toBlog")
    private String toBlog(){
        return "jsp/blogger";
    }

    @RequestMapping("/toMyBlog")
    private String tomyBlog(){
        return "jsp/myBlog";
    }
}
