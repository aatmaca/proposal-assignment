/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import ayas.command.AddProposalCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import ayas.util.AyasUtil;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author 
 */
public class AddProposalController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        //PanelCommand panelCommand = new PanelCommand();
        //return panelCommand;
        AddProposalCommand proposalCommand = new AddProposalCommand();
        return proposalCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        AddProposalCommand proposalCommand = new AddProposalCommand();
        proposalCommand.setPanelCommand(panelCommand);
        Map map = new HashMap();
        map.put("newProposalCount", new Integer(1));
        return map;
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {

        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        AddProposalCommand proposalCommand = new AddProposalCommand();
        proposalCommand.setPanelCommand(panelCommand);
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newProposalCount", "necessary");

        if (errors.hasErrors()) {
            proposalCommand.setNewProposalCount(1);
            errors.reject("addProposal.notInteger");
            return;
        }
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
          
          PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
          AddProposalCommand proposalCommand = (AddProposalCommand) command;
          proposalCommand.setPanelCommand(panelCommand);
          
          dataService.addProposal(panelCommand, proposalCommand.getNewProposalCount() );          
          return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId() ));
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
