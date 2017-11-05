/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import ayas.command.ProposalProvisionsCommand;
import ayas.model.AssignedProposal;
import ayas.util.AyasUtil;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
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
public class EnterProposalProvisionsController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();

        Integer numProposals = panelCommand.getProposals().size();
        ProposalProvisionsCommand provisionsCommand = new ProposalProvisionsCommand(numProposals);
        List<AssignedProposal> proposals = dataService.listAllAssignedProposals(panelCommand.getPanelId());
        provisionsCommand.setProposals(proposals);
        return provisionsCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
        ProposalProvisionsCommand provisionsCommand = (ProposalProvisionsCommand) command;

        //TODO: Validation
        boolean validate = true;
        if (validate == false) {
            errors.reject("error.capacityError");
        }
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        ProposalProvisionsCommand provisionsCommand = (ProposalProvisionsCommand) command;
        dataService.saveOrUpdateAllObjects(provisionsCommand.getProposals());

        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();

//        ArrayList<ProposalAndItsPanelists> assignments = (ArrayList<ProposalAndItsPanelists>) panelCommand.getProposals();
//        int index = 0;
//        Integer provisions[] = provisionsCommand.getProposalProvisions();
//        for (Iterator<ProposalAndItsPanelists> it1 = assignments.iterator(); it1.hasNext();) {
//            ProposalAndItsPanelists curAssignment = it1.next();
//            curAssignment.getProposal().setProvision(provisions[index]);
//            index++;
//        }
//        sessionData.setCurrentPanelCommand(panelCommand);
//        this.dataService.updateProposalProvisions(curPanelId, provisionsCommand.getProposalProvisions(), recordIds);
        
        return new ModelAndView(new RedirectView("panel.htm?panelId=" +  panelCommand.getPanel().getId()));
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
