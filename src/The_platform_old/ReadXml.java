package The_platform_old;

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
	
	public ReadXml(File xmlfile, List<List<List<String>>> pianzhang)  {
		
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(xmlfile);
			Element root = document.getRootElement();
			Iterator<Element> num_of_paragraph = root.elementIterator();
			while (num_of_paragraph.hasNext()) {
				Element pElement = num_of_paragraph.next();
				String id = pElement.attributes().get(0).getValue();
				int index = Integer.valueOf(id) - 1;
				Iterator<Element> it_row = pElement.elementIterator();
				List<List<String>> duan_rela = new ArrayList<>();
				while (it_row.hasNext()) {
					Element el_ename = (Element) it_row.next();
					List<Attribute> list = el_ename.attributes();
					List<String> one_rela = new ArrayList<>();
					for (int i = 1; i < list.size(); i++) {
						one_rela.add(list.get(i).getValue());
					}
					duan_rela.add(one_rela);
				}
				pianzhang.set(index, duan_rela);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
