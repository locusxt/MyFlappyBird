package bird;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileGet类用于从文件中读取内容,以String形式返回.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 * 
 */
public class FileGet {
	/**
	 * 从指定文件名的文件中获取内容（String形式）。
	 * 
	 * @param fileName
	 *            指定的文件名
	 * @return 返回文件内容，String形式
	 */
	public static String getStr(String fileName) {
		char[] cbuf = new char[1000];
		int num = 1;
		try {
			FileReader fileReader = new FileReader(fileName);
			num = fileReader.read(cbuf);

			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("no help found");
		} catch (IOException ioe) {
			System.out.println("error occured!");
		}

		return new String(cbuf).substring(0, num - 1);
	}
}
