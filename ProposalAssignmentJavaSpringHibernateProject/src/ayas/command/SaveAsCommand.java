/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

/**
 *
 * @author hardemr
 */
public class SaveAsCommand 
{
    private PanelCommand panelCommand;
    private String savedString;
    private int panelId;
    private boolean saved;

    public PanelCommand getPanelCommand() {
        return panelCommand;
    }

    public void setPanelCommand(PanelCommand panelCommand) {
        this.panelCommand = panelCommand;
    }

    public String getSavedString() {
        return savedString;
    }

    public void setSavedString(String savedString) {
        this.savedString = savedString;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
