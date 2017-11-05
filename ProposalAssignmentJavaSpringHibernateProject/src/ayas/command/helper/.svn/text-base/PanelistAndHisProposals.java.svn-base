/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command.helper;

import ayas.model.AssignedPanelistToProposal;
import ayas.model.AssignedProposal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Atmaca
 */
public class ProposalAndItsPanelists implements Serializable {

    private AssignedProposal proposal;
    private List<AssignedPanelistToProposal> panelists = new ArrayList<AssignedPanelistToProposal>();

    /**
     * @return the proposal
     */
    public AssignedProposal getProposal() {
        return proposal;
    }

    /**
     * @param proposal the proposal to set
     */
    public void setProposal(AssignedProposal proposal) {
        this.proposal = proposal;
    }

    /**
     * @return the panelists
     */
    public List<AssignedPanelistToProposal> getPanelists() {
        return panelists;
    }

    /**
     * @param panelists the panelists to set
     */
    public void setPanelists(List<AssignedPanelistToProposal> panelists) {
        this.panelists = panelists;
    }

    public void addPanelist(AssignedPanelistToProposal assignedPanelistToProposal){
        List temp = new ArrayList(panelists.size() +1);
        boolean onlyOnce = true;

        for (Iterator<AssignedPanelistToProposal> it = panelists.iterator(); it.hasNext();) {
            AssignedPanelistToProposal p = it.next();
            if (onlyOnce && (p.getPanelistToProposalId().getAssignedPanelist().getAssignmentId() > assignedPanelistToProposal.getPanelistToProposalId().getAssignedPanelist().getAssignmentId()) ) {
                temp.add(assignedPanelistToProposal);
                onlyOnce = false;
            }
            temp.add(p);
        }

        if (onlyOnce) {
            temp.add(assignedPanelistToProposal);
        }

        setPanelists(temp);
    }

}
