/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

/**
 *
 * @author beyza
 */
public class AddProposalCommand {
    private PanelCommand panelCommand;
    private int newProposalCount;
   

    public PanelCommand getPanelCommand() {
        return panelCommand;
    }

    public void setPanelCommand(PanelCommand panelCommand) {
        this.panelCommand = panelCommand;
    }
    
    public int getNewProposalCount(){
        return newProposalCount;
    }
    
    public void setNewProposalCount(int newProposalCount){
        this.newProposalCount = newProposalCount;
    }
}