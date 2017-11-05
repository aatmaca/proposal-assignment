/*
 * DefaultFilter.java
 *
 * Created on December 17, 2007, 5:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ayas.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import ayas.business.DataService;
import ayas.util.AyasUtil;

public class SessionControlFilter extends GenericFilterBean {

    private DataService dataService;

    protected ApplicationContext getContext(ServletContext servletContext) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    @Override
    protected void initFilterBean() throws ServletException {
        final String targetBean = "dataService";
        final ApplicationContext ctx = getContext(getServletContext());

        if (targetBean == null || !ctx.containsBean(targetBean)) {
            throw new ServletException(targetBean + " not found in context.");
        }

        dataService = (DataService) ctx.getBean(targetBean, DataService.class);

        super.initFilterBean();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        if (requestURI != null && requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length()).startsWith("login.htm")) {
            chain.doFilter(request, response);
            return;
        }

        if (httpRequest.getSession().getAttribute("sessionData") != null) {
            boolean check = AyasUtil.isSessionValid(httpRequest);

            if (!check) {
                httpResponse.sendRedirect("login.htm");
                return;
            }

        } else {

            httpResponse.sendRedirect("login.htm");
            return;
        }

        chain.doFilter(request, response);
    }
}