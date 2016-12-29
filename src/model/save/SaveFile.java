package model.save;

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
 * Represents a SaveFile and writes a Save to that file
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
		this.savename = getFilePath(savename).toString();
	}

	public String getSavename() {
		return savename;
	}

	private void setSaveName() {
		int result = 1;

		while (true) {
			String savename = "save" + result + ".ser";
			if (!(new File(getFilePath(savename).toString())).exists()) {
				this.savename = getFilePath(savename).toString();
				return;
			}
			result += 1;
		}
	}

	public static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> result = new ArrayList<>();

		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				result.add(fileEntry.getName());
			}
		}
		return result;
	}

	public static ArrayList<String> getSavedFiles() {
		String workingDirectory = System.getProperty("user.dir");
		return listFilesForFolder(Paths.get(workingDirectory, "src", "saves")
				.toFile());
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
			FileInputStream fileIn = new FileInputStream(getFilePath(savename)
					.toString());
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
