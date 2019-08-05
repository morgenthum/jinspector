package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.VerificationType;

/**
 * FullFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class FullFrame extends StackMapFrame {

	public int offsetDelta;
	public int numberOfLocals;
	public VerificationType[] locals;
	public int numberOfStackItems;
	public VerificationType[] stack;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
