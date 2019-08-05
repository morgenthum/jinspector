package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.ParameterAnnotation;

/**
 * RuntimeInvisibleParameterAnnotationsInfo created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class RuntimeInvisibleParameterAnnotationsInfo extends AttributeInfo {

	public int numParameters;
	public ParameterAnnotation[] parameterAnnotations;

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
