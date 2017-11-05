/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.business;

import ayas.command.KeywordSearchCommand;
import ayas.command.PanelCommand;
import ayas.command.PanelConfigurationCommand;
import ayas.command.helper.ProposalAndItsPanelists;
import ayas.command.ProposalSearchCommand;
import ayas.command.RefereeSearchCommand;
import ayas.command.helper.PanelistAndHisProposals;
import ayas.dao.AyasDAO;
import ayas.model.AssignedPanelist;
import ayas.model.AssignedPanelistToProposal;
import ayas.model.AssignedProposal;
import ayas.model.Panel;
import ayas.model.User;
import ayas.model.Proposal;
import ayas.model.Company;
import ayas.model.Panelist;
import ayas.model.Title;
import ayas.model.Subject;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataService {

    private AyasDAO ayasDAO;

    public List listAllAssignmentsOfPanel(Integer currentPanelId) {
        return getAyasDAO().listAllAssignmentsOfPanel(currentPanelId);
    }

    public List listAllAssignedProposals(Integer panelId) {
        return getAyasDAO().listAllAssignedProposals(panelId);
    }

    public User login(String userName, String password) {
        return getAyasDAO().login(userName, password);
    }

    public void saveOrUpdateAllObjects(List list) {
        getAyasDAO().saveOrUpdateAllObjects(list);
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getAyasDAO().loadObject(clazz, id);
    }

    public void removeObject(Object object) {
        getAyasDAO().removeObject(object);
    }

    /**
     * @return the ayasDAO
     */
    public AyasDAO getAyasDAO() {
        return ayasDAO;
    }

    /**
     * @param ayasDAO the ayasDAO to set
     */
    public void setAyasDAO(AyasDAO ayasDAO) {
        this.ayasDAO = ayasDAO;
    }

    public List getAllPanelsOfUser(Integer id) {
        return getAyasDAO().getAllPanelsOfUser(id);
    }

    public void saveObject(Object obj) {
        getAyasDAO().saveObject(obj);
    }

    public void addProposal(PanelCommand command, int newProposalCount) {

        List<ProposalAndItsPanelists> paips = command.getProposals();
        for (int i = 0; i < newProposalCount; i++) {
            AssignedProposal assignedProposal = new AssignedProposal();
            assignedProposal.setPanelId(command.getPanelId());
            saveObject(assignedProposal);

            for (Iterator<AssignedPanelistToProposal> it = paips.get(0).getPanelists().iterator(); it.hasNext();) {
                AssignedPanelistToProposal assignedPanelistToProposal = it.next();
                AssignedPanelistToProposal newaptp = new AssignedPanelistToProposal(assignedProposal.getRecordId(), assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist().getAssignmentId());
                saveObject(newaptp);
            }
        }
    }

    public void deleteProposal(PanelCommand command, int recordId) {

        List<ProposalAndItsPanelists> assignedProposalList = command.getProposals();

        for (int i = 0; i < assignedProposalList.size(); i++) {
            AssignedProposal assignedProposal = assignedProposalList.get(i).getProposal();
            if (assignedProposal.getRecordId() == recordId) {
                assignedProposalList.remove(i);
                removeObject(assignedProposal);
                return;
            }
        }
    }

    public void addReferee(PanelCommand command, int newRefereeCount) {

        List<ProposalAndItsPanelists> assignedProposalList = command.getProposals();
        List<AssignedPanelistToProposal> assignedPanelistList = assignedProposalList.iterator().next().getPanelists();

        for (int i = 0; i < newRefereeCount; i++) {
            AssignedPanelist panelist = new AssignedPanelist();
            panelist.setPanelId(command.getPanelId());
            saveObject(panelist);

            for (int k = 0; k < assignedProposalList.size(); k++) {
                Integer recordId = assignedProposalList.get(k).getProposal().getRecordId();
                AssignedPanelistToProposal newPanelist = new AssignedPanelistToProposal(recordId, panelist.getAssignmentId());

                assignedPanelistList.add(newPanelist);
                saveObject(newPanelist);
            }
        }
    }

    public void deleteReferee(PanelCommand command, int assignmentId) {

        List<AssignedPanelistToProposal> assignedPanelistList = command.getProposals().iterator().next().getPanelists();

        for (int i = 0; i < assignedPanelistList.size(); i++) {
            AssignedPanelist assignedPanelist = assignedPanelistList.get(i).getPanelistToProposalId().getAssignedPanelist();

            if (assignedPanelist.getAssignmentId().intValue() == assignmentId) {
                removeObject(assignedPanelist);
                return;
            }
        }
    }

    public Panel doNewPanelOperations(User user, PanelConfigurationCommand command) {

        Panel myPanel = new Panel();
        myPanel.setName(command.getPanelName());
        myPanel.setOwner(user.getId());
        saveObject(myPanel);

        List<Integer> assignedProposalList = new ArrayList();
        for (int i = 0; i < command.getProposalCount(); i++) {
            AssignedProposal assignedProposal = new AssignedProposal();
            assignedProposal.setPanelId(myPanel.getId());
            saveObject(assignedProposal);
            assignedProposalList.add(assignedProposal.getRecordId());
        }

        for (int i = 0; i < command.getRefereeCount(); i++) {
            AssignedPanelist assignedPanelist = new AssignedPanelist();
            assignedPanelist.setPanelId(myPanel.getId());
            saveObject(assignedPanelist);
            for (Iterator<Integer> it = assignedProposalList.iterator(); it.hasNext();) {
                Integer recordId = it.next();
                saveObject(new AssignedPanelistToProposal(recordId, assignedPanelist.getAssignmentId()));
            }
        }
        return myPanel;
    }

    public void savePanelCompletely(PanelCommand panelCommand) {
        for (Iterator it = panelCommand.getAllAssignments().iterator(); it.hasNext();) {
            updateObject(it.next());
        }
        confirmAllPairs(panelCommand);
    }

    private void updateObject(Object obj) {
        getAyasDAO().updateObject(obj);
    }

    public List listAllAssignedPanelists(Integer currentPanelId) {
        return getAyasDAO().listAllAssignedPanelists(currentPanelId);
    }

    public int updateRefereeCapacities(Integer[] capacities, Integer[] assignmentIds) {
        return getAyasDAO().updateRefereeCapacities(capacities, assignmentIds);
    }

    public int updateProposalProvisions(Integer[] provisions, Integer[] recordIds) {
        return getAyasDAO().updateProposalProvisions(provisions, recordIds);
    }

    public List getAllAssignedIds(Integer[] recordIds) {
        return getAyasDAO().getAllAssignedIds(recordIds);
    }

    public List getRefereeCapacities(Integer panelId) {
        return getAyasDAO().getRefereeCapacities(panelId);
    }

    public int getTotalCapacityOfReferee(Integer panelistId) {
        return getAyasDAO().getTotalCapacityOfReferee(panelistId);
    }

    public List<Proposal> doSearchProposalOperation(ProposalSearchCommand command) {
        return getAyasDAO().getSearchedProposals(command.getFilter());

    }

    public List<Proposal> doSearchKeywordOperation(KeywordSearchCommand command) {
        return getAyasDAO().getSearchedProposalsKeyword(command.getFilter());

    }

    public List<Panelist> doSearchRefereeOperation(RefereeSearchCommand command) {
        return getAyasDAO().getSearchedReferee(command.getNames(), command.getSurnames(), command.getCompanies(), command.getTitles(), command.getSubjects());
    }

    public void updateAll(List list) {
        for (Iterator it = list.iterator(); it.hasNext();) {
            updateObject(it.next());
        }
    }

    public List<Company> getCompanies() {
        return getAyasDAO().getAllCompanies();

    }

    public List<Title> getTitles() {
        return getAyasDAO().getAllTitles();

    }

    public List<Subject> getSubjects() {
        return getAyasDAO().getAllSubjects();

    }

    public void confirmAllPairs(PanelCommand panelCommand) {

        List<PanelistAndHisProposals> list = new ArrayList<PanelistAndHisProposals>();

        for (Iterator<AssignedPanelistToProposal> it = panelCommand.getAllAssignments().iterator(); it.hasNext();) {
            AssignedPanelistToProposal aptp = it.next();
            if ("1".equals(aptp.getStatus())) {
                getPanelistAndHisProposals(list, aptp);
            }
        }

        int proposalCount = panelCommand.getProposalCount();

        int[][] matrix = new int[proposalCount][proposalCount];

        Map proIdConvertion = new HashMap();
        Map reverse = new HashMap();
        Integer counter = 0;
        for (Iterator<ProposalAndItsPanelists> it = panelCommand.getProposals().iterator(); it.hasNext();) {
            ProposalAndItsPanelists pap = it.next();
            reverse.put(counter, pap.getProposal().getRecordId());
            proIdConvertion.put(pap.getProposal().getRecordId(), counter++);

        }

        for (Iterator<PanelistAndHisProposals> it = list.iterator(); it.hasNext();) {
            PanelistAndHisProposals panelistAndHisProposals = it.next();
            for (Iterator<AssignedProposal> it1 = panelistAndHisProposals.getProposals().iterator(); it1.hasNext();) {
                AssignedProposal assignedProposal = it1.next();
                for (Iterator<AssignedProposal> it2 = panelistAndHisProposals.getProposals().iterator(); it2.hasNext();) {
                    AssignedProposal pair = it2.next();
                    matrix[(Integer) proIdConvertion.get(assignedProposal.getRecordId())][(Integer) proIdConvertion.get(pair.getRecordId())] = 1;
                }
            }
        }

        String missingPairs = "Missing proposal pairs are ";
        boolean miss = false;
        for (int i = 0; i < proposalCount; i++) {
            for (int j = i + 1; j < proposalCount; j++) {
                if (matrix[i][j] == 0) {
                    miss= true;
                    missingPairs += "(" + (Integer) reverse.get(i) + "-" + (Integer) reverse.get(j) + "),";
                }
            }
        }

        if (miss) {
            missingPairs= missingPairs.substring(0, missingPairs.length()-1);
        }else{
            missingPairs = "All pairs are covered.";
        }

        panelCommand.setMissingPairs(missingPairs);
    }

    private void getPanelistAndHisProposals(List<PanelistAndHisProposals> list, AssignedPanelistToProposal apptp) {
        for (Iterator<PanelistAndHisProposals> it = list.iterator(); it.hasNext();) {
            PanelistAndHisProposals pap = it.next();
            if (pap.getAssignedPanelist().getAssignmentId().equals(apptp.getPanelistToProposalId().getAssignedPanelist().getAssignmentId())) {
                pap.getProposals().add(apptp.getPanelistToProposalId().getAssignedProposal());
                return;
            }
        }

        PanelistAndHisProposals pahp = new PanelistAndHisProposals();
        pahp.setAssignedPanelist(apptp.getPanelistToProposalId().getAssignedPanelist());
        pahp.getProposals().add(apptp.getPanelistToProposalId().getAssignedProposal());
        list.add(pahp);
    }
}
