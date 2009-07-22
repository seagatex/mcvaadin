package com.googlecode.mcvaadin.helpers;

import java.io.Serializable;

import com.googlecode.mcvaadin.McApplication;
import com.googlecode.mcvaadin.McComponent;
import com.googlecode.mcvaadin.McListener;
import com.googlecode.mcvaadin.McWindow;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * User interface factory class.
 *
 * This class offers factory functions to build a user interface in a linear
 * way. Each Vaadin component (and McVaadin extension component) has a function
 * counterpart in this class that creates a new instance of it and adds it to
 * selected component container (layout etc). Effectively this makes the
 * creation of user interfaces more simple. This:
 * <code>Label label = new Label("Hello"); layout.addComponent(label);</code>
 * becomes simply this: <code>label("Hello")</code>
 * <p>
 * The layout or component container can be selected using the
 * {@link #with(ComponentContainer)} function and previous component containers
 * with {@link #endWith()} function.
 * <p>
 * There also is a set of functions to help displaying user messages more
 * conveniently as specified in {@link UserMessages} class.
 * <p>
 * As you may use this class separately to build UI in any Vaadin application,
 * it is meant to be used to through the {@link McApplication}, {@link McWindow}
 * and {@link McComponent}. They all implement delegate functions for this class
 * to minimize the amount of code needed.
 */
public class UIBuilder extends VaadinBuilder implements Serializable {

    private static final long serialVersionUID = 3266450463409789028L;

    private UserMessages msg;

    public UIBuilder(ComponentContainer cc) {
        super(cc);
        msg = McApplication.current().getMsg();
    }

    /** Create new builder with given root component container. */
    public UIBuilder build(ComponentContainer cc) {
        return new UIBuilder(cc);
    }

    // The following functions require thread local pattern

    /**
     * Get current application instance. This uses the thread local pattern and
     * requires that application is inherited from the MCApplication class.
     *
     * @see #getWin()
     * @return application instance or null if not found.
     */
    public McApplication getApp() {
        return McApplication.current();
    }

    /**
     * Get the main window of current application. This is effectively same as
     * getApp().getMainWindow(). See requirements for application in
     * {@link #getApp()}.
     *
     * @see #getApp()
     * @return Main window instance or null if not found.
     */
    public Window getMainWin() {
        McApplication a = getApp();
        if (a != null) {
            return a.getMainWindow();
        }
        return null;
    }

    // TODO: Translation functions
    // private ResourceBundle resourceBundle = null;
    //
    // private String resourceBundleName = null;
    //
    // public String tr(String str) {
    // if (resourceBundle == null) {
    // resourceBundle = ResourceBundle.getBundle(resourceBundleName);
    // }
    // return resourceBundle.getString(str);
    // }
    //
    // public String tr(String str, Object... arguments) {
    // if (resourceBundle == null) {
    // resourceBundle = ResourceBundle.getBundle(resourceBundleName);
    // }
    // String tr = resourceBundle.getString(str);
    // return com.google.code.mcvaadin.se.SeUtils.format(tr, arguments);
    // }

    // /**
    // * Translate a string using current locale. his is effectively same as
    // * getApp().tr(String). See requirements for application in
    // * {@link #getApp()}.
    // *
    // * @param str
    // * @return
    // */
    // public String tr(String str) {
    // MCApplication a = getApp();
    // if (a != null) {
    // return a.tr(str);
    // }
    // return str;
    // }

    // ---------------------------------------------------------------------------------------------------
    // --------------------------------- Custom component constructors
    // ---------------------------------------------------------------------------------------------------

    /**
     * Override for better defaults.
     *
     */
    @Override
    public Window window() {
        Window w = super.window();

        // Make sure the size full is set
        w.getContent().setSizeFull();

        // Set margin on by defaults
        ((Layout) w.getContent()).setMargin(true);
        return w;
    }

    public Label h1(String str) {
        return labelWithStyle(str, "h1");
    }

    public Label h2(String str) {
        return labelWithStyle(str, "h2");
    }

    public Label hr() {
        return html("<hr />");
    }

    public Label html(String str) {
        Label c = label();
        c.setContentMode(Label.CONTENT_RAW);
        c.setValue(str);
        return c;
    }

    private Label labelWithStyle(String str, String style) {
        Label c = label(str);
        c.setStyleName(style);
        return c;
    }

    /**
     * Create a textfield of given size and default value.
     *
     * @param caption
     * @param value
     * @param cols
     * @return
     */
    public TextField textfield(String caption, String value, int cols) {
        TextField c = textfield(caption);
        c.setValue(value);
        c.setColumns(cols);
        return c;
    }

    public GridLayout gridlayout(int w, int h) {
        GridLayout c = new GridLayout(w, h);
        add(c);
        return c;
    }

    // ---------------------------------------------------------------------------------------------------
    // --------------------------------- Delegate Functions for user messages
    // ---------------------------------------------------------------------------------------------------

    public void alert(String message, String description) {
        msg.alert(message, description);
    }

    public void alert(String message) {
        msg.alert(message);
    }

    public Window confirm(String message, McListener listener) {
        return msg.confirm(message, listener);
    }

    public Window confirm(String title, String message, McListener listener) {
        return msg.confirm(title, message, listener);
    }

    public Window confirm(String title, String message, String okTitle,
            String cancelTitle, McListener listener) {
        return msg.confirm(title, message, okTitle, cancelTitle, listener);
    }

    public void error(String message, String description, Throwable t) {
        msg.error(message, description, t);
    }

    public void error(String message, String description) {
        msg.error(message, description);
    }

    public void error(String message, Throwable t) {
        msg.error(message, t);
    }

    public void error(String message) {
        msg.error(message);
    }

    public void error(Throwable e) {
        msg.error(e);
    }

    public void notification(String message, String description) {
        msg.notification(message, description);
    }

    public void notification(String message) {
        msg.notification(message);
    }

}
