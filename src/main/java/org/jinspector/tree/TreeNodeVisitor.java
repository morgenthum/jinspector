package org.jinspector.tree;

import java.lang.reflect.Array;

import org.jinspector.Util;
import org.jinspector.Visitable;
import org.jinspector.Visitor;
import org.jinspector.classfile.ClassFile;
import org.jinspector.classfile.FieldInfo;
import org.jinspector.classfile.MethodInfo;
import org.jinspector.classfile.attributes.AnnotationDefaultInfo;
import org.jinspector.classfile.attributes.AttributeInfo;
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
import org.jinspector.classfile.attributes.helpers.stackmap.StackMapFrame;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.DoubleVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.FloatVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.IntegerVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.LongVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.NullVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.ObjectVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.TopVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.UninitializedThisVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.UninitializedVariableInfo;
import org.jinspector.classfile.attributes.helpers.stackmap.variables.VerificationType;
import org.jinspector.classfile.constantpool.ClassInfo;
import org.jinspector.classfile.constantpool.ConstantInfo;
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
public class TreeNodeVisitor extends Visitor<BasicTreeNode> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.ClassFile)
	 */
	@Override
	public BasicTreeNode visit(ClassFile classFile) {

		VisitableTreeNode node = new VisitableTreeNode(classFile, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("magic", classFile.getMagicString()));
		node.add(PlainTreeNode.create("minor_version", classFile.minorVersion));
		node.add(PlainTreeNode.create("major_version", classFile.getMajorVersionString()));
		node.add(PlainTreeNode.create("constant_pool_count", classFile.constantPoolCount));
		node.add(createVisitableArrayNode("constant_pool", classFile.constantPool, this));
		node.add(PlainTreeNode.create("access_flags", Util.getAccessFlagsString(classFile, classFile.accessFlags)));
		node.add(PlainTreeNode.create("this_class", classFile.thisClass.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("super_class", classFile.superClass.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("interfaces_count", classFile.interfacesCount));
		node.add(createVisitableArrayNode("interfaces", classFile.interfaces, this));
		node.add(PlainTreeNode.create("fields_count", classFile.fieldsCount));
		node.add(createVisitableArrayNode("fields", classFile.fields, this));
		node.add(PlainTreeNode.create("methods_count", classFile.methodsCount));
		node.add(createVisitableArrayNode("methods", classFile.methods, this));
		node.add(PlainTreeNode.create("attributes_count", classFile.attributesCount));
		node.add(createVisitableArrayNode("attributes", classFile.attributes, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * ClassInfo)
	 */
	@Override
	public BasicTreeNode visit(ClassInfo classInfo) {

		BasicTreeNode node = preVisit(classInfo);

		node.add(PlainTreeNode.create("name_index", classInfo.name.visit(ReferenceVisitor.getInstance())));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * DoubleInfo)
	 */
	@Override
	public BasicTreeNode visit(DoubleInfo doubleInfo) {

		BasicTreeNode node = preVisit(doubleInfo);

		node.add(PlainTreeNode.create("high_bytes", doubleInfo.highBytes));
		node.add(PlainTreeNode.create("low_bytes", doubleInfo.lowBytes));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * FieldrefInfo)
	 */
	@Override
	public BasicTreeNode visit(FieldrefInfo fieldrefInfo) {

		BasicTreeNode node = preVisit(fieldrefInfo);

		node.add(PlainTreeNode.create("class_index", fieldrefInfo.clazz.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("name_and_type_index",
				fieldrefInfo.nameAndTypeIndex.visit(ReferenceVisitor.getInstance())));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * FloatInfo)
	 */
	@Override
	public BasicTreeNode visit(FloatInfo floatInfo) {

		BasicTreeNode node = preVisit(floatInfo);

		node.add(PlainTreeNode.create("bytes", floatInfo.bytes));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * IntegerInfo)
	 */
	@Override
	public BasicTreeNode visit(IntegerInfo integerInfo) {

		BasicTreeNode node = preVisit(integerInfo);

		node.add(PlainTreeNode.create("bytes", integerInfo.bytes));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * InterfaceMethodrefInfo)
	 */
	@Override
	public BasicTreeNode visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {

		BasicTreeNode node = preVisit(interfaceMethodrefInfo);

		node.add(PlainTreeNode.create("class_index",
				interfaceMethodrefInfo.clazz.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("name_and_type_index",
				interfaceMethodrefInfo.nameAndType.visit(ReferenceVisitor.getInstance())));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * InvokeDynamicInfo)
	 */
	@Override
	public BasicTreeNode visit(InvokeDynamicInfo invokeDynamicInfo) {

		BasicTreeNode node = preVisit(invokeDynamicInfo);

		node.add(PlainTreeNode.create("bootstrap_method_attr_index", invokeDynamicInfo.bootstrapMethodAttributeIndex));
		node.add(PlainTreeNode.create("name_and_type_index", invokeDynamicInfo.nameAndTypeIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * LongInfo)
	 */
	@Override
	public BasicTreeNode visit(LongInfo longInfo) {

		BasicTreeNode node = preVisit(longInfo);

		node.add(PlainTreeNode.create("high_bytes", longInfo.highBytes));
		node.add(PlainTreeNode.create("low_bytes", longInfo.lowBytes));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodHandleInfo)
	 */
	@Override
	public BasicTreeNode visit(MethodHandleInfo methodHandleInfo) {

		BasicTreeNode node = preVisit(methodHandleInfo);

		node.add(PlainTreeNode.create("reference_kind", methodHandleInfo.referenceKind));
		node.add(PlainTreeNode.create("reference_index", methodHandleInfo.referenceIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodrefInfo)
	 */
	@Override
	public BasicTreeNode visit(MethodrefInfo methodrefInfo) {

		BasicTreeNode node = preVisit(methodrefInfo);

		node.add(PlainTreeNode.create("class_index", methodrefInfo.clazz.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("name_and_type_index",
				methodrefInfo.nameAndType.visit(ReferenceVisitor.getInstance())));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * MethodTypeInfo)
	 */
	@Override
	public BasicTreeNode visit(MethodTypeInfo methodTypeInfo) {

		BasicTreeNode node = preVisit(methodTypeInfo);

		node.add(PlainTreeNode.create("descriptor_index", methodTypeInfo.descriptorIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * NameAndTypeInfo)
	 */
	@Override
	public BasicTreeNode visit(NameAndTypeInfo nameAndTypeInfo) {

		BasicTreeNode node = preVisit(nameAndTypeInfo);

		node.add(PlainTreeNode.create("name_index", nameAndTypeInfo.name.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("descriptor_index",
				nameAndTypeInfo.descriptor.visit(ReferenceVisitor.getInstance())));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * StringInfo)
	 */
	@Override
	public BasicTreeNode visit(StringInfo stringInfo) {

		BasicTreeNode node = preVisit(stringInfo);

		node.add(new ReferenceTreeNode("string_index", stringInfo.string));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.tree.Visitor#visit(org.jreverse.classfile.constantpool.
	 * UTF8Info)
	 */
	@Override
	public BasicTreeNode visit(UTF8Info utf8Info) {

		BasicTreeNode node = preVisit(utf8Info);

		node.add(PlainTreeNode.create("length", utf8Info.length));
		node.add(createPrimitiveArrayNode("bytes", utf8Info.bytes));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.FieldInfo)
	 */
	@Override
	public BasicTreeNode visit(FieldInfo fieldInfo) {

		VisitableTreeNode node = new VisitableTreeNode(fieldInfo, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("access_flags", Util.getAccessFlagsString(fieldInfo, fieldInfo.accessFlags)));
		node.add(PlainTreeNode.create("name_index", fieldInfo.name.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("descriptor_index", fieldInfo.descriptor.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("attributes_count", fieldInfo.attributesCount));
		node.add(createVisitableArrayNode("attributes", fieldInfo.attributes, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.MethodInfo)
	 */
	@Override
	public BasicTreeNode visit(MethodInfo methodInfo) {

		VisitableTreeNode node = new VisitableTreeNode(methodInfo, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("access_flags", Util.getAccessFlagsString(methodInfo, methodInfo.accessFlags)));
		node.add(PlainTreeNode.create("name_index", methodInfo.name.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("descriptor_index", methodInfo.descriptor.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("attributes_count", methodInfo.attributesCount));
		node.add(createVisitableArrayNode("attributes", methodInfo.attributes, this));

		return node;
	}

	/**
	 * @param info
	 * @return
	 */
	private BasicTreeNode preVisit(ConstantInfo info) {

		VisitableTreeNode node = new VisitableTreeNode(info, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("tag", info.tag));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * AnnotationDefaultInfo)
	 */
	@Override
	public BasicTreeNode visit(AnnotationDefaultInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(new VisitableTreeNode(info.defaultValue, ReferenceVisitor.getInstance()));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * BootstrapMethodsInfo)
	 */
	@Override
	public BasicTreeNode visit(BootstrapMethodsInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("num_bootstrap_methods", info.numBootstrapMethods));
		node.add(createVisitableArrayNode("bootstrap_methods", info.bootstrapMethods, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.CodeInfo)
	 */
	@Override
	public BasicTreeNode visit(CodeInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("max_stack", info.maxStack));
		node.add(PlainTreeNode.create("max_locals", info.maxLocals));
		node.add(PlainTreeNode.create("code_length", info.codeLength));
		node.add(createPrimitiveArrayNode("code", info.code));
		node.add(PlainTreeNode.create("exception_table_length", info.exceptionTableLength));
		node.add(createVisitableArrayNode("exception_table", info.exceptionTable, this));
		node.add(PlainTreeNode.create("attributes_count", info.attributesCount));
		node.add(createVisitableArrayNode("attributes", info.attributes, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * ConstantValueInfo)
	 */
	@Override
	public BasicTreeNode visit(ConstantValueInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("constantvalue_index", info.constantValueIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * DeprecatedInfo)
	 */
	@Override
	public BasicTreeNode visit(DeprecatedInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * EnclosingMethodInfo)
	 */
	@Override
	public BasicTreeNode visit(EnclosingMethodInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("class_index", info.classIndex));
		node.add(PlainTreeNode.create("method_index", info.methodIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * ExceptionInfo)
	 */
	@Override
	public BasicTreeNode visit(ExceptionInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("number_of_exception", info.numberOfExceptions));
		node.add(createPrimitiveArrayNode("exception_index_table", info.exceptionIndexTable));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * InnerClassesInfo)
	 */
	@Override
	public BasicTreeNode visit(InnerClassesInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("number_of_classes", info.numberOfClasses));
		node.add(createVisitableArrayNode("classes", info.classes, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LineNumberTableInfo)
	 */
	@Override
	public BasicTreeNode visit(LineNumberTableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("line_number_table_length", info.lineNumberTableLength));
		node.add(createVisitableArrayNode("line_number_table", info.lineNumberTable, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTableInfo)
	 */
	@Override
	public BasicTreeNode visit(LocalVariableTableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("local_variable_table_length", info.tableLength));
		node.add(createVisitableArrayNode("local_variable_table", info.table, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTypeTableInfo)
	 */
	@Override
	public BasicTreeNode visit(LocalVariableTypeTableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("local_variable_type_table_length", info.tableLength));
		node.add(createVisitableArrayNode("local_variable_type_table", info.table, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeInvisibleAnnotationInfo)
	 */
	@Override
	public BasicTreeNode visit(RuntimeInvisibleAnnotationInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("num_annotations", info.numAnnotations));
		node.add(createVisitableArrayNode("annotations", info.annotations, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeInvisibleParameterAnnotationsInfo)
	 */
	@Override
	public BasicTreeNode visit(RuntimeInvisibleParameterAnnotationsInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("num_parameters", info.numParameters));
		node.add(createVisitableArrayNode("parameter_annotations", info.parameterAnnotations, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeVisibleAnnotationsInfo)
	 */
	@Override
	public BasicTreeNode visit(RuntimeVisibleAnnotationsInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("num_annotations", info.numAnnotations));
		node.add(createVisitableArrayNode("annotations", info.annotations, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * RuntimeVisibleParameterAnnotationsInfo)
	 */
	@Override
	public BasicTreeNode visit(RuntimeVisibleParameterAnnotationsInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("num_parameters", info.numParameters));
		node.add(createVisitableArrayNode("parameter_annotations", info.parameterAnnotations, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SignatureInfo)
	 */
	@Override
	public BasicTreeNode visit(SignatureInfo info) {

		BasicTreeNode node = preVisit(info);

		UTF8Info utf8Info = info.classFile.getUtf8Info(info.signatureIndex);
		node.add(PlainTreeNode.create("signature_index", info.signatureIndex + " (" + utf8Info.getContent() + ")"));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SourceDebugExtensionInfo)
	 */
	@Override
	public BasicTreeNode visit(SourceDebugExtensionInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(createPrimitiveArrayNode("debug_extension", info.debugExtension));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SourceFileInfo)
	 */
	@Override
	public BasicTreeNode visit(SourceFileInfo info) {

		BasicTreeNode node = preVisit(info);

		String sourceFile = info.sourcefile.visit(ReferenceVisitor.getInstance());
		node.add(PlainTreeNode.create("sourcefile_index", sourceFile));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * StackMapTableInfo)
	 */
	@Override
	public BasicTreeNode visit(StackMapTableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("number_of_entries", info.numberOfEntries));
		node.add(createVisitableArrayNode("entries", info.entries, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * SyntheticInfo)
	 */
	@Override
	public BasicTreeNode visit(SyntheticInfo info) {

		return preVisit(info);
	}

	/**
	 * @param attributeInfo
	 * @return
	 */
	private BasicTreeNode preVisit(AttributeInfo attributeInfo) {

		VisitableTreeNode node = new VisitableTreeNode(attributeInfo, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("attribute_name_index",
				attributeInfo.attributeNameIndex.visit(ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("attribute_length", attributeInfo.attributeLength));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * Annotation)
	 */
	@Override
	public BasicTreeNode visit(Annotation annotation) {

		VisitableTreeNode node = new VisitableTreeNode(annotation, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("type_index", annotation.typeIndex));
		node.add(PlainTreeNode.create("num_element_value_pairs", annotation.numElementValuePairs));
		node.add(createVisitableArrayNode("element_value_pairs", annotation.elementValuePairs, this));

		return node;
	}

	/**
	 * @param value
	 * @return
	 */
	@Override
	public BasicTreeNode visit(ArrayValue value) {

		VisitableTreeNode node = new VisitableTreeNode(value, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("num_values", value.numValues));
		node.add(createVisitableArrayNode("values", value.values, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * ElementValue)
	 */
	@Override
	public BasicTreeNode visit(ElementValue element) {

		VisitableTreeNode node = new VisitableTreeNode(element, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("tag", element.tag));

		if (element.value instanceof Visitable) {
			node.add(new VisitableTreeNode((Visitable) element.value, ReferenceVisitor.getInstance()));
		} else {
			node.add(PlainTreeNode.create("value", element.value));
		}

		return node;
	}

	/**
	 * @param value
	 * @return
	 */
	@Override
	public BasicTreeNode visit(EnumConstValue value) {

		VisitableTreeNode node = new VisitableTreeNode(value, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("type_name_index", value.typeNameIndex));
		node.add(PlainTreeNode.create("const_name_index", value.constNameIndex));

		return null;
	}

	/**
	 * @param entry
	 * @return
	 */
	@Override
	public BasicTreeNode visit(ExceptionEntry entry) {

		VisitableTreeNode node = new VisitableTreeNode(entry, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("start_pc", entry.startPc));
		node.add(PlainTreeNode.create("end_pc", entry.endPc));
		node.add(PlainTreeNode.create("handler_pc", entry.handlerPc));
		node.add(PlainTreeNode.create("catch_type", entry.catchType));

		return node;
	}

	/**
	 * @param annotation
	 * @return
	 */
	@Override
	public BasicTreeNode visit(ParameterAnnotation annotation) {

		VisitableTreeNode node = new VisitableTreeNode(annotation, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("num_annotations", annotation.numAnnotations));
		node.add(createVisitableArrayNode("annotations", annotation.annotations, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.AppendFrame)
	 */
	@Override
	public BasicTreeNode visit(AppendFrame frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(PlainTreeNode.create("offset_delta", frame.offsetDelta));
		node.add(createVisitableArrayNode("locals", frame.locals, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.ChopFrame)
	 */
	@Override
	public BasicTreeNode visit(ChopFrame frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(PlainTreeNode.create("offset_delta", frame.offsetDelta));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.FullFrame)
	 */
	@Override
	public BasicTreeNode visit(FullFrame frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(PlainTreeNode.create("offset_delta", frame.offsetDelta));
		node.add(PlainTreeNode.create("number_of_locals", frame.numberOfLocals));
		node.add(createVisitableArrayNode("locals", frame.locals, this));
		node.add(PlainTreeNode.create("number_of_stack_items", frame.numberOfStackItems));
		node.add(createVisitableArrayNode("stack", frame.stack, this));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameFrame)
	 */
	@Override
	public BasicTreeNode visit(SameFrame frame) {

		return preVisit(frame);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameFrameExtended)
	 */
	@Override
	public BasicTreeNode visit(SameFrameExtended frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(PlainTreeNode.create("offset_delta", frame.offsetDelta));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameLocals1StackItemFrame)
	 */
	@Override
	public BasicTreeNode visit(SameLocals1StackItemFrame frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(new VisitableTreeNode(frame.stack, ReferenceVisitor.getInstance()));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.SameLocals1StackitemFrameExtended)
	 */
	@Override
	public BasicTreeNode visit(SameLocals1StackitemFrameExtended frame) {

		BasicTreeNode node = preVisit(frame);

		node.add(PlainTreeNode.create("offset_delta", frame.offsetDelta));
		node.add(new VisitableTreeNode(frame.stack, ReferenceVisitor.getInstance()));

		return node;
	}

	/**
	 * @param frame
	 * @return
	 */
	private BasicTreeNode preVisit(StackMapFrame frame) {

		VisitableTreeNode node = new VisitableTreeNode(frame, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("frame_type", frame.frameType));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.DoubleVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(DoubleVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.FloatVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(FloatVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.IntegerVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(IntegerVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.LongVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(LongVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.NullVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(NullVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.ObjectVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(ObjectVariableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("cpool_index", info.cpoolIndex));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.TopVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(TopVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.UninitializedThisVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(UninitializedThisVariableInfo info) {

		return preVisit(info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * stackmap.variables.UninitializedVariableInfo)
	 */
	@Override
	public BasicTreeNode visit(UninitializedVariableInfo info) {

		BasicTreeNode node = preVisit(info);

		node.add(PlainTreeNode.create("offset", info.offset));

		return node;
	}

	/**
	 * @param type
	 * @return
	 */
	private BasicTreeNode preVisit(VerificationType type) {

		VisitableTreeNode node = new VisitableTreeNode(type, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("tag", type.tag));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.helpers.
	 * Annotation.ElementValuePair)
	 */
	@Override
	public BasicTreeNode visit(ElementValuePair pair) {

		VisitableTreeNode node = new VisitableTreeNode(pair, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("element_name_index", pair.elementNameIndex));
		node.add(new VisitableTreeNode(pair.elementValue, ReferenceVisitor.getInstance()));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * BootstrapMethodsInfo.BootstrapMethod)
	 */
	@Override
	public BasicTreeNode visit(BootstrapMethod method) {

		VisitableTreeNode node = new VisitableTreeNode(method, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("bootstrap_method_ref", method.bootstrapMethodRef));
		node.add(PlainTreeNode.create("num_bootstrap_arguments", method.numBootstrapArguments));
		node.add(createPrimitiveArrayNode("bootstrap_arguments", method.bootstrapArguments));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * InnerClassesInfo.Clazz)
	 */
	@Override
	public BasicTreeNode visit(Clazz clazz) {

		VisitableTreeNode node = new VisitableTreeNode(clazz, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("inner_class_info_index",
				safeVisit(clazz.innerClassInfoIndex, ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("outer_class_info_index",
				safeVisit(clazz.outerClassInfoIndex, ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("inner_name_index",
				safeVisit(clazz.innerNameIndex, ReferenceVisitor.getInstance())));
		node.add(PlainTreeNode.create("inner_class_access_flags", clazz.innerClassAccessFlags));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LineNumberTableInfo.LineNumber)
	 */
	@Override
	public BasicTreeNode visit(LineNumber lineNumber) {

		VisitableTreeNode node = new VisitableTreeNode(lineNumber, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("start_pc", lineNumber.startPc));
		node.add(PlainTreeNode.create("line_number", lineNumber.lineNumber));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTableInfo.LocalVariable)
	 */
	@Override
	public BasicTreeNode visit(LocalVariable var) {

		VisitableTreeNode node = new VisitableTreeNode(var, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("start_pc", var.startPc));
		node.add(PlainTreeNode.create("length", var.length));
		node.add(PlainTreeNode.create("name_index", var.nameIndex));
		node.add(PlainTreeNode.create("descriptor_index", var.descriptorIndex));
		node.add(PlainTreeNode.create("index", var.index));

		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitor#visit(org.jreverse.classfile.attributes.
	 * LocalVariableTypeTableInfo.LocalVariableType)
	 */
	@Override
	public BasicTreeNode visit(LocalVariableType var) {

		VisitableTreeNode node = new VisitableTreeNode(var, DeclarationVisitor.getInstance());

		node.add(PlainTreeNode.create("start_pc", var.startPc));
		node.add(PlainTreeNode.create("length", var.length));
		node.add(PlainTreeNode.create("name_index", var.nameIndex));
		node.add(PlainTreeNode.create("signature_index", var.signatureIndex));
		node.add(PlainTreeNode.create("index", var.index));

		return node;
	}

	/**
	 * @param name
	 * @param array
	 * @param visitor
	 * @return
	 */
	private <T extends Visitable> BasicTreeNode createVisitableArrayNode(String name, T[] array,
			TreeNodeVisitor visitor) {

		PlainTreeNode node = PlainTreeNode.create(name);

		for (int i = 0; i < array.length; ++i) {
			T element = array[i];
			node.add(new ArrayTreeNode(i, safeVisit(element, visitor)));
		}

		return node;
	}

	/**
	 * @param name
	 * @param array
	 * @return
	 */
	private BasicTreeNode createPrimitiveArrayNode(String name, Object array) {

		PlainTreeNode node = PlainTreeNode.create(name);
		int length = Array.getLength(array);

		for (int i = 0; i < length; ++i) {
			Object element = Array.get(array, i);
			node.add(new ArrayTreeNode(i, PlainTreeNode.create(element)));
		}
		return node;
	}
}
