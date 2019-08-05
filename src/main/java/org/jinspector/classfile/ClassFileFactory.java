package org.jinspector.classfile;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jinspector.Constants;
import org.jinspector.classfile.attributes.AnnotationDefaultInfo;
import org.jinspector.classfile.attributes.AttributeInfo;
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
import org.jinspector.classfile.attributes.helpers.Annotation;
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
 * ClassFileFactory created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class ClassFileFactory {

	private DataInputStream input;

	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public ClassFileFactory(InputStream input) {

		this.input = new DataInputStream(input);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public ClassFile createClassFile() throws IOException {

		ClassFile classFile = new ClassFile();

		classFile.magic = this.input.readInt();
		classFile.minorVersion = this.input.readUnsignedShort();
		classFile.majorVersion = this.input.readUnsignedShort();

		classFile.constantPoolCount = this.input.readUnsignedShort();
		this.readConstantPool(classFile);

		classFile.accessFlags = this.input.readUnsignedShort();
		classFile.thisClass = classFile.getClassInfo(input.readUnsignedShort());
		classFile.superClass = classFile.getClassInfo(input.readUnsignedShort());
		classFile.interfacesCount = this.input.readUnsignedShort();
		this.readInterfaces(classFile);

		classFile.fieldsCount = this.input.readUnsignedShort();
		this.readFields(classFile);

		classFile.methodsCount = this.input.readUnsignedShort();
		this.readMethods(classFile);

		classFile.attributesCount = this.input.readUnsignedShort();
		classFile.attributes = this.readAttributes(classFile, classFile.attributesCount);

		return classFile;
	}

	/**
	 * @param classFile
	 * @throws IOException
	 */
	private void readConstantPool(ClassFile classFile) throws IOException {

		ConstantInfo[] infos = new ConstantInfo[classFile.constantPoolCount];

		for (short i = 1; i < classFile.constantPoolCount; ++i) {
			ConstantInfo constant = null;
			byte tag = (byte) this.input.readUnsignedByte();
			switch (tag) {
			case ConstantInfo.CLASS: {
				ClassInfo info = ensureEntry(infos, new ClassInfo(classFile, i));
				info.name = ensureEntry(infos, new UTF8Info(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.FIELDREF: {
				FieldrefInfo info = ensureEntry(infos, new FieldrefInfo(classFile, i));
				info.clazz = ensureEntry(infos, new ClassInfo(classFile, this.input.readUnsignedShort()));
				info.nameAndTypeIndex = ensureEntry(infos,
						new NameAndTypeInfo(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.METHODREF: {
				MethodrefInfo info = ensureEntry(infos, new MethodrefInfo(classFile, i));
				info.clazz = ensureEntry(infos, new ClassInfo(classFile, this.input.readUnsignedShort()));
				info.nameAndType = ensureEntry(infos, new NameAndTypeInfo(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.INTERFACE_METHODREF: {
				InterfaceMethodrefInfo info = ensureEntry(infos, new InterfaceMethodrefInfo(classFile, i));
				info.clazz = ensureEntry(infos, new ClassInfo(classFile, this.input.readUnsignedShort()));
				info.nameAndType = ensureEntry(infos, new NameAndTypeInfo(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.STRING: {
				StringInfo info = ensureEntry(infos, new StringInfo(classFile, i));
				info.string = ensureEntry(infos, new UTF8Info(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.INTEGER: {
				IntegerInfo info = ensureEntry(infos, new IntegerInfo(classFile, i));
				info.bytes = this.input.readInt();
				constant = info;
				break;
			}
			case ConstantInfo.FLOAT: {
				FloatInfo info = ensureEntry(infos, new FloatInfo(classFile, i));
				info.bytes = this.input.readInt();
				constant = info;
				break;
			}
			case ConstantInfo.LONG: {
				LongInfo info = ensureEntry(infos, new LongInfo(classFile, i));
				info.highBytes = this.input.readInt();
				info.lowBytes = this.input.readInt();
				constant = info;
				++i;
				break;
			}
			case ConstantInfo.DOUBLE: {
				DoubleInfo info = ensureEntry(infos, new DoubleInfo(classFile, i));
				info.highBytes = this.input.readInt();
				info.lowBytes = this.input.readInt();
				constant = info;
				++i;
				break;
			}
			case ConstantInfo.NAME_AND_TYPE: {
				NameAndTypeInfo info = ensureEntry(infos, new NameAndTypeInfo(classFile, i));
				info.name = ensureEntry(infos, new UTF8Info(classFile, this.input.readUnsignedShort()));
				info.descriptor = ensureEntry(infos, new UTF8Info(classFile, this.input.readUnsignedShort()));
				constant = info;
				break;
			}
			case ConstantInfo.UTF8: {
				UTF8Info info = ensureEntry(infos, new UTF8Info(classFile, i));
				info.length = this.input.readUnsignedShort();
				byte[] bytes = new byte[info.length];
				for (short j = 0; j < bytes.length; ++j) {
					bytes[j] = (byte) this.input.readUnsignedByte(); // TODO
				}
				info.bytes = bytes;
				constant = info;
				break;
			}
			case ConstantInfo.METHOD_HANDLE: {
				MethodHandleInfo info = ensureEntry(infos, new MethodHandleInfo(classFile, i));
				info.referenceKind = (byte) this.input.readUnsignedByte();
				info.referenceIndex = this.input.readUnsignedShort();
				constant = info;
				break;
			}
			case ConstantInfo.METHOD_TYPE: {
				MethodTypeInfo info = ensureEntry(infos, new MethodTypeInfo(classFile, i));
				info.descriptorIndex = this.input.readUnsignedByte();
				constant = info;
				break;
			}
			case ConstantInfo.INVOKE_DYNAMIC: {
				InvokeDynamicInfo info = ensureEntry(infos, new InvokeDynamicInfo(classFile, i));
				info.bootstrapMethodAttributeIndex = this.input.readUnsignedShort();
				info.nameAndTypeIndex = this.input.readUnsignedShort();
				constant = info;
				break;
			}
			}

			constant.classFile = classFile;
			constant.index = i;
			constant.tag = tag;
			infos[i] = constant;
		}

		classFile.constantPool = infos;
	}

	/**
	 * @param constantPool
	 * @param entry
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T extends ConstantInfo> T ensureEntry(ConstantInfo[] constantPool, T entry) {

		if (constantPool[entry.index] != null) {
			return (T) constantPool[entry.index];
		}

		return (T) (constantPool[entry.index] = entry);
	}

	/**
	 * @param classFile
	 * @throws IOException
	 */
	private void readInterfaces(ClassFile classFile) throws IOException {

		ClassInfo[] interfaces = new ClassInfo[classFile.interfacesCount];

		for (short i = 0; i < interfaces.length; ++i) {
			interfaces[i] = classFile.getClassInfo(input.readUnsignedShort());
		}

		classFile.interfaces = interfaces;
	}

	/**
	 * @param classFile
	 * @throws IOException
	 */
	private void readFields(ClassFile classFile) throws IOException {

		FieldInfo[] fields = new FieldInfo[classFile.fieldsCount];

		for (short i = 0; i < fields.length; ++i) {
			FieldInfo info = new FieldInfo(classFile, i);
			info.accessFlags = this.input.readUnsignedShort();
			info.name = classFile.getUtf8Info(this.input.readUnsignedShort());
			info.descriptor = classFile.getUtf8Info(this.input.readUnsignedShort());
			info.attributesCount = this.input.readUnsignedShort();
			info.attributes = this.readAttributes(classFile, info.attributesCount);

			fields[i] = info;
		}

		classFile.fields = fields;
	}

	/**
	 * @param classFile
	 * @param n
	 * @return
	 * @throws IOException
	 */
	private AttributeInfo[] readAttributes(ClassFile classFile, int n) throws IOException {

		AttributeInfo[] infos = new AttributeInfo[n];

		for (short i = 0; i < infos.length; ++i) {
			infos[i] = this.readAttribute(classFile);
		}

		return infos;
	}

	/**
	 * @param classFile
	 * @return
	 * @throws IOException
	 */
	private AttributeInfo readAttribute(ClassFile classFile) throws IOException {

		AttributeInfo info = null;
		int nameIndex = this.input.readUnsignedShort();
		int attributeLength = this.input.readInt();
		UTF8Info nameInfo = (UTF8Info) classFile.constantPool[nameIndex];
		String name = new String(nameInfo.bytes);

		switch (name) {
		case Constants.CONSTANT_CONSTANTVALUE: {
			ConstantValueInfo temp = new ConstantValueInfo();
			temp.constantValueIndex = this.input.readUnsignedShort();
			info = temp;
			break;
		}
		case Constants.CONSTANT_CODE: {
			CodeInfo temp = new CodeInfo();
			temp.maxStack = this.input.readUnsignedShort();
			temp.maxLocals = this.input.readUnsignedShort();
			temp.codeLength = this.input.readInt();
			int[] code = new int[temp.codeLength];
			for (int i = 0; i < code.length; ++i) {
				code[i] = this.input.readUnsignedByte();
			}
			temp.code = code;
			temp.exceptionTableLength = this.input.readUnsignedShort();
			ExceptionEntry[] exceptions = new ExceptionEntry[temp.exceptionTableLength];
			for (int i = 0; i < exceptions.length; ++i) {
				ExceptionEntry exception = new ExceptionEntry();
				exception.startPc = this.input.readUnsignedShort();
				exception.endPc = this.input.readUnsignedShort();
				exception.handlerPc = this.input.readUnsignedShort();
				exception.catchType = this.input.readUnsignedShort();
				exceptions[i] = exception;
			}
			temp.exceptionTable = exceptions;
			temp.attributesCount = this.input.readUnsignedShort();
			AttributeInfo[] attributes = new AttributeInfo[temp.attributesCount];
			for (int i = 0; i < attributes.length; ++i) {
				attributes[i] = this.readAttribute(classFile);
			}
			temp.attributes = attributes;
			info = temp;
			break;
		}
		case Constants.CONSTANT_STACKMAPTABLE: {
			StackMapTableInfo temp = new StackMapTableInfo();
			temp.numberOfEntries = this.input.readUnsignedShort();
			StackMapFrame[] frames = new StackMapFrame[temp.numberOfEntries];
			for (int i = 0; i < frames.length; ++i) {
				frames[i] = this.readStackMapFrame();
			}
			temp.entries = frames;
			info = temp;
			break;
		}
		case Constants.CONSTANT_EXCEPTIONS: {
			ExceptionInfo temp = new ExceptionInfo();
			temp.numberOfExceptions = this.input.readUnsignedShort();
			int[] exceptionsIndexes = new int[temp.numberOfExceptions];
			for (int i = 0; i < exceptionsIndexes.length; ++i) {
				exceptionsIndexes[i] = this.input.readUnsignedShort();
			}
			temp.exceptionIndexTable = exceptionsIndexes;
			info = temp;
			break;
		}
		case Constants.CONSTANT_INNERCLASSES: {
			InnerClassesInfo temp = new InnerClassesInfo();
			temp.numberOfClasses = this.input.readUnsignedShort();
			InnerClassesInfo.Clazz[] classes = new InnerClassesInfo.Clazz[temp.numberOfClasses];
			for (int i = 0; i < classes.length; ++i) {
				InnerClassesInfo.Clazz clazz = temp.new Clazz();
				clazz.innerClassInfoIndex = classFile.getClassInfo(this.input.readUnsignedShort());
				clazz.outerClassInfoIndex = classFile.getClassInfo(this.input.readUnsignedShort());
				clazz.innerNameIndex = classFile.getUtf8Info(this.input.readUnsignedShort());
				clazz.innerClassAccessFlags = this.input.readUnsignedShort();
				classes[i] = clazz;
			}
			temp.classes = classes;
			info = temp;
			break;
		}
		case Constants.CONSTANT_ENCLOSINGMETHOD: {
			EnclosingMethodInfo temp = new EnclosingMethodInfo();
			temp.classIndex = this.input.readUnsignedShort();
			temp.methodIndex = this.input.readUnsignedShort();
			info = temp;
			break;
		}
		case Constants.CONSTANT_SYNTHETIC: {
			info = new SyntheticInfo();
			break;
		}
		case Constants.CONSTANT_SIGNATURE: {
			SignatureInfo temp = new SignatureInfo();
			temp.signatureIndex = this.input.readUnsignedShort();
			info = temp;
			break;
		}
		case Constants.CONSTANT_SOURCEFILE: {
			SourceFileInfo temp = new SourceFileInfo();
			temp.sourcefile = classFile.getUtf8Info(this.input.readUnsignedShort());
			info = temp;
			break;
		}
		case Constants.CONSTANT_SOURCEDEBUGEXTENSION: {
			SourceDebugExtensionInfo temp = new SourceDebugExtensionInfo();
			int[] debugExtension = new int[attributeLength];
			for (int i = 0; i < debugExtension.length; ++i) {
				debugExtension[i] = this.input.readUnsignedByte();
			}
			temp.debugExtension = debugExtension;
			info = temp;
			break;
		}
		case Constants.CONSTANT_LINENUMBERTABLE: {
			LineNumberTableInfo temp = new LineNumberTableInfo();
			temp.lineNumberTableLength = this.input.readUnsignedShort();
			LineNumberTableInfo.LineNumber[] lineNumbers = new LineNumberTableInfo.LineNumber[temp.lineNumberTableLength];
			for (short i = 0; i < lineNumbers.length; ++i) {
				LineNumberTableInfo.LineNumber lineNumber = temp.new LineNumber();
				lineNumber.startPc = this.input.readUnsignedShort();
				lineNumber.lineNumber = this.input.readUnsignedShort();
				lineNumbers[i] = lineNumber;
			}
			temp.lineNumberTable = lineNumbers;
			info = temp;
			break;
		}
		case Constants.CONSTANT_LOCALVARIABLETABLE: {
			LocalVariableTableInfo temp = new LocalVariableTableInfo();
			temp.tableLength = this.input.readUnsignedShort();
			LocalVariableTableInfo.LocalVariable[] table = new LocalVariableTableInfo.LocalVariable[temp.tableLength];
			for (int i = 0; i < table.length; ++i) {
				LocalVariableTableInfo.LocalVariable element = temp.new LocalVariable();
				element.startPc = this.input.readUnsignedShort();
				element.length = this.input.readUnsignedShort();
				element.nameIndex = this.input.readUnsignedShort();
				element.descriptorIndex = this.input.readUnsignedShort();
				element.index = this.input.readUnsignedShort();
				table[i] = element;
			}
			temp.table = table;
			info = temp;
			break;
		}
		case Constants.CONSTANT_LOCALVARIABLETYPETABLE: {
			LocalVariableTypeTableInfo temp = new LocalVariableTypeTableInfo();
			temp.tableLength = this.input.readUnsignedShort();
			LocalVariableTypeTableInfo.LocalVariableType[] table = new LocalVariableTypeTableInfo.LocalVariableType[temp.tableLength];
			for (int i = 0; i < table.length; ++i) {
				LocalVariableTypeTableInfo.LocalVariableType element = temp.new LocalVariableType();
				element.startPc = this.input.readUnsignedShort();
				element.length = this.input.readUnsignedShort();
				element.nameIndex = this.input.readUnsignedShort();
				element.signatureIndex = this.input.readUnsignedShort();
				element.index = this.input.readUnsignedShort();
				table[i] = element;
			}
			temp.table = table;
			info = temp;
			break;
		}
		case Constants.CONSTANT_DEPRECATED: {
			info = new DeprecatedInfo();
			break;
		}
		case Constants.CONSTANT_RUNTIMEVISIBLEANNOTATIONS: {
			RuntimeVisibleAnnotationsInfo temp = new RuntimeVisibleAnnotationsInfo();
			temp.numAnnotations = this.input.readUnsignedShort();
			Annotation[] annotations = new Annotation[temp.numAnnotations];
			for (int i = 0; i < annotations.length; ++i) {
				annotations[i] = this.readAnnotation();
			}
			temp.annotations = annotations;
			info = temp;
			break;
		}
		case Constants.CONSTANT_RUNTIMEINVISIBLEANNOTATIONS: {
			RuntimeInvisibleAnnotationInfo temp = new RuntimeInvisibleAnnotationInfo();
			temp.numAnnotations = this.input.readUnsignedShort();
			Annotation[] annotations = new Annotation[temp.numAnnotations];
			for (int i = 0; i < annotations.length; ++i) {
				annotations[i] = this.readAnnotation();
			}
			temp.annotations = annotations;
			info = temp;
			break;
		}
		case Constants.CONSTANT_RUNTIMEVISIBLEPARAMETERANNOTATIONS: {
			RuntimeVisibleParameterAnnotationsInfo temp = new RuntimeVisibleParameterAnnotationsInfo();
			temp.numParameters = (byte) this.input.readUnsignedByte();
			ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[temp.numParameters];
			for (int i = 0; i < parameterAnnotations.length; ++i) {
				parameterAnnotations[i] = this.readParameterAnnotation();
			}
			temp.parameterAnnotations = parameterAnnotations;
			info = temp;
			break;
		}
		case Constants.CONSTANT_RUNTIMEINVISIBLEPARAMETERANNOTATIONS: {
			RuntimeInvisibleParameterAnnotationsInfo temp = new RuntimeInvisibleParameterAnnotationsInfo();
			temp.numParameters = (byte) this.input.readUnsignedByte();
			ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[temp.numParameters];
			for (int i = 0; i < parameterAnnotations.length; ++i) {
				parameterAnnotations[i] = this.readParameterAnnotation();
			}
			temp.parameterAnnotations = parameterAnnotations;
			info = temp;
			break;
		}
		case Constants.CONSTANT_ANNOTATIONDEFAULT: {
			AnnotationDefaultInfo temp = new AnnotationDefaultInfo();
			temp.defaultValue = this.readElementValue();
			info = temp;
			break;
		}
		case Constants.CONSTANT_BOOTSTRAPMETHODS: {
			BootstrapMethodsInfo temp = new BootstrapMethodsInfo();
			temp.numBootstrapMethods = this.input.readUnsignedShort();
			BootstrapMethodsInfo.BootstrapMethod[] methods = new BootstrapMethodsInfo.BootstrapMethod[temp.numBootstrapMethods];
			for (int i = 0; i < methods.length; ++i) {
				BootstrapMethodsInfo.BootstrapMethod method = temp.new BootstrapMethod();
				method.bootstrapMethodRef = this.input.readUnsignedShort();
				method.numBootstrapArguments = this.input.readUnsignedShort();
				int[] arguments = new int[method.numBootstrapArguments];
				for (int j = 0; j < arguments.length; ++j) {
					arguments[j] = this.input.readUnsignedShort();
				}
				method.bootstrapArguments = arguments;
				methods[i] = method;
			}
			temp.bootstrapMethods = methods;
			info = temp;
			break;
		}
		}

		info.classFile = classFile;
		info.attributeNameIndex = classFile.getUtf8Info(nameIndex);
		info.attributeLength = attributeLength;

		return info;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private StackMapFrame readStackMapFrame() throws IOException {

		StackMapFrame frame = null;
		int frameType = this.input.readUnsignedByte();
		if ((frameType >= 0) && (frameType <= 63)) {
			frame = new SameFrame();
		} else if ((frameType >= 64) && (frameType <= 127)) {
			SameLocals1StackItemFrame temp = new SameLocals1StackItemFrame();
			temp.stack = this.readType((byte) this.input.readUnsignedByte());
			frame = temp;
		} else if (frameType == 247) {
			SameLocals1StackitemFrameExtended temp = new SameLocals1StackitemFrameExtended();
			temp.offsetDelta = this.input.readUnsignedShort();
			temp.stack = this.readType((byte) this.input.readUnsignedByte());
			frame = temp;
		} else if ((frameType >= 248) && (frameType <= 250)) {
			ChopFrame temp = new ChopFrame();
			temp.offsetDelta = this.input.readUnsignedShort();
			frame = temp;
		} else if (frameType == 251) {
			SameFrameExtended temp = new SameFrameExtended();
			temp.offsetDelta = this.input.readUnsignedShort();
			frame = temp;
		} else if ((frameType >= 252) && (frameType <= 254)) {
			AppendFrame temp = new AppendFrame();
			temp.offsetDelta = this.input.readUnsignedShort();
			VerificationType[] locals = new VerificationType[frameType - 251];
			for (int i = 0; i < locals.length; ++i) {
				locals[i] = this.readType((byte) this.input.readUnsignedByte());
			}
			temp.locals = locals;
			frame = temp;
		} else if (frameType == 255) {
			FullFrame temp = new FullFrame();
			temp.offsetDelta = this.input.readUnsignedShort();
			temp.numberOfLocals = this.input.readUnsignedShort();
			VerificationType[] locals = new VerificationType[temp.numberOfLocals];
			for (int i = 0; i < locals.length; ++i) {
				locals[i] = this.readType((byte) this.input.readUnsignedByte());
			}
			temp.locals = locals;
			temp.numberOfStackItems = this.input.readUnsignedShort();
			VerificationType[] stackItems = new VerificationType[temp.numberOfStackItems];
			for (int i = 0; i < stackItems.length; ++i) {
				stackItems[i] = this.readType((byte) this.input.readUnsignedByte());
			}
			temp.stack = stackItems;
			frame = temp;
		}
		frame.frameType = frameType;
		return frame;
	}

	/**
	 * @param tag
	 * @return
	 * @throws IOException
	 */
	public VerificationType readType(byte tag) throws IOException {

		VerificationType type = null;
		if (tag == 0) {
			type = new TopVariableInfo();
		} else if (tag == 1) {
			type = new IntegerVariableInfo();
		} else if (tag == 2) {
			type = new FloatVariableInfo();
		} else if (tag == 4) {
			type = new LongVariableInfo();
		} else if (tag == 3) {
			type = new DoubleVariableInfo();
		} else if (tag == 5) {
			type = new NullVariableInfo();
		} else if (tag == 6) {
			type = new UninitializedThisVariableInfo();
		} else if (tag == 7) {
			type = new ObjectVariableInfo();
			((ObjectVariableInfo) type).cpoolIndex = this.input.readUnsignedShort();
		} else if (tag == 8) {
			type = new UninitializedVariableInfo();
			((UninitializedVariableInfo) type).offset = this.input.readUnsignedShort();
		}
		type.tag = tag;
		return type;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private ParameterAnnotation readParameterAnnotation() throws IOException {

		ParameterAnnotation parameterAnnotation = new ParameterAnnotation();
		parameterAnnotation.numAnnotations = this.input.readUnsignedShort();
		Annotation[] annotations = new Annotation[parameterAnnotation.numAnnotations];
		for (int i = 0; i < annotations.length; ++i) {
			annotations[i] = this.readAnnotation();
		}
		parameterAnnotation.annotations = annotations;
		return parameterAnnotation;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private Annotation readAnnotation() throws IOException {

		Annotation annotation = new Annotation();
		annotation.typeIndex = this.input.readUnsignedShort();
		annotation.numElementValuePairs = this.input.readUnsignedShort();
		Annotation.ElementValuePair[] pairs = new Annotation.ElementValuePair[annotation.numElementValuePairs];
		for (int i = 0; i < pairs.length; ++i) {
			Annotation.ElementValuePair pair = annotation.new ElementValuePair();
			pair.elementNameIndex = this.input.readUnsignedShort();
			pair.elementValue = this.readElementValue();
			pairs[i] = pair;
		}
		annotation.elementValuePairs = pairs;
		return annotation;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private ElementValue readElementValue() throws IOException {

		ElementValue value = new ElementValue();
		byte tag = (byte) this.input.readUnsignedByte();
		value.tag = tag;
		if ((tag == 'B') || (tag == 'C') || (tag == 'D') || (tag == 'F') || (tag == 'I') || (tag == 'J') || (tag == 'S')
				|| (tag == 'Z') || (tag == 's')) {
			value.value = this.input.readUnsignedShort();
		} else if (tag == 'e') {
			EnumConstValue enumConstValue = new EnumConstValue();
			enumConstValue.typeNameIndex = this.input.readUnsignedShort();
			enumConstValue.constNameIndex = this.input.readUnsignedShort();
			value.value = enumConstValue;
		} else if (tag == 'c') {
			value.value = this.input.readUnsignedShort();
		} else if (tag == '@') {
			value.value = this.readAnnotation();
		} else if (tag == '[') {
			ArrayValue arrayValue = new ArrayValue();
			arrayValue.numValues = this.input.readUnsignedShort();
			ElementValue[] elementValues = new ElementValue[arrayValue.numValues];
			for (int i = 0; i < elementValues.length; ++i) {
				elementValues[i] = this.readElementValue();
			}
			arrayValue.values = elementValues;
			value.value = arrayValue;
		}
		return value;
	}

	/**
	 * @param classFile
	 * @throws IOException
	 */
	private void readMethods(ClassFile classFile) throws IOException {

		MethodInfo[] methods = new MethodInfo[classFile.methodsCount];

		for (short i = 0; i < methods.length; ++i) {
			MethodInfo method = new MethodInfo(classFile, i);
			method.accessFlags = this.input.readUnsignedShort();
			method.name = classFile.getUtf8Info(this.input.readUnsignedShort());
			method.descriptor = classFile.getUtf8Info(this.input.readUnsignedShort());
			method.attributesCount = this.input.readUnsignedShort();
			method.attributes = this.readAttributes(classFile, method.attributesCount);

			methods[i] = method;
		}

		classFile.methods = methods;
	}
}
