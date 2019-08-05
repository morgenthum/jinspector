package org.jinspector.tree;

import org.jinspector.Visitor;
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
 */
public class DeclarationVisitor extends Visitor<String> {

	private static DeclarationVisitor instance;

	/**
	 * @return
	 */
	public static synchronized DeclarationVisitor getInstance() {

		if (instance == null) {
			instance = new DeclarationVisitor();
		}

		return instance;
	}

	/**
	 * 
	 */
	private DeclarationVisitor() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.ClassFile)
	 */
	@Override
	public String visit(ClassFile classFile) {

		return classFile.thisClass.name.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * ClassInfo)
	 */
	@Override
	public String visit(ClassInfo classInfo) {

		return "CONSTANT_Class_info (" + classInfo.name.getContent() + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * DoubleInfo)
	 */
	@Override
	public String visit(DoubleInfo doubleInfo) {

		return "CONSTANT_Double_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * FieldrefInfo)
	 */
	@Override
	public String visit(FieldrefInfo fieldrefInfo) {

		return "CONSTANT_Fieldref_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * FloatInfo)
	 */
	@Override
	public String visit(FloatInfo floatInfo) {

		return "CONSTANT_Float_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * IntegerInfo)
	 */
	@Override
	public String visit(IntegerInfo integerInfo) {

		return "CONSTANT_Integer_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * InterfaceMethodrefInfo)
	 */
	@Override
	public String visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {

		return "CONSTANT_InterfaceMethodref_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * InvokeDynamicInfo)
	 */
	@Override
	public String visit(InvokeDynamicInfo invokeDynamicInfo) {

		return "CONSTANT_InvokeDynamic_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * LongInfo)
	 */
	@Override
	public String visit(LongInfo longInfo) {

		return "CONSTANT_Long_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodHandleInfo)
	 */
	@Override
	public String visit(MethodHandleInfo methodHandleInfo) {

		return "CONSTANT_MethodHandle_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodrefInfo)
	 */
	@Override
	public String visit(MethodrefInfo methodrefInfo) {

		return "CONSTANT_Methodref_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodTypeInfo)
	 */
	@Override
	public String visit(MethodTypeInfo methodTypeInfo) {

		return "CONSTANT_MethodType_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * NameAndTypeInfo)
	 */
	@Override
	public String visit(NameAndTypeInfo nameAndTypeInfo) {

		return "CONSTANT_NameAndType_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * StringInfo)
	 */
	@Override
	public String visit(StringInfo stringInfo) {

		return "CONSTANT_String_info";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * UTF8Info)
	 */
	@Override
	public String visit(UTF8Info utf8Info) {

		return "CONSTANT_Utf8_info (\"" + utf8Info.getContent() + "\")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.FieldInfo)
	 */
	@Override
	public String visit(FieldInfo fieldInfo) {

		return fieldInfo.name.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.MethodInfo)
	 */
	@Override
	public String visit(MethodInfo methodInfo) {

		return methodInfo.name.getContent() + methodInfo.descriptor.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * AnnotationDefaultInfo)
	 */
	@Override
	public String visit(AnnotationDefaultInfo annotationDefaultInfo) {

		return "AnnotationDefault_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * BootstrapMethodsInfo)
	 */
	@Override
	public String visit(BootstrapMethodsInfo bootstrapMethodsInfo) {

		return "BootstrapMethods_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.CodeInfo)
	 */
	@Override
	public String visit(CodeInfo codeInfo) {

		return "Code_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * ConstantValueInfo)
	 */
	@Override
	public String visit(ConstantValueInfo constantValueInfo) {

		return "ConstantValue_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * DeprecatedInfo)
	 */
	@Override
	public String visit(DeprecatedInfo deprecatedInfo) {

		return "Deprecated_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * EnclosingMethodInfo)
	 */
	@Override
	public String visit(EnclosingMethodInfo enclosingMethodInfo) {

		return "EnclosingMethod_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * ExceptionInfo)
	 */
	@Override
	public String visit(ExceptionInfo exceptionInfo) {
		return "Exception_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * InnerClassesInfo)
	 */
	@Override
	public String visit(InnerClassesInfo innerClassesInfo) {
		return "InnerClassesInfo_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LineNumberTableInfo)
	 */
	@Override
	public String visit(LineNumberTableInfo lineNumberTableInfo) {
		return "LineNumberTable_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTableInfo)
	 */
	@Override
	public String visit(LocalVariableTableInfo localVariableTableInfo) {
		return "LocalVariableTable_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTypeTableInfo)
	 */
	@Override
	public String visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		return "LocalVariableTypeTable_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeInvisibleAnnotationInfo)
	 */
	@Override
	public String visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		return "RuntimeInvisibleAnnotations_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeInvisibleParameterAnnotationsInfo)
	 */
	@Override
	public String visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		return "RuntimeInvisibleParameterAnnotations_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeVisibleAnnotationsInfo)
	 */
	@Override
	public String visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		return "RuntimeVisibleAnnotations_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeVisibleParameterAnnotationsInfo)
	 */
	@Override
	public String visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		return "RuntimeVisibleParameterAnnotations_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SignatureInfo)
	 */
	@Override
	public String visit(SignatureInfo signatureInfo) {
		return "Signature_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SourceDebugExtensionInfo)
	 */
	@Override
	public String visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		return "SourceDebugExtension_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SourceFileInfo)
	 */
	@Override
	public String visit(SourceFileInfo sourceFileInfo) {
		return "SourceFile_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * StackMapTableInfo)
	 */
	@Override
	public String visit(StackMapTableInfo stackMapTableInfo) {
		return "StackMapTable_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SyntheticInfo)
	 */
	@Override
	public String visit(SyntheticInfo syntheticInfo) {
		return "Synthetic_attribute";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * Annotation)
	 */
	@Override
	public String visit(Annotation annotation) {
		return "annotation";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * ArrayValue)
	 */
	@Override
	public String visit(ArrayValue arrayValue) {
		return "array_value";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * ElementValue)
	 */
	@Override
	public String visit(ElementValue elementValue) {
		return "element_value";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * EnumConstValue)
	 */
	@Override
	public String visit(EnumConstValue enumConstValue) {
		return "enum_const_value";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * ExceptionEntry)
	 */
	@Override
	public String visit(ExceptionEntry exceptionEntry) {
		return "exception";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * ParameterAnnotation)
	 */
	@Override
	public String visit(ParameterAnnotation parameterAnnotation) {
		return "parameter_annotation";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.AppendFrame)
	 */
	@Override
	public String visit(AppendFrame appendFrame) {
		return "append_frame";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.ChopFrame)
	 */
	@Override
	public String visit(ChopFrame chopFrame) {
		return "chop_frame";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.FullFrame)
	 */
	@Override
	public String visit(FullFrame fullFrame) {
		return "full_frame";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameFrame)
	 */
	@Override
	public String visit(SameFrame sameFrame) {
		return "same_frame";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameFrameExtended)
	 */
	@Override
	public String visit(SameFrameExtended sameFrameExtended) {
		return "same_frame_extended";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameLocals1StackItemFrame)
	 */
	@Override
	public String visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		return "same_locals_1_stack_item_frame";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameLocals1StackitemFrameExtended)
	 */
	@Override
	public String visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		return "same_locals_1_stack_item_frame_extended";
	}

	@Override
	public String visit(DoubleVariableInfo doubleVariableInfo) {
		return "Double_variable_info";
	}

	@Override
	public String visit(FloatVariableInfo floatVariableInfo) {
		return "Float_variable_info";
	}

	@Override
	public String visit(IntegerVariableInfo integerVariableInfo) {
		return "Integer_variable_info";
	}

	@Override
	public String visit(LongVariableInfo longVariableInfo) {
		return "Long_variable_info";
	}

	@Override
	public String visit(NullVariableInfo nullVariableInfo) {
		return "Null_variable_info";
	}

	@Override
	public String visit(ObjectVariableInfo objectVariableInfo) {
		return "Object_variable_info";
	}

	@Override
	public String visit(TopVariableInfo topVariableInfo) {
		return "Top_variable_info";
	}

	@Override
	public String visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		return "UninitializedThis_variable_info";
	}

	@Override
	public String visit(UninitializedVariableInfo uninitializedVariableInfo) {
		return "Uninitialized_variable_info";
	}

	@Override
	public String visit(ElementValuePair elementValuePair) {
		return "element_value_pairs";
	}

	@Override
	public String visit(BootstrapMethod bootstrapMethod) {
		return "bootstrap_method";
	}

	@Override
	public String visit(Clazz clazz) {
		return "class";
	}

	@Override
	public String visit(LineNumber lineNumber) {
		return "line_number";
	}

	@Override
	public String visit(LocalVariable localVariable) {
		return "local_variable";
	}

	@Override
	public String visit(LocalVariableType localVariableType) {
		return "local_variable_type_table";
	}
}
