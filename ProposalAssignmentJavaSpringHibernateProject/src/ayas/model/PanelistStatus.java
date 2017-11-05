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
public class PanelistStatus implements Serializable{

    private Integer code;
    private String explanation;

    public PanelistStatus() {
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation the explanation to set
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
