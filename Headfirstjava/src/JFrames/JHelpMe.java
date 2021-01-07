package JFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import Work.Dragged;
import Work.WindowsButtons;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JHelpMe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JHelpMe frame = new JHelpMe();
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
	public JHelpMe() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
		}
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Insets i = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		setBounds(d.width - 325 - i.right, d.height - 150 - i.bottom, 300, 125);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15,15,35));
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200), 1, false),
				new EmptyBorder(5, 5, 5, 5)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel x = new JLabel("\u00D7");
		x.setForeground(new Color(200,200,200));
		x.setBounds(275, 0, 35, 30);
		x.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(x);
		
		x.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				x.setForeground(new Color(250,100,100));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				x.setForeground(new Color(200,200,200));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				x.setForeground(new Color(250,200,200));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 300, 125);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u041A\u0430\u0436\u0435\u0442\u0441\u044F \u0442\u044B \u043F\u0440\u043E\u0441\u0442\u043E \u044D\u0442\u043E \u0437\u0430\u043A\u0440\u043E\u0435\u0448\u044C");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setOpaque(false);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new EmptyBorder(0, 10, 0, 5));
		textPane.setForeground(new Color(200,200,200));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPane.setEditable(false);
		textPane.setOpaque(false);
		textPane.setSelectedTextColor(new Color(200,200,200));
		textPane.setSelectionColor(new Color(40,40,80));
		textPane.setFocusable(false);
		textPane.setText("\u041D\u0430 \u044D\u0442\u043E\u0442 \u043F\u0440\u043E\u0435\u043A\u0442 \u044F \u043F\u043E\u0442\u0440\u0430\u0442\u0438\u043B \u043C\u043D\u043E\u0433\u043E \u0441\u0438\u043B \u0438 \u0432\u0440\u0435\u043C\u0435\u043D\u0438, \u043D\u043E \u0442\u044B \u043C\u043E\u0436\u0435\u0448\u044C \u043F\u043E\u0434\u0434\u0435\u0440\u0436\u0430\u0442\u044C \u043C\u0435\u043D\u044F, \u0432 YouTube \u0438 GitHub");
		panel_2.add(textPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);
		
		JButton yt = new JButton("YouTube");
		panel_3.add(yt);
		
		JButton gh = new JButton("GitHub");
		panel_3.add(gh);

		WindowsButtons.ConvertToWindowsButton(yt,
				new Color(255,50,50), new Color(255,100,100), new Color(200,50,50));
		WindowsButtons.ConvertToWindowsButton(gh,
				new Color(100,100,255), new Color(200,200,255), new Color(100,100,200));
		Url(yt, "https://www.youtube.com/channel/UC1WDVmqsb2gQZH08G86cP8g", "YouTube");
		Url(gh, "https://github.com/Agzam4", "GitHub");
		
		Dragged.SetJFrame(this, false);
		Dragged.SetJComponent(textPane, this, false);
		Dragged.SetJComponent(lblNewLabel_1, this, false);
	}
	
	private void Url(JButton b, String url, String file) {
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(url));
					try (FileWriter writer = new FileWriter(new File(file + ".txt"))) {
						writer.write(url);
						writer.flush();
					} catch (IOException ex) {
					}
				} catch (IOException | URISyntaxException e1) {
				}
			}
		});
	}
}
