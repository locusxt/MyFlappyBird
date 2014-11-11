package bird;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileGet�����ڴ��ļ��ж�ȡ����,��String��ʽ����.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 * 
 */
public class FileGet {
	/**
	 * ��ָ���ļ������ļ��л�ȡ���ݣ�String��ʽ����
	 * 
	 * @param fileName
	 *            ָ�����ļ���
	 * @return �����ļ����ݣ�String��ʽ
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
