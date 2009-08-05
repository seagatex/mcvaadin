package com.googlecode.mcvaadin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import com.googlecode.mcvaadin.helpers.Constants;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Window.CloseEvent;

/**
 * A generic listener for events of the Vaadin UI framework. By implementing the
 * 'exec'-method, virtually any event sent by the Vaadin components can be
 * received. This limits the amount of code developers need to write and makes
 * the code more consistent.
 *
 * The listener implements an event filtering scheme to avoid undesired
 * recursion. This means that the same listener implementation is never invoked
 * twice.
 *
 * If even more strict event filtering is desired see the {@link McUserListener}
 * .
 */
public abstract class McListener implements Button.ClickListener,
        Property.ValueChangeListener, URIHandler, ParameterHandler,
        Upload.Receiver, Upload.FinishedListener, Table.ColumnGenerator,
        Window.CloseListener {

    /** Generated Serial Version UID. */
    private static final long serialVersionUID = 763117584566103799L;

    /** Data cache for uploads. */
    ByteArrayOutputStream uploadData;

    private boolean lock;

    /** Default constructor. */
    public McListener() {
    }

    /**
     * Upload finished listener. Does nothing.
     */
    public void uploadFinished(FinishedEvent event) {
        // Should be empty
    }

    /**
     * Receive upload implementation.
     *
     */
    public OutputStream receiveUpload(String filename, String MIMEType) {
        uploadData = new ByteArrayOutputStream();
        return uploadData;
    }

    /**
     * URI Handler implementation.
     *
     */
    public DownloadStream handleURI(URL context, String relativeUri) {
        try {
            McEvent e = new McEvent(context, relativeUri);
            execOnce(e);
            if (e.getReturnStream() != null) {
                return e.getReturnStream();
            } else if (e.getReturnData() != null) {
                String type = e.getReturnDataType() != null ? e
                        .getReturnDataType()
                        : Constants.DEFAULT_DOWNLOAD_MIME_TYPE;
                String name = e.getReturnDataName() != null ? e
                        .getReturnDataName()
                        : Constants.DEFAULT_DOWNLOAD_FILENAME;
                return new DownloadStream(new ByteArrayInputStream(e
                        .getReturnData()), type, name);

            }
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
        return null;
    }

    /**
     * Log an unhandled exception.
     *
     * @param msg
     * @param t
     */
    private void error(String msg, Throwable t) {
        McApplication app = McApplication.current();
        if (app != null && app.getMsg() != null) {
            app.getMsg().error(msg, t);
        } else {
            System.err.println(msg + ": " + t);
        }

    }

    @SuppressWarnings("unchecked")
    public void handleParameters(Map parameters) {
        try {
            execOnce(new McEvent(parameters));
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
    }

    public void valueChange(ValueChangeEvent event) {
        try {
            execOnce(new McEvent(event));
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
    }

    public void buttonClick(ClickEvent event) {
        try {
            execOnce(new McEvent(event));
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
    }

    public Component generateCell(Table source, Object itemId, Object columnId) {
        McEvent e = new McEvent(source, itemId, columnId);
        try {
            execOnce(e);
            return e.getReturnComponent();
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
        return null;
    }

    public void windowClose(CloseEvent e) {
        try {
            execOnce(new McEvent(e));
        } catch (Throwable t) {
            error("Unhandled exception", t);
        }
    }

    /**
     * Process the event. This calls the exec inside a lock to avoid double
     * execution (recursion) of the same events.
     *
     * This is to avoid situations where for example the text field value change
     * event that modifies the text field itself. Normally this would cause a
     * recursion of the same events.
     *
     * @param e
     *            The event object
     * @throws Exception
     */
    protected synchronized void execOnce(McEvent e) throws Exception {

        // Check if we are currently in lock
        if (lock) {
            return;
        }
        lock = true;

        try {
            exec(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            // Release the lock
            lock = false;
        }
    }

    /**
     * Process the event. The event object contains data about the specific
     * event. Note that the data available varies depending on event type. For
     * URIHandlers the data may be returned using. setReturn* functions.
     *
     * @param e
     *            The event object
     * @throws Exception
     */
    public abstract void exec(McEvent e) throws Exception;

}