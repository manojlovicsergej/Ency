package gnu;



import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import classes.MainFrameFunctions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AESMenu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	
	public Border borderLineForPanel = BorderFactory.createLineBorder(new Color(72,89,118), 1);
	public Color backgroundPanel = new Color(13,22,44);
	public Color foregroundFont = new Color(99,116,151);
	
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
	public AESMenu() {
		setResizable(false);
		setBounds(100, 100, 483, 520);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(5,13,32));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(5,13,32));
		panel.setBorder(borderLineForPanel);
		panel.setBounds(0, 0, 483, 520);
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
		lblNewLabel.setBounds(427, 11, 46, 38);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 107, 410, 339);
		panel_1.setBackground(backgroundPanel);
		panel_1.setBorder(borderLineForPanel);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 410, 339);
		scrollPane.setBorder(null);
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textArea.setBorder(borderLineForPanel);
		textArea.setBackground(backgroundPanel);
		textArea.setForeground(foregroundFont);
		
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
		btnOpen.setBounds(36, 457, 118, 39);
		panel.add(btnOpen);
		
		JLabel lblEnterKey = new JLabel("Enter key :");
		lblEnterKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterKey.setForeground(Color.WHITE);
		lblEnterKey.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblEnterKey.setBounds(36, 64, 286, 38);
		panel.add(lblEnterKey);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(MainFrame.crypted==true && MainFrame.txtFolderCrypted == false) {
					MainFrameFunctions.encryptTXTAES(MainFrame.textAreaEncrypt.getText().trim(), MainFrame.textAreaDecrypt,textArea.getText().trim());
				}
				else if(MainFrame.crypted==false && MainFrame.txtFolderCrypted == false){
					MainFrameFunctions.decryptTXTAES(MainFrame.textAreaDecrypt.getText().trim(), MainFrame.textAreaEncrypt,textArea.getText().trim());
				}
				
				if(MainFrame.crypted==true && MainFrame.txtFolderCrypted == true) {
					MainFrameFunctions.encryptTXTFolderAES();
					
					JOptionPane.showMessageDialog(null,"Files have been encrypted !");
					MainFrame.txtFolderCrypted = false;
				}
				else if(MainFrame.crypted==false && MainFrame.txtFolderCrypted == true) {
					MainFrameFunctions.decryptTXTFolderAES();
					
					JOptionPane.showMessageDialog(null,"Files have been decrypted !");
					MainFrame.txtFolderCrypted = false;
				}

				dispose();
			}
		});
		btnConfirm.setToolTipText("");
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnConfirm.setFocusable(false);
		btnConfirm.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnConfirm.setBackground(new Color(13, 22, 44));
		btnConfirm.setBounds(328, 457, 118, 39);
		panel.add(btnConfirm);
	}
}
