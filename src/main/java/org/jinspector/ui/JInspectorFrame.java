package org.jinspector.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;

import org.jinspector.LocalizedConstants;
import org.jinspector.Resources;

/**
 * JClassFileViewerFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class JInspectorFrame extends JFrame {

	private static final long serialVersionUID = -3689990433330154054L;

	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenuItem menuItemOpen;
	private JMenuItem menuItemExit;

	private JTree tree;
	private JPanel details;

	/**
	 * 
	 */
	public JInspectorFrame() {
		super(LocalizedConstants.WINDOW_TITLE);

		this.init();
		this.build();
		this.config();
	}

	/**
	 *
	 */
	private void init() {

		this.menuBar = new JMenuBar();

		this.menuFile = new JMenu("File");
		this.menuItemOpen = new JMenuItem("Open");
		this.menuItemExit = new JMenuItem("Exit");

		this.tree = new JTree((TreeModel) null);
		this.details = new JPanel();
	}

	/**
	 *
	 */
	private void build() {

		this.menuFile.add(this.menuItemOpen);
		this.menuFile.addSeparator();
		this.menuFile.add(this.menuItemExit);
		this.menuBar.add(this.menuFile);

		this.setJMenuBar(this.menuBar);

		JSplitPane splitPane = new JSplitPane();

		splitPane.setLeftComponent(makeScrollable(this.tree));
		splitPane.setRightComponent(makeScrollable(this.details));
		splitPane.setDividerLocation(450);

		this.getContentPane().add(splitPane);
	}

	/**
	 * @param component
	 * @return
	 */
	private JScrollPane makeScrollable(Component component) {

		JScrollPane scrollPane = new JScrollPane(component);
		scrollPane.getVerticalScrollBar().setUnitIncrement(35);

		return scrollPane;
	}

	/**
	 *
	 */
	private void config() {

		this.tree.setCellRenderer(new ClassFileTreeCellRenderer());
		this.tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		this.tree.setRowHeight(0);

		this.details.setLayout(new BorderLayout());

		this.setIconImage(new ImageIcon(this.getClass().getResource(Resources.PATH_IMAGE_APP)).getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);
	}

	/**
	 * @return the menuItemOpen.
	 */
	public JMenuItem getMenuItemOpen() {
		return this.menuItemOpen;
	}

	/**
	 * @return the menuItemExit.
	 */
	public JMenuItem getMenuItemExit() {
		return this.menuItemExit;
	}

	/**
	 * @return the tree.
	 */
	public JTree getTree() {
		return this.tree;
	}

	public JPanel getDetails() {
		return details;
	}
}
