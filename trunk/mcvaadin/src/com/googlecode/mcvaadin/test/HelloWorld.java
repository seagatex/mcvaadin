package com.googlecode.mcvaadin.test;

import com.googlecode.mcvaadin.McApplication;

@SuppressWarnings("serial")
public class HelloWorld extends McApplication {
    @Override
    public void ui() {
        label("Hello World");
    }
}
