package The_platform_old;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read_only_rela {
	
	public Read_only_rela(File file, List<List<String>> duan_relative) throws IOException {
		
		FileReader fileReader = new FileReader(file);
		BufferedReader input = new BufferedReader(fileReader);
		String string = null;
		boolean flag = false;
		List<String> duan = new ArrayList<>();
		int para_id = 0;
		input.readLine();
		while ((string = input.readLine()) != null) {
			
			if (string.equals("")) {
				flag = true;	
				 input.readLine();
			}
			if (flag) {
				duan_relative.set(para_id, duan);
				duan = new ArrayList<>();
				para_id++;
				flag = false;
			}else {
				
				duan.add(string);
			}
				
		}
		input.close();
	}
	
}
