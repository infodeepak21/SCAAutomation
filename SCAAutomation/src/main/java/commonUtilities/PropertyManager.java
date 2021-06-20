package commonUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyManager {
	public static Properties prop;

	/*
	 * This method helps to load the properties from the configuration.properties
	 * file
	 * 
	 * @param the file location of "configuration.properties"
	 */
	public static void loadConfig() {
		File file = new File("./configuration.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}
}