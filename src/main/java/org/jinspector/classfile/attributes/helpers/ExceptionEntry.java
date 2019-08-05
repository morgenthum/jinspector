package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * Exception created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ExceptionEntry implements Visitable {

	public int startPc;
	public int endPc;
	public int handlerPc;
	public int catchType;

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
