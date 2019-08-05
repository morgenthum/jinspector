package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.Visitor;
import org.jinspector.classfile.constantpool.ClassInfo;
import org.jinspector.classfile.constantpool.UTF8Info;

/**
 * InnerClassesInfo created on 15.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class InnerClassesInfo extends AttributeInfo {

	/**
	 * Class created on 15.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class Clazz implements Visitable {

		public ClassInfo innerClassInfoIndex;
		public ClassInfo outerClassInfoIndex;
		public UTF8Info innerNameIndex;
		public int innerClassAccessFlags;

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

	public int numberOfClasses;
	public Clazz[] classes;

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
