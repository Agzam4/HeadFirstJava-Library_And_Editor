package Work;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Uploader {

	public ArrayList<String> code = new ArrayList<String>();
	public ArrayList<String> name = new ArrayList<String>();
	public ArrayList<String> path = new ArrayList<String>();
	public ArrayList<Integer> page = new ArrayList<Integer>();
	public ArrayList<Integer> chapter = new ArrayList<Integer>();

	public void Init() {
		
		File[] chapters = new File("Library").listFiles();
		for (int chap = 0; chap < chapters.length; chap++) {
			File[] pages = chapters[chap].listFiles();
			for (int p = 0; p < pages.length; p++) {
				File[] clases = pages[p].listFiles();
				for (int c = 0; c < clases.length; c++) {
					File f = clases[c];
					try {
						if(f.getName().indexOf(".java") > -1) {
							code.add(new String(Files.readAllBytes(Paths.get(f.getPath()))));
							name.add(f.getName().replaceAll("\\.java", ""));
							page.add(Integer.valueOf(pages[p].getName()));
							path.add(f+"");
							chapter.add(chap);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public ArrayList<Integer> getAllID(int chap, int p, String namee) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for (int i = 0; i < code.size(); i++) {
			boolean isMatch = true;
			if(chap > -1 && chapter.get(i) != chap) // TODO: add break
				isMatch = false;
			if(p > -1 && page.get(i) != p)
				isMatch = false;
			if(!namee.equals("") && name.get(i).toLowerCase().indexOf(namee.toLowerCase()) < 0)
				isMatch = false;
			if(isMatch) {
				ids.add(i);
			}
		}
		return ids;
	}
	
}
