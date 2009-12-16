package com.googlecode.mcvaadin.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

import com.googlecode.mcvaadin.McEvent;
import com.googlecode.mcvaadin.McListener;
import com.googlecode.mcvaadin.McWindow;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.Notification;

/**
 * Helper class for generic way of different kinds of messages to user.
 *
 * There are functions like {@link #notification(String)},
 * {@link #alert(String)}, {@link #error(String)} (and their variants) to help
 * applications developers to inform user in easy and consistent way.
 *<p>
 * The {@link #confirm(String, McListener)} function offers an easy way of
 * querying user for a confirmation before executing some task. In event driven
 * UI framework like Vaadin this kind of task typically requires state
 * management and several listeners to be implemented, but using this function
 * it is simplified to this:
 *
 * <pre>
 * confirm(&quot;Are you sure?&quot;, new McListener() {
 *     public void exec(McEvent e) {
 *         if (e.isConfirmed()) {
 *            // User confirmed to continue
 *         }
 *     }
 * });
 * </pre>
 *
 */
public class UserMessages implements Serializable {

    private static final long serialVersionUID = 2344274165863515876L;
    static final String CONFIRM_TITLE = "Confirm";
    static final String CONFIRM_CANCEL_TITLE = "Cancel";
    static final String CONFIRM_OK_TITLE = "Ok";
    static final int MAX_DESCRIPTION_LINES = 20;

    private Window win;

    public UserMessages(Window w) {
        win = w;
        if (win == null) {
            throw new IllegalStateException(
                    "User messages require a window instance");
        }
    }

    public void error(String message) {
        error(message, null, null);
    }

    public void error(String message, String description) {
        error(message, description, null);
    }

    public void error(String message, String description, Throwable t) {
        if (t != null) {
            ByteArrayOutputStream stos = new ByteArrayOutputStream();
            PrintStream sto = new PrintStream(stos, false);
            t.printStackTrace(sto);
            sto.flush();
            try {
                stos.flush();
            } catch (IOException ignored) {
            }
            String st = stos.toString();
            t.printStackTrace();
            if (description == null) {
                description = st;
            } else {
                description += ":\n" + st;
            }
        }
        description = formatDescription(description);
        win.showNotification(Utils.escapeXML(message), description,
                Notification.TYPE_ERROR_MESSAGE);
    }

    public void error(String message, Throwable t) {
        error(message, null, t);
    }

    public void error(Throwable e) {
        error("Unhandled Exception", null, e);
    }

    public void warning(String message) {
        warning(message, null);
    }

    public void warning(String message, String description) {
        win.showNotification(message, formatDescription(description),
                Notification.TYPE_WARNING_MESSAGE);
    }

    public void trayNotification(String message) {
        trayNotification(message, null);
    }

    public void trayNotification(String message, String description) {
        win.showNotification(message, formatDescription(description),
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void notification(String message) {
        notification(message, null);
    }

    public void notification(String message, String description) {
        win.showNotification(message, formatDescription(description));
    }

    public void alert(String message) {
        alert(message, null);
    }

    public void alert(String message, String description) {
        win.showNotification(message, formatDescription(description),
                Notification.DELAY_FOREVER);
    }

    private String formatDescription(String description) {
        if (description != null) {
            description = Utils.escapeXML(description);
            description = description.replaceAll("\n", "<br />");
            if (description.length() > 80) {
                String orig = description;
                description = "";
                while (orig.length() > 0) {
                    int last = Math.min(80, orig.length());
                    description += orig.substring(0, last);
                    int lastnl = description.lastIndexOf("<br");
                    int lastwb = description.lastIndexOf(' ');
                    if (lastwb - lastnl > 10
                            && lastwb < description.length() - 1) {
                        description = description.substring(0, lastwb)
                                + "<br />" + description.substring(lastwb);
                    }
                    orig = last == orig.length() ? "" : orig.substring(last);
                }
            }

            // limit number of lines
            int pos = description.indexOf("<br");
            int lineCount = 1;
            while (lineCount < MAX_DESCRIPTION_LINES && pos > 0
                    && pos < description.length()) {
                pos = description.indexOf("<br", pos + 3);
                lineCount++;
            }
            if (pos > 0 && lineCount >= MAX_DESCRIPTION_LINES) {
                description = description.substring(0, pos) + "<br />(...)";
            }
        }
        return description;
    }

    public Window confirm(String message, McListener listener) {
        return confirm(CONFIRM_TITLE, message, CONFIRM_OK_TITLE,
                CONFIRM_CANCEL_TITLE, listener);
    }

    public Window confirm(String title, String message, McListener listener) {
        return confirm(title, message, CONFIRM_OK_TITLE,
                CONFIRM_CANCEL_TITLE, listener);
    }

    public Window confirm(String title, String message, String okTitle,
            String cancelTitle, final McListener listener) {

        // Check for default captions
        if (title == null) {
            title = CONFIRM_OK_TITLE;
        }
        if (cancelTitle == null) {
            cancelTitle = CONFIRM_CANCEL_TITLE;
        }
        if (okTitle == null) {
            okTitle = CONFIRM_OK_TITLE;
        }

        // Create a confirm dialog
        final McWindow confirm = new McWindow(title);
        win.addWindow(confirm);
        confirm.setStyleName(Window.STYLE_LIGHT);

        // Close listener implementation
        confirm.addListener(new Window.CloseListener() {

            private static final long serialVersionUID = 1971800928047045825L;

            public void windowClose(CloseEvent ce) {
                Object data = ce.getWindow().getData();
                if (data != null) {
                    try {
                        listener.exec(new McEvent(ce));
                    } catch (Exception exception) {
                        error("Unhandled Exception", exception);
                    }
                }
            }
        });

        // Approximate the size of the dialog
        int chrW = 5;
        int chrH = 15;
        int txtWidth = Math.max(150, Math.min(300, message.length() * chrW));
        int btnHeight = 25;
        int vmargin = 100;
        int hmargin = 40;

        int txtHeight = 2 * chrH * (message.length() * chrW) / txtWidth;

        confirm.setWidth((txtWidth + hmargin) + "px");
        confirm.setHeight((vmargin + txtHeight + btnHeight) + "px");
        confirm.getContent().setSizeFull();

        // Modal position in the center
        confirm.center();
        confirm.setModal(true);

        // Create content
        final UIBuilder h = new UIBuilder(confirm.getContent());
        Label text = h.label(message);
        text.setWidth("100%");
        text.setHeight("100%");
        h.expand(text, 1f);


        HorizontalLayout buttons = h.horizontal();
        buttons.setHeight(btnHeight + "px");
        buttons.setWidth("100%");
        Label spacer = h.label("");
        spacer.setWidth("100%");
        h.expand(spacer, 1f);

        // Create a listener for buttons
        McListener btnListener = new McListener() {

            private static final long serialVersionUID = 517796258497875393L;

            @Override
            public void exec(McEvent e) throws Exception {
                // Copy the button date to window for passing through either
                // "OK" or "CANCEL"
                confirm.setData(e.getClickEvent().getButton().getData());
                confirm.closeWindow();
            }

        };
        Button cancel = h.button(cancelTitle, btnListener);
        cancel.setData(McEvent.USER_CONFIRM_CANCEL);
        h.bindKey(cancel, KeyCode.ESCAPE);
        Button ok = h.button(okTitle, btnListener);
        ok.setData(McEvent.USER_CONFIRM_OK);
        h.bindKey(ok, KeyCode.ENTER);
        return confirm;
    }

}
