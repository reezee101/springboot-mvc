package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //반환 : void -> jsp 호출하는 MyView
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
