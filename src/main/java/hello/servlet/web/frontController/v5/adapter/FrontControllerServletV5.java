package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.ControllerV4;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
                            //모든 버전의 컨트롤러를 받을 수 있게 하기 위해 Object 로 선언
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerAdapterList.add(new ControllerV3HandlerAdapter());
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        Object handler = handlerMappingMap.get(requestURI);
        if (handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);   //404
            return;
        }

        MyHandlerAdapter adapter;

        for (MyHandlerAdapter myHandlerAdapter : handlerAdapterList) {
            if (myHandlerAdapter.support(handler)){
                adapter = myHandlerAdapter;
            }
        }
        throw new IllegalAccessException("handler adapter를 찾을 수 없습니다." + handler);

        ModelView modelView = adapter.handle(req, resp, handler);

        //논리이름을 물리 이름으로 변경
        String viewName = modelView.getViewName();   //논리이름
        MyView mv = new MyView("/WEB-INF/views/" + viewName + ".jsp");  //물리이름으로
        mv.render(modelView.getModel(), req, resp);
    }
}
