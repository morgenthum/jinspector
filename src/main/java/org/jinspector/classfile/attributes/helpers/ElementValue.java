package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * ElementValue created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ElementValue implements Visitable {

	public int tag;
	public Object value;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
