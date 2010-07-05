package br.edu.ufcg.sig.persistence.geoserver.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	private static File createFile(String filePath) throws IOException {
		File file = new File(filePath);
		
		if (file.exists()) {
			file.delete();
		}
		
		file.createNewFile();
		
		return file;
	}
	
	public static File writeXMLToFile(String filePath, String xml) throws IOException {
		File file = createFile(filePath);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write(xml);
		writer.close();
		
		return file;
	}
}
