/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.command;

import ayas.model.AssignedPanelist;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egemen
 */
public class RefereeCommand implements Serializable {

    private Integer refereeCapacities[];
    private Integer numReferees;
    private List<AssignedPanelist> panelists;

    public RefereeCommand() {
        panelists = new ArrayList<AssignedPanelist>();
    }

    public RefereeCommand(int numReferees) {
        this.numReferees = new Integer(numReferees);
        this.refereeCapacities = new Integer[numReferees];
        panelists = new ArrayList<AssignedPanelist>();
    }

    public Integer[] getRefereeCapacities() {
        return refereeCapacities;
    }

    public void setRefereeCapacities(Integer[] refereeCapacities) {
        this.refereeCapacities = refereeCapacities;
    }

    public Integer getNumReferees() {
        return numReferees;
    }

    public void setNumReferees(int numReferees) {
        this.numReferees = numReferees;
    }

    /**
     * @return the panelists
     */
    public List<AssignedPanelist> getPanelists() {
        return panelists;
    }

    /**
     * @param panelists the panelists to set
     */
    public void setPanelists(List<AssignedPanelist> panelists) {
        this.panelists = panelists;
    }
}
