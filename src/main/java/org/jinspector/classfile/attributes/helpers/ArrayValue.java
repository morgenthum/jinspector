package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * ArrayValue created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ArrayValue implements Visitable {

	public int numValues;
	public ElementValue[] values;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
