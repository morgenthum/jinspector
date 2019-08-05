package org.jinspector.tree;

import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;
import org.jinspector.classfile.FieldInfo;
import org.jinspector.classfile.MethodInfo;
import org.jinspector.classfile.attributes.AnnotationDefaultInfo;
import org.jinspector.classfile.attributes.BootstrapMethodsInfo;
import org.jinspector.classfile.attributes.BootstrapMethodsInfo.BootstrapMethod;
import org.jinspector.classfile.attributes.CodeInfo;
import org.jinspector.classfile.attributes.ConstantValueInfo;
import org.jinspector.classfile.attributes.DeprecatedInfo;
import org.jinspector.classfile.attributes.EnclosingMethodInfo;
import org.jinspector.classfile.attributes.ExceptionInfo;
import org.jinspector.classfile.attributes.InnerClassesInfo;
import org.jinspector.classfile.attributes.InnerClassesInfo.Clazz;
import org.jinspector.classfile.attributes.LineNumberTableInfo;
import org.jinspector.classfile.attributes.LineNumberTableInfo.LineNumber;
import org.jinspector.classfile.attributes.LocalVariableTableInfo;
import org.jinspector.classfile.attributes.LocalVariableTableInfo.LocalVariable;
import org.jinspector.classfile.attributes.LocalVariableTypeTableInfo;
import org.jinspector.classfile.attributes.LocalVariableTypeTableInfo.LocalVariableType;
import org.jinspector.classfile.attributes.RuntimeInvisibleAnnotationInfo;
import org.jinspector.classfile.attributes.RuntimeInvisibleParameterAnnotationsInfo;
import org.jinspector.classfile.attributes.RuntimeVisibleAnnotationsInfo;
import org.jinspector.classfile.attributes.RuntimeVisibleParameterAnnotationsInfo;
import org.jinspector.classfile.attributes.SignatureInfo;
import org.jinspector.classfile.attributes.SourceDebugExtensionInfo;
import org.jinspector.classfile.attributes.SourceFileInfo;
import org.jinspector.classfile.attributes.StackMapTableInfo;
import org.jinspector.classfile.attributes.SyntheticInfo;
import org.jinspector.classfile.attributes.helpers.Annotation;
import org.jinspector.classfile.attributes.helpers.Annotation.ElementValuePair;
import org.jinspector.classfile.attributes.helpers.ArrayValue;
import org.jinspector.classfile.attributes.helpers.ElementValue;
import org.jinspector.classfile.attributes.helpers.EnumConstValue;
import org.jinspector.classfile.attributes.helpers.ExceptionEntry;
import org.jinspector.classfile.attributes.helpers.ParameterAnnotation;
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

public class SpecificationVisitor extends Visitor<String> {

	private static final String SPECIFICATION_URL = "https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-";

	private static SpecificationVisitor instance;

	/**
	 * @return
	 */
	public static synchronized SpecificationVisitor getInstance() {

		if (instance == null) {
			instance = new SpecificationVisitor();
		}

		return instance;
	}

	/**
	 * 
	 */
	private SpecificationVisitor() {
	}

	@Override
	public String visit(ClassFile classFile) {
		return SPECIFICATION_URL + "4.1";
	}

	@Override
	public String visit(ClassInfo classInfo) {
		return SPECIFICATION_URL + "4.4.1";
	}

	@Override
	public String visit(DoubleInfo doubleInfo) {
		return SPECIFICATION_URL + "4.4.5";
	}

	@Override
	public String visit(FieldrefInfo fieldrefInfo) {
		return SPECIFICATION_URL + "4.4.2";
	}

	@Override
	public String visit(FloatInfo floatInfo) {
		return SPECIFICATION_URL + "4.4.4";
	}

	@Override
	public String visit(IntegerInfo integerInfo) {
		return SPECIFICATION_URL + "4.4.4";
	}

	@Override
	public String visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {
		return SPECIFICATION_URL + "4.4.2";
	}

	@Override
	public String visit(InvokeDynamicInfo invokeDynamicInfo) {
		return SPECIFICATION_URL + "4.4.10";
	}

	@Override
	public String visit(LongInfo longInfo) {
		return SPECIFICATION_URL + "4.4.5";
	}

	@Override
	public String visit(MethodHandleInfo methodHandleInfo) {
		return SPECIFICATION_URL + "4.4.8";
	}

	@Override
	public String visit(MethodrefInfo methodrefInfo) {
		return SPECIFICATION_URL + "4.4.2";
	}

	@Override
	public String visit(MethodTypeInfo methodTypeInfo) {
		return SPECIFICATION_URL + "4.4.9";
	}

	@Override
	public String visit(NameAndTypeInfo nameAndTypeInfo) {
		return SPECIFICATION_URL + "4.4.6";
	}

	@Override
	public String visit(StringInfo stringInfo) {
		return SPECIFICATION_URL + "4.4.3";
	}

	@Override
	public String visit(UTF8Info utf8Info) {
		return SPECIFICATION_URL + "4.4.7";
	}

	@Override
	public String visit(FieldInfo fieldInfo) {
		return SPECIFICATION_URL + "4.5";
	}

	@Override
	public String visit(MethodInfo methodInfo) {
		return SPECIFICATION_URL + "4.6";
	}

	@Override
	public String visit(AnnotationDefaultInfo annotationDefaultInfo) {
		return SPECIFICATION_URL + "4.7.22";
	}

	@Override
	public String visit(BootstrapMethodsInfo bootstrapMethodsInfo) {
		return SPECIFICATION_URL + "4.7.23";
	}

	@Override
	public String visit(CodeInfo codeInfo) {
		return SPECIFICATION_URL + "4.7.3";
	}

	@Override
	public String visit(ConstantValueInfo constantValueInfo) {
		return SPECIFICATION_URL + "4.7.2";
	}

	@Override
	public String visit(DeprecatedInfo deprecatedInfo) {
		return SPECIFICATION_URL + "4.7.15";
	}

	@Override
	public String visit(EnclosingMethodInfo enclosingMethodInfo) {
		return SPECIFICATION_URL + "4.7.7";
	}

	@Override
	public String visit(ExceptionInfo exceptionInfo) {
		return SPECIFICATION_URL + "4.7.5";
	}

	@Override
	public String visit(InnerClassesInfo innerClassesInfo) {
		return SPECIFICATION_URL + "4.7.6";
	}

	@Override
	public String visit(LineNumberTableInfo lineNumberTableInfo) {
		return SPECIFICATION_URL + "4.7.12";
	}

	@Override
	public String visit(LocalVariableTableInfo localVariableTableInfo) {
		return SPECIFICATION_URL + "4.7.13";
	}

	@Override
	public String visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		return SPECIFICATION_URL + "4.7.14";
	}

	@Override
	public String visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		return SPECIFICATION_URL + "4.7.17";
	}

	@Override
	public String visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		return SPECIFICATION_URL + "4.7.19";
	}

	@Override
	public String visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		return SPECIFICATION_URL + "4.7.16";
	}

	@Override
	public String visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		return SPECIFICATION_URL + "4.7.18";
	}

	@Override
	public String visit(SignatureInfo signatureInfo) {
		return SPECIFICATION_URL + "4.7.9";
	}

	@Override
	public String visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		return SPECIFICATION_URL + "4.7.11";
	}

	@Override
	public String visit(SourceFileInfo sourceFileInfo) {
		return SPECIFICATION_URL + "4.7.10";
	}

	@Override
	public String visit(StackMapTableInfo stackMapTableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(SyntheticInfo syntheticInfo) {
		return SPECIFICATION_URL + "4.7.8";
	}

	@Override
	public String visit(Annotation annotation) {
		return SPECIFICATION_URL + "4.7.16.1";
	}

	@Override
	public String visit(ArrayValue arrayValue) {
		return SPECIFICATION_URL + "4.7.16.1";
	}

	@Override
	public String visit(ElementValue elementValue) {
		return SPECIFICATION_URL + "4.7.16.1";
	}

	@Override
	public String visit(EnumConstValue enumConstValue) {
		return SPECIFICATION_URL + "4.7.16.1";
	}

	@Override
	public String visit(ExceptionEntry exceptionEntry) {
		return SPECIFICATION_URL + "4.7.3";
	}

	@Override
	public String visit(ParameterAnnotation parameterAnnotation) {
		return SPECIFICATION_URL + "4.7.18";
	}

	@Override
	public String visit(AppendFrame appendFrame) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(ChopFrame chopFrame) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(FullFrame fullFrame) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(SameFrame sameFrame) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(SameFrameExtended sameFrameExtended) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(DoubleVariableInfo doubleVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(FloatVariableInfo floatVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(IntegerVariableInfo integerVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(LongVariableInfo longVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(NullVariableInfo nullVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(ObjectVariableInfo objectVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(TopVariableInfo topVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(UninitializedVariableInfo uninitializedVariableInfo) {
		return SPECIFICATION_URL + "4.7.4";
	}

	@Override
	public String visit(ElementValuePair elementValuePair) {
		return SPECIFICATION_URL + "4.7.16";
	}

	@Override
	public String visit(BootstrapMethod bootstrapMethod) {
		return SPECIFICATION_URL + "4.7.23";
	}

	@Override
	public String visit(Clazz clazz) {
		return SPECIFICATION_URL + "4.7.6";
	}

	@Override
	public String visit(LineNumber lineNumber) {
		return SPECIFICATION_URL + "4.7.12";
	}

	@Override
	public String visit(LocalVariable localVariable) {
		return SPECIFICATION_URL + "4.7.13";
	}

	@Override
	public String visit(LocalVariableType localVariableType) {
		return SPECIFICATION_URL + "4.7.14";
	}

}
