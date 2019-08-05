package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.classfile.ClassFile;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * AttributeInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public abstract class AttributeInfo implements Visitable {

	public ClassFile classFile;

	public UTF8Info attributeNameIndex;
	public int attributeLength;
}
