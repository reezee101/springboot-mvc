package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
* 1. 파라미터 전송 기능
* http://localhost:8080/requestParam?userName=hello&age=20
* */
@WebServlet(name = "requestParamServlet", urlPatterns = "/requestParam")
public class requestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전체 파라미터 조회
        req.getParameterNames().asIterator().forEachRemaining(paramName ->
                System.out.println(paramName + " = " + req.getParameter(paramName)));
                                    //key               value

        //단일 파라미터 조회(get, post 방식 둘 다 사용 가능 )
        System.out.println(req.getParameter("userName"));   //중복시 첫번째값 반환
        System.out.println(req.getParameter("age"));

        //이름이 같은 복수의 파라미터 조회
        String[] userNames = req.getParameterValues("userName");
        for(String str : userNames){
            System.out.println(str);
        }

        resp.getWriter().write("ok");
    }
}

