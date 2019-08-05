package org.jinspector;

import java.util.ResourceBundle;

public class LocalizedConstants {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(Resources.BUNDLE_BASE_NAME);

	public static final String ACTION_NAME_SHOW_SPECIFICATION = BUNDLE.getString("show.specification");

	public static final String WINDOW_TITLE = BUNDLE.getString("window.title");
}
