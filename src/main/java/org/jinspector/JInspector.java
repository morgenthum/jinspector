package org.jinspector;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.jinspector.classfile.ClassFile;
import org.jinspector.classfile.ClassFileFactory;
import org.jinspector.tree.ArrayTreeNode;
import org.jinspector.tree.DetailPanelVisitor;
import org.jinspector.tree.PlainTreeNode;
import org.jinspector.tree.PopupMenuVisitor;
import org.jinspector.tree.ReferenceTreeNode;
import org.jinspector.tree.TreeNodeVisitor;
import org.jinspector.tree.VisitableTreeNode;
import org.jinspector.ui.JInspectorFrame;

/**
 * JClassFileViewer created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class JInspector implements ActionListener, MouseListener, TreeSelectionListener {

	private static JInspector instance;

	/**
	 * @return
	 */
	public static JInspector getInstance() {
		if (instance == null) {
			instance = new JInspector();
		}
		return instance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		for (Object key : defaults.keySet()) {
			String keyValue = key.toString();

			if (keyValue.endsWith(".font")) {
				defaults.put(key, ThemeUtils.OPEN_SANS_REGULAR);
			} else if (keyValue.endsWith(".selectionBackground")) {
				defaults.put(key, ThemeUtils.SELECTION_BACKGROUND);
			} else if (keyValue.endsWith(".selectionForeground")) {
				defaults.put(key, ThemeUtils.SELECTION_FOREGROUND);
			} else if (keyValue.endsWith(".background")) {
				defaults.put(key, ThemeUtils.BACKGROUND);
			} else if (keyValue.endsWith(".foreground")) {
				defaults.put(key, ThemeUtils.FOREGROUND);
			} else if (keyValue.endsWith(".focusCellHighlightBorder")) {
				defaults.put(key, new EmptyBorder(new Insets(0, 0, 0, 0)));
			}
		}

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		URL urlExpand = JInspector.class.getResource(Resources.PATH_IMAGE_EXPAND);
		ImageIcon iconExpand = new ImageIcon(urlExpand);
		UIManager.put("Tree.collapsedIcon", new IconUIResource(iconExpand));

		URL urlCollapse = JInspector.class.getResource(Resources.PATH_IMAGE_COLLAPSE);
		ImageIcon iconCollapse = new ImageIcon(urlCollapse);
		UIManager.put("Tree.expandedIcon", new IconUIResource(iconCollapse));

		JInspector.getInstance().start();
	}

	private JInspectorFrame frame;

	private List<ClassFile> classFiles;

	/**
	 * 
	 */
	private JInspector() {
		this.init();
		this.addListeners();
	}

	/**
	 *
	 */
	private void init() {
		this.frame = new JInspectorFrame();
	}

	/**
	 *
	 */
	private void addListeners() {
		this.frame.getMenuItemOpen().addActionListener(this);
		this.frame.getMenuItemExit().addActionListener(this);

		this.frame.getTree().addMouseListener(this);
		this.frame.getTree().addTreeSelectionListener(this);
	}

	/**
	 *
	 */
	private void start() {
		this.frame.setVisible(true);
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == this.frame.getMenuItemOpen()) {
			this.openFile();
		} else if (source == this.frame.getMenuItemExit()) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		JTree tree = this.frame.getTree();

		if (e.getSource() == tree && SwingUtilities.isRightMouseButton(e)) {
			showTreePopupMenu(tree, e);
		}
	}

	/**
	 * @param tree
	 * @param e
	 */
	private void showTreePopupMenu(JTree tree, MouseEvent e) {

		TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		tree.setSelectionPath(path);

		int row = tree.getRowForLocation(e.getX(), e.getY());
		if (row != -1) {
			tree.setSelectionRow(row);
		}

		Visitable visitable = getClosestVisitableFromTreePath(path);

		System.out.println(visitable);

		if (visitable != null) {
			JPopupMenu menu = visitable.visit(PopupMenuVisitor.getInstance());

			if (menu != null) {
				menu.show(tree, e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		Visitable visitable = getClosestVisitableFromTreePath(e.getPath());

		JPanel details = frame.getDetails();
		details.removeAll();

		if (visitable != null) {
			JPanel detailPanel = visitable.visit(DetailPanelVisitor.getInstance());

			if (detailPanel != null) {
				details.add(detailPanel, BorderLayout.CENTER);
			}
		}

		details.updateUI();
	}

	/**
	 * @param path
	 * @return
	 */
	private Visitable getClosestVisitableFromTreePath(TreePath path) {

		Visitable visitable = null;

		while (path != null) {
			Object component = path.getLastPathComponent();

			while (component instanceof ArrayTreeNode) {
				component = ((ArrayTreeNode) component).getWrapped();
			}

			if (component instanceof ReferenceTreeNode) {
				visitable = ((ReferenceTreeNode) component).getVisitable();
				break;
			}

			if (component instanceof VisitableTreeNode) {
				visitable = ((VisitableTreeNode) component).getVisitable();
				break;
			}

			path = path.getParentPath();
		}

		return visitable;
	}

	/**
	 *
	 */
	private void openFile() {

		JFileChooser fileChooser = new JFileChooser();
		int userOption = fileChooser.showOpenDialog(this.frame);
		if (JFileChooser.APPROVE_OPTION != userOption) {
			return;
		}

		classFiles = new ArrayList<ClassFile>();

		File file = fileChooser.getSelectedFile();
		try {
			loadFile(file);
			createRootNode();
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Could not load file!";
			String title = "Error";
			JOptionPane.showMessageDialog(this.frame, message, title, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	private void loadFile(File file) throws IOException {

		if (file.getName().endsWith(".class")) {
			loadClassFile(file);
		} else {
			loadZipFile(new ZipFile(file));
		}
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	private void loadClassFile(File file) throws IOException {

		FileInputStream input = new FileInputStream(file);
		ClassFileFactory factory = new ClassFileFactory(input);

		classFiles.add(factory.createClassFile());
	}

	/**
	 * @param zipFile
	 * @throws IOException
	 */
	private void loadZipFile(ZipFile zipFile) throws IOException {

		try {
			Iterator<? extends ZipEntry> iter = zipFile.stream().iterator();

			while (iter.hasNext()) {
				ZipEntry entry = iter.next();

				if (entry.getName().endsWith(".class")) {
					InputStream input = zipFile.getInputStream(entry);
					ClassFileFactory factory = new ClassFileFactory(input);
					classFiles.add(factory.createClassFile());
				}
			}
		} finally {
			if (zipFile != null) {
				zipFile.close();
			}
		}
	}

	/**
	 * 
	 */
	private void createRootNode() {

		PlainTreeNode rootNode = PlainTreeNode.create("Classes");

		TreeNodeVisitor visitor = new TreeNodeVisitor();

		for (ClassFile classFile : classFiles) {
			rootNode.add(classFile.visit(visitor));
		}

		this.frame.getTree().setModel(new DefaultTreeModel(rootNode));
	}

	/**
	 * @return the frame.
	 */
	public JInspectorFrame getFrame() {

		return this.frame;
	}
}
