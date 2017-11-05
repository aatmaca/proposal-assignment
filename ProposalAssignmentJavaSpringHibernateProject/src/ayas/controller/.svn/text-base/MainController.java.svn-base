package ayas.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import ayas.business.DataService;
import ayas.command.PanelCommand;
import ayas.command.helper.ProposalAndItsPanelists;
import ayas.model.AssignedPanelist;
import ayas.model.AssignedPanelistToProposal;
import ayas.model.AssignedProposal;
import ayas.util.AyasUtil;
import ayas.util.SessionData;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.servlet.view.RedirectView;

public class MainController extends MultiActionController {

    private DataService dataService;

    public ModelAndView handleGotoMainPage(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleGotoMainPage");

        //Object Data
        SessionData sessionData = AyasUtil.getSessionData(request);

        //Business Layer
        List panels = dataService.getAllPanelsOfUser(sessionData.getSessionUser().getId());

        // Do any operation you like...


        //Presentation Layer
        Map map = new HashMap();
        map.put("panelList", panels);

        return new ModelAndView("manager/MainPage", map);
    }

    public ModelAndView handleProposalAssigner(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleProposalAssigner");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        Integer id = Integer.parseInt(request.getParameter("proposalId"));

        Integer recordID = null;
        if (request.getParameter("recordID") != null && !request.getParameter("recordID").equals("")) {
            recordID = new Integer(request.getParameter("recordID"));
        }

        if (recordID == null) {
            for (Iterator<ProposalAndItsPanelists> it = panelCommand.getProposals().iterator(); it.hasNext();) {
                AssignedProposal assignedProposal = it.next().getProposal();
                if (assignedProposal.getProposal() == null) {
                    assignedProposal.setProposalId(id);
                    dataService.getAyasDAO().updateObject(assignedProposal);
                    break;
                }
            }
        } else {
             for (Iterator<ProposalAndItsPanelists> it = panelCommand.getProposals().iterator(); it.hasNext();) {
                AssignedProposal assignedProposal = it.next().getProposal();
                if (assignedProposal.getRecordId().equals(recordID)) {
                    assignedProposal.setProposalId(id);
                    dataService.getAyasDAO().updateObject(assignedProposal);
                    break;
                }
            }
        }

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleDeleteProposal(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleDeleteProposal");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        Integer recordId = Integer.parseInt(request.getParameter("recordId"));

        //Business Layer        
        dataService.deleteProposal(panelCommand, recordId);

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleAddProposal(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleAddProposal");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();

        //Business Layer        
        dataService.addProposal(panelCommand, 1 );

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleAddReferee(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleAddReferee");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();

        //Business Layer            
        dataService.addReferee(panelCommand, 1 );

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleDeleteReferee(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleDeleteReferee");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        Integer assignmentId = Integer.parseInt(request.getParameter("assignmentId"));

        //Business Layer        
        dataService.deleteReferee(panelCommand, assignmentId);

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleRefereeAssigner(HttpServletRequest request, HttpServletResponse response) {
        logger.info("********************************************handleRefereeAssigner");

        //Object Data
        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        Integer id = Integer.parseInt(request.getParameter("refereeId"));

        Integer assignmentID = null;
        if (request.getParameter("assignmentID") != null && !request.getParameter("assignmentID").equals("")) {
            assignmentID = new Integer(request.getParameter("assignmentID"));
        }

        if (assignmentID == null) {
            for (Iterator<AssignedPanelistToProposal> it = panelCommand.getProposals().iterator().next().getPanelists().iterator(); it.hasNext();) {
                AssignedPanelistToProposal assignedPanelistToProposal = it.next();
                if (assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist().getPanelistId() == null) {
                    AssignedPanelist panelist = assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist();
                    panelist.setPanelistId(id);
                    dataService.getAyasDAO().updateObject(panelist);
                    break;
                }
            }
        } else {
            for (Iterator<AssignedPanelistToProposal> it = panelCommand.getProposals().iterator().next().getPanelists().iterator(); it.hasNext();) {
                AssignedPanelistToProposal assignedPanelistToProposal = it.next();
                if (assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist().getAssignmentId().equals(assignmentID)) {
                    AssignedPanelist panelist = assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist();
                    panelist.setPanelistId(id);
                    dataService.getAyasDAO().updateObject(panelist);
                    break;
                }
            }
        }

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId()));
    }

    public ModelAndView handleLogout(HttpServletRequest request, HttpServletResponse response) {

        logger.info("********************************************handleLogout");

        java.util.Enumeration attributeList = request.getSession().getAttributeNames();
        while (attributeList.hasMoreElements()) {
            request.getSession().removeAttribute((String) attributeList.nextElement());
        }
        request.getSession().invalidate();

        logger.info("********************************************successfully logout");

        return new ModelAndView("common/Logout");
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}
