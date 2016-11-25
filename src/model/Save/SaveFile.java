package model.Save;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a SaveFile and writes a Save to that file
 * Only allows one save currently however this can be expanded
 * @author Jonathon Davis
 *
 */
public class SaveFile {
	
	public static void save(){
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("save.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(new Save());
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void load(){
		Save s = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("save.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         s = (Save) in.readObject();
	         s.setState();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c) {
	         c.printStackTrace();
	      }
	}

}
