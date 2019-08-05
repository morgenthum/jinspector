package org.jinspector.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jinspector.ThemeUtils;
import org.jinspector.classfile.instructions.Instruction;

/**
 * @author morgenthum
 *
 */
public class InstructionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final EmptyBorder EMPTY_BORDER = new EmptyBorder(0, 2, 0, 2);
	private static final FontRenderContext RENDER_CONTEXT = new FontRenderContext(new AffineTransform(), true, true);

	private JLabel labelPc;
	private JLabel labelInstruction;

	private Instruction instruction;
	private int highestPc;

	/**
	 * @param instruction
	 */
	public InstructionPanel(Instruction instruction, int highestPc) {

		this.instruction = instruction;
		this.highestPc = highestPc;

		this.init();
		this.build();
		this.config();
	}

	private void init() {

		this.labelPc = new JLabel(String.valueOf(instruction.pc));
		this.labelInstruction = new JLabel(Instruction.NAMES.get(instruction.opcode));
	}

	private void build() {

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(labelPc);
		this.add(labelInstruction);
	}

	private void config() {

		this.setBackground(Color.WHITE);

		Font font = this.labelPc.getFont();
		int maxWidth = (int) font.getStringBounds(String.valueOf(this.highestPc), RENDER_CONTEXT).getWidth();
		int width = (int) font.getStringBounds(labelPc.getText(), RENDER_CONTEXT).getWidth();
		int leftInset = maxWidth - width + 5;

		this.labelPc.setBorder(new EmptyBorder(0, leftInset, 0, 5));
		this.labelPc.setBackground(ThemeUtils.BACKGROUND);
		this.labelPc.setForeground(ThemeUtils.FOREGROUND);
		this.labelPc.setOpaque(true);

		this.labelInstruction.setBorder(EMPTY_BORDER);
		this.labelInstruction.setBackground(ThemeUtils.SELECTION_BACKGROUND);
		this.labelInstruction.setForeground(ThemeUtils.SELECTION_FOREGROUND);
		this.labelInstruction.setOpaque(true);

		setSelected(false);
	}

	/**
	 * @param selected
	 */
	public void setSelected(boolean selected) {

		this.setBackground(selected ? ThemeUtils.SELECTION_BACKGROUND : ThemeUtils.BACKGROUND);
	}
}
