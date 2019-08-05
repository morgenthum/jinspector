package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.stackmap.StackMapFrame;

/**
 * StackMapTableInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class StackMapTableInfo extends AttributeInfo {

	public int numberOfEntries;
	public StackMapFrame[] entries;

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
