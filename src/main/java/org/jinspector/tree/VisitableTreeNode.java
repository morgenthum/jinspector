package org.jinspector.tree;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * @author morgenthum
 *
 */
public class VisitableTreeNode extends BasicTreeNode {

	private Visitable visitable;
	private Visitor<String> presentationVisitor;

	public VisitableTreeNode(Visitable visitable, Visitor<String> presentationVisitor) {

		this.visitable = visitable;
		this.presentationVisitor = presentationVisitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return visitable == null ? "null" : visitable.visit(presentationVisitor);
	}

	public Visitable getVisitable() {
		return visitable;
	}
}
