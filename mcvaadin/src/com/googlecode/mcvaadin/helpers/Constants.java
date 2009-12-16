package com.googlecode.mcvaadin.helpers;

import java.io.Serializable;

import com.vaadin.ui.SplitPanel;

/**
 * McVaadin constants and defaults.
 *
 */
public interface Constants extends Serializable {

    static final int SPLIT_HORIZONTAL = SplitPanel.ORIENTATION_HORIZONTAL;
    static final int SPLIT_VERTICAL = SplitPanel.ORIENTATION_VERTICAL;

    static final String DEFAULT_DOWNLOAD_MIME_TYPE = "text/plain";
    static final String DEFAULT_DOWNLOAD_FILENAME = "download.txt";

}
