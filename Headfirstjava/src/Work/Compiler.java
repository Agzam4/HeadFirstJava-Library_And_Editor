package Work;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import JFrames.JCode;


public class Compiler {

	public int startOfErrs = 0;
	public int lenghtOfErrs = 0;
	
	public void mainn(String[] args) {
		JTextPane pane = new JTextPane();
		compileAndRun("Library\\Chapter1\\44\\", "BeerSong", pane);
	}

	String jdkbinPath = getJdkBinPath();
	String jdkPath = getJdkPath();
	String javaPath = getJavaPath();
	
	public void compileAndRun(String path, String name, JTextPane console) {
		compile(path, name,// + ".java"
				console);
		AddTextln(console, "---");
		run(path, name, console);
	}

	public void compile(String path, String name, JTextPane console) {
		AddTextln(console, "Compiling...");
		try {
			cmd(console, path, jdkPath + " " + name + ".java"); //TODO
//			cmd(console, path, "javac " + name + ".java");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			AddText(console, "Comand err: "  + e.getMessage());
		}
		AddTextln(console, "End of Compiling");
	}
	
	public void run(String path, String name, JTextPane console) {
		Thread t = new Thread() {
			@Override
			public void run() {
				AddTextln(console, "Running...");
				try {
//					cmd(console, path, "\"" + javaPath + "\\bin\\java.exe" + "\"" +
//							" " + name);// -classpath .
//					cmd(console, path, "java " + name);// -classpath . TODO
					cmd(console, path, javaPath + " " + name);// -classpath .
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
					AddText(console, "Comand err: "  + e.getMessage());
				}
				AddTextln(console, "End of programm");
				JCode.paintConsole(console);
			}
		};
		t.start();
	}
	
	private void cmd(JTextPane console, String dir , String command) throws IOException, InterruptedException {
		System.err.println(command);
		Process p = Runtime.getRuntime().exec(command, null, new File(
				System.getProperty("user.dir") + "\\" + dir));

		BufferedReader in2 = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//		String temp2;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		String temp;

		Stream<String> stream = in.lines();
		stream.forEach(e -> AddTextln(console, "\u2009"+e));//System.out.println(e); 
		Stream<String> errStream = in2.lines();
		errStream.forEach(e -> AddTextln(console, "\u200A" +e));//System.out.println(e); 
		stream.close();
		errStream.close();
//		
//		while ((temp = in.readLine()) != null && (temp2 = in2.readLine()) != null) {
//		    System.out.println(temp);
//		    AddTextln(console, temp);
////		    System.out.println(console.getText().replaceAll("\n","").length() -
////		    		temp.length() + " : " + temp.length());
////		    setStyle(console, console.getText().replaceAll("\n","").length() - temp.length(),
////		    		temp.length(), false, false, new Color(200,200,200));
////		    if(temp2 != null) {
////			    System.err.println(temp2);
////			    AddTextln(console, temp2);
////			    setStyle(console, console.getText().replaceAll("\n","").length() - temp2.length(),
////			    		temp2.length() + 1, false, false, new Color(200,200,200));
////		    }
//		}
		
		int start = console.getText().length();
//		while ((temp2 = in2.readLine()) != null) {
//		    System.err.println(temp2);
//		    AddTextln(console, temp2);
//		}
//		startOfErrs = start-1;
//		lenghtOfErrs = console.getText().length() - start - 1;
//		JCode.setStyle(console, start, lenght, false, false, new Color(255,0,0));
		
		in.close();
		in2.close();
		
        p.waitFor();
        p.destroy();
	}

	private void AddText(JTextPane console, String txt) {
		console.setText(console.getText() + txt);
	}
	private void AddTextln(JTextPane console, String txt) {
		console.setText(console.getText() + "\n" + txt);
	}

	public String getJdkPath() {
		return jdkbinPath + "\\bin\\javac.exe";
//		byte[] all;
//		try {
//			all = Files.readAllBytes(Paths.get("jdkData\\jdk-path.txt"));
//			return "\"" + new String(all) + "\"";
//		} catch (IOException e) {
//			String input = null;
//			while (input == null) {
//				input = JOptionPane.showInputDialog("Путь к jdk не найден, пожалуйста укажите его\n"
//						+ "Пример:\n"
//						+ "C:\\Program Files\\Java\\jdk-15.0.1\n"
//						+ "Но если Вы уже добавили в переменную среды PATH значение,"
//						+ "оставьте поле пустым");
//			}
//			String path = input + "\\bin\\javac.exe";
//			try (FileWriter writer = new FileWriter(new File("jdkData\\jdk-path.txt"))) {
//				writer.write(path);
//				writer.flush();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//			return path;
//		}
//		return "javac";
	}
	
	public String getJdkBinPath() {
		byte[] all;
		try {
			all = Files.readAllBytes(Paths.get("jdkData\\jdk-path.txt"));
			return "\"" + new String(all) + "\"";
		} catch (IOException e) {
			String input = null;
			while (input == null) {
				input = JOptionPane.showInputDialog("Путь к jdk не найден, пожалуйста укажите его\n"
						+ "Пример:\n"
						+ "C:\\Program Files\\Java\\jdk-15.0.1\n"
						+ "Но если Вы уже добавили в переменную среды PATH значение,"
						+ "оставьте поле пустым");
			}
			String path = input;
			try (FileWriter writer = new FileWriter(new File("jdkData\\jdk-path.txt"))) {
				writer.write(path);
				writer.flush();
			} catch (IOException ex) {
			}
			return path;
		}
//		return "javac";
	}
	
	public String getJavaPath() {
		return jdkbinPath + "\\bin\\java.exe";
//		byte[] all;
//		try {
//			all = Files.readAllBytes(Paths.get("jdkData\\java-path.txt"));
//			String str = new String(all);
//			return str.equals("") ? "java" : "\"" + str + "\"";
//		} catch (IOException e) {
//			String input = null;
//			while (input == null) {
//				input = JOptionPane.showInputDialog("Путь к java не найден, пожалуйста укажите его\n"
//						+ "Пример:\n"
//						+ "C:\\Program Files\\Java\\jdk-15.0.1\n"
//						+ "Но если Вы уже добавили в переменную среды PATH значение,"
//						+ "оставьте поле пустым") + "\\bin\\java.exe";
//			}
//			String path =  input + "\\bin\\java.exe";
//			try (FileWriter writer = new FileWriter(new File("jdkData\\java-path.txt"))) {
//				writer.write(path);
//				writer.flush();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//			return path;
//		}
//		return "java";
	}
	
	public void setStyle(JTextPane tc, int s, int l, boolean bold, boolean i, Color fg) {
		StyledDocument doc = (StyledDocument) tc.getDocument();

		Element element = doc.getCharacterElement(s);

		AttributeSet as = element.getAttributes();

		MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		
		StyleConstants.setForeground(asNew, fg);		
		StyleConstants.setBold(asNew, bold);		
		StyleConstants.setItalic(asNew, i);

		doc.setCharacterAttributes(s, l, asNew, true);
	}
}
