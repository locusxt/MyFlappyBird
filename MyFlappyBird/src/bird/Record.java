package bird;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Info记录从记录文件中读取到的信息.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
class Info {
	/**
	 * score是读取到的分数数组.
	 */
	public int[] score;

	/**
	 * name是读取到的名字数组.
	 */
	public String[] name;

	/**
	 * 以score和name进行构造.
	 * 
	 * @param i
	 *            score
	 * @param s
	 *            name
	 */
	Info(int[] i, String[] s) {
		score = i;
		name = s;
	}
}

/**
 * Record类的功能是读取record.txt中的信息,以及对record.txt进行一些操作.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Record {
	/**
	 * 读取record.txt中的信息,以Info的形式返回
	 * 
	 * @return 读取到的Info
	 */
	public static Info getRecord() {
		int[] allRecord = new int[15];
		String[] name = new String[15];
		int recordNum = 0;
		File file = new File("record.txt");

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				++recordNum;
				allRecord[recordNum] = scanner.nextInt();
				name[recordNum] = scanner.next();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		allRecord[0] = recordNum;
		return new Info(allRecord, name);
	}

	/**
	 * 判断一个分数能不能加入到记录文件中去.
	 * 当分数能排前9,就可以加入.
	 * 
	 * @param s 分数的值.
	 * @return 如果能加入就返回true,否则返回false.
	 */
	public static boolean isAddable(int s) {
		boolean isAddable = false;

		Info info = getRecord();
		int[] score = info.score;
		int num = score[0];

		int cur = 0;
		for (int i = 1; i <= num; ++i) {
			if (s >= score[i]) {
				cur = i;
				break;
			}
		}
		if (cur != 0) {
			isAddable = true;
		} else {
			if (num < 9) {
				isAddable = true;
			}
		}
		return isAddable;
	}

	/**
	 * 向记录文件中加入一条记录.
	 * 在记录文件中的记录多余9条时,会删除掉最后的一条记录.
	 * 
	 * @param s 要加入的记录的分数score
	 * @param n 要加入的记录的名字name
	 */
	public static void addRecord(int s, String n) {

		Info info = getRecord();
		int[] score = info.score;
		String[] name = info.name;
		int num = score[0];

		int cur = 0;
		for (int i = 1; i <= num; ++i) {
			if (s >= score[i]) {
				cur = i;
				break;
			}
		}
		if (cur != 0) {
			if (num + 1 <= 9)
				++num;
			for (int i = num; i > cur; --i) {
				score[i] = score[i - 1];
				name[i] = name[i - 1];
			}
			score[cur] = s;
			name[cur] = n;
		} else {
			if (num < 9) {
				++num;
				score[num] = s;
				name[num] = n;
			}
		}


		try {
			FileWriter fWriter = new FileWriter("record.txt", false);
			fWriter.write("");
			fWriter.close();
			fWriter = new FileWriter("record.txt", true);

			for (int i = 1; i <= num; ++i) {
				fWriter.write(score[i] + " " + name[i] + "\n");
			}
			fWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
