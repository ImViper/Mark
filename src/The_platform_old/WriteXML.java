package The_platform_old;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class WriteXML {

	public WriteXML(File save_dir, String number, List<List<List<String>>> pianzhang, List<String> pian_text) throws IOException {
		// TODO Auto-generated constructor stub
		File file = new File(save_dir + "\\XMLÎÄµµ");
		file.mkdir();
		File xmlFile = new File(file + "\\" + number + ".xml");

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("doc");
		String[] strings = { "Relation_Pair", "Center", "times", "Sentence", "position", "ConnectiveType", "Connective",
				"Major_relationType", "RelationType", "ConnectivePosition", "ConnectiveAttribute", "RoleLocation",
				"LanguageSense" };

		for (int i = 1; i < pianzhang.size(); i++) {

			List<List<String>> paragraph = pianzhang.get(i);
			Element pElement = null, rElement = null;
			
			pElement = root.addElement("pragraph");
			pElement.addAttribute("ID", (i + 1) + "");
			String[] tokens = pian_text.get(i).split("/");
			String content = "";
			for (int j = 0; j < tokens.length; j++) {
				content += (j + 1) + "¡¢" + tokens[j];
			}
			pElement.addAttribute("content", content);
			if (paragraph.size() == 0) {
				continue;
			}
			for (int j = 0; j < paragraph.size(); j++) {
				List<String> relative = paragraph.get(j);
				rElement = pElement.addElement("relative");
				rElement.addAttribute("ID", (j + 1) + "");
				int pos = 0;
				rElement.addAttribute(strings[pos], relative.get(6));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(11));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(12));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(9));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(10));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(0));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(1));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(2));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(3));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(4));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(5));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(7));
				pos++;
				rElement.addAttribute(strings[pos], relative.get(8));
				
			}
		}

		try {

			FileOutputStream fos = new FileOutputStream(xmlFile);
			XMLWriter writer = new XMLWriter(fos, outputFormat);
			writer.write(document);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
