package org.jinspector.ui;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.jinspector.Visitable;
import org.jinspector.tree.VisitableTreeNode;

public class ClassFileTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(2, 2, 2, 2);

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, false);

		setBorder(EMPTY_BORDER);

		if (value instanceof VisitableTreeNode) {
			Visitable visitable = ((VisitableTreeNode) value).getVisitable();
			setIcon(visitable.visit(IconVisitor.getInstance()));
		} else {
			setIcon(null);
		}

		return this;
	}
}
