package com.nhnacademy.Servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacademy.RequestDispatcher.ERROR_EXCEPTION;

@Slf4j
@WebServlet(name="frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
            } else {
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));


            com.nhnacademy.RequestDispatcher rd = (com.nhnacademy.RequestDispatcher) req.getRequestDispatcher("/error/jsp");
            rd.forward(req,resp);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;
        if("/student/list.do".equals(servletPath)){
            processingServlet="/student/list";
        }else if("/student/view.do".equals(servletPath)){
            processingServlet="/student/view";
        }else if("/student/update.do".equals(servletPath)){
            processingServlet="/student/update";
        }else if("/student/register.do".equals(servletPath)){
            processingServlet="/student/register";
        }else if("/error.do".equals(servletPath)){
            processingServlet="/error";
        }
        return processingServlet;
    }
}
