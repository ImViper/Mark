package The_platform;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {
	
	public ReadXml(File xmlfile, List<List<List<String>>> pianzhang, List<String> pian_text, List<List<Integer>> sort)  {
		
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(xmlfile);
			Element root = document.getRootElement();
			Iterator<Element> num_of_paragraph = root.elementIterator();
			
			while (num_of_paragraph.hasNext()) {
				Element pElement = num_of_paragraph.next();
				String id = pElement.attributes().get(0).getValue();
				int index = Integer.valueOf(id) - 1;
				String string = pElement.attributes().get(1).getValue();
				int j = 2;
				string = string.substring(2);
				while (string.contains(j + "¡¢")) {	
					string = string.replaceFirst(j + "¡¢", "/");
					j++;		
				}
				pian_text.set(index, string);
				Iterator<Element> it_row = pElement.elementIterator();
				List<List<String>> duan_rela = new ArrayList<>();
				while (it_row.hasNext()) {
					Element el_ename = (Element) it_row.next();
					List<Attribute> list = el_ename.attributes();
					List<String> one_rela = new ArrayList<>();
					for (int i = 1; i < list.size(); i++) {
						one_rela.add(list.get(i).getValue());
					}
					String[] strings = list.get(1).getValue().split("--");
					boolean flag = true;
					int j1 = 0;
					while (j1 < sort.get(index).size()) {
						if (Integer.valueOf(strings[0]) < sort.get(index).get(j1)) {
							sort.get(index).add(j1, Integer.valueOf(strings[0]));
							sort.get(index).add(j1 + 1, Integer.valueOf(strings[1]));
							flag = false;
							break;
						} else {
							if (Integer.valueOf(strings[0]) == sort.get(index).get(j1)) {
								if (Integer.valueOf(strings[1]) < sort.get(index).get(j1 + 1)) {
									sort.get(index).add(j1, Integer.valueOf(strings[0]));
									sort.get(index).add(j1 + 1, Integer.valueOf(strings[1]));
									flag = false;
									break;
								} else {
									j1 = j1 + 2;
								}
							} else {
								j1 = j1 + 2;
							}
						}
					}

					if (flag) {
						sort.get(index).add(Integer.valueOf(strings[0]));
						sort.get(index).add(Integer.valueOf(strings[1]));
					}
					duan_rela.add(j1/2, one_rela);
				}
				pianzhang.set(index, duan_rela);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
