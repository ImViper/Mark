package The_platform;


import javax.swing.JTextPane;

import javafx.embed.swing.SwingNode;

public class CreateSwingNode {

	public CreateSwingNode(SwingNode swingNode, String para_text) {
		JTextPane jTextPane = new JTextPane();
		jTextPane.setText(para_text);
		swingNode.setContent(jTextPane);
	}
}
