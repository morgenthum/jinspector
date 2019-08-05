package org.jinspector.classfile.instructions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Instruction {

	public static final Map<Integer, String> NAMES = new HashMap<Integer, String>();

	static {
		Field[] fields = Opcodes.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				int code = field.getInt(null);
				String name = field.getName().toLowerCase();
				NAMES.put(code, name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int pc;
	public int opcode;
	public Operand[] operands;

	public Instruction(int pc, int opcode, Operand... operands) {

		this.pc = pc;
		this.opcode = opcode;
		this.operands = operands;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(pc);
		builder.append(": ");
		builder.append(NAMES.get(opcode));
		if (operands != null) {
			builder.append(' ');
			for (Object object : operands) {
				builder.append(object);
				builder.append(' ');
			}
		}

		return builder.toString();
	}
}
