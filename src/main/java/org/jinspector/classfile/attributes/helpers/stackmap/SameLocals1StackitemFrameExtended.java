package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.VerificationType;

/**
 * SameLocals1StackitemFrameExtended created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SameLocals1StackitemFrameExtended extends StackMapFrame {

	public int offsetDelta;
	public VerificationType stack;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
