package org.jinspector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jinspector.classfile.FieldInfo;
import org.jinspector.classfile.MethodInfo;

/**
 * @author morgenthum
 *
 */
public class Util {

	/**
	 * @param source
	 * @param accessFlags
	 * @return
	 */
	public static String getAccessFlagsString(Object source, int accessFlags) {

		List<String> modifiers = new ArrayList<String>();

		if ((accessFlags & 0x0001) != 0) {
			modifiers.add("PUBLIC");
		}
		if ((accessFlags & 0x0002) != 0) {
			modifiers.add("PRIVATE");
		}
		if ((accessFlags & 0x0004) != 0) {
			modifiers.add("PROTECTED");
		}
		if ((accessFlags & 0x0008) != 0) {
			modifiers.add("STATIC");
		}
		if ((accessFlags & 0x0010) != 0) {
			modifiers.add("FINAL");
		}
		if ((accessFlags & 0x0020) != 0) {
			modifiers.add("SUPER");
		}
		if ((accessFlags & 0x0040) != 0) {
			if (source instanceof FieldInfo) {
				modifiers.add("VOLATILE");
			} else if (source instanceof MethodInfo) {
				modifiers.add("BRIDGE");
			}
		}
		if ((accessFlags & 0x0080) != 0) {
			if (source instanceof FieldInfo) {
				modifiers.add("TRANSIENT");
			} else if (source instanceof MethodInfo) {
				modifiers.add("VARARGS");
			}
		}
		if ((accessFlags & 0x0100) != 0) {
			modifiers.add("NATIVE");
		}
		if ((accessFlags & 0x0200) != 0) {
			modifiers.add("INTERFACE");
		}
		if ((accessFlags & 0x0400) != 0) {
			modifiers.add("ABSTRACT");
		}
		if ((accessFlags & 0x0800) != 0) {
			modifiers.add("STRICT");
		}
		if ((accessFlags & 0x1000) != 0) {
			modifiers.add("SYNTHETIC");
		}
		if ((accessFlags & 0x2000) != 0) {
			modifiers.add("ANNOTATION");
		}
		if ((accessFlags & 0x4000) != 0) {
			modifiers.add("ENUM");
		}

		StringBuilder builder = new StringBuilder();
		builder.append(accessFlags);
		builder.append(" (");
		Iterator<String> iterator = modifiers.iterator();
		if (iterator.hasNext()) {
			builder.append(iterator.next());
			while (iterator.hasNext()) {
				builder.append(" | ");
				builder.append(iterator.next());
			}
		}
		builder.append(")");

		return builder.toString();
	}
}
