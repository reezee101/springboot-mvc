package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")  //중복되면 안됨
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println(request);
        System.out.println(response);
        String userName = request.getParameter("userName");
        System.out.println(userName);

        response.setContentType("text/plane");  //헤더
        response.setCharacterEncoding("utf-8"); //헤더
        response.getWriter().write("Hello" + userName); //http body에 들어갈 값
    }
}