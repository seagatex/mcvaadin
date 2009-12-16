package com.googlecode.mcvaadin.helpers;

import java.io.Serializable;
import java.util.ResourceBundle;

public class Translator implements Serializable {

    private static final long serialVersionUID = 8605713237416911763L;

    private ResourceBundle resourceBundle = null;

    private String resourceBundleName;

    public String getResourceBundleName() {
        return resourceBundleName;
    }

    public void setResourceBundleName(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    public String tr(String str) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(resourceBundleName);
        }
        return resourceBundle.getString(str);
    }

    public String tr(String str, Object... arguments) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(resourceBundleName);
        }
        String tr = resourceBundle.getString(str);
        return Utils.format(tr, arguments);
    }

}
