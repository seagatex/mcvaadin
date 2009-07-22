package com.googlecode.mcvaadin;

/**
 * This listener allows only the first listener (i.e. listener for a user
 * initiated event) in the executing thread to be triggered.
 *
 * In some cases this is the desired behavior of user interface components as
 * modification of component state does not trigger any subsequent events.
 *
 * Listener that filters out every subsequent calls to listeners. If other event
 * listeners are used they will not be filtered in any way. So to be really
 * usable, all related listeners should be the type McUserListeners.
 *
 * @see McListener
 */
public abstract class McUserListener extends McListener {

    private static final long serialVersionUID = -4745589022176838603L;

    // Listener state for the current thread
    private static ThreadLocal<Boolean> userEventAlreadyProcessed = new ThreadLocal<Boolean>();
    static {
        userEventAlreadyProcessed.set(false);
    }

    public McUserListener() {
        super();
    }

    @Override
    protected synchronized void execOnce(McEvent e) throws Exception {
        if (userEventAlreadyProcessed.get() == null
                || !userEventAlreadyProcessed.get()) {
            try {
                userEventAlreadyProcessed.set(true);
                super.execOnce(e);
            } finally {
                userEventAlreadyProcessed.set(false);
            }
        }
    }

}
