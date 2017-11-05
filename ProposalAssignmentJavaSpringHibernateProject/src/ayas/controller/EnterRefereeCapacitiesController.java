/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import ayas.command.RefereeCommand;
import ayas.model.AssignedPanelist;
import ayas.util.AyasUtil;
import org.springframework.validation.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author 
 */
public class EnterRefereeCapacitiesController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        PanelCommand panelCommand =  AyasUtil.getSessionData(request).getCurrentPanelCommand();

        RefereeCommand refereeCommand = new RefereeCommand();
        List<AssignedPanelist> panelists = dataService.listAllAssignedPanelists(panelCommand.getPanelId());
        refereeCommand.setPanelists(panelists);

        return refereeCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
        RefereeCommand refereeCommand = (RefereeCommand) command;

        List<AssignedPanelist> panelists = refereeCommand.getPanelists();
        int sumCapacity;
        for( int k = 0; k < panelists.size(); k++){
            AssignedPanelist ap = panelists.get(k);
            if (ap.getPanelistId() != null) {
                sumCapacity = dataService.getTotalCapacityOfReferee( ap.getPanelistId());
                if (sumCapacity > ap.getPanelist().maxCapacity) {
                    errors.reject("error.capacityError", "" + (k+1));
                }
            }
        }
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        RefereeCommand refereeCommand = (RefereeCommand) command;
        dataService.saveOrUpdateAllObjects(refereeCommand.getPanelists());

        return new ModelAndView(new RedirectView( "panel.htm?panelId=" + AyasUtil.getSessionData(request).getCurrentPanelCommand().getPanel().getId()));
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
