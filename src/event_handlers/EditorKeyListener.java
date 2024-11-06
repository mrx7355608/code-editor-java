/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_handlers;

import controllers.EditorController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ghost
 */
public class EditorKeyListener implements KeyListener {
    
    private final EditorController controller;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();
    
    public EditorKeyListener(EditorController controller) {
        this.controller = controller;
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
        // 1. After a certain delay, add the buffer to EditorModel's "code" field
        final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
            try {
                this.controller.updateCode();
            } finally {
                delayedMap.remove("test");
            }
        }, 800, TimeUnit.MILLISECONDS));
        
        if (prev != null) {
            prev.cancel(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
