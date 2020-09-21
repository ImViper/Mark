package The_platform_old;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextColor {

	StyledDocument doc;
	SimpleAttributeSet notSelected;
	SimpleAttributeSet selected;

	public TextColor(int start, int length, Color color, JTextPane jTextPane) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				notSelected = new SimpleAttributeSet();
				selected = new SimpleAttributeSet(notSelected);
				StyleConstants.setBackground(selected, color);
				doc = jTextPane.getStyledDocument();
				doc.setCharacterAttributes(start, length, selected, true);
				
			}
		});

	}
}
