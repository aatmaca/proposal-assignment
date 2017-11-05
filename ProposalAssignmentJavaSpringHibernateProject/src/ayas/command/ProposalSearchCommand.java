/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

import java.io.Serializable;
/**
 *
 * @author asil2
 */
public class ProposalSearchCommand implements Serializable{
    private String filter;
    private Integer selectedRecordID;
    /**
     * @return the proposals
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param proposals the proposals to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * @return the selectedRecordID
     */
    public Integer getSelectedRecordID() {
        return selectedRecordID;
    }

    /**
     * @param selectedRecordID the selectedRecordID to set
     */
    public void setSelectedRecordID(Integer selectedRecordID) {
        this.selectedRecordID = selectedRecordID;
    }

  

}
