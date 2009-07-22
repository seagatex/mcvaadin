package com.googlecode.mcvaadin.test;

import com.googlecode.mcvaadin.McApplication;
import com.googlecode.mcvaadin.McComponent;
import com.googlecode.mcvaadin.McEvent;
import com.googlecode.mcvaadin.McListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class TestApplication extends McApplication {

    private static final long serialVersionUID = 1321262686746447993L;

    @SuppressWarnings("serial")
    @Override
    public void ui() {

        h1("Welcome to world of McVaadin");
        label("McVaadin is the scripting son of Vaadin.");

        // HelloWorld sample application
        add(new McComponent() {
            @Override
            public void ui() {
                with(panel("HelloWorld Sample"));
                label("Hello McVaadin User");
            }
        });

        // Calculator sample application
        add(new McComponent() {

            private double current = 0.0;
            private double stored = 0.0;
            private char lastOperationRequested = 'C';

            @Override
            public void ui() {
                with(panel("Calculator Sample"));

                // Create a grid layout
                GridLayout layout = gridlayout(4, 5);

                // We add the display label in the same way as in original
                // sample
                final Label display;
                layout.addComponent(display = new Label("0,0"), 0, 0, 3, 0);
                with(layout);

                // Create the calculator buttons
                for (String caption : new String[] { "7", "8", "9", "/", "4",
                        "5", "6", "*", "1", "2", "3", "-", "0", "=", "C", "+" }) {
                    button(caption, new McListener() {
                        @Override
                        public void exec(McEvent e) throws Exception {
                            display.setValue(calculate(e.getButton()));
                        }

                    });
                }

            }

            // Calculator "business logic" implemented here to keep the example
            // minimal
            private double calculate(Button buttonClicked) {
                char requestedOperation = buttonClicked.getCaption().charAt(0);
                if ('0' <= requestedOperation && requestedOperation <= '9') {
                    current = current * 10
                            + Double.parseDouble("" + requestedOperation);
                    return current;
                }
                switch (lastOperationRequested) {
                case '+':
                    stored += current;
                    break;
                case '-':
                    stored -= current;
                    break;
                case '/':
                    stored /= current;
                    break;
                case '*':
                    stored *= current;
                    break;
                case 'C':
                    stored = current;
                    break;
                }
                lastOperationRequested = requestedOperation;
                current = 0.0;
                if (requestedOperation == 'C') {
                    stored = 0.0;
                }
                return stored;
            }
        });

        // Testing panel for all functions
        add(new McVaadinTestPanel());
    }

}
