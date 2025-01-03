
package com.mycompany.editor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class PasteAction extends AbstractAction {

    private final Action action;
    private final Runnable myAction;

    public PasteAction(Action action, Runnable myAction) {
        this.action = action;
        this.myAction = myAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action.actionPerformed(e); // performs the basic paste operation
        this.myAction.run(); // this custom action updates the line numbers
    }

}
