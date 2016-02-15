package chutesAndLadders;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener {

	private final String hint;

	public HintTextField(final String hint) {
		super(hint);
		this.hint = hint;
		super.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		if (this.getText().equals(hint)) {
			super.setText("");

		}

	}

	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
		}
	}
}