package The_platform_old;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Creat_only_rela {

	public Creat_only_rela(File save_dir, String number, List<List<String>> only_relative)  {
		
		File relative = new File(save_dir + "\\С���������������");
		File center = new File(save_dir + "\\��������");
		relative.mkdir();
		center.mkdir();
		try {
			PrintWriter outout = new PrintWriter(relative + "\\" + number + ".txt");
			PrintWriter outout2 = new PrintWriter(center + "\\" + number + ".txt");
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
					outout2.println(strings[0] + " " + strings[3] + " " + strings[1]);
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
