package org.jinspector.ui;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import org.jinspector.classfile.instructions.Instruction;

public class InstructionsListCellRenderer implements ListCellRenderer<Instruction> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Instruction> list, Instruction value, int index,
			boolean isSelected, boolean cellHasFocus) {

		ListModel<? extends Instruction> model = list.getModel();
		Instruction lastInstruction = model.getElementAt(model.getSize() - 1);

		InstructionPanel panel = new InstructionPanel(value, lastInstruction.pc);
		panel.setSelected(isSelected);
		return panel;
	}
}
