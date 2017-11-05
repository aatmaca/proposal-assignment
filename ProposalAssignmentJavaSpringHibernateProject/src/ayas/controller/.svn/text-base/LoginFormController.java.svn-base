/*
 * LoginFormController.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ayas.controller;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import java.util.Map;
import java.util.HashMap;

import ayas.business.DataService;
import ayas.command.UserCommand;
import ayas.model.Proposal;
import ayas.model.User;
import ayas.util.SessionData;
import org.springframework.validation.ValidationUtils;

public class LoginFormController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
        return super.handleRequestInternal(request, arg1);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        UserCommand userCommand = new UserCommand();
        return userCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        request.getSession().removeAttribute("sessionData");

        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
        UserCommand userCommand = (UserCommand) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "necessary");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "necessary");

        if (errors.hasErrors()) {
            userCommand.setUserName("");
            userCommand.setPassword("");
            errors.reject("error.login");
            return;
        }

        if (!errors.hasErrors()) {

            HttpSession session = request.getSession();
            SessionData sessionData = (SessionData) session.getAttribute("sessionData");
            User user;

            if (sessionData == null) {

                user = dataService.login(userCommand.getUserName(), userCommand.getPassword());

                if (user != null) {
                    logger.info("login successfull...");

                    sessionData = new SessionData();
                    sessionData.setSessionUser(user);
                    sessionData.setSessionID(session.getId());
                    sessionData.setProposalList(dataService.getAyasDAO().listObjects(Proposal.class));
                    session.setAttribute("sessionData", sessionData);

                } else {
                    errors.reject("error.login");
                }
            } else {
                if (!sessionData.getSessionID().equals(session.getId())) {
                    errors.reject("error.login");
                }
            }
        }
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        logger.info("***********************************************login form controller is now redirecting to role-related main page ...");

        return new ModelAndView(new RedirectView("gotoMainPage.htm"));
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}