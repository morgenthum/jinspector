package org.jinspector.tree;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.jinspector.Constants;
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
import org.jinspector.classfile.instructions.Instruction;
import org.jinspector.classfile.instructions.InstructionBuilder;
import org.jinspector.ui.InstructionsListCellRenderer;

public class DetailPanelVisitor extends Visitor<JPanel> {

	private static DetailPanelVisitor instance;

	/**
	 * @return
	 */
	public static synchronized DetailPanelVisitor getInstance() {

		if (instance == null) {
			instance = new DetailPanelVisitor();
		}

		return instance;
	}

	/**
	 * 
	 */
	private DetailPanelVisitor() {
	}

	@Override
	public JPanel visit(ClassFile classFile) {

		JPanel panel = new JPanel();
		panel.add(new JLabel("eyy classfile"));

		return panel;
	}

	@Override
	public JPanel visit(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(DoubleInfo doubleInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(FieldrefInfo fieldrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(FloatInfo floatInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(IntegerInfo integerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(InvokeDynamicInfo invokeDynamicInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LongInfo longInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(MethodHandleInfo methodHandleInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(MethodrefInfo methodrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(MethodTypeInfo methodTypeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(NameAndTypeInfo nameAndTypeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(StringInfo stringInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(UTF8Info utf8Info) {

		JPanel panel = new JPanel();
		panel.add(new JLabel("utf"));

		return panel;
	}

	@Override
	public JPanel visit(FieldInfo fieldInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(MethodInfo methodInfo) {

		JList<Instruction> list = new JList<>();
		list.setCellRenderer(new InstructionsListCellRenderer());

		for (int i = 0; i < methodInfo.attributesCount; ++i) {
			AttributeInfo info = methodInfo.attributes[i];

			if (Constants.CONSTANT_CODE.equals(info.attributeNameIndex.getContent())) {
				CodeInfo codeInfo = (CodeInfo) info;
				InstructionBuilder builder = new InstructionBuilder(methodInfo.classFile, codeInfo.code);
				List<Instruction> instructions = builder.getInstructions();
				Instruction[] elements = new Instruction[instructions.size()];
				list.setListData(instructions.toArray(elements));
			}
		}

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(list, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JPanel visit(AnnotationDefaultInfo annotationDefaultInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(BootstrapMethodsInfo bootstrapMethodsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(CodeInfo codeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ConstantValueInfo constantValueInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(DeprecatedInfo deprecatedInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(EnclosingMethodInfo enclosingMethodInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ExceptionInfo exceptionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(InnerClassesInfo innerClassesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LineNumberTableInfo lineNumberTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LocalVariableTableInfo localVariableTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SignatureInfo signatureInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SourceFileInfo sourceFileInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(StackMapTableInfo stackMapTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SyntheticInfo syntheticInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(Annotation annotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ArrayValue arrayValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ElementValue elementValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(EnumConstValue enumConstValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ExceptionEntry exceptionEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ParameterAnnotation parameterAnnotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(AppendFrame appendFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ChopFrame chopFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(FullFrame fullFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SameFrame sameFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SameFrameExtended sameFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(DoubleVariableInfo doubleVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(FloatVariableInfo floatVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(IntegerVariableInfo integerVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LongVariableInfo longVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(NullVariableInfo nullVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ObjectVariableInfo objectVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(TopVariableInfo topVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(UninitializedVariableInfo uninitializedVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(ElementValuePair elementValuePair) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(BootstrapMethod bootstrapMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(Clazz clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LineNumber lineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LocalVariable localVariable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel visit(LocalVariableType localVariableType) {
		// TODO Auto-generated method stub
		return null;
	}

}
