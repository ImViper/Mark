package The_platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Create_txt {

	public Create_txt(File out_dir, String number, List<String> fix_pianzhang){
		
		File sub_txt = new File(out_dir + "\\ÇÐ·Ö½á¹û");
		sub_txt.mkdir();
		PrintWriter outout = null;
		try {
			try {
				outout = new PrintWriter(sub_txt + "\\" + number + ".txt","utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < fix_pianzhang.size(); i++) {
			outout.print("    " + fix_pianzhang.get(i));
			outout.println();
		}
		
		outout.close();
	}
	
}
