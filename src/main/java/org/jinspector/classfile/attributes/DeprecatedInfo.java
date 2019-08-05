package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;

/**
 * Deprecated created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class DeprecatedInfo extends AttributeInfo {

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
