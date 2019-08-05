package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * LineNumberTableInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class LineNumberTableInfo extends AttributeInfo {

	/**
	 * LineNumber created on 15.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class LineNumber implements Visitable {

		public int startPc;
		public int lineNumber;

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

	public int lineNumberTableLength;
	public LineNumber[] lineNumberTable;

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
