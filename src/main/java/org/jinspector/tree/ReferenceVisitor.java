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

public class ReferenceVisitor extends Visitor<String> {

	private static ReferenceVisitor instance;

	/**
	 * @return
	 */
	public static synchronized ReferenceVisitor getInstance() {

		if (instance == null) {
			instance = new ReferenceVisitor();
		}

		return instance;
	}

	/**
	 * 
	 */
	private ReferenceVisitor() {
	}

	@Override
	public String visit(ClassFile classFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ClassInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(DoubleInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(FieldrefInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(FloatInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(IntegerInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(InterfaceMethodrefInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(InvokeDynamicInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(LongInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(MethodHandleInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(MethodrefInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(MethodTypeInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(NameAndTypeInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(StringInfo info) {
		return preVisit(info);
	}

	@Override
	public String visit(UTF8Info info) {
		return preVisit(info);
	}

	public String preVisit(ConstantInfo info) {
		return info.index + " (" + info.getResolvedValue() + ")";
	}

	@Override
	public String visit(FieldInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(MethodInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(AnnotationDefaultInfo annotationDefaultInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(BootstrapMethodsInfo bootstrapMethodsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CodeInfo codeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ConstantValueInfo constantValueInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DeprecatedInfo deprecatedInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(EnclosingMethodInfo enclosingMethodInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ExceptionInfo exceptionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(InnerClassesInfo innerClassesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LineNumberTableInfo lineNumberTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LocalVariableTableInfo localVariableTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LocalVariableTypeTableInfo localVariableTypeTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(RuntimeInvisibleAnnotationInfo runtimeInvisibleAnnotationInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(RuntimeInvisibleParameterAnnotationsInfo runtimeInvisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(RuntimeVisibleAnnotationsInfo runtimeVisibleAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(RuntimeVisibleParameterAnnotationsInfo runtimeVisibleParameterAnnotationsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SignatureInfo signatureInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SourceDebugExtensionInfo sourceDebugExtensionInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SourceFileInfo sourceFileInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(StackMapTableInfo stackMapTableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SyntheticInfo syntheticInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Annotation annotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ArrayValue arrayValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ElementValue elementValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(EnumConstValue enumConstValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ExceptionEntry exceptionEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ParameterAnnotation parameterAnnotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(AppendFrame appendFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ChopFrame chopFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(FullFrame fullFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SameFrame sameFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SameFrameExtended sameFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SameLocals1StackitemFrameExtended sameLocals1StackitemFrameExtended) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DoubleVariableInfo doubleVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(FloatVariableInfo floatVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IntegerVariableInfo integerVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LongVariableInfo longVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(NullVariableInfo nullVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ObjectVariableInfo objectVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TopVariableInfo topVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(UninitializedVariableInfo uninitializedVariableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ElementValuePair elementValuePair) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(BootstrapMethod bootstrapMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Clazz clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LineNumber lineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LocalVariable localVariable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LocalVariableType localVariableType) {
		// TODO Auto-generated method stub
		return null;
	}

}
