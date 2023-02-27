package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //key : url
    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3(){
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (controllerV3 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);   //404
            return;
        }

        //paramMap
        Map<String, String> paramMap = new HashMap<>();
        //파라미터 다 꺼내기
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        ModelView view = controllerV3.process(paramMap);

        //논리이름을 물리 이름으로 변경
        String viewName = view.getViewName();   //논리이름
        MyView mv = new MyView("/WEB-INF/views/" + viewName + ".jsp");  //물리이름으로
        mv.render(view.getModel(), req, resp);
    }
}
