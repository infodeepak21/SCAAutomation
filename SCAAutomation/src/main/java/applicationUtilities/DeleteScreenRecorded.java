package applicationUtilities;

import java.io.File;

public class DeleteScreenRecorded {
	public static File fileDirectory; 
	public static File [] files;
	
	public static void deleteScreenRecorded(String filePathName) {
		fileDirectory = new File(filePathName);
		files = fileDirectory.listFiles();
		for(File file : files) {
			file.delete();
		}
	}
}
