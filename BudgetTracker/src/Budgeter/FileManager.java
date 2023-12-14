package Budgeter;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class FileManager {
	 private String filepath;
	 
	 public FileManager(String fp) {
		 filepath = fp;
	 }
	 
	 public boolean save(String s) {
		 try {
			 File file = new File(filepath);
			 file.createNewFile();
			 FileWriter w = new FileWriter(file, false);
			 w.write(s);
			 w.close();
		 } catch (Exception e) {
			 System.err.println(e);
			 return false;
		 }
		 return true;
	 }
	 
	 public String load() {
		 String s = "";
		 try {
			FileReader r = new FileReader(filepath);
			while(r.ready()) {
				s+=(char)r.read();
			}
			r.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		return s;
	 }
}
