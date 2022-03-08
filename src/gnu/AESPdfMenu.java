package gnu;

import java.awt.Color;
import java.awt.Desktop;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import algorithms.AES256ImageEncryption;
import classes.MainFrameFunctions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class AESPdfMenu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */

	public Border borderLineForPanel = BorderFactory.createLineBorder(new Color(72, 89, 118), 1);
	public Color backgroundPanel = new Color(13, 22, 44);
	public Color foregroundFont = new Color(99, 116, 151);
	public static JTextArea textArea;
	public static JTextArea textAreaListOfImages;
	public static ArrayList<File> filesInDirectory = new ArrayList<File>();

	public static void main(String[] args) {
		try {
			AESMenu dialog = new AESMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AESPdfMenu() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 939, 531);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(5, 13, 32));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(5, 13, 32));
		panel.setBorder(borderLineForPanel);
		panel.setBounds(0, 0, 939, 531);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(883, 11, 46, 38);
		panel.add(lblNewLabel);

		JButton btnOpen = new JButton("IMPORT");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.opentxtFile(textArea);
			}
		});

		btnOpen.setToolTipText("");
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnOpen.setFocusable(false);
		btnOpen.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnOpen.setBackground(new Color(13, 22, 44));
		btnOpen.setBounds(470, 230, 118, 39);
		panel.add(btnOpen);

		JLabel lblEnterKey = new JLabel("Enter key :");
		lblEnterKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterKey.setForeground(Color.WHITE);
		lblEnterKey.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblEnterKey.setBounds(470, 22, 286, 38);
		panel.add(lblEnterKey);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(33, 71, 410, 387);
		panel.add(scrollPane_1);

		textAreaListOfImages = new JTextArea();
		scrollPane_1.setViewportView(textAreaListOfImages);
		textAreaListOfImages.setLineWrap(true);
		textAreaListOfImages.setForeground(new Color(99, 116, 151));
		textAreaListOfImages.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textAreaListOfImages.setBorder(borderLineForPanel);
		textAreaListOfImages.setBackground(new Color(13, 22, 44));

		JLabel lblLoadImage = new JLabel("Load pdf :");
		lblLoadImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoadImage.setForeground(Color.WHITE);
		lblLoadImage.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblLoadImage.setBounds(33, 22, 286, 38);
		panel.add(lblLoadImage);

		JButton btnOpen_1 = new JButton("LOAD");
		btnOpen_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(chooser);
				chooser.setVisible(true);

				String s = chooser.getSelectedFile().toString();

				textAreaListOfImages.append(s);

			}
		});
		btnOpen_1.setToolTipText("");
		btnOpen_1.setForeground(Color.WHITE);
		btnOpen_1.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnOpen_1.setFocusable(false);
		btnOpen_1.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnOpen_1.setBackground(new Color(13, 22, 44));
		btnOpen_1.setBounds(33, 469, 118, 39);
		panel.add(btnOpen_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 71, 435, 148);
		panel.add(scrollPane);
		scrollPane.setBorder(null);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textArea.setBorder(borderLineForPanel);
		textArea.setBackground(backgroundPanel);
		textArea.setForeground(foregroundFont);

		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrameFunctions.saveToTxtFileEncryptWithJTextArea(textArea.getText().trim(), btnSave, textArea);
			}
		});
		btnSave.setToolTipText("");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnSave.setFocusable(false);
		btnSave.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnSave.setBackground(new Color(13, 22, 44));
		btnSave.setBounds(787, 230, 118, 39);
		panel.add(btnSave);

		JButton btnEncypt = new JButton("ENCRYPT");
		btnEncypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (MainFrame.selectedButton.equals("AES256")) {

					
						try {
							encryptImageAES(textAreaListOfImages.getText().trim());
							JOptionPane.showMessageDialog(null, "Successful !");
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Failure !");
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Failure !");
							e1.printStackTrace();
						}
					

				}

			}
		});
		btnEncypt.setToolTipText("");
		btnEncypt.setForeground(Color.WHITE);
		btnEncypt.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnEncypt.setFocusable(false);
		btnEncypt.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnEncypt.setBackground(Color.RED);
		btnEncypt.setBounds(470, 360, 118, 39);
		panel.add(btnEncypt);

		JButton btnDecrypt = new JButton("DECRYPT");
		btnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (MainFrame.selectedButton.equals("AES256")) {
					
					
						try {
							decryptImageAES(textAreaListOfImages.getText().trim());
							JOptionPane.showMessageDialog(null, "Successful !");
							Desktop.getDesktop().open(new File(textAreaListOfImages.getText().trim()));
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Failure !");
							e1.printStackTrace();
						}
					
					
				}
			}
		});
		btnDecrypt.setToolTipText("");
		btnDecrypt.setForeground(Color.WHITE);
		btnDecrypt.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnDecrypt.setFocusable(false);
		btnDecrypt.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnDecrypt.setBackground(Color.GREEN);
		btnDecrypt.setBounds(470, 419, 118, 39);
		panel.add(btnDecrypt);

		JButton btnGenerate = new JButton("GENERATE");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				generateBase64Key16BitsInLenth();

			}
		});
		btnGenerate.setToolTipText("");
		btnGenerate.setForeground(Color.WHITE);
		btnGenerate.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnGenerate.setFocusable(false);
		btnGenerate.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnGenerate.setBackground(new Color(13, 22, 44));
		btnGenerate.setBounds(598, 230, 118, 39);
		panel.add(btnGenerate);
	}

	public static void generateBase64Key16BitsInLenth() {

		String originalInput = String.valueOf(geek_Password(24));
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		textArea.setText(encodedString);

	}

	static char[] geek_Password(int len) {

		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";

		String values = Capital_chars + Small_chars;

		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {

			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		return password;
	}

	public static void encryptImageAES(String filePath) throws NoSuchAlgorithmException, IOException {

		String keyString = textArea.getText().trim();

		byte[] decodedKey = Base64.getDecoder().decode(keyString);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		byte content[] = AES256ImageEncryption.getFile(filePath);
		System.out.println(content);
		byte encrypted[] = AES256ImageEncryption.encryptImageFile(originalKey, content);
		System.out.println(encrypted);

		AES256ImageEncryption.saveFile(encrypted, filePath);
		
		resetFields();
	}

	public static void decryptImageAES(String filePath) throws IOException {

		String keyString = textArea.getText().trim();

		byte[] decodedKey = Base64.getDecoder().decode(keyString);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		byte content[] = AES256ImageEncryption.getFile(filePath);
		System.out.println(content);
		byte[] decrypted = AES256ImageEncryption.decryptImageFile(originalKey, content);
		System.out.println(decrypted);

		AES256ImageEncryption.saveFile(decrypted, filePath);
		
		resetFields();
	}

	
	public static void resetFields() {
		filesInDirectory.clear();
		textAreaListOfImages.setText("");
		textArea.setText("");
	}

}
