/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import ayas.business.DataService;
import javax.servlet.http.HttpServletRequest;
import ayas.command.RefereeSearchCommand;
import ayas.model.Company;
import ayas.model.Panelist;
import ayas.model.Subject;
import ayas.model.Title;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 
 */
public class SearchRefereeController extends SimpleFormController{
    private DataService dataService;
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RefereeSearchCommand refereeSearchCommand = new RefereeSearchCommand();
        refereeSearchCommand.setTitles(dataService.getTitles());
        refereeSearchCommand.setCompanies(dataService.getCompanies());
        
        if (request.getParameter("assignmentID") != null) {
            refereeSearchCommand.setSelectedAssignmentID(new Integer(request.getParameter("assignmentID")));
        }
        
        return refereeSearchCommand;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        return map;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {
       RefereeSearchCommand c1 = (RefereeSearchCommand) command;

       HttpSession session = request.getSession();
       SessionData sessionData = (SessionData) session.getAttribute("sessionData");
       if(!(c1.getName().equals("") || c1.getName().equals(null)))
       {
           try
           {
                sessionData.getNames().add(c1.getName());
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<String> l1 = (List<String>) k1;
               sessionData.setNames(l1);
               sessionData.getNames().add(c1.getName());
           }
       }
       else
       {
           try
           {
                sessionData.getNames().add(null);
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<String> l1 = (List<String>) k1;
               sessionData.setNames(l1);
               sessionData.getNames().add(null);
           }

       }

       if(!(c1.getSurname().equals("") || c1.getSurname().equals(null)))
       {
           try
           {
                sessionData.getSurnames().add(c1.getSurname());
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<String> l1 = (List<String>) k1;
               sessionData.setSurnames(l1);
               sessionData.getSurnames().add(c1.getSurname());
           }
       }
       else
       {
           try
           {
                sessionData.getSurnames().add(null);
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<String> l1 = (List<String>) k1;
               sessionData.setSurnames(l1);
               sessionData.getSurnames().add(null);
           }

           
       }

       if(c1.getSelectedCompany() != null && c1.getSelectedCompany() > 0)
       {
           try
           {
                sessionData.getCompanies().add(c1.getCompanies().get(c1.getSelectedCompany()));
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Company> l1 = (List<Company>) k1;
               sessionData.setCompanies(l1);
               sessionData.getCompanies().add(c1.getCompanies().get(c1.getSelectedCompany()));
           }
       }
       else
       {
           try
           {
                sessionData.getCompanies().add(null);
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Company> l1 = (List<Company>) k1;
               sessionData.setCompanies(l1);
               sessionData.getCompanies().add(null);
           }

       }

       if(c1.getSelectedSubject() != null && c1.getSelectedSubject() > 0)
       {
           try
           {
                sessionData.getSubjects().add(c1.getSubjects().get(c1.getSelectedSubject()));
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Subject> l1 = (List<Subject>) k1;
               sessionData.setSubjects(l1);
               sessionData.getSubjects().add(c1.getSubjects().get(c1.getSelectedSubject()));
           }
       }
       else
       {
           try
           {
                sessionData.getSubjects().add(null);
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Subject> l1 = (List<Subject>) k1;
               sessionData.setSubjects(l1);
               sessionData.getSubjects().add(null);
           }

       }


       if(c1.getSelectedTitle() != null && c1.getSelectedTitle() > 0)
       {
           try
           {
                sessionData.getTitles().add(c1.getTitles().get(c1.getSelectedTitle()));
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Title> l1 = (List<Title>) k1;
               sessionData.setTitles(l1);
               sessionData.getTitles().add(c1.getTitles().get(c1.getSelectedTitle()));
           }
       }
       else
       {
           try
           {
                sessionData.getTitles().add(null);
           }
           catch (Exception e)
           {
               List k1 = new ArrayList();
               List<Title> l1 = (List<Title>) k1;
               sessionData.setTitles(l1);
               sessionData.getTitles().add(null);
           }

       }


//       sessionData.getSubjects().add(c1.getSubjects().get(c1.getSelectedSubject()));
//       sessionData.getTitles().add(c1.getTitles().get(c1.getSelectedTitle()));
       session.setAttribute("sessionData", sessionData);

       if (request.getParameter("submitButton") == null) {
           errors.reject("adding to session");
           return;
       }
    }
    
     @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
          HttpSession session = request.getSession();
          SessionData sessionData = (SessionData) session.getAttribute("sessionData");
          RefereeSearchCommand c1 = (RefereeSearchCommand) command;
          c1.setNames(sessionData.getNames());
          c1.setSurnames(sessionData.getSurnames());
          c1.setSubjects(sessionData.getSubjects());
          c1.setTitles(sessionData.getTitles());
          c1.setCompanies(sessionData.getCompanies());
          List<Panelist> p1 =  dataService.doSearchRefereeOperation(c1);
         Map model = new HashMap();
        model.put("referees", p1);
        model.put("assignmentID", c1.getSelectedAssignmentID());
        return new ModelAndView("manager/RefereeSearch2", "model", model);

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
