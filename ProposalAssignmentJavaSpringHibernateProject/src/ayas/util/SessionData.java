package ayas.util;

import ayas.command.PanelCommand;
import ayas.model.AssignedProposal;
import ayas.model.User;
import ayas.model.Company;
import ayas.model.Subject;
import ayas.model.Title;
import java.util.List;

public class SessionData implements java.io.Serializable {

    private String sessionID;
    private User sessionUser;

    private List<String> names;
    private List<String> surnames;
    private List<Company> companies;
    private List<Title> titles;
    private List<Subject> subjects;

    private PanelCommand currentPanelCommand;
    private List<AssignedProposal> proposalList;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    /**
     * @return the currentPanelCommand
     */
    public PanelCommand getCurrentPanelCommand() {
        return currentPanelCommand;
    }

    /**
     * @param currentPanelCommand the currentPanelCommand to set
     */
    public void setCurrentPanelCommand(PanelCommand currentPanelCommand) {
        this.currentPanelCommand = currentPanelCommand;
    }

    /**
     * @return the names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * @return the surnames
     */
    public List<String> getSurnames() {
        return surnames;
    }

    /**
     * @param surnames the surnames to set
     */
    public void setSurnames(List<String> surnames) {
        this.surnames = surnames;
    }

    /**
     * @return the companies
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * @param companies the companies to set
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    /**
     * @return the titles
     */
    public List<Title> getTitles() {
        return titles;
    }

    /**
     * @param titles the titles to set
     */
    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    /**
     * @return the subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }



    /**
     * @return the proposalList
     */
    public List<AssignedProposal> getProposalList() {
        return proposalList;
    }

    /**
     * @param proposalList the proposalList to set
     */
    public void setProposalList(List<AssignedProposal> proposalList) {
        this.proposalList = proposalList;
    }
}
