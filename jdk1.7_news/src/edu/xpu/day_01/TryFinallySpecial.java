package edu.xpu.day_01;

public class TryFinallySpecial {
	public static void main(String[] args) {
		try {
			System.exit(0);
		}finally {
			System.out.println("finally...");
		}
	}
}
