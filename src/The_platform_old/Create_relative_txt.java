package The_platform_old;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Create_relative_txt {

	public Create_relative_txt(File save_dir, String number, List<List<List<String>>> pian_rela)
			throws FileNotFoundException {

		File relative = new File(save_dir + "\\小句关联结果");
		relative.mkdir();
		PrintWriter outout = new PrintWriter(relative + "\\" + number + ".txt");
		
		File relative2 = new File(save_dir + "\\关系特征、主特征等");
		relative2.mkdir();
		PrintWriter output2 = new PrintWriter(relative2 + "\\" + number + ".txt");
		// 一篇文章
		for (int i = 0; i < pian_rela.size(); i++) {
			// 一段里包含的关系
			outout.println("第" + (i + 1) + "段落");
			output2.println("第" + (i + 1) + "段落");
			List<List<String>> duan_rela = new ArrayList<>();
			duan_rela = pian_rela.get(i);
			// 每一个关系
			for (int j = 0; j < duan_rela.size(); j++) {
				// 每一个关系里的各种属性
				List<String> every_rela = new ArrayList<>();
				every_rela = duan_rela.get(j);
				if (every_rela.size() < 5) { // 尚未建立关联属性
					output2.println(every_rela.get(0) + " 暂未标注属性特征");
					for (int k = 0; k < every_rela.size(); k++) {
						outout.print(every_rela.get(k) + " ");
					}
					outout.println();
				} else {

					for (int k = 0; k < every_rela.size(); k++) {
						outout.print(every_rela.get(k) + " ");
					}
					output2.println(every_rela.get(6) + " " + every_rela.get(0) + " " + every_rela.get(1) + " " + " "
							+ every_rela.get(2) + " " + every_rela.get(3) + " " + every_rela.get(7));
					outout.println();
				}
			}
			outout.println();
			output2.println();
		}

		outout.close();
		output2.close();
	}
}
