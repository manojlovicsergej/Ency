package classes;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import algorithms.AES256;
import gnu.AESImageMenu;
import gnu.AESMenu;
import gnu.AESPdfMenu;
import gnu.MainFrame;
import library.LibSan;

public class MainFrameFunctions {
	
	public static void opentxtFile(JTextArea s1) {
		LibSan.readFromAnyTextFileWithJFileChooserForJTextArea(s1);
	}
	
	/////Txt encryption type for specific file or folder of txt files
	public static void encryptTXTAES(String s1 , JTextArea t1 , String key) {


		String ecryptedText = AES256.encrypt(s1, key);
		t1.setText(ecryptedText);
		
	}
	public static void encryptTXTFolderAES() {
		
	}
	public static void decryptTXTAES(String s1 , JTextArea t1 , String key) {
		
		String decryptedText = AES256.decrypt(s1, key);
		t1.setText(decryptedText);
		
	}
	public static void decryptTXTFolderAES() {
		
	}
	/////
	
	
	
	public static void saveToTxtFileEncryptWithJTextArea(String s,JButton b,JTextArea t) {
		JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showSaveDialog(b);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getParentFile(), file.getName() + ".txt");
                }
                try {
                   t.write(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
		
	}

	public static void generateBase64Key16BitsInLenth(JTextArea textArea) {

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
	
	//Txt encryption type chooser
	public static void checkTxtEncryptionType() {

		
		if(MainFrame.selectedButton.equals("")) {
			JOptionPane.showMessageDialog(null, "Choose type of encryption !");
		}
		else {
			
			if(MainFrame.selectedButton.equals("AES256")) {
				MainFrame.crypted = true;
				AESMenu as = new AESMenu();
				as.setVisible(true);
				
			}
			if(MainFrame.selectedButton.equals("OTP")) {
				JOptionPane.showMessageDialog(null, "IN PROGRESS !");
			}
			
			
			
		}
		
		
	}
	public static void checkTxtDecryptionType() {
		
		if(MainFrame.selectedButton.equals("")) {
			JOptionPane.showMessageDialog(null, "Choose type of decryption !");
		}
		else {
			
			if(MainFrame.selectedButton.equals("AES256")) {
				MainFrame.crypted = false;
				AESMenu as = new AESMenu();
				as.setVisible(true);
				
			}
			if(MainFrame.selectedButton.equals("OTP")) {
				JOptionPane.showMessageDialog(null, "IN PROGRESS !");
			}
		}
	}
	
	
	//Pdf and image encryption type chooser
	public static void checkPdfEncryptionType() {
		if(MainFrame.selectedButton.equals("")) {
			JOptionPane.showMessageDialog(null, "Choose type of encryption !");
		}
		else {
			
			if(MainFrame.selectedButton.equals("AES256")) {
				
				AESPdfMenu aespdfmenu = new AESPdfMenu();
				aespdfmenu.setVisible(true);
				
			}
			if(MainFrame.selectedButton.equals("OTP")) {
				JOptionPane.showMessageDialog(null, "There is no option for OTP pdf encryption !");
			}
		}
	}

	public static void checkImgEncryptionType() {
		if(MainFrame.selectedButton.equals("")) {
			JOptionPane.showMessageDialog(null, "Choose type of encryption !");
		}
		else {
			
			if(MainFrame.selectedButton.equals("AES256")) {
				
				AESImageMenu aesimagemenu = new AESImageMenu();
				aesimagemenu.setVisible(true);
				
			}
			if(MainFrame.selectedButton.equals("OTP")) {
				JOptionPane.showMessageDialog(null, "There is no option for OTP pdf encryption !");
			}
		}
		
	}
	
	
	
}
