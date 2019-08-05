package org.jinspector.classfile.instructions;

import org.jinspector.classfile.ClassFile;

public class ConstantPoolOperand extends Operand {

	public ClassFile classFile;
	public int index;

	public ConstantPoolOperand(ClassFile classFile, int index) {
		this.classFile = classFile;
		this.index = index;
	}

	@Override
	public String toString() {
		return "#" + index;
	}
}
