package org.jinspector.classfile;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * FieldInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class FieldInfo extends ElementInfo implements Visitable {

	/**
	 * @param classFile
	 * @param index
	 */
	public FieldInfo(ClassFile classFile, int index) {

		super(classFile, index);
	}

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
