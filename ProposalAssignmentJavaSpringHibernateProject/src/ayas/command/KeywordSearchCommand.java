/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

import ayas.model.Proposal;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author asil2
 */
public class KeywordSearchCommand implements Serializable{
    private String filter;
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



}