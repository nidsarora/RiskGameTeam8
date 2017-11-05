package risk.helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * This class is used to get the path of the resources used in the project.
 */
public final class Utility {

	public static String getImagePath(String imageNameWithExtention) {
		return "/risk/resources/images/" + imageNameWithExtention;
	}

	public static String getMapPath(String mapNameWithExtension) {
		return "/risk/resources/map/" + mapNameWithExtension;
	}

	public static String getMapPathforFile(String mapNameWithExtension) {
		return "src/risk/resources/map/" + mapNameWithExtension;
	}

	public static String getPathforFile(String fileNameWithExtension) {
		return "src/risk/resources/" + fileNameWithExtension;
	}

	public static void writeLog(String text) {
		try (FileWriter fw = new FileWriter("log\\log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(new Date().toString() + ":" + text);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
