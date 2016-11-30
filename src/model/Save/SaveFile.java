package model.Save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a SaveFile and writes a Save to that file Only allows one save
 * currently however this can be expanded
 * 
 * @author Jonathon Davis
 *
 */
public class SaveFile {

	private String savename;
	
	public SaveFile() {
		setSaveName();
	}
	
	public SaveFile(String savename) {
		this.savename = getFilePath(savename+".ser").toString();
	}
	
	public String getSavename() {
		return savename;
	}
	
	private void setSaveName() {
		int result = 1;
		
		while (true) {
			String savename = "save"+result+".ser";
			if (!(new File(getFilePath(savename).toString())).exists()) {
				this.savename = getFilePath(savename).toString();
				return;
			}
			result += 1;
		}	
	}
	
	public static ArrayList<String> getSavedFiles() {
		ArrayList<String> result = new ArrayList<>();
		int count = 1;
		while (true) {
			String savename = "save"+count+".ser";
			if (!(new File(getFilePath(savename).toString())).exists()) {
				break;
			} else {
				result.add(savename.substring(0,savename.length()-4));
			}
			count += 1;
		}
		return result;
	}

	private static Path getFilePath(String savename) {
		String workingDirectory = System.getProperty("user.dir");
		Path path = Paths.get(workingDirectory, "src", "saves", savename);
		return path;
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(savename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new Save());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void load(String savename) {
		Save s = null;
		try {
			FileInputStream fileIn = new FileInputStream(savename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			s = (Save) in.readObject();
			s.setState();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

}
