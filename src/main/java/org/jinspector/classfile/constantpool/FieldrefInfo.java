package org.jinspector.classfile.constantpool;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;

/**
 * FieldrefInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class FieldrefInfo extends ConstantInfo {

	public ClassInfo clazz;
	public NameAndTypeInfo nameAndTypeIndex;

	public FieldrefInfo(ClassFile classFile, int index) {
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

	@Override
	public Object getResolvedValue() {
		return clazz.getResolvedValue() + " " + nameAndTypeIndex.getResolvedValue();
	}
}
