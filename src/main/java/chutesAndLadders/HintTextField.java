package chutesAndLadders;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener {

	private final String hint;
	//private boolean showingHint;

	public HintTextField(final String hint) {
		super(hint);
		this.hint = hint;
		//this.showingHint = true;
		super.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		if (this.getText().equals(hint)) {
			super.setText("");
			//showingHint = false;
		}

	}

	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			//showingHint = true;
		}
	}
}