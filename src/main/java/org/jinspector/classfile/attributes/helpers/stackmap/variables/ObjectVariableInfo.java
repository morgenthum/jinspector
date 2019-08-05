package org.jinspector.classfile.attributes.helpers.stackmap.variables;

import org.jinspector.Visitor;

/**
 * ObjectVariableInfo created on 17.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ObjectVariableInfo extends VerificationType {

	public int cpoolIndex;

	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
