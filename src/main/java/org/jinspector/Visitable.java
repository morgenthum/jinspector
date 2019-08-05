package org.jinspector;

public interface Visitable {

	<T> T visit(Visitor<T> visitor);
}
