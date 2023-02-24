package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    //key : url
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4(){
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberFormControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);
        if (controllerV4 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);   //404
            return;
        }

        //paramMap
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        ModelView view = controllerV4.process(paramMap);

        String viewName = view.getViewName();
        MyView mv = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        mv.render(view.getModel(), req, resp);
    }
}
