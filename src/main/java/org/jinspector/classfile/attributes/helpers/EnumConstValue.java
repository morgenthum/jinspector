package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * EnumConstValue created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class EnumConstValue implements Visitable {

	public int typeNameIndex;
	public int constNameIndex;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
