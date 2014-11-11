package demo;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bird.FileGet;
import bird.Record;
import bird.ShowRankBoard;
import bird.World;

/**
 * 这是一个Demo.
 * 游戏部分体现了对"事件"的处理.
 * 排行榜部分体现了对"组件"和"布局".
 * 图片来自网络,代码均为原创.
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
			int option = JOptionPane.showConfirmDialog(null, "是否开启hard模式(水管会动~分数*2)?");
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
				String yourName = JOptionPane.showInputDialog("少侠好身手,留下你的尊姓大名吧:");
				if (yourName == null)
				{
					yourName = "???";
				}
				Record.addRecord(world.getScore(), yourName);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "少侠技不如人,请重新来过.");
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
