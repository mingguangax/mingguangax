package cn.wolfcode.web.interceptor;

import cn.wolfcode.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckIndexInterceptor implements HandlerInterceptor {


    @Autowired
    private IEmployeeService employeeService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //返回true 放行 返回false 不放行
        HttpSession session = request.getSession();
        if (session.getAttribute("employee_in_session")==null){
            response.sendRedirect("/static/login.html");
            return false;
        }else {
            //登录成功
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
