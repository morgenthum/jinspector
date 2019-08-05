package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;

/**
 * SourceDebugExtensionInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SourceDebugExtensionInfo extends AttributeInfo {

	public int[] debugExtension;

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
