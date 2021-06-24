package cn.wolfcode.web.interceptor;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckPermissionInterceptor  implements HandlerInterceptor {

    @Autowired
    private IPermissionService permissionServicer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Employee employee = (Employee) request.getSession().getAttribute("employee_in_session");
        if (employee.getAdmin()) {
            //是管理员
            return true;
        }
        //不是管理员
        //拿到注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequirePermission annotation = handlerMethod.getMethodAnnotation(RequirePermission.class);
        //没有注解
        if (annotation==null){
            return true;
        }
        //方法有注解需要权限控制
        //获取当前方法的权限表达器
        String expression = annotation.expression();
        //查询当前访问员工的权限
        List<String> expressions = (List<String>) request.getSession().getAttribute("expression_in_session");

        if(expressions.contains(expression)){
            //访问的员工有权限
            return true;
        }
        //不是管理员 方法有权限控制 此员工没有访问权限
        response.sendRedirect("/permission/nopermission");

        return false;
    }
}
