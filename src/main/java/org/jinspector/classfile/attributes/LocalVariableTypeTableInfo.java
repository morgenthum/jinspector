package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * LocalVariableTypeTable created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class LocalVariableTypeTableInfo extends AttributeInfo {

	/**
	 * LocalVariableType created on 16.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class LocalVariableType implements Visitable {

		public int startPc;
		public int length;
		public int nameIndex;
		public int signatureIndex;
		public int index;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.jreverse.Visitable#visit(org.jreverse.Visitor)
		 */
		@Override
		public <T> T visit(Visitor<T> visitor) {

			return visitor.visit(this);
		}
	}

	public int tableLength;
	public LocalVariableType[] table;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitable#visit(org.jreverse.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}
}
