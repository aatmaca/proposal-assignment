/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.model.TableModel;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class RowNumTableDecorator extends TableDecorator {

    @Override
    public void init(PageContext pageContext, Object decorated, TableModel tableModel) {
        super.init(pageContext, decorated, tableModel);
        int pageNumber = 1;

        pageNumber = Integer.parseInt(pageContext.getRequest().getParameter(new ParamEncoder(tableModel.getId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        pageContext.getRequest().setAttribute("my_pagenumber", new Integer(pageNumber));

    }
} 