package org.jinspector.actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.net.URI;

import javax.swing.AbstractAction;

import org.jinspector.LocalizedConstants;
import org.jinspector.Visitable;
import org.jinspector.tree.SpecificationVisitor;

public class ShowSpecificationAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Visitable visitable;

	public ShowSpecificationAction(Visitable visitable) {

		super(LocalizedConstants.ACTION_NAME_SHOW_SPECIFICATION);

		this.visitable = visitable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String url = this.visitable.visit(SpecificationVisitor.getInstance());

		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
