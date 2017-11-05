/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdullah
 */
public class AssignedProposal implements Serializable{

    private Integer recordId;
    private Integer panelId;
    private Integer proposalId;
    private Proposal proposal;
    private Integer provision = 0;
    
    public AssignedProposal() {
    }

    AssignedProposal(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getProvision() {
        return provision;
    }

    public void setProvision(Integer provision) {
        this.provision = provision;
    }
    /**
     * @return the recordId
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * @param recordId the recordId to set
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * @return the panelId
     */
    public Integer getPanelId() {
        return panelId;
    }

    /**
     * @param panelId the panelId to set
     */
    public void setPanelId(Integer panelId) {
        this.panelId = panelId;
    }

    /**
     * @return the proposalId
     */
    public Proposal getProposal() {
        return proposal;
    }

    /**
     * @param proposalId the proposalId to set
     */
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    /**
     * @return the proposalId
     */
    public Integer getProposalId() {
        return proposalId;
    }

    /**
     * @param proposalId the proposalId to set
     */
    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }
}
