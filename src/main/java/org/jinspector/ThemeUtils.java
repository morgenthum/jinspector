package org.jinspector;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.jinspector.ui.InstructionPanel;

/**
 * @author morgenthum
 *
 */
public class ThemeUtils {

	public static final Color FOREGROUND = Color.BLACK;
	public static final Color BACKGROUND = Color.WHITE;
	public static final Color SELECTION_FOREGROUND = Color.WHITE;
	public static final Color SELECTION_BACKGROUND = new Color(51, 153, 255);

	public static final Font OPEN_SANS_REGULAR = loadFont(Resources.PATH_FONT_OPEN_SANS_REGULAR);
	public static final Font SOURCE_CODE_PRO_LIGHT = loadFont(Resources.PATH_FONT_SOURCE_CODE_PRO_LIGHT);

	/**
	 * @param path
	 * @return
	 */
	private static Font loadFont(String path) {

		Font font = null;

		InputStream input = InstructionPanel.class.getResourceAsStream(path);

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, input);
			font = font.deriveFont(Font.PLAIN, 12);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(JInspector.getInstance().getFrame(), "Could not load font: " + path, "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		return font;
	}
}
