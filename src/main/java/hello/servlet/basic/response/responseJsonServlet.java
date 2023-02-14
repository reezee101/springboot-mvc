package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/responseJson")
public class responseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUserName("jiwon");
        helloData.setAge(29);

        //objectMapper 사용하여 json으로 바꾸기
        //{"userName" : "jiwon", "age" : 29}
        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);

    }
}
