package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/responseHeader")
public class responseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //status line
        resp.setStatus(HttpServletResponse.SC_OK);

        //response header
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragme", "no-cache");
        resp.setHeader("my-header", "hello");   //사용자 지정 헤더

        resp.getWriter().write("ok");
    }

    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

    }
    private void cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("myCookie", "delicious");
        cookie.setMaxAge(600);  //쿠키 유효시간 " 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_FOUND);   //302 : 300번대 = redirect
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
