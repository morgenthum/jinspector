package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;

/**
 * ExceptionInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ExceptionInfo extends AttributeInfo {

	public int numberOfExceptions;
	public int[] exceptionIndexTable;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitable#visit(org.jreverse.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
