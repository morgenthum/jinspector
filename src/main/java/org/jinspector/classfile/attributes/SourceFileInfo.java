package org.jinspector.classfile.attributes;

import org.jinspector.Visitor;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * SourceFileInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class SourceFileInfo extends AttributeInfo {

	public UTF8Info sourcefile;

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
