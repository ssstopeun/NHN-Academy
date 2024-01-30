package com.nhnacademy.Servlet;

import com.nhnacademy.Controller.*;
import com.nhnacademy.init.ControllerFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacademy.RequestDispatcher.*;

@Slf4j
@WebServlet (name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect";
    private ControllerFactory controllerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controllerFactory = (ControllerFactory) config.getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            //todo 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
//            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            Command command = (Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
            String view = command.execute(req, resp);

            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));
                // todo  `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            log.error("error: {}", ex);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }

    }

//
//    private Command resolveCommand(String servletPath, String method){
//        Command command = null;
//        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentListController();
//        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentViewController();
//        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentDeleteController();
//        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentUpdateFormController();
//        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentUpdateController();
//        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentRegisterFormController();
//        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentRegisterController();
//        }else if("/error.do".equals(servletPath)){
//            command = new ErrorController();
//        }
//        return command;
//    }
}
