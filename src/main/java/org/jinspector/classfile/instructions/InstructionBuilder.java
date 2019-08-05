package org.jinspector.classfile.instructions;

import java.util.ArrayList;
import java.util.List;

import org.jinspector.classfile.ClassFile;

public class InstructionBuilder {

	private ClassFile classFile;
	private int[] code;
	private int pc;

	public InstructionBuilder(ClassFile classFile, int[] code) {

		this.classFile = classFile;
		this.code = code;
	}

	public List<Instruction> getInstructions() {

		List<Instruction> instructions = new ArrayList<Instruction>();

		while (pc < code.length) {
			instructions.add(getInstruction());
		}

		return instructions;
	}

	private Instruction getInstruction() {

		switch (code[pc]) {
		case Opcodes.RET:
		case Opcodes.NEWARRAY:
		case Opcodes.LSTORE:
		case Opcodes.LLOAD:
		case Opcodes.ISTORE:
		case Opcodes.ILOAD:
		case Opcodes.FSTORE:
		case Opcodes.FLOAD:
		case Opcodes.DSTORE:
		case Opcodes.DLOAD:
		case Opcodes.BIPUSH:
		case Opcodes.ASTORE:
		case Opcodes.ALOAD: {
			return new Instruction(pc, code[pc++], prim(code[pc++]));
		}

		case Opcodes.LDC: {
			return new Instruction(pc, code[pc++], cp(code[pc++]));
		}

		case Opcodes.GOTO:
		case Opcodes.IF_ICMPEQ:
		case Opcodes.IF_ICMPNE:
		case Opcodes.IF_ICMPLT:
		case Opcodes.IF_ICMPGE:
		case Opcodes.IF_ICMPGT:
		case Opcodes.IF_ICMPLE:
		case Opcodes.IF_ACMPEQ:
		case Opcodes.IF_ACMPNE:
		case Opcodes.IFEQ:
		case Opcodes.IFNE:
		case Opcodes.IFLT:
		case Opcodes.IFGE:
		case Opcodes.IFGT:
		case Opcodes.IFLE:
		case Opcodes.IFNONNULL:
		case Opcodes.IFNULL:
		case Opcodes.JSR:
		case Opcodes.SIPUSH: {
			return new Instruction(pc, code[pc++], prim(toShort(code[pc++], code[pc++])));
		}

		case Opcodes.ANEWARRAY:
		case Opcodes.CHECKCAST:
		case Opcodes.GETSTATIC:
		case Opcodes.GETFIELD:
		case Opcodes.INSTANCEOF:
		case Opcodes.INVOKESTATIC:
		case Opcodes.INVOKESPECIAL:
		case Opcodes.INVOKEVIRTUAL:
		case Opcodes.LDC_W:
		case Opcodes.LDC2_W:
		case Opcodes.NEW:
		case Opcodes.PUTFIELD:
		case Opcodes.PUTSTATIC: {
			return new Instruction(pc, code[pc++], cp(toShort(code[pc++], code[pc++])));
		}

		case Opcodes.IINC: {
			return new Instruction(pc, code[pc++], prim(code[pc++]), prim(code[pc++]));
		}

		case Opcodes.MULTIANEWARRAY: {
			return new Instruction(pc, code[pc++], cp(toShort(code[pc++], code[pc++])), prim(code[pc++]));
		}

		case Opcodes.GOTO_W:
		case Opcodes.JSR_W: {
			return new Instruction(pc, code[pc++], prim(toInt(code[pc++], code[pc++], code[pc++], code[pc++])));
		}

		case Opcodes.INVOKEDYNAMIC:
		case Opcodes.INVOKEINTERFACE: {
			return new Instruction(pc, code[pc++], cp(toShort(code[pc++], code[pc++])), prim(code[pc++]),
					prim(code[pc++]));
		}

		case Opcodes.LOOKUPSWITCH: {
			System.err.println("lookupswitch unimplemented");
			System.exit(1);
		}

		case Opcodes.TABLESWITCH: {
			System.err.println("tableswitch unimplemented");
			System.exit(1);
		}

		case Opcodes.WIDE: {
			System.err.println("wide unimplemented");
			System.exit(1);
		}

		default: {
			return new Instruction(pc, code[pc++]);
		}
		}
	}

	/**
	 * @param first
	 * @param second
	 * @return
	 */
	private int toShort(int first, int second) {

		return (first << 8) | second;
	}

	/**
	 * @param first
	 * @param second
	 * @param third
	 * @param fourth
	 * @return
	 */
	private int toInt(int first, int second, int third, int fourth) {

		return (first << 24) | (second << 16) | (third << 8) | fourth;
	}

	/**
	 * @param value
	 * @return
	 */
	private PrimitiveOperand prim(int value) {

		return new PrimitiveOperand(value);
	}

	/**
	 * @param index
	 * @return
	 */
	private ConstantPoolOperand cp(int index) {

		return new ConstantPoolOperand(this.classFile, index);
	}
}
