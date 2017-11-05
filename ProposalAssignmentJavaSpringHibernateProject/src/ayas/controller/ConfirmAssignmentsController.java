/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import ayas.util.AyasUtil;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class ConfirmAssignmentsController extends SimpleFormController {
     private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();
        return panelCommand;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        //Map model = dataService.confirmAllPairs((PanelCommand)command); 
        return new ModelAndView("manager/missingPairs");
    }

    /**
     * @return the dataService
     */
    public DataService getDataService() {
        return dataService;
    }

    /**
     * @param dataService the dataService to set
     */
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}
