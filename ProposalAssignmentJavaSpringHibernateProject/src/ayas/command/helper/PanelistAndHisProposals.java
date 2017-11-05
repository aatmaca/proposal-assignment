/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command.helper;

import ayas.model.AssignedPanelist;
import ayas.model.AssignedProposal;
import ayas.model.Proposal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Atmaca
 */
public class PanelistAndHisProposals implements Serializable {

    private AssignedPanelist assignedPanelist;
    private List<AssignedProposal> proposals = new ArrayList<AssignedProposal>();

    /**
     * @return the assignedPanelist
     */
    public AssignedPanelist getAssignedPanelist() {
        return assignedPanelist;
    }

    /**
     * @param assignedPanelist the assignedPanelist to set
     */
    public void setAssignedPanelist(AssignedPanelist assignedPanelist) {
        this.assignedPanelist = assignedPanelist;
    }

    /**
     * @return the proposals
     */
    public List<AssignedProposal> getProposals() {
        return proposals;
    }

    /**
     * @param proposals the proposals to set
     */
    public void setProposals(List<AssignedProposal> proposals) {
        this.proposals = proposals;
    }

}
