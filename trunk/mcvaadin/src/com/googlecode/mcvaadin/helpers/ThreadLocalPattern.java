package com.googlecode.mcvaadin.helpers;

import com.googlecode.mcvaadin.McApplication;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;

/**
 * Implementation of the thread local pattern.
 *
 * This is used internally by the {@link McApplication}, but if desired can be
 * used separately with a "normal" Vaadin application also.
 *
 */
public class ThreadLocalPattern implements
        ApplicationContext.TransactionListener {

    private static final long serialVersionUID = 5391709280076063498L;
    private static ThreadLocal<McApplication> current = new ThreadLocal<McApplication>();
    private Application app;

    /**
     * Create and init thread local to given application.
     *
     * @param app
     */
    public ThreadLocalPattern(Application app) {
        this.app = app;
        app.getContext().addTransactionListener(this);
    }

    public void transactionEnd(Application application, Object transactionData) {
        if (application == app && current != null) {
            current.set(null);
        }
    }

    public void transactionStart(Application application, Object transactionData) {
        if (application == app && current != null) {
            current.set((McApplication) application);
        }
    }

    public static McApplication current() {
        return current.get();
    }

}
