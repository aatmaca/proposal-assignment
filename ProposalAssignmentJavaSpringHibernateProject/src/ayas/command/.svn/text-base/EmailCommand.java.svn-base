/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

/**
 *
 * @author hardemr
 */
public class EmailCommand {
    private PanelCommand panelCommand;
    private String to;
    private String from;
    private String message;
    private String subject;
    private boolean isSent;

    public PanelCommand getPanelCommand() {
        return panelCommand;
    }

    public void setPanelCommand(PanelCommand panelCommand) {
        this.panelCommand = panelCommand;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String createMessage(){
        int panelistCount = panelCommand.getPanelistCount();
        String message = "";
        for(int i = 1;i <= panelistCount; i++)
        {
            message += "\t Referee " + i;
        }
        message += "\n";
        for(int i = 0;i < panelCommand.getProposals().size();i++)
        {
            message += "Proposal " + panelCommand.getProposals().get(i).getProposal().getRecordId() + " : ";
            message += "\t";
            for(int j = 0;j < panelCommand.getProposals().get(i).getPanelists().size(); j++)
            {
                message += "Status : " + panelCommand.getProposals().get(i).getPanelists().get(j).getStatus();
                message += " Grade : " + panelCommand.getProposals().get(i).getPanelists().get(j).getGrade();
                message += "\t";
            }
            message += "\n";
        }
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isIsSent() {
        return isSent;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }
}
