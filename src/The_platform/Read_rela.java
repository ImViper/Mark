package The_platform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read_rela {

	public Read_rela(File file, List<List<List<String>>> pian) throws IOException {
		
		FileReader fileReader = new FileReader(file);
		BufferedReader input = new BufferedReader(fileReader);
		String string = null;
		int index = 0;
		List<List<String>> pList = null;
		input.readLine();
		while ((string = input.readLine()) != null) {
			if (string.equals("")) {
				
				List<List<String>> duan = new ArrayList<>();
				pList = duan;
				input.readLine();
				index++;
				
			} else {
		
				List<String> one_rela = new ArrayList<>();
				
				String[] strings = string.split(" ");
				for (int i = 0; i < strings.length; i++) {
					one_rela.add(strings[i]);
				}
				pList.add(one_rela);
				pian.set(index, pList);
				
			}

		}
		input.close();
	}
}
