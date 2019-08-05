package org.jinspector.classfile.constantpool;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;

/**
 * UTF8Info created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class UTF8Info extends ConstantInfo {

	public int length;
	public byte[] bytes;

	public UTF8Info(ClassFile classFile, int index) {
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

	/**
	 * @return
	 */
	public String getContent() {

		return new String(this.bytes);
	}

	@Override
	public Object getResolvedValue() {
		return "\"" + getContent() + "\"";
	}
}
