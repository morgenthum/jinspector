package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;

/**
 * SignatureInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SignatureInfo extends AttributeInfo {

	public int signatureIndex;

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
