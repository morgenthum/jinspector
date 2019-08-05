package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;

/**
 * EnclosingMethodInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class EnclosingMethodInfo extends AttributeInfo {

	public int classIndex;
	public int methodIndex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitable#visit(org.jreverse.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
