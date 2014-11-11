package demo;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bird.FileGet;
import bird.Record;
import bird.ShowRankBoard;
import bird.World;

/**
 * ����һ��Demo.
 * ��Ϸ���������˶�"�¼�"�Ĵ���.
 * ���а񲿷������˶�"���"��"����".
 * ͼƬ��������,�����Ϊԭ��.
 * 
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class MyDemo 
{
	public static void main(String args[])
	{
		JFrame myFrame = new JFrame("my flappy bird");
		World world = new World();
		
		myFrame.add(world);
		myFrame.setSize(384, 512);
		myFrame.setResizable(false);
		
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myFrame.setVisible(true);
		
		String helpStr = FileGet.getStr("help.txt");
		JOptionPane.showMessageDialog(null, 
				helpStr + "\nAUTHOR:ZhuTing\nVERSION:v1.1.2\nALL RIGHTS RESERVED");
		
		while (true)
		{
			int option = JOptionPane.showConfirmDialog(null, "�Ƿ���hardģʽ(ˮ�ܻᶯ~����*2)?");
			if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.NO_OPTION)
			{
				world.setIsHard(false);
			}
			else
				world.setIsHard(true);
			
			world.init();
			world.gameStage();
			
			boolean isAddable = Record.isAddable(world.getScore());
			if (isAddable)
			{
				String yourName = JOptionPane.showInputDialog("����������,����������մ�����:");
				if (yourName == null)
				{
					yourName = "???";
				}
				Record.addRecord(world.getScore(), yourName);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "������������,����������.");
			}
			
			ShowRankBoard myRankBoard = new ShowRankBoard();
			int res = myRankBoard.showRankBoard();
			if (res == 2)
				break;
		}
		myFrame.setVisible(false);
		myFrame.dispose();
	}
}
