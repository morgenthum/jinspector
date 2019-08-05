package org.jinspector.classfile.constantpool;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;

/**
 * InvokeDynamicInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class InvokeDynamicInfo extends ConstantInfo {

	public int bootstrapMethodAttributeIndex;
	public int nameAndTypeIndex;

	public InvokeDynamicInfo(ClassFile classFile, int index) {
		super(classFile, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitable#visit(org.jreverse.tree.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}

	@Override
	public Object getResolvedValue() {
		// TODO Auto-generated method stub
		return 0;
	}
}
