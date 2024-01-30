package com.nhnacademy.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName="characterEncodingFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name="encoding",value="UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter{
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(this.encoding);
        chain.doFilter(request,response);
    }
}
