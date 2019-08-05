package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.VerificationType;

/**
 * SameLocals1StackItemFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SameLocals1StackItemFrame extends StackMapFrame {

	public VerificationType stack;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
