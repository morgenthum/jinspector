package org.jinspector.classfile.attributes.helpers.stackmap;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.VerificationType;

/**
 * AppendFrame created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class AppendFrame extends StackMapFrame {

	public int offsetDelta;
	public VerificationType[] locals;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
