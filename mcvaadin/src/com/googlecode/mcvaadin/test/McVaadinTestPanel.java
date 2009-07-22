package com.googlecode.mcvaadin.test;

import com.googlecode.mcvaadin.McComponent;
import com.googlecode.mcvaadin.McEvent;
import com.googlecode.mcvaadin.McListener;

public class McVaadinTestPanel extends McComponent {

    private static final long serialVersionUID = -1052423424939888477L;

    @SuppressWarnings("serial")
    @Override
    public void ui() {

        with(panel("McVaadin Tests"));
        h1("Testing h1");
        h2("Testing h2");
        textfield("Testing textfield");
        html("Testing html");
        accordion("Testing accordion");
        embedded("Testing embedded");
        checkbox("Testing checkbox");
        splitpanel("Testing splitpanel");
        popupdatefield("Testing popupdatefield");
        twincolselect("Testing twincolselect");
        listselect("Testing listselect");
        link("Testing link");
        nativeselect("Testing nativeselect");
        inlinedatefield("Testing inlinedatefield");
        richtextarea("Testing richtextarea");
        optiongroup("Testing optiongroup");
        progressindicator("Testing progressindicator");
        form("Testing form");
        datefield("Testing datefield");
        tabsheet("Testing tabsheet");
        table("Testing table");
        label("Testing label");
        button("Testing button");
        select("Testing select");
        tree("Testing tree");
        slider("Testing slider");
        panel("Testing panel");
        combobox("Testing combobox");

        // Test confirmation dialog
        button("Confirm Test", new McListener() {

            @Override
            public void exec(McEvent e) throws Exception {
                confirm("Are you sure?", new McListener() {

                    @Override
                    public void exec(McEvent e) throws Exception {
                        notification("You were sure!");
                    }
                });

            }
        });

        // Test an non-handled exception
        button("Error Test", new McListener() {

            @Override
            public void exec(McEvent e) throws Exception {
                throw new NullPointerException(
                        "This is a test of unhandled exception.");

            }
        });
        button("Notification Test", new McListener() {

            @Override
            public void exec(McEvent e) throws Exception {
                notification("This is a notification");

            }
        });

    }

}
