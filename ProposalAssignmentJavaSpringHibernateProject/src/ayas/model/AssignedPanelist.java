/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.model;

import java.io.Serializable;

/**
 *
 * @author abdullah
 */
public class AssignedPanelist implements Serializable{

    private Integer assignmentId;
    private Integer panelId;
    private Integer panelistId;
    private Panelist panelist;
    private Integer capacity = 0;


    public AssignedPanelist() {
    }

    public AssignedPanelist( Integer assignmentId){
        this.assignmentId = assignmentId;
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
     * @return the panelistId
     */
    public Panelist getPanelist() {
        return panelist;
    }

    /**
     * @param panelistId the panelistId to set
     */
    public void setPanelist(Panelist panelist) {
        this.panelist = panelist;
    }

    /**
     * @return the assignmentId
     */
    public Integer getAssignmentId() {
        return assignmentId;
    }

    /**
     * @param assignmentId the assignmentId to set
     */
    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    /**
     * 
     * @return the capacity
     */
    public Integer getCapacity() {
        return capacity;
    }
    /**
     * 
     * @param capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the panelistId
     */
    public Integer getPanelistId() {
        return panelistId;
    }

    /**
     * @param panelistId the panelistId to set
     */
    public void setPanelistId(Integer panelistId) {
        this.panelistId = panelistId;
    }
}
