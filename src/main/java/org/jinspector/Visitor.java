package org.jinspector;

import org.jinspector.classfile.ClassFile;
import org.jinspector.classfile.FieldInfo;
import org.jinspector.classfile.MethodInfo;
import org.jinspector.classfile.attributes.AnnotationDefaultInfo;
import org.jinspector.classfile.attributes.BootstrapMethodsInfo;
import org.jinspector.classfile.attributes.CodeInfo;
import org.jinspector.classfile.attributes.ConstantValueInfo;
import org.jinspector.classfile.attributes.DeprecatedInfo;
import org.jinspector.classfile.attributes.EnclosingMethodInfo;
import org.jinspector.classfile.attributes.ExceptionInfo;
import org.jinspector.classfile.attributes.InnerClassesInfo;
import org.jinspector.classfile.attributes.LineNumberTableInfo;
import org.jinspector.classfile.attributes.LocalVariableTableInfo;
import org.jinspector.classfile.attributes.LocalVariableTypeTableInfo;
import org.jinspector.classfile.attributes.RuntimeInvisibleAnnotationInfo;
import org.jinspector.classfile.attributes.RuntimeInvisibleParameterAnnotationsInfo;
import org.jinspector.classfile.attributes.RuntimeVisibleAnnotationsInfo;
import org.jinspector.classfile.attributes.RuntimeVisibleParameterAnnotationsInfo;
import org.jinspector.classfile.attributes.SignatureInfo;
import org.jinspector.classfile.attributes.SourceDebugExtensionInfo;
import org.jinspector.classfile.attributes.SourceFileInfo;
import org.jinspector.classfile.attributes.StackMapTableInfo;
import org.jinspector.classfile.attributes.SyntheticInfo;
import org.jinspector.classfile.attributes.BootstrapMethodsInfo.BootstrapMethod;
import org.jinspector.classfile.attributes.InnerClassesInfo.Clazz;
import org.jinspector.classfile.attributes.LineNumberTableInfo.LineNumber;
import org.jinspector.classfile.attributes.LocalVariableTableInfo.LocalVariable;
import org.jinspector.classfile.attributes.LocalVariableTypeTableInfo.LocalVariableType;
import org.jinspector.classfile.attributes.helpers.Annotation;
import org.jinspector.classfile.attributes.helpers.ArrayValue;
import org.jinspector.classfile.attributes.helpers.ElementValue;
import org.jinspector.classfile.attributes.helpers.EnumConstValue;
import org.jinspector.classfile.attributes.helpers.ExceptionEntry;
import org.jinspector.classfile.attributes.helpers.ParameterAnnotation;
import org.jinspector.classfile.attributes.helpers.Annotation.ElementValuePair;
import org.jinspector.classfile.attributes.helpers.stackmap.AppendFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.ChopFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.FullFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.SameFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.SameFrameExtended;
import org.jinspector.classfile.attributes.helpers.stackmap.SameLocals1StackItemFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.SameLocals1StackitemFrameExtended;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.DoubleVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.FloatVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.IntegerVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.LongVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.NullVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.ObjectVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.TopVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.UninitializedThisVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.UninitializedVariableInfo;
import org.jinspector.classfile.constantpool.ClassInfo;
import org.jinspector.classfile.constantpool.DoubleInfo;
import org.jinspector.classfile.constantpool.FieldrefInfo;
import org.jinspector.classfile.constantpool.FloatInfo;
import org.jinspector.classfile.constantpool.IntegerInfo;
import org.jinspector.classfile.constantpool.InterfaceMethodrefInfo;
import org.jinspector.classfile.constantpool.InvokeDynamicInfo;
import org.jinspector.classfile.constantpool.LongInfo;
import org.jinspector.classfile.constantpool.MethodHandleInfo;
import org.jinspector.classfile.constantpool.MethodTypeInfo;
import org.jinspector.classfile.constantpool.MethodrefInfo;
import org.jinspector.classfile.constantpool.NameAndTypeInfo;
import org.jinspector.classfile.constantpool.StringInfo;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * @author morgenthum
 *
 * @param <T>
 */
public abstract class Visitor<T> {

	/**
	 * @param visitable
	 * @param visitor
	 * @return
	 */
	protected static <T> T safeVisit(Visitable visitable, Visitor<T> visitor) {

		if (visitable != null) {
			return visitable.visit(visitor);
		}

		return null;
	}

	/**
	 * @param classFile
	 * @return
	 */
	public abstract T visit(ClassFile classFile);

	/**
	 * @param classInfo
	 * @return
	 */
	public abstract T visit(ClassInfo classInfo);

	/**
	 * @param doubleInfo
	 * @return
	 */
	public abstract T visit(DoubleInfo doubleInfo);

	/**
	 * @param fieldrefInfo
	 * @return
	 */
	public abstract T visit(FieldrefInfo fieldrefInfo);

	/**
	 * @param floatInfo
	 * @return
	 */
	public abstract T visit(FloatInfo floatInfo);

	/**
	 * @param integerInfo
	 * @return
	 */
	public abstract T visit(IntegerInfo integerInfo);

	/**
	 * @param interfaceMethodrefInfo
	 * @return
	 */
	public abstract T visit(InterfaceMethodrefInfo interfaceMethodrefInfo);

	/**
	 * @param invokeDynamicInfo
	 * @return
	 */
	public abstract T visit(InvokeDynamicInfo invokeDynamicInfo);

	/**
	 * @param longInfo
	 * @return
	 */
	public abstract T visit(LongInfo longInfo);

	/**
	 * @param methodHandleInfo
	 * @return
	 */
	public abstract T visit(MethodHandleInfo methodHandleInfo);

	/**
	 * @param methodrefInfo
	 * @return
	 */
	public abstract T visit(MethodrefInfo methodrefInfo);

	/**
	 * @param methodTypeInfo
	 * @return
	 */
	public abstract T visit(MethodTypeInfo methodTypeInfo);

	/**
	 * @param nameAndTypeInfo
	 * @return
	 */
	public abstract T visit(NameAndTypeInfo nameAndTypeInfo);

	/**
	 * @param stringInfo
	 * @return
	 */
	public abstract T visit(StringInfo stringInfo);

	/**
	 * @param utf8Info
	 * @return
	 */
	public abstract T visit(UTF8Info utf8Info);

	/**
	 * @param fieldInfo
	 * @return
	 */
	public abstract T visit(FieldInfo fieldInfo);

	/**
	 * @param methodInfo
	 * @return
	 */
	public abstract T visit(MethodInfo methodInfo);

	/**
	 * @param annotationDefaultInfo
	 */
	public abstract T visit(AnnotationDefaultInfo annotationDefaultInfo);

	/**
	 * @param bootstrapMethodsInfo
	 * @return
	 */
	public abstract T visit(BootstrapMethodsInfo bootstrapMethodsInfo);

	/**
	 * @param codeInfo
	 * @return
	 */
	public abstract T visit(CodeInfo codeInfo);

	/**
	 * @param constantValueInfo
	 * @return
	 */
	public abstract T visit(ConstantValueInfo constantValueInfo);

	/**
	 * @param deprecatedInfo
	 * @return
	 */
	public abstract T visit(DeprecatedInfo deprecatedInfo);

	/**
	 * @param enclosingMethodInfo
	 * @return
	 */
	public abstract T visit(EnclosingMethodInfo enclosingMethodInfo);

	/**
	 * @param exceptionInfo
	 * @return
	 */
	public abstract T visit(ExceptionInfo exceptionInfo);

	/**
	 * @param innerClassesInfo
	 * @return
	 */
	public abstract T visit(InnerClassesInfo innerClassesInfo);

	/**
	 * @param lineNumberTableInfo
	 * @return
	 */
	public abstract T visit(LineNumberTableInfo lineNumberTableInfo);

	/**
	 * @param localVariableTableInfo
	 * @return
	 */
	public abstract T visit(LocalVariableTableInfo localVariableTableInfo);

	/**
	 * @param localVariableTypeTableInfo
	 * @return
	 */
	public abstract T visit(LocalVariableTypeTableInfo localVariableTypeTableInfo);

	/**
	 * @param runtimeInvisibleAnnotationInfo
	 * @return
	 */
	public abstract T visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo);

	/**
	 * @param runtimeInvisibleParameterAnnotationsInfo
	 * @return
	 */
	public abstract T visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo);

	/**
	 * @param runtimeVisibleAnnotationsInfo
	 * @return
	 */
	public abstract T visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo);

	/**
	 * @param runtimeVisibleParameterAnnotationsInfo
	 * @return
	 */
	public abstract T visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo);

	/**
	 * @param signatureInfo
	 * @return
	 */
	public abstract T visit(SignatureInfo signatureInfo);

	/**
	 * @param sourceDebugExtensionInfo
	 * @return
	 */
	public abstract T visit(SourceDebugExtensionInfo sourceDebugExtensionInfo);

	/**
	 * @param sourceFileInfo
	 * @return
	 */
	public abstract T visit(SourceFileInfo sourceFileInfo);

	/**
	 * @param stackMapTableInfo
	 * @return
	 */
	public abstract T visit(StackMapTableInfo stackMapTableInfo);

	/**
	 * @param syntheticInfo
	 * @return
	 */
	public abstract T visit(SyntheticInfo syntheticInfo);

	public abstract T visit(Annotation annotation);

	public abstract T visit(ArrayValue arrayValue);

	public abstract T visit(ElementValue elementValue);

	public abstract T visit(EnumConstValue enumConstValue);

	public abstract T visit(ExceptionEntry exceptionEntry);

	public abstract T visit(ParameterAnnotation parameterAnnotation);

	public abstract T visit(AppendFrame appendFrame);

	public abstract T visit(ChopFrame chopFrame);

	public abstract T visit(FullFrame fullFrame);

	public abstract T visit(SameFrame sameFrame);

	public abstract T visit(SameFrameExtended sameFrameExtended);

	public abstract T visit(SameLocals1StackItemFrame sameLocals1StackItemFrame);

	public abstract T visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended);

	public abstract T visit(DoubleVariableInfo doubleVariableInfo);

	public abstract T visit(FloatVariableInfo floatVariableInfo);

	public abstract T visit(IntegerVariableInfo integerVariableInfo);

	public abstract T visit(LongVariableInfo longVariableInfo);

	public abstract T visit(NullVariableInfo nullVariableInfo);

	public abstract T visit(ObjectVariableInfo objectVariableInfo);

	public abstract T visit(TopVariableInfo topVariableInfo);

	public abstract T visit(UninitializedThisVariableInfo uninitializedThisVariableInfo);

	public abstract T visit(UninitializedVariableInfo uninitializedVariableInfo);

	public abstract T visit(ElementValuePair elementValuePair);

	public abstract T visit(BootstrapMethod bootstrapMethod);

	public abstract T visit(Clazz clazz);

	public abstract T visit(LineNumber lineNumber);

	public abstract T visit(LocalVariable localVariable);

	public abstract T visit(LocalVariableType localVariableType);
}
