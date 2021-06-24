package cn.wolfcode.util;

import cn.wolfcode.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class UserContext {

    public static final String employee_in_session="employee_in_session";
    public static final String expression_in_session="expression_in_session";
    //获取session对象
    public static HttpSession getSession(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    //获取请求对象
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    //往session存入员工对象
    public static void setEmployee(Employee employee){
        getSession().setAttribute(employee_in_session,employee);
    }
    //取session中的员工对象
    public static Employee getEmployee(){
         return (Employee) getSession().getAttribute(employee_in_session);
    }

    //往session中存入权限对象
    public static void setExpressions(List<String> expressions){
        getSession().setAttribute(expression_in_session,expressions);
    }

    //往session中存入权限对象
    public static List<String> getExpressions(List<String> expressions){
       return (List<String>) getSession().getAttribute(expression_in_session);
    }
}
