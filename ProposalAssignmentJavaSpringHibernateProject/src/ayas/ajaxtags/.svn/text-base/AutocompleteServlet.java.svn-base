/*
 * Created on 22 Ağustos 2007 Çarşamba, 10:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ayas.ajaxtags;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ayas.business.DataService;

/**
 * An example servlet that responds to an ajax:autocomplete tag action. This
 * servlet would be referenced by the baseUrl attribute of the JSP tag.
 * <p>
 * This servlet should generate XML in the following format:
 * </p>
 * <code><![CDATA[<?xml version="1.0"?>
 * <list>
 *   <item value="Item1">First Item</item>
 *   <item value="Item2">Second Item</item>
 *   <item value="Item3">Third Item</item>
 * </list>]]></code>
 * 
 * @author Darren L. Spurgeon
 * @version $Revision: 28 $ $Date: 2008-11-10 00:12:33 +0200 (Pzt, 10 Kas 2008) $
 */
public class AutocompleteServlet extends BaseAjaxServlet {

    private DataService dataService;

    protected ApplicationContext getContext(ServletContext servletContext) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        final String targetBean = "dataService";
        final ApplicationContext ctx = getContext(config.getServletContext());

        if (targetBean == null || !ctx.containsBean(targetBean)) {
            throw new ServletException(targetBean + " not found in context.");
        }

        dataService = (DataService) ctx.getBean(targetBean, DataService.class);
    }

    /**
     * @see BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String getXmlContent(HttpServletRequest request, HttpServletResponse response) {
        String s = "";

        try {

            String partialData = request.getParameter("partialData");

            s = new AjaxXmlBuilder().addItem("ad soyad", "9999", true).toString();


        } catch (Exception e) {
            e.printStackTrace();
            s = new AjaxXmlBuilder().toString();
        }
        return s;
    }
}
