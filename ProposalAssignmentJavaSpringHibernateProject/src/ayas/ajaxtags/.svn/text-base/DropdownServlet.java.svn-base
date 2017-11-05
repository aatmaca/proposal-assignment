/**
 * Copyright 2005 Darren L. Spurgeon
 * Copyright 2007 Jens Kapitza
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * An example servlet that responds to an ajax:select tag action. This servlet would be referenced
 * by the baseUrl attribute of the JSP tag.
 * 
 * @author Darren L. Spurgeon
 * @author Jens Kapitza
 * @version $Revision: 1.7 $ $Date: 2007/07/22 18:29:45 $
 */
public class DropdownServlet extends BaseAjaxServlet {

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
     * @see  BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public String getXmlContent(HttpServletRequest request, HttpServletResponse response) {


        AjaxXmlBuilder xml = new AjaxXmlBuilder();

        Integer selectCriteria = new Integer(request.getParameter("selectCriteria"));


        for (int i = 0; i < 10; i++) {
            xml.addItem("id" + i, i + "");
        }


        return xml.toString();
    }
}
