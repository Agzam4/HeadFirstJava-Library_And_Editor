package JFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.sql.rowset.serial.SerialStruct;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

import Work.Uploader;
import Work.WindowsButtons;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Work.Compiler;

public class JCode extends JFrame {

	private JPanel contentPane;
	static JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String code, String name, int id, Uploader ul) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCode frame = new JCode(("\n" + code).replaceAll("\n", "\n "), id, ul, name);
					frame.setVisible(true);
					frame.setTitle(name);
					scrollPane.getVerticalScrollBar().setValue(1000);
					scrollPane.getHorizontalScrollBar().setValue(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private String savedcode;
	private String myName;
	private boolean isEdit = false;
	
	public JCode(String code, int id, Uploader ul, String title2) {
		
		savedcode = code;
		myName = ul.name.get(id);
		setName("Library\\Chapter" + (ul.chapter.get(id)+1) +
				"\\" + ul.page.get(id) + "\\");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(15,15,35));
		
		scrollPane = new JScrollPane();
		
		JScrollPane otherClasses = new JScrollPane();
//		scrollPane.setColumnHeaderView(otherClasses);
		otherClasses.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		otherClasses.setMinimumSize(new Dimension(100, 100));
		otherClasses.setBorder(new EmptyBorder(0,0,0,0));
		otherClasses.setBackground(new Color(10,10,25));
		contentPane.add(otherClasses, BorderLayout.WEST);//NORTH WEST
		
		JPanel oc = new JPanel();
		oc.setBackground(new Color(10,10,25));
		oc.setLayout(new BoxLayout(oc, BoxLayout.Y_AXIS));
		otherClasses.setViewportView(oc);

		// Console
		
//		JPanel southPane = new JPanel();
//		southPane.setLayout(new BoxLayout(southPane, BoxLayout.X_AXIS));
//		southPane.setMinimumSize(new Dimension(1000, 2000));
//		southPane.setMaximumSize(new Dimension(1000, 2000));
//		contentPane.add(southPane, BorderLayout.EAST);//NORTH WEST SOUTH
		
		JScrollPane consoleScroll = new JScrollPane();
		consoleScroll.getVerticalScrollBar().setValue(1000);
		consoleScroll.setMinimumSize(new Dimension(100, 200));
		consoleScroll.setBorder(new LineBorder(new Color(17,68,93)));
		consoleScroll.setBackground(new Color(10,10,25));
//		southPane.add(consoleScroll);
		contentPane.add(consoleScroll, BorderLayout.SOUTH);//NORTH  SOUTH EAST
		
//		JPanel consolePanel = new JPanel();
//		consolePanel.setBackground(new Color(10,10,25));
//		consolePanel.setLayout(new BorderLayout(0, 0));
//		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
//		consoleScroll.setColumnHeaderView(consolePanel);
		
		JPanel consoleMenu = new JPanel();
		consoleMenu.setBackground(new Color(10,10,25));
		consoleMenu.setBorder(new EmptyBorder(5,5,5,5));
		consoleMenu.setLayout(new BoxLayout(consoleMenu, BoxLayout.X_AXIS));
		consoleScroll.setColumnHeaderView(consoleMenu);


		JPanel consoleP = new JPanel();
		consoleP.setBackground(new Color(10,10,25));
		consoleP.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
		consoleP.setMinimumSize(new Dimension(Integer.MAX_VALUE, 500));
		consoleP.setLayout(new BoxLayout(consoleP, BoxLayout.Y_AXIS));
		consoleScroll.setViewportView(consoleP);

		JTextPane textPane = new JTextPane();
		JTextPane console = new JTextPane();
		console.setText("\n\n\n\n\n");
		console.setBorder(new EmptyBorder(8, 8, 8, 8));
		console.setBackground(new Color(15,15,35));
		console.setForeground(new Color(100,100,100));
//		console.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
//		console.setMinimumSize(new Dimension(Integer.MAX_VALUE, 500));
		console.setEditable(false);
		consoleP.add(console);
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		JMenuBar mb  = new JMenuBar();
		mb.setBackground(new Color(25,25,35));
		mb.setForeground(new Color(200,200,200));
		mb.setBorder(new EmptyBorder(1,5,1,5));
		setJMenuBar(mb);
				
		JButton run = new JButton("Run");
		JButton compile = new JButton("Compile");
		JButton rc = new JButton("Run & Compile");
		
		
		WindowsButtons.ConvertToWindowsButton(rc, new Color(0,255,100),
				new Color(0,200,100), new Color(0,200,150));
		WindowsButtons.ConvertToWindowsButton(run, new Color(50,255,100),
				new Color(50,200,100), new Color(50,200,150));
		WindowsButtons.ConvertToWindowsButton(compile, new Color(50,255,100),
				new Color(50,200,100), new Color(50,200,150));

		rc.setForeground(new Color(200,200,200));
		run.setForeground(new Color(200,200,200));
		compile.setForeground(new Color(200,200,200));

		UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", new Color(100,100,150));
		UIManager.put("MenuBar.background", Color.ORANGE);
		
		JMenu fileJM = new JMenu("Фалй");
		fileJM.setBackground(new Color(25,25,35));
		fileJM.setForeground(new Color(200,200,200));
		fileJM.setOpaque(true);
//		fileJM.setBorder(new EmptyBorder(1,5,1,5));
		mb.add(fileJM);
		
		JMenuItem save = new JMenuItem("Сохранить");
		save.setBackground(new Color(25,25,35));
		save.setOpaque(true);
		save.setForeground(new Color(200,200,200));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		save.setBorder(new EmptyBorder(1,5,1,5));
		fileJM.add(save);

		
		Compiler comp = new Compiler();
		
		run.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				console.setText(">" + myName + "<");
				/*
				 * "Library\\Chapter" + (ul.chapter.get(id)+1) +
						"\\" + ul.page.get(id) + "\\"
				 */
				comp.run(getName(), myName, console);
				paintConsole(console);
//				setStyle(console, comp.startOfErrs, comp.lenghtOfErrs-1,
//						false, false, new Color(255,35,35));
			}
		});
		
		compile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save(textPane, title2, ul, id);
				console.setText(">" + myName + "<");
				comp.compile(getName(), myName, console);
//				setStyle(console, Compiler.startOfErrs, Compiler.lenghtOfErrs,
//						false, false, new Color(255,35,35));
			}
		});
		
		rc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save(textPane, title2, ul, id);
				console.setText(">" + myName + "<");
				comp.compileAndRun(getName(), myName, console);
				paintConsole(console);
//				setStyle(console, Compiler.startOfErrs, Compiler.lenghtOfErrs-1,
//						false, false, new Color(255,35,35));
			}
		});
		
		// TODO
		consoleMenu.add(rc);
		consoleMenu.add(new JLabel(" "));
		consoleMenu.add(run);
		consoleMenu.add(new JLabel(" "));
		consoleMenu.add(compile);
		consoleMenu.add(getSepratorPane());
		
		scrollPane.setBorder(new LineBorder(new Color(17,68,93)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {   
			
	        @Override
	        protected JButton createDecreaseButton(int orientation) {
	        	 JButton jbutton = new JButton();
	        	 jbutton.setBackground(new Color(10,10,25));
	            return createZeroButton();
	        }

	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            jbutton.setBackground(new Color(30,30,75));
	            jbutton.setBorderPainted(false);
	            return jbutton;
	        }
	        
	        @Override
		    protected void paintThumb( Graphics g, JComponent c, Rectangle tb ) {
		        g.setColor(new Color(30,30,75));
//		        tb.width = 7;
		        g.fillRect(tb.x, tb.y, tb.width, tb.height);
	        }
		    
	        @Override
		    protected void paintTrack(Graphics g, JComponent c, Rectangle tb) {
//		        tb.width = 7;
		        g.setColor(new Color(10,10,25));
		        g.fillRect( tb.x, tb.y, tb.width, tb.height );
		    }
	        
	    });
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane lines = new JTextPane();
		lines.setText("");
		scrollPane.setRowHeaderView(lines);
		lines.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 1, new Color(17,68,93)),
				new EmptyBorder(8, 8, 17, 8)));
		lines.setBackground(new Color(15,15,35));
		lines.setForeground(new Color(200,200,200));
		setLines(lines, code);
		
		textPane.setBorder(new EmptyBorder(8, 17, 17, 17));
		textPane.setBackground(new Color(15,15,35));
		textPane.setForeground(new Color(200,200,200));
		textPane.setCaretColor(new Color(200,200,200));
//		textPane.setEditable(false); FIXME
		textPane.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				setTitle(title2 + (textPane.getText().equals(savedcode) ? "":"*"));
				isEdit = true;
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setTitle(title2 + (textPane.getText().equals(savedcode) ? "":"*"));
				isEdit = true;
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});

		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String txt = textPane.getText();
				if(!savedcode.equals(txt)) {
					try (FileWriter writer = new FileWriter(new File(getName() + ul.name.get(id) + ".java"))) {
						writer.write(txt);
						writer.flush();
						savedcode = txt;
						setTitle(title2);
						myName = ul.name.get(id);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		AddTiles(oc, "", ul.page.get(id), ul, textPane, lines);//ul.chapter.get(id) + TODO
		
		FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
		int charWidth = fm.charWidth(' ');
		int tabWidth = charWidth * 8;
		TabStop[] tabs = new TabStop[5];

		for (int j = 0; j < tabs.length; j++) {
			int tab = j + 1;
			tabs[j] = new TabStop(tab * tabWidth);
		}
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setTabSet(attributes, tabSet);
		int length = textPane.getDocument().getLength();
		textPane.getStyledDocument().setParagraphAttributes(0, length, attributes, false);
		textPane.setText(code);
		paintJTP(code, textPane);
		scrollPane.setViewportView(textPane);

		
		scrollPane.addAncestorListener(new AncestorListener() {
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorAdded(AncestorEvent event) {
				scrollPane.getVerticalScrollBar().setValue(0);
//				contentPane.repaint();
//				contentPane.setVisible(false);
//				contentPane.setVisible(true);
//				scrollPane.setVisible(false);
//				scrollPane.setVisible(true);
				
			}
		});
		
//	    Action open = new AbstractAction("open/hide second") {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            f.setVisible(!f.isVisible());
//	        }
//
//	    };
	    
		Thread colors = new Thread() {
			@Override
			public void run() {
				while (true) {// FIXME
					if(isEdit) {
						String code = textPane.getText();
						paintJTP(code, textPane);
						setLines(lines, code + " ");
						isEdit = false;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		colors.start();
		/*

Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					DataFlavor flavor = DataFlavor.stringFlavor;
					String df;
					try {
						df = (String) clipboard.getData(flavor);
						StringSelection selection = new StringSelection(df.replaceAll("\r", "\n") + " TEST");
						clipboard.setContents(selection, null);
					} catch (UnsupportedFlavorException | IOException e1) {
					}



		 */
	}
	
	private void save(JTextPane textPane, String title2, Uploader ul, int id) {
		String txt = textPane.getText();
		if(!savedcode.equals(txt)) {
			try (FileWriter writer = new FileWriter(new File(getName() + ul.name.get(id) + ".java"))) {
				writer.write(txt);
				writer.flush();
				savedcode = txt;
				setTitle(title2);
				myName = ul.name.get(id);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void AddTiles(JPanel sp, String chap, int page, Uploader uploader,
			JTextPane textPane, JTextPane lines) {
		int c = -1;
		try {
			c = Integer.valueOf(chap);
		} catch (NumberFormatException e) {
		}
		ArrayList<Integer> ids = uploader.getAllID(-1, page, "");
		
		sp.removeAll();
		for (int i = 0; i < ids.size(); i++) {
			CreateTile(sp, ids.get(i), i, uploader, textPane,lines);
		}
		sp.setVisible(false);
		sp.setVisible(true);
		sp.repaint();
	}
	
	public static void paintConsole(JTextPane console) {
		String[] lines = console.getText().split("\n");
		int pos = 0;
		for (String l : lines) {
			if(l.toCharArray()[0] == '\u200A') {
				setStyle(console, pos+1, l.length()-1, false, false, new Color(255,35,35));
			}else if (l.toCharArray()[0] == '\u2009') {
				setStyle(console, pos+1, l.length()-1, false, false, new Color(200,200,200));
			}
			pos += l.length() + 1;
		}
	}
	
	private void CreateTile(JPanel sp, int id, int id2, Uploader uploader,
			JTextPane textPane, JTextPane lines) {
		JPanel tile = new JPanel();
		tile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		tile.setBackground(id2%2 == 0 ? new Color(15,15,35):new Color(20,20,40));
		sp.add(tile);
		
		JLabel name = new JLabel(uploader.name.get(id));
		name.setForeground(new Color(150,150,150));
		tile.add(name);
		name.setFocusable(true);

		setMouseListener(name, tile, id,id2, uploader,textPane,lines);
		setMouseListener(tile, tile, id,id2, uploader,textPane,lines);
		
	}
	
	private JPanel getSepratorPane() {
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setBorder(new EmptyBorder(5,5,5,5));
		return p;
	}
	
	private void setMouseListener(JComponent c, JPanel pp, int id, int id2,
			Uploader uploader, JTextPane textPane, JTextPane lines) {
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pp.setBackground(id2%2 == 0 ? new Color(15,15,35):new Color(20,20,40));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				pp.setBackground(id2%2 == 0 ? new Color(30,30,70):new Color(40,40,80));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				scrollPane.remove(textPane);
				scrollPane.setVisible(false);
				String code = ("\n" + uploader.code.get(id)).replaceAll("\n", "\n ");
				textPane.setText(code);
				paintJTP(code, textPane);
				setTitle("Глава " + (uploader.chapter.get(id)+1) +
						" - стр " + uploader.page.get(id) + " | " + uploader.name.get(id));
				setLines(lines, code);
				setName("Library\\Chapter" + (uploader.chapter.get(id)+1) +
						"\\" + uploader.page.get(id) + "\\");
				scrollPane.setVisible(true);
				scrollPane.setViewportView(textPane);
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
	}
	
	static boolean thr = false;

	private void setLines(JTextPane p, String code) {
		String txt = "";
		for (int i = 1; i < code.split("\n").length; i++) {
			txt += "\n" + i;
		}
		p.setText(txt);
	}
	
	public static void paintJTP(String code, JTextPane tp) {
		
		setStyle(tp, 0, code.length(), false, false, new Color(200,200,200));
		String code2 = code.replaceAll("\r", "").replaceAll("\t", " ").replaceAll("\n", " ")
				.replaceAll("\\.", " ");
		
		for (int i = 0; i < modificators.length; i++) {
			paintIO(tp, code2, modificators[i], new Color(255,0,220), true, false);
		}
		for (int i = 0; i < ss.length; i++) {
			paintIO(tp, code2, ss[i], new Color(0,255,220), false, false);
		}
//		for (int i = 0; i < ss.length; i++) {
//		paintIO(tp, code, "//", new Color(116,255,60), false, false);
//		paintIO(tp, code, "//", new Color(0,255,144), false, false);
//		}

		boolean isComment = false;
		boolean isComment2 = false;
		boolean isString = false;

		int csl = 0;
		int csl2 = 0;
		int ssl = 0;

		int id = 0;
		int id2 = 0;
			
			String[] lines = code.split("\n");
			for (int i = 0; i < lines.length; i++) {
				char[] cs = lines[i].toCharArray();
				csl = 0;
				for (int j = 0; j < cs.length-1; j++) {
					if(!isString && !isComment2 && cs[j] == '/' && cs[j+1] == '/') {
						isComment = true;
					}
					if(!isString && !isComment && !isComment2 && cs[j] == '/' && cs[j+1] == '*') {
						isComment2 = true;
						csl2 = id2;
					}
					if(isComment2 && cs[j] == '*' && cs[j+1] == '/') {
						isComment2 = false;
						setStyle(tp, csl2, id2 - csl2 + 2, false, true, new Color(255,180,0));
					}
					if(!isComment && !isComment2 && cs[j] != '\\' && cs[j+1] == '"') {/// FIXME "\\"
						if(isString) {
							setStyle(tp, id2 - ssl  +1, ssl + 1, false, false, new Color(0,148,255));
						}else {
							ssl = 0;
						}
						isString = !isString;
					}
					if(isComment) {
						csl++;
					}
					if(isComment2) {
//						csl2++;
					}
					if(isString) {
						ssl++;
					}
					id2++;
				}
				id+=cs.length;
				id2++;
				if(isComment) {
					setStyle(tp, id  - csl - 1, csl + 2, false, false, new Color(0,255,144));
				}
				isComment = false;
				csl = 0;
			}
	}
	
	private static void paintIO(JTextPane tp, String code, String io, Color c, boolean b, boolean i) {
		ArrayList<Integer> indexs = new ArrayList<Integer>();

		int index = code.indexOf(io);

		int indexCount = 0;
		while (index != -1) {
			index = code.indexOf(io, indexCount);
			if(index != -1) {
				setStyle(tp, index, io.length(), b, i, c);
				indexs.add(index);
			}
			indexCount = index+io.length();
		}
		
//		for (Integer integer : indexs) {
//		}
	}
	
	private String PaintCodeHTML(String code, JTextPane tp) {
		String html = "";
		ArrayList<String> varNames = new ArrayList<String>();
		
		String[] lines = code.replaceAll("\n", "\n\r").split("\n");
		for (int l = 0; l < lines.length; l++) {
			String[] words = (lines[l]).replaceAll("\"", " \" ")
					.replaceAll("\\[", " \\[ ").replaceAll("\\]", " \\] ")
					.replaceAll(",", " , ").replaceAll("\t", " \t ")
					.replaceAll("\\{", " { ").replaceAll("\\}", " } ")
					.replaceAll("\\(", "( ").replaceAll("\\)", " )").split(" ");// |\\\"
			boolean comment = false;
//			html += "<span style=\"color: '#EEE'; font-size: '7px'\"> " + l + ")&#8192;&#8192;</span>";
			for (int w = 0; w < words.length; w++) {
				String word = words[w].replaceAll(" ", "");
//						.replaceAll("<", "&#60;").replaceAll("\t",
//						"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
				String color = "EEEEEE";
				String tag = "span";
				if(word.indexOf("//") > -1) {
					comment = true;
				}
				if(IndexOf(modificators, word)) {
					color = MC;
					tag = "b";
				}
				if(IndexOf(ss, word))
					color = SSC;
				try {
					if(words[w+1].equals("=")) {
						color = VARC;
						varNames.add(word);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				if(varNames.indexOf(word) > -1) {
					color = VARC;
				}
				if(comment) {
					color = COMMENTC;
					tag = "i";
				}
				System.out.println("|" + word + "|");
					
				html += "<" + tag + " style=\"color: '#" + color + "'\">" + word + " </" + tag + ">";
			}
			System.out.println();
			html += "<br/>";
		}
		return html;
	}
	
	public static void setStyle(JTextPane tc, int s, int l, boolean bold, boolean i, Color fg) {
		StyledDocument doc = (StyledDocument) tc.getDocument();

		Element element = doc.getCharacterElement(s);

		AttributeSet as = element.getAttributes();

		MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		
		StyleConstants.setForeground(asNew, fg);		
		StyleConstants.setBold(asNew, bold);		
		StyleConstants.setItalic(asNew, i);

		doc.setCharacterAttributes(s, l, asNew, true);
	}
	 
	
	static final String modificators[] = {
			" boolean", 	" byte", 		" char", 		" double", 	" float", 		" int", 		" long", 			" short", 		" public", 		" private",
			" protected", 	" abstract", 	" final", 		" native", 	" static", 		" strictfp", 	" synchronized", 	" transient", 	" volatile", 	" if",
			" else", 		" do", 			" while",		" switch",	" case", 		" default", 	" for",				" break",		" continue", 	" assert",
			" class",		" extends",		" implements",	" import",	" instanceof", 	" interface",	" new",				" package",		" super",		" this",
			" catch",		" finally",		" catch", 		" throw", 	" throws", 		" return", 		" void",			" const",		" goto",		" enum",
			" true", " false", " null",
	};

	static final String ss[] = {
			";","{", "}","=",":","+","-",",",".","[","]","(",")",
//			" 0"," 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9",
//			"0 ","1 ","2 ","3 ","4 ","5 ","6 ","7 ","8 "," 9",
//			" 0"," 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9"
			};
	
	final String MC = "FF00DC";
	final String SSC = "16DEE5";
	final String VARC = "74FF3D";
	final String COMMENTC = "00CE71";
	
	
	
	private boolean IndexOf(String s[], String index) {
		for (int i = 0; i < s.length; i++) {
			if(s[i].equals(index)) {
				return true;
			}
		}
		return false;
	}
}
