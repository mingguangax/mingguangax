package cn.wolfcode.web.advice;

import cn.wolfcode.qo.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionAdvice {

    //处理异常的方法, 方法需要贴ExceptionHandler 注解

    @ExceptionHandler(RuntimeException.class)
    public String handlerException(Exception e, HttpServletResponse response, HandlerMethod handlerMethod, Model model) throws IOException {
        ResponseBody anno = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (anno!=null) {
            JsonResult jsonResult = new JsonResult(false, "系统异常,请稍后重试");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonResult));
            return null;
        }
        e.printStackTrace();
        model.addAttribute("msg","哎呀 出错了 尴尬");
        return "common/error";
    }

}
