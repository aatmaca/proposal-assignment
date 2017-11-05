/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class RowNumDecorator implements DisplaytagColumnDecorator {

    public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException {
        int pageSize = 20;
	
        Object pageSizeAtrribute = pageContext.getAttribute("pageSize");
        if(pageSizeAtrribute != null)
            pageSize = Integer.parseInt((String)pageSizeAtrribute);
        
        Integer myRownum = (Integer) pageContext.getRequest().getAttribute("my_rownum");
                if(myRownum == null){
                    myRownum = new Integer(0);
                }
        Integer pageNumber = (Integer) pageContext.getRequest().getAttribute("my_pagenumber");
        if(pageNumber == null){
            pageNumber = new Integer(1);
        }
        
        myRownum = new Integer(myRownum.intValue()+1);
        pageContext.getRequest().setAttribute("my_rownum",myRownum);
       
        return new Integer((pageNumber.intValue()-1) * pageSize + myRownum.intValue());
     }
    
} 