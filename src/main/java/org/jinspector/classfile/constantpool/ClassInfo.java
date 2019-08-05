package org.jinspector.classfile.constantpool;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;

/**
 * ClassInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ClassInfo extends ConstantInfo {

	public UTF8Info name;

	public ClassInfo(ClassFile classFile, int index) {
		super(classFile, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.classfile.constantpool.ConstantInfo#visit(org.jreverse.tree.
	 * Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Object getResolvedValue() {
		return name.getContent();
	}
}
