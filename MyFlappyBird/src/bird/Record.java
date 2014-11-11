package bird;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Info��¼�Ӽ�¼�ļ��ж�ȡ������Ϣ.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
class Info {
	/**
	 * score�Ƕ�ȡ���ķ�������.
	 */
	public int[] score;

	/**
	 * name�Ƕ�ȡ������������.
	 */
	public String[] name;

	/**
	 * ��score��name���й���.
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
 * Record��Ĺ����Ƕ�ȡrecord.txt�е���Ϣ,�Լ���record.txt����һЩ����.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Record {
	/**
	 * ��ȡrecord.txt�е���Ϣ,��Info����ʽ����
	 * 
	 * @return ��ȡ����Info
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
	 * �ж�һ�������ܲ��ܼ��뵽��¼�ļ���ȥ.
	 * ����������ǰ9,�Ϳ��Լ���.
	 * 
	 * @param s ������ֵ.
	 * @return ����ܼ���ͷ���true,���򷵻�false.
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
	 * ���¼�ļ��м���һ����¼.
	 * �ڼ�¼�ļ��еļ�¼����9��ʱ,��ɾ��������һ����¼.
	 * 
	 * @param s Ҫ����ļ�¼�ķ���score
	 * @param n Ҫ����ļ�¼������name
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
