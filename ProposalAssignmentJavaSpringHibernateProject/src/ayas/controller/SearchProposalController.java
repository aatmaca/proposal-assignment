/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import ayas.business.DataService;
import javax.servlet.http.HttpServletRequest;
import ayas.model.Proposal;
import ayas.command.ProposalSearchCommand;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author 
 */
public class SearchProposalController extends SimpleFormController {

    private DataService dataService;
    
    
   
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ProposalSearchCommand panelConfigurationCommand = new ProposalSearchCommand();

        
        if (request.getParameter("recordID") != null) {
            panelConfigurationCommand.setSelectedRecordID(new Integer(request.getParameter("recordID")));
        }
        
        return panelConfigurationCommand;
    }
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        map.put("filter", new String());
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filter", "necessary");
        if (errors.hasErrors()) {
            errors.reject("panel.notInteger");
            return;
        }

    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        ProposalSearchCommand proposalSearchCommand = (ProposalSearchCommand) command;
        List<Proposal> proposals = dataService.doSearchProposalOperation(proposalSearchCommand);
        Map model = new HashMap();
        model.put("proposals", proposals);
        model.put("recordID", proposalSearchCommand.getSelectedRecordID());
        return new ModelAndView("manager/ProposalSearch2", "model", model);
    }
    
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