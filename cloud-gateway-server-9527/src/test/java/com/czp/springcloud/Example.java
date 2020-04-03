package com.czp.springcloud;

import java.io.*;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-15 12:08:07
 * @description :
 */

public class Example {

	public static void main(String[] args) {
//		readFileWithLine("D:\\A.txt", "D:\\B.txt");
		int[] nums = new int[]{1,2,34,346,123,546,21};
		exam47(nums);
	}

	private static void readFileWithLine(String readPath, String writePath) {
		File readFile = new File(readPath);
		File writeFile = new File(writePath);
		OutputStream writer;
		if (readFile.exists() && readFile.isFile()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
				writer = new FileOutputStream(writeFile);
				String tempString;
				while ((tempString = reader.readLine()) != null) {//BufferedReader有readLine()，可以实现按行读取
					writer.write((tempString + "\r\n").getBytes());
					System.out.println(tempString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void exam47(int[] nums) {
		int sum = 0;
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] % 2 == 0) {
					nums[i] = nums[i] / 2;
					sum++;
					flag = true;
				}
			}
		}
		System.out.println(sum);
	}

}