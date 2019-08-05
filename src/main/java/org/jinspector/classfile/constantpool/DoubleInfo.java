package org.jinspector.classfile.constantpool;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;

/**
 * DoubleInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class DoubleInfo extends ConstantInfo {

	public int highBytes;
	public int lowBytes;

	public DoubleInfo(ClassFile classFile, int index) {
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
		// TODO Auto-generated method stub
		return 0;
	}
}
