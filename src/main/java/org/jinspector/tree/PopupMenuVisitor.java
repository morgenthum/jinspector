package org.jinspector.tree;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.jinspector.Visitable;
import org.jinspector.Visitor;
import org.jinspector.actions.ShowSpecificationAction;
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

public class PopupMenuVisitor extends Visitor<JPopupMenu> {

	private static PopupMenuVisitor instance;

	/**
	 * @return
	 */
	public static synchronized PopupMenuVisitor getInstance() {

		if (instance == null) {
			instance = new PopupMenuVisitor();
		}

		return instance;
	}

	/**
	 * 
	 */
	private PopupMenuVisitor() {
	}

	@Override
	public JPopupMenu visit(ClassFile classFile) {
		return createDefaultPopupMenu(classFile);
	}

	@Override
	public JPopupMenu visit(ClassInfo classInfo) {
		return createDefaultPopupMenu(classInfo);
	}

	@Override
	public JPopupMenu visit(DoubleInfo doubleInfo) {
		return createDefaultPopupMenu(doubleInfo);
	}

	@Override
	public JPopupMenu visit(FieldrefInfo fieldrefInfo) {
		return createDefaultPopupMenu(fieldrefInfo);
	}

	@Override
	public JPopupMenu visit(FloatInfo floatInfo) {
		return createDefaultPopupMenu(floatInfo);
	}

	@Override
	public JPopupMenu visit(IntegerInfo integerInfo) {
		return createDefaultPopupMenu(integerInfo);
	}

	@Override
	public JPopupMenu visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {
		return createDefaultPopupMenu(interfaceMethodrefInfo);
	}

	@Override
	public JPopupMenu visit(InvokeDynamicInfo invokeDynamicInfo) {
		return createDefaultPopupMenu(invokeDynamicInfo);
	}

	@Override
	public JPopupMenu visit(LongInfo longInfo) {
		return createDefaultPopupMenu(longInfo);
	}

	@Override
	public JPopupMenu visit(MethodHandleInfo methodHandleInfo) {
		return createDefaultPopupMenu(methodHandleInfo);
	}

	@Override
	public JPopupMenu visit(MethodrefInfo methodrefInfo) {
		return createDefaultPopupMenu(methodrefInfo);
	}

	@Override
	public JPopupMenu visit(MethodTypeInfo methodTypeInfo) {
		return createDefaultPopupMenu(methodTypeInfo);
	}

	@Override
	public JPopupMenu visit(NameAndTypeInfo nameAndTypeInfo) {
		return createDefaultPopupMenu(nameAndTypeInfo);
	}

	@Override
	public JPopupMenu visit(StringInfo stringInfo) {
		return createDefaultPopupMenu(stringInfo);
	}

	@Override
	public JPopupMenu visit(UTF8Info utf8Info) {
		return createDefaultPopupMenu(utf8Info);
	}

	@Override
	public JPopupMenu visit(FieldInfo fieldInfo) {
		return createDefaultPopupMenu(fieldInfo);
	}

	@Override
	public JPopupMenu visit(MethodInfo methodInfo) {
		return createDefaultPopupMenu(methodInfo);
	}

	@Override
	public JPopupMenu visit(AnnotationDefaultInfo annotationDefaultInfo) {
		return createDefaultPopupMenu(annotationDefaultInfo);
	}

	@Override
	public JPopupMenu visit(BootstrapMethodsInfo bootstrapMethodsInfo) {
		return createDefaultPopupMenu(bootstrapMethodsInfo);
	}

	@Override
	public JPopupMenu visit(CodeInfo codeInfo) {
		return createDefaultPopupMenu(codeInfo);
	}

	@Override
	public JPopupMenu visit(ConstantValueInfo constantValueInfo) {
		return createDefaultPopupMenu(constantValueInfo);
	}

	@Override
	public JPopupMenu visit(DeprecatedInfo deprecatedInfo) {
		return createDefaultPopupMenu(deprecatedInfo);
	}

	@Override
	public JPopupMenu visit(EnclosingMethodInfo enclosingMethodInfo) {
		return createDefaultPopupMenu(enclosingMethodInfo);
	}

	@Override
	public JPopupMenu visit(ExceptionInfo exceptionInfo) {
		return createDefaultPopupMenu(exceptionInfo);
	}

	@Override
	public JPopupMenu visit(InnerClassesInfo innerClassesInfo) {
		return createDefaultPopupMenu(innerClassesInfo);
	}

	@Override
	public JPopupMenu visit(LineNumberTableInfo lineNumberTableInfo) {
		return createDefaultPopupMenu(lineNumberTableInfo);
	}

	@Override
	public JPopupMenu visit(LocalVariableTableInfo localVariableTableInfo) {
		return createDefaultPopupMenu(localVariableTableInfo);
	}

	@Override
	public JPopupMenu visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		return createDefaultPopupMenu(localVariableTypeTableInfo);
	}

	@Override
	public JPopupMenu visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		return createDefaultPopupMenu(runtimeInvisibleAnnotationInfo);
	}

	@Override
	public JPopupMenu visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		return createDefaultPopupMenu(runtimeInvisibleParameterAnnotationsInfo);
	}

	@Override
	public JPopupMenu visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		return createDefaultPopupMenu(runtimeVisibleAnnotationsInfo);
	}

	@Override
	public JPopupMenu visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		return createDefaultPopupMenu(runtimeVisibleParameterAnnotationsInfo);
	}

	@Override
	public JPopupMenu visit(SignatureInfo signatureInfo) {
		return createDefaultPopupMenu(signatureInfo);
	}

	@Override
	public JPopupMenu visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		return createDefaultPopupMenu(sourceDebugExtensionInfo);
	}

	@Override
	public JPopupMenu visit(SourceFileInfo sourceFileInfo) {
		return createDefaultPopupMenu(sourceFileInfo);
	}

	@Override
	public JPopupMenu visit(StackMapTableInfo stackMapTableInfo) {
		return createDefaultPopupMenu(stackMapTableInfo);
	}

	@Override
	public JPopupMenu visit(SyntheticInfo syntheticInfo) {
		return createDefaultPopupMenu(syntheticInfo);
	}

	@Override
	public JPopupMenu visit(Annotation annotation) {
		return createDefaultPopupMenu(annotation);
	}

	@Override
	public JPopupMenu visit(ArrayValue arrayValue) {
		return createDefaultPopupMenu(arrayValue);
	}

	@Override
	public JPopupMenu visit(ElementValue elementValue) {
		return createDefaultPopupMenu(elementValue);
	}

	@Override
	public JPopupMenu visit(EnumConstValue enumConstValue) {
		return createDefaultPopupMenu(enumConstValue);
	}

	@Override
	public JPopupMenu visit(ExceptionEntry exceptionEntry) {
		return createDefaultPopupMenu(exceptionEntry);
	}

	@Override
	public JPopupMenu visit(ParameterAnnotation parameterAnnotation) {
		return createDefaultPopupMenu(parameterAnnotation);
	}

	@Override
	public JPopupMenu visit(AppendFrame appendFrame) {
		return createDefaultPopupMenu(appendFrame);
	}

	@Override
	public JPopupMenu visit(ChopFrame chopFrame) {
		return createDefaultPopupMenu(chopFrame);
	}

	@Override
	public JPopupMenu visit(FullFrame fullFrame) {
		return createDefaultPopupMenu(fullFrame);
	}

	@Override
	public JPopupMenu visit(SameFrame sameFrame) {
		return createDefaultPopupMenu(sameFrame);
	}

	@Override
	public JPopupMenu visit(SameFrameExtended sameFrameExtended) {
		return createDefaultPopupMenu(sameFrameExtended);
	}

	@Override
	public JPopupMenu visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		return createDefaultPopupMenu(sameLocals1StackItemFrame);
	}

	@Override
	public JPopupMenu visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		return createDefaultPopupMenu(sameLocals1StackitemFrameExtended);
	}

	@Override
	public JPopupMenu visit(DoubleVariableInfo doubleVariableInfo) {
		return createDefaultPopupMenu(doubleVariableInfo);
	}

	@Override
	public JPopupMenu visit(FloatVariableInfo floatVariableInfo) {
		return createDefaultPopupMenu(floatVariableInfo);
	}

	@Override
	public JPopupMenu visit(IntegerVariableInfo integerVariableInfo) {
		return createDefaultPopupMenu(integerVariableInfo);
	}

	@Override
	public JPopupMenu visit(LongVariableInfo longVariableInfo) {
		return createDefaultPopupMenu(longVariableInfo);
	}

	@Override
	public JPopupMenu visit(NullVariableInfo nullVariableInfo) {
		return createDefaultPopupMenu(nullVariableInfo);
	}

	@Override
	public JPopupMenu visit(ObjectVariableInfo objectVariableInfo) {
		return createDefaultPopupMenu(objectVariableInfo);
	}

	@Override
	public JPopupMenu visit(TopVariableInfo topVariableInfo) {
		return createDefaultPopupMenu(topVariableInfo);
	}

	@Override
	public JPopupMenu visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		return createDefaultPopupMenu(uninitializedThisVariableInfo);
	}

	@Override
	public JPopupMenu visit(UninitializedVariableInfo uninitializedVariableInfo) {
		return createDefaultPopupMenu(uninitializedVariableInfo);
	}

	@Override
	public JPopupMenu visit(ElementValuePair elementValuePair) {
		return createDefaultPopupMenu(elementValuePair);
	}

	@Override
	public JPopupMenu visit(BootstrapMethod bootstrapMethod) {
		return createDefaultPopupMenu(bootstrapMethod);
	}

	@Override
	public JPopupMenu visit(Clazz clazz) {
		return createDefaultPopupMenu(clazz);
	}

	@Override
	public JPopupMenu visit(LineNumber lineNumber) {
		return createDefaultPopupMenu(lineNumber);
	}

	@Override
	public JPopupMenu visit(LocalVariable localVariable) {
		return createDefaultPopupMenu(localVariable);
	}

	@Override
	public JPopupMenu visit(LocalVariableType localVariableType) {
		return createDefaultPopupMenu(localVariableType);
	}

	private JPopupMenu createDefaultPopupMenu(Visitable visitable) {

		JPopupMenu menu = new JPopupMenu();

		menu.add(new JMenuItem(new ShowSpecificationAction(visitable)));

		return menu;
	}
}
