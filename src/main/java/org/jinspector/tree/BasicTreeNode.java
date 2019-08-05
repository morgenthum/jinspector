package org.jinspector.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * @author morgenthum
 *
 */
public abstract class BasicTreeNode implements TreeNode {

	private TreeNode parent;
	private List<TreeNode> children;

	/**
	 * @param parent
	 * @param value
	 */
	public BasicTreeNode() {

		this.children = new ArrayList<TreeNode>();
	}

	/**
	 * @param child
	 */
	public void add(BasicTreeNode child) {

		child.parent = this;
		children.add(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int childIndex) {

		return children.get(childIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount() {

		return children.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {

		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node) {

		return children.indexOf(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {

		return children.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	public Enumeration<?> children() {

		return Collections.enumeration(children);
	}
}
