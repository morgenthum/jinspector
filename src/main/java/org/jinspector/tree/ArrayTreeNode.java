package org.jinspector.tree;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

/**
 * @author morgenthum
 *
 */
public class ArrayTreeNode extends BasicTreeNode {

	private int index;
	private BasicTreeNode wrapped;

	/**
	 * @param index
	 * @param wrapped
	 */
	public ArrayTreeNode(int index, BasicTreeNode wrapped) {

		this.index = index;
		this.wrapped = wrapped;
	}

	/**
	 * @param child
	 */
	@Override
	public void add(BasicTreeNode child) {

		if (wrapped != null) {
			wrapped.add(child);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int childIndex) {

		return wrapped == null ? null : wrapped.getChildAt(childIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount() {

		return wrapped == null ? 0 : wrapped.getChildCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {

		return wrapped == null ? null : wrapped.getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node) {

		return wrapped == null ? -1 : wrapped.getIndex(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {

		return wrapped != null && wrapped.getAllowsChildren();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {

		return wrapped == null || wrapped.isLeaf();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	public Enumeration<?> children() {

		return wrapped == null ? null : wrapped.children();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + index + "]: " + String.valueOf(wrapped);
	}

	/**
	 * @return the wrapped
	 */
	public BasicTreeNode getWrapped() {
		return wrapped;
	}
}
