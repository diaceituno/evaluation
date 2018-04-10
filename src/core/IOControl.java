package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOControl {
	
	private static final String ENCODING = "UTF-8";

	public String load(String path) throws IOException {
		
		StringBuilder stringBuilder = new StringBuilder();
		File file = new File(path);
		FileInputStream fiStream = new FileInputStream(file);
		InputStreamReader isReader = new InputStreamReader(fiStream, ENCODING);
		BufferedReader bufferedReader = new BufferedReader(isReader);
		
		String line = bufferedReader.readLine();
		while(line != null) {
			stringBuilder.append(line);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		return stringBuilder.toString();
	}
	
	public void save(String path, String content, boolean append) throws IOException {
		
		File file = new File(path);
		FileOutputStream foStream = new FileOutputStream(file, append);
		OutputStreamWriter osWriter = new OutputStreamWriter(foStream, ENCODING);
		osWriter.write(content);
		osWriter.close();
	}
}
