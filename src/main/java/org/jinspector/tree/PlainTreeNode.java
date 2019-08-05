package org.jinspector.tree;

/**
 * @author morgenthum
 *
 */
public class PlainTreeNode extends BasicTreeNode {

	/**
	 * @param name
	 * @param value
	 * @return
	 */
	public static PlainTreeNode create(String name, Object value) {

		return new PlainTreeNode(name + ": " + value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static PlainTreeNode create(Object value) {

		return new PlainTreeNode(value);
	}

	private Object value;

	/**
	 * @param value
	 */
	private PlainTreeNode(Object value) {

		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return String.valueOf(value);
	}
}
