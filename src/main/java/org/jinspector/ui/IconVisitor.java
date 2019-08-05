package org.jinspector.ui;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jinspector.JInspector;
import org.jinspector.Resources;
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

public class IconVisitor extends Visitor<Icon> {

	private static IconVisitor instance;

	/**
	 * @return
	 */
	public static synchronized IconVisitor getInstance() {

		if (instance == null) {
			instance = new IconVisitor();
		}

		return instance;
	}

	private final Icon annotationIcon;
	private final Icon classIcon;
	private final Icon enumIcon;
	private final Icon interfaceIcon;

	private IconVisitor() {
		URL url = JInspector.class.getResource(Resources.PATH_IMAGE_ANNOTATION);
		annotationIcon = new ImageIcon(url);
		url = JInspector.class.getResource(Resources.PATH_IMAGE_CLASS);
		classIcon = new ImageIcon(url);
		url = JInspector.class.getResource(Resources.PATH_IMAGE_ENUM);
		enumIcon = new ImageIcon(url);
		url = JInspector.class.getResource(Resources.PATH_IMAGE_INTERFACE);
		interfaceIcon = new ImageIcon(url);
	}

	@Override
	public Icon visit(ClassFile classFile) {
		Icon icon;
		if (classFile.isAnnotation()) {
			icon = annotationIcon;
		} else if (classFile.isEnum()) {
			icon = enumIcon;
		} else if (classFile.isInterface()) {
			icon = interfaceIcon;
		} else {
			icon = classIcon;
		}
		return icon;
	}

	@Override
	public Icon visit(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(DoubleInfo doubleInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(FieldrefInfo fieldrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(FloatInfo floatInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(IntegerInfo integerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(InterfaceMethodrefInfo interfaceMethodrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(InvokeDynamicInfo invokeDynamicInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LongInfo longInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(MethodHandleInfo methodHandleInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(MethodrefInfo methodrefInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(MethodTypeInfo methodTypeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(NameAndTypeInfo nameAndTypeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(StringInfo stringInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(UTF8Info utf8Info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(FieldInfo fieldInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(MethodInfo methodInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(AnnotationDefaultInfo annotationDefaultInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(BootstrapMethodsInfo bootstrapMethodsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(CodeInfo codeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ConstantValueInfo constantValueInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(DeprecatedInfo deprecatedInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(EnclosingMethodInfo enclosingMethodInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ExceptionInfo exceptionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(InnerClassesInfo innerClassesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LineNumberTableInfo lineNumberTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LocalVariableTableInfo localVariableTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SignatureInfo signatureInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SourceFileInfo sourceFileInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(StackMapTableInfo stackMapTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SyntheticInfo syntheticInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(Annotation annotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ArrayValue arrayValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ElementValue elementValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(EnumConstValue enumConstValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ExceptionEntry exceptionEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ParameterAnnotation parameterAnnotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(AppendFrame appendFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ChopFrame chopFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(FullFrame fullFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SameFrame sameFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SameFrameExtended sameFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(DoubleVariableInfo doubleVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(FloatVariableInfo floatVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(IntegerVariableInfo integerVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LongVariableInfo longVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(NullVariableInfo nullVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ObjectVariableInfo objectVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(TopVariableInfo topVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(UninitializedVariableInfo uninitializedVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(ElementValuePair elementValuePair) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(BootstrapMethod bootstrapMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(Clazz clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LineNumber lineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LocalVariable localVariable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon visit(LocalVariableType localVariableType) {
		// TODO Auto-generated method stub
		return null;
	}
}
