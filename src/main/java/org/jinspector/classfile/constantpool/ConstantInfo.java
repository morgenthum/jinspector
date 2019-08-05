package org.jinspector.classfile.constantpool;

import org.jinspector.Visitable;
import org.jinspector.classfile.ClassFile;

/**
 * ConstantPoolInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public abstract class ConstantInfo implements Visitable {

	public static final int CLASS = 7;
	public static final int FIELDREF = 9;
	public static final int METHODREF = 10;
	public static final int INTERFACE_METHODREF = 11;
	public static final int STRING = 8;
	public static final int INTEGER = 3;
	public static final int FLOAT = 4;
	public static final int LONG = 5;
	public static final int DOUBLE = 6;
	public static final int NAME_AND_TYPE = 12;
	public static final int UTF8 = 1;
	public static final int METHOD_HANDLE = 15;
	public static final int METHOD_TYPE = 16;
	public static final int INVOKE_DYNAMIC = 18;

	public ClassFile classFile;
	public int index;

	public int tag;

	public ConstantInfo(ClassFile classFile, int index) {
		this.classFile = classFile;
		this.index = index;
	}

	public abstract Object getResolvedValue();
}
