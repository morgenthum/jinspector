package org.jinspector.classfile.attributes;

import org.jinspector.Visitable;
import org.jinspector.Visitor;

/**
 * BootstrapMethodsInfo created on 16.04.2013<br>
 * <br>
 * Specification:<br>
 */
public class BootstrapMethodsInfo extends AttributeInfo {

	/**
	 * BootstrapMethod created on 16.04.2013<br>
	 * <br>
	 * Specification:<br>
	 */
	public class BootstrapMethod implements Visitable {

		public int bootstrapMethodRef;
		public int numBootstrapArguments;
		public int[] bootstrapArguments;

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

	public int numBootstrapMethods;
	public BootstrapMethod[] bootstrapMethods;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jreverse.Visitable#visit(org.jreverse.Visitor)
	 */
	@Override
	public <T> T visit(Visitor<T> visitor) {

		return visitor.visit(this);
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "BootstrapMethods_attribute";
	}
}
