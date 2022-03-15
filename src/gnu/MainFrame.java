package gnu;

import java.awt.EventQueue;
import javax.swing.JFrame;
import library.LibSan;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import classes.MainFrameFunctions;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class MainFrame {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double width = screenSize.getWidth();
	static double height = screenSize.getHeight();
	
	public static ArrayList<File> filesInDirectory = new ArrayList<File>();
	private JFrame frame;
	private double percentSize=0.9;
	public Border borderLineForPanel = BorderFactory.createLineBorder(new Color(72,89,118), 1);
	public Border borderLineForButton = BorderFactory.createLineBorder(new Color(72,89,118), 1);
	public static JTextArea textAreaEncrypt;
	public static JTextArea textAreaDecrypt;
	public Color backgroundPanel = new Color(13,22,44);
	public Color foregroundFont = new Color(99,116,151);
	public static BufferedImage img;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton buttonAES;
	
	//Global variables used to show status of certain activities
	public static String selectedButton="";
	public static boolean crypted = false;
	public static boolean txtFolderCrypted = false;
	///
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(width>=1920 && height<=1080) {
						MainFrame window = new MainFrame();
						window.frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Your resolution has to be at least 1920x1080px !");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(5,13,32));

		
		JPanel panelHead = new JPanel();
		panelHead.setBackground(backgroundPanel);
		panelHead.setBorder(borderLineForPanel);
		
		JPanel panelEncrypt = new JPanel();
		panelEncrypt.setBackground(backgroundPanel);
		panelEncrypt.setBorder(borderLineForPanel);
		
		JPanel panelDecrypt = new JPanel();
		panelDecrypt.setBackground(backgroundPanel);
		panelDecrypt.setBorder(borderLineForPanel);
		
		buttonAES = new JButton("AES256");
		buttonAES.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseThatButton(buttonAES);
				selectedButton="AES256";
			}
		});
		buttonGroup.add(buttonAES);
		buttonAES.setToolTipText("Advanced Encryption Standard algorithm");
		buttonAES.setForeground(Color.WHITE);
		buttonAES.setFocusable(false);
		buttonAES.setFont(new Font("Monospaced", Font.BOLD, 17));
		buttonAES.setBackground(backgroundPanel);
		buttonAES.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		
		JLabel labelEncrypt = new JLabel("");
		labelEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.checkTxtEncryptionType();
			}
		});
		labelEncrypt.setIcon(new ImageIcon(new ImageIcon("images/icons/locked.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
		labelEncrypt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		labelEncrypt.setForeground(Color.WHITE);
		labelEncrypt.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel labelDecrypt = new JLabel("");
		labelDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.checkTxtDecryptionType();
			}
		});
		labelDecrypt.setIcon(new ImageIcon(new ImageIcon("images/icons/unlocked.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
		labelDecrypt.setHorizontalAlignment(SwingConstants.CENTER);
		labelDecrypt.setForeground(Color.WHITE);
		labelDecrypt.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JLabel labelClose = new JLabel("");
		labelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTxtFields();
				resetButtonsLook();
			}

		});
		labelClose.setIcon(new ImageIcon(new ImageIcon("images/icons/close.png").getImage().getScaledInstance(27,27, Image.SCALE_DEFAULT)));
		labelClose.setHorizontalAlignment(SwingConstants.CENTER);
		labelClose.setForeground(Color.WHITE);
		labelClose.setFont(new Font("Monospaced", Font.PLAIN, 17));
		
		JButton btnImgEncryption = new JButton("  IMG ENCRYPTION  ");
		btnImgEncryption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.checkImgEncryptionType();
			}
		});
		btnImgEncryption.setToolTipText("Image encryption");
		btnImgEncryption.setForeground(Color.WHITE);
		btnImgEncryption.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnImgEncryption.setFocusable(false);
		btnImgEncryption.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnImgEncryption.setBackground(new Color(13, 22, 44));
		
		JButton btnPdfEncrption = new JButton("PDF ENCRYPTION");
		btnPdfEncrption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.checkPdfEncryptionType();
			}
		});
		btnPdfEncrption.setToolTipText("PDF encryption");
		btnPdfEncrption.setForeground(Color.WHITE);
		btnPdfEncrption.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnPdfEncrption.setFocusable(false);
		btnPdfEncrption.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnPdfEncrption.setBackground(new Color(13, 22, 44));
		
		JButton btnLockdown = new JButton("<html>LOCKDOWN</html>");
		btnLockdown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LockdownFrame lf = new LockdownFrame();
				lf.setVisible(true);
			}
		});
		btnLockdown.setToolTipText("COMPLETE LOCKDOWN");
		btnLockdown.setForeground(Color.WHITE);
		btnLockdown.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnLockdown.setFocusable(false);
		btnLockdown.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnLockdown.setBackground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(buttonAES, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(592)
							.addComponent(btnLockdown, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addGap(355)
							.addComponent(btnPdfEncrption, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnImgEncryption, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelEncrypt, GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelEncrypt)
								.addComponent(labelDecrypt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelClose, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(panelDecrypt, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
						.addComponent(panelHead, GroupLayout.DEFAULT_SIZE, 1633, Short.MAX_VALUE))
					.addGap(52))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(panelHead, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonAES, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(btnImgEncryption, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(btnPdfEncrption, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLockdown, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(panelDecrypt, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
							.addComponent(panelEncrypt, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(224)
							.addComponent(labelEncrypt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelDecrypt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
							.addComponent(labelClose, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addGap(42))
		);
		
		JButton btnSave_1 = new JButton("SAVE");
		btnSave_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.saveToTxtFileEncryptWithJTextArea(textAreaDecrypt.getText().trim(), btnSave_1, textAreaDecrypt);
			}
		});
		btnSave_1.setToolTipText("");
		btnSave_1.setFocusable(false);
		btnSave_1.setForeground(Color.WHITE);
		btnSave_1.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnSave_1.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnSave_1.setBackground(new Color(13, 22, 44));
		
		JButton btnOpen_1 = new JButton("OPEN");
		btnOpen_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.opentxtFile(textAreaDecrypt);
			}
		});
		btnOpen_1.setToolTipText("");
		btnOpen_1.setFocusable(false);
		btnOpen_1.setForeground(Color.WHITE);
		btnOpen_1.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnOpen_1.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnOpen_1.setBackground(new Color(13, 22, 44));
		
		JScrollPane scrollPaneDecrypt = new JScrollPane();
		scrollPaneDecrypt.setBorder(null);
		
		JButton btnTxtFolderEncryption_1 = new JButton("TXT FOLDER DECRYPTION");
		btnTxtFolderEncryption_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFolderEncryption(textAreaDecrypt);
			}
		});
		btnTxtFolderEncryption_1.setToolTipText("");
		btnTxtFolderEncryption_1.setForeground(Color.WHITE);
		btnTxtFolderEncryption_1.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnTxtFolderEncryption_1.setFocusable(false);
		btnTxtFolderEncryption_1.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnTxtFolderEncryption_1.setBackground(new Color(13, 22, 44));
		GroupLayout gl_panelDecrypt = new GroupLayout(panelDecrypt);
		gl_panelDecrypt.setHorizontalGroup(
			gl_panelDecrypt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDecrypt.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneDecrypt)
					.addContainerGap())
				.addGroup(gl_panelDecrypt.createSequentialGroup()
					.addGap(32)
					.addComponent(btnSave_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(btnOpen_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
					.addComponent(btnTxtFolderEncryption_1, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		gl_panelDecrypt.setVerticalGroup(
			gl_panelDecrypt.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDecrypt.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneDecrypt, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelDecrypt.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpen_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTxtFolderEncryption_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(23))
		);
		
		textAreaDecrypt = new JTextArea();
		textAreaDecrypt.setFont(new Font("Monospaced", Font.PLAIN, 19));
		textAreaDecrypt.setLineWrap(true);
		textAreaDecrypt.setBorder(null);
		textAreaDecrypt.setForeground(foregroundFont);
		textAreaDecrypt.setBackground(backgroundPanel);
		scrollPaneDecrypt.setViewportView(textAreaDecrypt);
		panelDecrypt.setLayout(gl_panelDecrypt);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.saveToTxtFileEncryptWithJTextArea(textAreaEncrypt.getText().trim(), btnSave, textAreaEncrypt);
			}
		});
		btnSave.setFocusable(false);
		btnSave.setToolTipText("");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnSave.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnSave.setBackground(new Color(13, 22, 44));
		
		JButton btnOpen = new JButton("OPEN");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.opentxtFile(textAreaEncrypt);
			}
		});
		btnOpen.setFocusable(false);
		btnOpen.setToolTipText("");
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnOpen.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnOpen.setBackground(new Color(13, 22, 44));
		
		JScrollPane scrollPaneEncrypt = new JScrollPane();
		scrollPaneEncrypt.setBorder(null);
		
		JButton btnTxtFolderEncryption = new JButton("TXT FOLDER ENCRYPTION");
		btnTxtFolderEncryption.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFolderEncryption(textAreaEncrypt);
			}
		});
		btnTxtFolderEncryption.setToolTipText("");
		btnTxtFolderEncryption.setForeground(Color.WHITE);
		btnTxtFolderEncryption.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnTxtFolderEncryption.setFocusable(false);
		btnTxtFolderEncryption.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnTxtFolderEncryption.setBackground(new Color(13, 22, 44));
		GroupLayout gl_panelEncrypt = new GroupLayout(panelEncrypt);
		gl_panelEncrypt.setHorizontalGroup(
			gl_panelEncrypt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEncrypt.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneEncrypt)
					.addContainerGap())
				.addGroup(gl_panelEncrypt.createSequentialGroup()
					.addGap(27)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
					.addComponent(btnTxtFolderEncryption, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_panelEncrypt.setVerticalGroup(
			gl_panelEncrypt.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelEncrypt.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneEncrypt, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelEncrypt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEncrypt.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnTxtFolderEncryption, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		
		textAreaEncrypt = new JTextArea();
		textAreaEncrypt.setLineWrap(true);
		textAreaEncrypt.setFont(new Font("Monospaced", Font.PLAIN, 19));
		textAreaEncrypt.setForeground(foregroundFont);
		textAreaEncrypt.setBackground(backgroundPanel);
		textAreaEncrypt.setBorder(null);
		scrollPaneEncrypt.setViewportView(textAreaEncrypt);
		panelEncrypt.setLayout(gl_panelEncrypt);
		
		JLabel lblNewLabel = new JLabel("Welcome to Ency !");
		lblNewLabel.setForeground(foregroundFont);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 24));
		
		JLabel lblLetsBeginThe = new JLabel("Let\u2019s begin the encryptionization.");
		lblLetsBeginThe.setForeground(foregroundFont);
		lblLetsBeginThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetsBeginThe.setFont(new Font("Monospaced", Font.PLAIN, 24));
		GroupLayout gl_panelHead = new GroupLayout(panelHead);
		gl_panelHead.setHorizontalGroup(
			gl_panelHead.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHead.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHead.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1595, Short.MAX_VALUE)
						.addComponent(lblLetsBeginThe, GroupLayout.DEFAULT_SIZE, 1595, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelHead.setVerticalGroup(
			gl_panelHead.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHead.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLetsBeginThe, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addGap(4))
		);
		panelHead.setLayout(gl_panelHead);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setSize((int)(LibSan.getScreenWidth()*percentSize),(int)(LibSan.getScreenHeight()*percentSize));
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icons/logo.png"));
		
	}
	
	public void chooseThatButton(JButton b) {

		buttonAES.setForeground(Color.WHITE);
		buttonAES.setBackground(backgroundPanel);

		b.setForeground(foregroundFont);
		b.setBackground(Color.WHITE);
		
	}

	public void resetButtonsLook() {
		// TODO Auto-generated method stub
		buttonAES.setForeground(Color.WHITE);
		buttonAES.setBackground(backgroundPanel);
		
	}
	
	public void resetTxtFields() {
		textAreaEncrypt.setText("");
		textAreaDecrypt.setText("");
		selectedButton="";
	}
	
	public static void txtFolderEncryption(JTextArea textArea) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(chooser);
		chooser.setVisible(true);
		
		File[] files =chooser.getCurrentDirectory().listFiles();
		
		for(File file : files){
			if(file.getAbsoluteFile().toString().contains(".txt")) {
				filesInDirectory.add(file);
			}
		}

		for (File file : filesInDirectory) {
			textArea.append(file.getAbsolutePath().toString() + "\n");
		}
		
		txtFolderCrypted = true;
		
	}
}
