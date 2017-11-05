/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import ayas.command.helper.ProposalAndItsPanelists;
import ayas.util.AyasUtil;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author egemen
 */
public class ClearAssignmentsController extends SimpleFormController {
    private DataService dataService;
    Integer curPanelId;
    Integer numProposals;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();
        return panelCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
        PanelCommand panelCommand = (PanelCommand) command;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();
        curPanelId = panelCommand.getPanel().getId();
        //Find the record ids
        ArrayList<ProposalAndItsPanelists> assignments = (ArrayList<ProposalAndItsPanelists>) panelCommand.getProposals();
        Integer recordIds[] = new Integer[assignments.size()];
        int index = 0;
        Integer provisions[] = new Integer[assignments.size()];
        for (Iterator<ProposalAndItsPanelists> it1 = assignments.iterator(); it1.hasNext();) {
            //ProposalAndItsPanelists curAssignment = it1.next();
        }
        
        String returnUrl = "panel.htm?panelId=" + curPanelId;
        return new ModelAndView(new RedirectView(returnUrl));
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
