package The_platform_old;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Loading_button implements EventHandler<ActionEvent> {

	private String main_dir = "";
	private String file_name = "";
	private String save_dir = "";
	private List<String> pianzhang = new ArrayList<>();
	private List<List<List<String>>> pianzhang_with_rela = new ArrayList<>();

	public List<String> get_pianduan() {
		return pianzhang;
	}

	public List<List<List<String>>> get_rela() {
		return pianzhang_with_rela;
	}
	
	public Loading_button(String main_dir, String save_dir, String file_name) {
		this.main_dir = main_dir;
		this.file_name = file_name;
		this.save_dir = save_dir;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		File loading_file = new File(main_dir + "\\" + file_name);
		int index = file_name.indexOf(".");
		String number = file_name.substring(0, index);
		File mark_file = new File(save_dir + "\\已标注文档\\切分结果\\" + number + ".txt");
		if (loading_file.getName().endsWith(".doc")) {
			try {
				if (mark_file.exists()) {

					Scanner input = new Scanner(mark_file);
					while (input.hasNextLine()) {
						String string = input.nextLine();
						if (string.equals("")) {
							continue;
						}
						if (string.charAt(0) == ' ') {
							pianzhang.add(string.substring(4));
						} else {
							pianzhang.add(string);
						}

					}
					input.close();

				} else {

					FileInputStream stream = new FileInputStream(loading_file);
					WordExtractor word = new WordExtractor(stream);
					String text = word.getText();
					String[] strings = text.split("\r\n\r\n");
					for (int i = 0; i < strings.length; i++) {
						if (!strings[i].equals("")) {
							String para_text = "";
							strings[i].split("\r\n");
							for (int j = 0; j < strings[i].split("\r\n").length; j++) {
								
								para_text += strings[i].split("\r\n")[j];
							}
							if (para_text.length() > 0) {
								pianzhang.add(para_text);
							}
							
						}

					}
					stream.close();
					word.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (loading_file.getName().endsWith(".docx")) {
			try {
				FileInputStream stream = new FileInputStream(loading_file);
				OPCPackage oPCPackage = POIXMLDocument.openPackage(stream.toString());
				XWPFDocument xwpf = new XWPFDocument(oPCPackage);
				POIXMLTextExtractor ex = new XWPFWordExtractor(xwpf);
				String text = ex.getText();
				String[] strings = text.split("\r\n\r\n");
				for (int i = 0; i < strings.length; i++) {
					if (!strings[i].equals("")) {
						String para_text = "";
						strings[i].split("\r\n");
						for (int j = 0; j < strings[i].split("\r\n").length; j++) {
							
							para_text += strings[i].split("\r\n")[j];
						}
						pianzhang.add(para_text);
					}

				}
				stream.close();
				ex.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			if (loading_file.getName().endsWith(".xml")) {
				new ReadXml(loading_file, pianzhang_with_rela);
			}
		}

	}

}
