package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;

/**
 * SameFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SameFrame extends StackMapFrame {

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "same_frame";
	}
}
