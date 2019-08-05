package org.jinspector.classfile;

import java.util.ArrayList;
import java.util.List;

import org.jinspector.Visitable;
import org.jinspector.Visitor;
import org.jinspector.classfile.instructions.Instruction;

/**
 * MethodInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class MethodInfo extends ElementInfo implements Visitable {

	public List<Instruction> instructions = new ArrayList<Instruction>();

	/**
	 * @param classFile
	 * @param index
	 */
	public MethodInfo(ClassFile classFile, int index) {

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
