package org.jinspector.classfile.instructions;

public class PrimitiveOperand extends Operand {

	public int value;

	public PrimitiveOperand(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
