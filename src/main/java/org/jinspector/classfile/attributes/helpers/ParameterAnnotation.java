package org.jinspector.classfile.attributes.helpers;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * ParameterAnnotation created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ParameterAnnotation implements Visitable {

	public int numAnnotations;
	public Annotation[] annotations;

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
