package The_platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Creat_only_rela {

	public Creat_only_rela(File save_dir, String number, List<List<String>> only_relative, List<List<String>> center_degree)  {
		
		File relative = new File(save_dir + "\\С���������������");
		File center = new File(save_dir + "\\��������");
		relative.mkdir();
		center.mkdir();
		try {
			PrintWriter outout = null;
			try {
				outout = new PrintWriter(relative + "\\" + number + ".txt", "utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter outout2 = null;
			try {
				outout2 = new PrintWriter(center + "\\" + number + ".txt", "utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < only_relative.size(); i++) {
				List<String> duan_rela = new ArrayList<>();
				outout.println("��" + (i + 1) + "����");
				outout2.println("��" + (i + 1) + "����");
				duan_rela = only_relative.get(i);
				
				for (int j = 0; j < duan_rela.size(); j++) {
					
					outout.println(duan_rela.get(j));
					String[] strings = duan_rela.get(j).split(" ");
					if (strings[3].equals("0")) {
						strings[3] = "������δѡ�����ľ䡪��";
					}else {
						if (strings[3].equals("3")) {
							strings[3] = "������Ϊ���ľ䡪��";
						}else {
							strings[3] = "������" + strings[3] + "��Ϊ���ľ䡪��";
						}
					}
					
					String degree = "";
					if ( center_degree.get(i).size() > 0) {
						degree = center_degree.get(i).get(j);
					}
					
					if (strings.length > 4) {
						outout2.println(strings[0] + " " + strings[3] + " " + strings[1] + " " + strings[4] +
								" " + degree);
					}else {
						outout2.println(strings[0] + " " + strings[3] + " " + strings[1] + 
								" " + degree);
					}
					
				}
				outout.println();
				outout2.println();
			}
			outout.close();
			outout2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
