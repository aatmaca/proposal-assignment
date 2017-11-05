/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.controller;

import ayas.business.DataService;
import ayas.command.EmailCommand;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;


import ayas.command.helper.ProposalAndItsPanelists;
import ayas.model.AssignedPanelistToProposal;
import ayas.model.Panel;
import ayas.util.AyasUtil;
import ayas.util.EmailUtil;
import ayas.util.SessionData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author 
 */
public class MailPanelController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand currentPanelCommand = sessionData.getCurrentPanelCommand();
        Integer currentPanelId = currentPanelCommand.getPanelId();
        
        EmailCommand emailCommand = new EmailCommand();
        emailCommand.setFrom("panel@tubitak.gov.tr");
        emailCommand.setTo("");
        emailCommand.setIsSent(false);
        PanelCommand panelCommand = new PanelCommand();
        emailCommand.setPanelCommand(panelCommand);
        panelCommand.setPanel((Panel) dataService.loadObject(Panel.class, currentPanelId));
        panelCommand.setPanelId(currentPanelId);
        
        List<AssignedPanelistToProposal> assignments = dataService.listAllAssignmentsOfPanel(currentPanelId);
        panelCommand.setAllAssignments(assignments);

        boolean check;
        for (Iterator<AssignedPanelistToProposal> it = assignments.iterator(); it.hasNext();) {
            AssignedPanelistToProposal app = it.next();

            check = false;
            for (Iterator<ProposalAndItsPanelists> it1 = panelCommand.getProposals().iterator(); it1.hasNext();) {
                ProposalAndItsPanelists proposalAndItsPanelists = it1.next();
                if (proposalAndItsPanelists.getProposal().getRecordId().equals(app.getPanelistToProposalId().getAssignedProposal().getRecordId())) {
                    proposalAndItsPanelists.addPanelist(app);
                    check = true;
                    break;
                }
            }

            if (check == false) {
                ProposalAndItsPanelists paip = new ProposalAndItsPanelists();
                paip.setProposal(app.getPanelistToProposalId().getAssignedProposal());
                paip.addPanelist(app);
                panelCommand.getProposals().add(paip);
            }

        }
        emailCommand.setMessage(emailCommand.createMessage());
        
        return emailCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {

        // Update sessionData parameter: currentPanelCommand
//        SessionData sessionData = AyasUtil.getSessionData(request);
//        sessionData.setCurrentPanelCommand((PanelCommand) command);

        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {

        EmailCommand emailCommand = (EmailCommand) command;

        if(request.getParameter("sendPanel") != null){
            EmailUtil.sendMail(emailCommand.getFrom(), emailCommand.getTo(), emailCommand.getSubject(), emailCommand.getMessage());
            emailCommand.setIsSent(true);
        }

        errors.reject("error.returnToPage");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        return null;
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