package org.jinspector.classfile.attributes;

import java.util.ArrayList;
import java.util.List;

import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.helpers.ExceptionEntry;
import org.jinspector.classfile.instructions.Instruction;

/**
 * CodeInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class CodeInfo extends AttributeInfo {

	public int maxStack;
	public int maxLocals;
	public int codeLength;
	public int[] code;
	public int exceptionTableLength;
	public ExceptionEntry[] exceptionTable;
	public int attributesCount;
	public AttributeInfo[] attributes;

	public List<Instruction> instructions = new ArrayList<Instruction>();

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
