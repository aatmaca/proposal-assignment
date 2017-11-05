/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

import ayas.model.AssignedProposal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egemen
 */
public class ProposalProvisionsCommand implements Serializable  {
    
    private Integer proposalProvisions[];
    private Integer numProposals;
    private List<AssignedProposal> proposals;

    public ProposalProvisionsCommand( int numProposals){
        this.numProposals = numProposals;
        this.proposalProvisions = new Integer[numProposals];
        this.proposals = new ArrayList<AssignedProposal>();
    }

    public Integer getNumProposals() {
        return numProposals;
    }

    public void setNumProposals(Integer numProposals) {
        this.numProposals = numProposals;
    }

    public Integer[] getProposalProvisions() {
        return proposalProvisions;
    }

    public void setProposalProvisions(Integer[] proposalProvisions) {
        this.proposalProvisions = proposalProvisions;
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
