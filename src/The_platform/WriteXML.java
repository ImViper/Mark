package The_platform;

import java.io.File;
import java.io.FileNotFoundException;
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

	public WriteXML(File save_dir, String number, List<List<List<String>>> pianzhang, List<String> pian_txt) {
		// TODO Auto-generated constructor stub
		File file = new File(save_dir + "\\XML文档");
		file.mkdir();
		File xmlFile = new File(file + "\\" + number + ".xml");

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("doc");
		String[] strings = { "Relation_Pair", "Center", "times", "Sentence", "position", "ConnectiveType", "Connective",
				"Major_relationType", "RelationType", "ConnectivePosition", "ConnectiveAttribute", "RoleLocation",
				"LanguageSense" };

		//因为第一段存放的是篇章标题，故不把它当段落存入
		for (int i = 1; i < pianzhang.size(); i++) {

			List<List<String>> paragraph = pianzhang.get(i);
			Element pElement = null, rElement = null;
			
			pElement = root.addElement("pragraph");
			pElement.addAttribute("ID", (i + 1) + "");
			String[] tokens = pian_txt.get(i).split("/");
			String content = "";
			for (int j = 0; j < tokens.length; j++) {
				content += (j + 1) + "、" + tokens[j];
			}
			
			pElement.addAttribute("content", content);
			if (paragraph.size() == 0) {
				continue;
			}
			for (int j = 0; j < paragraph.size(); j++) {
				List<String> relative = paragraph.get(j);
				rElement = pElement.addElement("relative");
				rElement.addAttribute("ID", (j + 1) + "");

				for (int k = 0; k < relative.size(); k++) {
					rElement.addAttribute(strings[k], relative.get(k));
				}
			}
		}

		try {

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(xmlFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			XMLWriter writer = new XMLWriter(fos, outputFormat);
			try {
				writer.write(document);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
