package com.googlecode.mcvaadin.helpers;

import com.googlecode.mcvaadin.McEvent;
import com.googlecode.mcvaadin.McListener;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.Action.Handler;

public class KeyboardHandlerImpl implements Handler {

    private static final long serialVersionUID = 1867655708565732838L;
    private Action[] acts;
    private int key;
    private int[] mods;
    private McListener listener;

    public KeyboardHandlerImpl(int key, int[] modifiers, McListener listener) {
        this.key = key;
        this.listener = listener;
        mods = modifiers;
        acts = new Action[] { new ShortcutAction("", key, modifiers) };
    }

    public Action[] getActions(Object target, Object sender) {
        return acts;
    }

    public void handleAction(Action action, Object sender, Object target) {
        if (action == acts[0]) {
            try {
                listener.exec(new McEvent(key, mods));
            } catch (Throwable t) {
                listener.unhandledError("Unhandled exception", t);
            }
        }
    }
}
