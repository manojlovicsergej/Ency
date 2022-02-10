package library;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
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

		
	
		
		JFileChooser chooser=new JFileChooser();
		chooser.showOpenDialog(chooser);
		chooser.setVisible(true);
		
		File file = new File(chooser.getSelectedFile().toString());
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				
				printTo.append(data);
				 
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
}
