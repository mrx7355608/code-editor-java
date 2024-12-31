
package keyboard_shortcuts;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ShortcutAction extends AbstractAction {
    private final Runnable action;
    
    public ShortcutAction(Runnable action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.action.run();
    }
}
