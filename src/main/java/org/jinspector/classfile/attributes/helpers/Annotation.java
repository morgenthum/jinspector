package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * Annotation created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class Annotation implements Visitable {

	/**
	 * ElementValuePairs created on 16.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class ElementValuePair implements Visitable {

		public int elementNameIndex;
		public ElementValue elementValue;

		@Override
		public <T> T visit(Visitor<T> visitor) {

			return visitor.visit(this);
		}
	}

	public int typeIndex;
	public int numElementValuePairs;
	public ElementValuePair[] elementValuePairs;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
