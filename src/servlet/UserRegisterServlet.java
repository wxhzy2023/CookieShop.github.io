package servlet;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "user_register",urlPatterns = "/user_rigister")
public class UserRegisterServlet extends HttpServlet {
    private UserService uService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, req.getParameterMap());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(uService.register(user)) {
            req.setAttribute("msg", "注册成功，请登录！");
            req.getRequestDispatcher("user_login.jsp").forward(req, resp);
        }else {
            req.setAttribute("msg", "用户名或邮箱重复，请重新填写！");
            req.getRequestDispatcher("user_register.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
