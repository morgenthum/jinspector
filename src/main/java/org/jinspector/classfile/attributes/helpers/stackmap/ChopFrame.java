package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;

/**
 * ChopFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ChopFrame extends StackMapFrame {

	public int offsetDelta;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
