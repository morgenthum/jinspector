package org.jinspector.tree;

import org.jinspector.Visitable;

public class ReferenceTreeNode extends BasicTreeNode {

	private String name;
	private Visitable visitable;

	/**
	 * @param name
	 * @param visitable
	 * @return
	 */
	public ReferenceTreeNode(String name, Visitable visitable) {

		this.name = name;
		this.visitable = visitable;
	}

	@Override
	public String toString() {
		return this.name + ": " + this.visitable.visit(ReferenceVisitor.getInstance());
	}

	public Visitable getVisitable() {
		return visitable;
	}
}
