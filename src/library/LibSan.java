package library;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class LibSan {
	
	 static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
 

	
	public static int getScreenWidth() {
		 int width = (int)size.getWidth();
		return width;
		
	}
	public static int getScreenHeight() {

		int height = (int)size.getHeight();
		return height;
	}
	public static void readFromAnyTextFileWithJFileChooserForJTextArea(JTextArea printTo) {

		int numberOfLines = 0;
	
		
		JFileChooser chooser=new JFileChooser();
		chooser.showOpenDialog(chooser);
		chooser.setVisible(true);
		
		File file = new File(chooser.getSelectedFile().toString());
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				
				if(numberOfLines<=6500) {
					String data = scanner.nextLine();
					
					printTo.append(data+"\n");
					numberOfLines++;
				}
				else {
					JOptionPane.showMessageDialog(null, "Txt file loading size is 6500 lines !");
					break;
				}

				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
}
