package com.googlecode.mcvaadin.helpers;

import com.googlecode.mcvaadin.McEvent;
import com.googlecode.mcvaadin.McListener;
import com.googlecode.mcvaadin.McWindow;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
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
 *         // User confirmed to continue
 *     }
 * });
 * </pre>
 *
 */
public class UserMessages {

    static final String CONFIRM_TITLE = "Confirm";
    static final String CONFIRM_CANCEL_TITLE = "Cancel";
    static final String CONFIRM_OK_TITLE = "Ok";

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
            t.printStackTrace();
            if (description == null) {
                description = t.getMessage();
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
            // TODO: Toolkit layout tweak
            if (!description.startsWith("\n")) {
                description = "\n" + description;
            }
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
        }
        return description;
    }

    public Window confirm(String message, McListener listener) {
        return confirm(CONFIRM_TITLE, message, CONFIRM_OK_TITLE,
                CONFIRM_CANCEL_TITLE, listener);
    }

    public Window confirm(String title, String message, McListener listener) {
        return confirm(CONFIRM_TITLE, message, CONFIRM_OK_TITLE,
                CONFIRM_CANCEL_TITLE, listener);
    }

    public Window confirm(String title, String message, String okTitle,
            String cancelTitle, final McListener listener) {

        // Check for default captions
        if (title == null) {
            title = CONFIRM_OK_TITLE;
        }
        if (cancelTitle == null) {
            cancelTitle= CONFIRM_CANCEL_TITLE;
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
                McEvent e = (McEvent) ce.getWindow().getData();
                if (e != null) {
                    try {
                        listener.exec(e);
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
        int vmargin = 70;
        int hmargin = 40;

        int txtHeight = 2 * chrH * (message.length() * chrW) / txtWidth;

        confirm.setWidth((txtWidth + hmargin) + "px");
        confirm.setHeight((vmargin + txtHeight + btnHeight) + "px");

        // Modal position in the center
        confirm.center();
        confirm.setModal(true);

        // Create content
        final UIBuilder h = new UIBuilder(confirm);
        Label text = h.label(message);
        HorizontalLayout buttons = h.horizontallayout();
        buttons.setHeight(btnHeight + "px");
        text.setWidth(txtWidth + "px");
        text.setHeight(txtHeight + "px");
        ((VerticalLayout) buttons.getParent()).setComponentAlignment(buttons,
                Alignment.BOTTOM_RIGHT);
        McListener btnListener = new McListener() {

            private static final long serialVersionUID = 517796258497875393L;

            @Override
            public void exec(McEvent e) throws Exception {
                if ("OK".equals(e.getClickEvent().getButton().getData())) {
                    // Set event as data only when "ok" is clicked
                    confirm.setData(e);
                }
                confirm.closeWindow();
            }

        };
        // TODO: Keyboard listener helpers
        h.with(buttons);
        h.button(cancelTitle, btnListener).setData("CANCEL"); // .setKey(SeConstants.Key.ESCAPE);
        h.button(okTitle, btnListener).setData("OK"); // .setKey(SeConstants.Key.ENTER);
        return confirm;
    }

}
