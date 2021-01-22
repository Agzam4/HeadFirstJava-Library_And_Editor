package JFrames;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import Work.Uploader;
import Work.WindowsButtons;
import debug.JTestFrame;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JComponent;

public class JLibrary extends JFrame {

	private JPanel contentPane;
	private JTextField page;
	private JTextField className;
	
	private int chaptersLenght[] = new int[5];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLibrary frame = new JLibrary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLibrary() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JTestFrame.class.getResource("/JFrames/ICO2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(10,10,25));
		setContentPane(contentPane);

		// Сброс длины отметок у scrollPane
		resetСhaptersLenght();

		JButton find = new JButton("Найти");
		find.setBackground(new Color(10,10,25));
		find.setForeground(new Color(200,200,200));
		find.setFocusable(false);
		contentPane.add(find, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {   

			// UI у scrollPane - VerticalScrollBar
			
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
	            jbutton.setPreferredSize(new Dimension(155, 15));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            jbutton.setBackground(new Color(30,30,75));
	            jbutton.setBorderPainted(false);
	            return jbutton;
	        }
	        
	        @Override
		    protected void paintThumb( Graphics g, JComponent c, Rectangle tb ) {
		        g.setColor(new Color(61,61,153, 100));
		        g.fillRect(tb.x+1, tb.y+1, tb.width-2, tb.height-2);
		        g.setColor(new Color(30,30,75));
		        g.drawRect(tb.x+1, tb.y+1, tb.width-3, tb.height-3);
		        g.drawRect(tb.x, tb.y, tb.width-1, tb.height-1);
				g.setColor(Color.WHITE);
				g.dispose();
	        }
		    
	        @Override
		    protected void paintTrack(Graphics g, JComponent c, Rectangle tb) {
		        g.setColor(new Color(10,10,25));
		        g.fillRect(tb.x, tb.y, tb.width, tb.height );
		        int ph = 0;
		        for (int i = 0; i < chaptersLenght.length; i++) {
		        	if(chaptersLenght[i] > 0) {
		        		ph += chaptersLenght[i]*25 + 13;
		        	}
				}
				g.setColor(Color.RED);
		        double py = 6.5;
        		int h = (13*tb.height)/ph;
        		if(h < 1)
        			h = 1;
        		// Отрисовка отметок
		        for (int i = 0; i < chaptersLenght.length; i++) {
		        	if(chaptersLenght[i] > 0) {
						g.setColor(new Color(102,255,102,100));
						int y = (int) ((py*tb.height)/ph) + tb.y - h/2;
				        g.fillRect(tb.x, y, tb.width, h);
						g.setColor(new Color(102,255,102));
				        g.drawRect(tb.x, y, tb.width-1, h);
			        	py += 13;
						py += chaptersLenght[i]*25;
		        	}
				}
				g.setColor(Color.WHITE);
		    }
	        
	    });

		
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(10,10,25));
		contentPane.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		
		JButton bp = new JButton(">");
		JButton bm = new JButton("<");

		// Конвертаци в кнопки как у Windows 10
		WindowsButtons.ConvertToWindowsButton(find,
				new Color(50,50,75), new Color(25,25,50), new Color(100,100,150));
		WindowsButtons.ConvertToWindowsButton(bm,
				new Color(50,50,75), new Color(25,25,50), new Color(100,100,150));
		WindowsButtons.ConvertToWindowsButton(bp,
				new Color(50,50,75), new Color(25,25,50), new Color(100,100,150));

		addButton(bm, -1, 0);
		addButton(bp, 1, 1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(10,10,25));
		searchPanel.add(panel_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(10,10,25));
		searchPanel.add(panel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Имя класса: ");
		lblNewLabel_1.setForeground(new Color(200,200,200));
		panel_1.add(lblNewLabel_1);
		
		className = new JTextField();
		panel_1.add(className);
		className.setColumns(10);
		className.setBackground(new Color(10,10,25));
		className.setForeground(new Color(200,200,200));
		className.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200), 1, false),
				new EmptyBorder(2, 3, 2, 3)));
		
		JLabel lblNewLabel = new JLabel("Страница: ");
		lblNewLabel.setForeground(new Color(200,200,200));
		panel.add(lblNewLabel);
		
		page = new JTextField();
		panel.add(page);
		page.setColumns(10);
		page.setBackground(new Color(10,10,25));
		page.setForeground(new Color(200,200,200));
		page.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200), 1, false),
				new EmptyBorder(2, 3, 2, 3)));
		panel.add(bm);
		panel.add(bp);
		
		
		JPanel sp = new JPanel();
		sp.setBackground(new Color(10,10,25));
		scrollPane.setViewportView(sp);
		sp.setLayout(new BoxLayout(sp, BoxLayout.Y_AXIS));
		
		find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddTiles(sp, "", page.getText(), className.getText());
			}
		});

		uploader.Init();
		AddTiles(sp, "", page.getText(), className.getText());

		keyListener(className, sp);
		keyListener(page, sp);
		
		// Поток для перемотки страниц
		Thread bt = new Thread() {
			
			@Override
			public void run() {
				int time = 0;
				int time2 = 0;
				boolean presss1 = true;
				boolean presss2 = true;
				while (true) {
					if(isPress[1]) {
						time++;
						if(time%25==0) {
							presss1 = true;
						}
						if (presss1) {
							presss1 = false;
							plus2 = (int) Math.floor(time/100) + 1;
							int p = 0;
							try {
								p = Integer.valueOf(page.getText());
							} catch (NumberFormatException e) {
							}
							p += 1*plus2;
							if(p<1) p = 1;
							if(p>717) p = 717;
							page.setText(p+"");
						}
					}else {
						time = 0;
						presss1 = true;
					}
					if(isPress[0]) {
						time2++;
						if(time2%25==0) {
							presss2 = true;
						}
						if (presss2) {
							presss2 = false;
							plus2 = (int) Math.floor(time2/100) + 1;
							int p = 0;
							try {
								p = Integer.valueOf(page.getText());
							} catch (NumberFormatException e) {
							}
							p -= 1*plus2;
							if(p<1) p = 1;
							if(p>717) p = 717;
							page.setText(p+"");
						}
					}else {
						time2 = 0;
						presss2 = true;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		bt.start();

		// Поток для всплывающего окна
		Thread hm = new Thread() {
			
			@Override
			public void run() {
				boolean show = true;
				try {
					byte[] all = Files.readAllBytes(Paths.get("YouTube.txt"));
					String string = new String(all);
					show = !string.equals("https://www.youtube.com/channel/UC1WDVmqsb2gQZH08G86cP8g");
				} catch (IOException e1) {
					show = true;
				}
				try {
					byte[] all = Files.readAllBytes(Paths.get("GitHub.txt"));
					String string = new String(all);
					show = !string.equals("https://github.com/Agzam4");
				} catch (IOException e1) {
					show = true;
				}
				if(show) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					JHelpMe.main();
				}
			}
		};
		hm.start();
	}
	
	int plus2 = 1;
	boolean isPress[] = {false,false};
	
	private void addButton(JButton b, int plus, int id) {

		b.setForeground(new Color(200,200,200));
		b.setBorder(new EmptyBorder(2,5,2,5));
		
		b.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				isPress[id] = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				isPress[id] = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	private void keyListener(JTextField tf, JPanel sp) {

		tf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					AddTiles(sp, "", page.getText(), className.getText());
				}
			}
		});
	}
	
	private Uploader uploader = new Uploader();
	
	private void resetСhaptersLenght() {
		for (int i = 0; i < chaptersLenght.length; i++) {
			chaptersLenght[i] = 0;
		}
	}
	
	private void AddTiles(JPanel sp, String chap, String page, String name) {
		int c = -1;
		try {
			c = Integer.valueOf(chap);
		} catch (NumberFormatException e) {
		}
		int p = -1;
		try {
			p = Integer.valueOf(page);
		} catch (NumberFormatException e) {
		}
		ArrayList<Integer> ids = uploader.getAllID(c, p, name);
		
		sp.removeAll();
		int chaps = -1;
		int chapLenght = 0;
		resetСhaptersLenght();
		
		// Создание "кнопок"
		for (int i = 0; i < ids.size(); i++) {
			if(chaps != uploader.chapter.get(ids.get(i))) {
				if(chaps > -1)
					chaptersLenght[chaps] = chapLenght;
				chapLenght = 0;
				chaps = uploader.chapter.get(ids.get(i));
				
				// Создание разделителья между главами
				JPanel mp = new JPanel();
				mp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 13));
				mp.setMinimumSize(new Dimension(Integer.MAX_VALUE, 13));
				mp.setLayout(new BoxLayout(mp, BoxLayout.X_AXIS));
				mp.setBackground(new Color(10,10,25));
				JLabel bg = new JLabel("Глава " + (chaps+1));
				bg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 13));
				bg.setMinimumSize(new Dimension(Integer.MAX_VALUE, 13));
				bg.setForeground(new Color(200,200,200));
				bg.setBorder(new CompoundBorder(new LineBorder(new Color(25,25,90), 1, false),
						new EmptyBorder(0, 0, 0, 0)));
				bg.setHorizontalAlignment(SwingConstants.CENTER);
				mp.add(bg);
				sp.add(mp);
			}
			chapLenght++;
			createTile(sp, ids.get(i), i);
		}
		if(chaps > -1)
			chaptersLenght[chaps] = chapLenght;
		sp.setVisible(false);
		sp.setVisible(true);
		sp.repaint();
	}
	
	private void createTile(JPanel sp, int id, int id2) {
		JPanel tile = new JPanel();
		tile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		tile.setBackground(id2%2 == 0 ? new Color(15,15,35):new Color(20,20,40));
		sp.add(tile);
		
		JLabel name = new JLabel(uploader.name.get(id) + " | " + (uploader.chapter.get(id)+1) +
				" >> " + uploader.page.get(id));
		name.setForeground(new Color(150,150,150));
		tile.add(name);
		name.setFocusable(true);

		setMouseListener(name, tile, id2);
		setMouseListener(tile, tile, id2);
	}
	
	private void setMouseListener(JComponent c, JPanel pp, int id) {
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pp.setBackground(id%2 == 0 ? new Color(15,15,35):new Color(20,20,40));
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				pp.setBackground(id%2 == 0 ? new Color(30,30,70):new Color(40,40,80));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Открыть редактор
				JCode.main(uploader.code.get(id), "Глава " + (uploader.chapter.get(id)+1) +
						" - стр " + uploader.page.get(id) + " | " + uploader.name.get(id), id, uploader);
			}
		});
	}
}
