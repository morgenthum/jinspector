package org.jinspector.classfile;

import org.jinspector.classfile.attributes.AttributeInfo;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * @author morgenthum
 *
 */
public class ElementInfo {

	public ClassFile classFile;
	public int index;

	public int accessFlags;
	public UTF8Info name;
	public UTF8Info descriptor;
	public int attributesCount;
	public AttributeInfo[] attributes;

	/**
	 * @param classFile
	 * @param index
	 */
	public ElementInfo(ClassFile classFile, int index) {

		this.classFile = classFile;
		this.index = index;
	}
}
