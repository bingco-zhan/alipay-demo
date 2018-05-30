package com.bingCo.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends GenericServlet {

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse reps = (HttpServletResponse) arg1;
        String servletPath = req.getServletPath();
        if (servletPath.startsWith(getRoute())) {
            String method = servletPath.split(getRoute())[1];
            if (method.indexOf(".") >= 0) {
                method = method.substring(0, method.indexOf("."));
            }

            if (method != null && method.indexOf("!") >= 0) {
                method = method.split("!")[1];
            }

            if (method == null || method.length() <= 0) {
                method = "index";
            }

            try {
                Method invoke = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                invoke.invoke(this, req, reps);
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract String getRoute();

}
