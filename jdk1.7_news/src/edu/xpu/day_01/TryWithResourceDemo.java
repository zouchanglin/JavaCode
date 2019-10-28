package edu.xpu.day_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TryWithResourceDemo {
	public void usuallyTry(String[] args) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File("filename"));
			//TODO ...
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void tryWithResource(String[] args) {
		try(FileInputStream fileInputStream = new FileInputStream(new File("filename"))) {
			//TODO ...
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
