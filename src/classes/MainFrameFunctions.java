package classes;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import algorithms.AES256;
import library.LibSan;

public class MainFrameFunctions {
	
	public static void opentxtFile(JTextArea s1) {
		LibSan.readFromAnyTextFileWithJFileChooserForJTextArea(s1);
	}
	
	public static void encryptTXTAES(String s1 , JTextArea t1 , String key) {

		
		String ecryptedText = AES256.encrypt(s1, key);
		t1.setText(ecryptedText);
		
	}
	
	public static void decryptTXTAES(String s1 , JTextArea t1 , String key) {
		
		String decryptedText = AES256.decrypt(s1, key);
		t1.setText(decryptedText);
		
	}
	
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

	
}
