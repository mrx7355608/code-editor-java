/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_handlers;

import controllers.ConsoleController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author ghost
 */
public class ConsoleMouseListener implements MouseListener {
    private final ConsoleController controller;
    
    public ConsoleMouseListener(ConsoleController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        this.controller.executeCode();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
