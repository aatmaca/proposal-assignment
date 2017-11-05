/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;


import ayas.business.DataService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;


import ayas.command.PanelConfigurationCommand;
import ayas.model.Panel;
import ayas.model.User;
import ayas.util.AyasUtil;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/** 
 *
 * @author 
 */
public class AddNewPanelController extends SimpleFormController {

    private DataService dataService;
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        PanelConfigurationCommand panelConfigurationCommand = new PanelConfigurationCommand();
        return panelConfigurationCommand;
    }
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        map.put("refereeCount", new Integer(7));
        map.put("proposalCount", new Integer(7));
        map.put("panelName",new String());
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
        PanelConfigurationCommand panelConfigurationCommand = (PanelConfigurationCommand) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proposalCount", "necessary");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "refereeCount", "necessary");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "panelName", "necessary");


        if (errors.hasErrors()) {
            panelConfigurationCommand.setProposalCount(7);
            panelConfigurationCommand.setRefereeCount(7);
            panelConfigurationCommand.setPanelName("");
            errors.reject("panel.notInteger");
            return;
        }

    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        PanelConfigurationCommand panelConfigurationCommand = (PanelConfigurationCommand) command;
        User user = AyasUtil.getSessionData(request).getSessionUser();

        Panel myPanel = dataService.doNewPanelOperations(user,panelConfigurationCommand);
        

        return new ModelAndView(new RedirectView("panel.htm?panelId=" + myPanel.getId()));
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