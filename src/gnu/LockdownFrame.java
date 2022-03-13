package gnu;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JTextArea;


public class LockdownFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public Border borderLineForPanel = BorderFactory.createLineBorder(new Color(72, 89, 118), 1);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LockdownFrame dialog = new LockdownFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LockdownFrame() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 939, 531);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(5, 13, 32));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 939, 531);
		panel.setBackground(new Color(5, 13, 32));
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel.setLayout(null);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(910, 0, 19, 43);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 28, 434, 441);
		panel.add(scrollPane);
		
		JList<Object> list = new JList<Object>();
		scrollPane.setViewportView(list);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setToolTipText("");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnDelete.setFocusable(false);
		btnDelete.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(344, 480, 118, 39);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setToolTipText("");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(28, 480, 118, 39);
		panel.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("<html>*** with add button you add all folder that you want to be part of lockdown protocol (also all the subfolders and files in them will be encrypted)</html>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(482, 30, 386, 149);
		panel.add(lblNewLabel_1);
		
		JLabel lblEnterKey = new JLabel("Enter key :");
		lblEnterKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterKey.setForeground(Color.WHITE);
		lblEnterKey.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblEnterKey.setBounds(482, 175, 286, 38);
		panel.add(lblEnterKey);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(new Color(99, 116, 151));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textArea.setBorder(borderLineForPanel);
		textArea.setBackground(new Color(13, 22, 44));
		textArea.setBounds(482, 224, 435, 148);
		panel.add(textArea);
		
		JButton btnOpen = new JButton("IMPORT");
		btnOpen.setToolTipText("");
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnOpen.setFocusable(false);
		btnOpen.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnOpen.setBackground(new Color(13, 22, 44));
		btnOpen.setBounds(482, 383, 118, 39);
		panel.add(btnOpen);
		
		JButton btnGenerate = new JButton("GENERATE");
		btnGenerate.setToolTipText("");
		btnGenerate.setForeground(Color.WHITE);
		btnGenerate.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnGenerate.setFocusable(false);
		btnGenerate.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnGenerate.setBackground(new Color(13, 22, 44));
		btnGenerate.setBounds(610, 383, 118, 39);
		panel.add(btnGenerate);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setToolTipText("");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnSave.setFocusable(false);
		btnSave.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnSave.setBackground(new Color(13, 22, 44));
		btnSave.setBounds(799, 383, 118, 39);
		panel.add(btnSave);
		
		JButton btnEncypt = new JButton("ENCRYPT");
		btnEncypt.setToolTipText("");
		btnEncypt.setForeground(Color.WHITE);
		btnEncypt.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnEncypt.setFocusable(false);
		btnEncypt.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnEncypt.setBackground(Color.RED);
		btnEncypt.setBounds(568, 457, 118, 39);
		panel.add(btnEncypt);
		
		JButton btnDecrypt = new JButton("DECRYPT");
		btnDecrypt.setToolTipText("");
		btnDecrypt.setForeground(Color.WHITE);
		btnDecrypt.setFont(new Font("Monospaced", Font.BOLD, 17));
		btnDecrypt.setFocusable(false);
		btnDecrypt.setBorder(new LineBorder(new Color(72, 89, 118), 1, true));
		btnDecrypt.setBackground(Color.GREEN);
		btnDecrypt.setBounds(716, 457, 118, 39);
		panel.add(btnDecrypt);
		
	}
}
