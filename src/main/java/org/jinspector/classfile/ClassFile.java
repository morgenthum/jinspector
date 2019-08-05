package org.jinspector.classfile;

import org.jinspector.Visitable;
import org.jinspector.Visitor;
import org.jinspector.classfile.attributes.AttributeInfo;
import org.jinspector.classfile.constantpool.ClassInfo;
import org.jinspector.classfile.constantpool.ConstantInfo;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * ClassFile created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ClassFile implements Visitable {

	public int magic;
	public int minorVersion;
	public int majorVersion;
	public int constantPoolCount;
	public ConstantInfo[] constantPool;
	public int accessFlags;
	public ClassInfo thisClass;
	public ClassInfo superClass;
	public int interfacesCount;
	public ClassInfo[] interfaces;
	public int fieldsCount;
	public FieldInfo[] fields;
	public int methodsCount;
	public MethodInfo[] methods;
	public int attributesCount;
	public AttributeInfo[] attributes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitable#visit(org.jreverse.tree.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}

	public boolean isAbstract() {

		return (accessFlags & 0x0400) != 0;
	}

	public boolean isAnnotation() {

		return (accessFlags & 0x2000) != 0;
	}

	public boolean isEnum() {

		return (accessFlags & 0x4000) != 0;
	}

	public boolean isInterface() {

		return (accessFlags & 0x0200) != 0;
	}

	/**
	 * @return
	 */
	public String getMagicString() {

		return this.magic + " (0x" + Integer.toHexString(magic).toUpperCase() + ")";
	}

	/**
	 * @return
	 */
	public String getMajorVersionString() {

		StringBuilder builder = new StringBuilder();

		builder.append(majorVersion);

		if (majorVersion >= 45 && majorVersion <= 52) {
			builder.append(" (");
			switch (majorVersion) {
			case 52:
				builder.append("J2SE 8");
				break;
			case 51:
				builder.append("J2SE 7");
				break;
			case 50:
				builder.append("J2SE 6.0");
				break;
			case 49:
				builder.append("J2SE 5.0");
				break;
			case 48:
				builder.append("JDK 1.4");
				break;
			case 47:
				builder.append("JDK 1.3");
				break;
			case 46:
				builder.append("JDK 1.2");
				break;
			case 45:
				builder.append("JDK 1.1");
				break;
			}
			builder.append(")");
		}

		return builder.toString();
	}

	/**
	 * @param index
	 * @return
	 */
	public ClassInfo getClassInfo(int index) {

		return (ClassInfo) constantPool[index];
	}

	/**
	 * @param index
	 * @return
	 */
	public UTF8Info getUtf8Info(int index) {

		return (UTF8Info) constantPool[index];
	}
}
