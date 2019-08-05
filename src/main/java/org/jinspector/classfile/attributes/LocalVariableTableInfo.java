package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * LocalVariableTableInfo created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class LocalVariableTableInfo extends AttributeInfo {

	/**
	 * LocalVariableTable created on 16.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class LocalVariable implements Visitable {

		public int startPc;
		public int length;
		public int nameIndex;
		public int descriptorIndex;
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
	public LocalVariable[] table;

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
