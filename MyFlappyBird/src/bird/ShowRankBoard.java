package bird;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * ShowRankBoard类用于弹出一个窗口显示排行榜.
 * 同时通过两个按钮,判断是again还是quit.
 * 总体采用BorderLayout布局.
 * 按钮面板采用了GridLayout布局.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class ShowRankBoard 
{
	/**
	 * 弹出一个窗口,包括排行榜和两个按钮.
	 * 不断判断按钮有没有被点击,一旦点击就返回相应信息.
	 * 
	 * @return 点击了again按钮,返回1;点击了quit按钮,返回2.
	 */
	@SuppressWarnings("static-access")
	public int showRankBoard()
	{	
		JFrame rankFrame = new JFrame("Rank Board");
		ButPanel butPanel = new ButPanel();
		BoardPanel boardPanel = new BoardPanel(Record.getRecord());
		
		rankFrame.setLayout(new BorderLayout());

		rankFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		rankFrame.add(new JLabel("Rank Board", JLabel.CENTER), BorderLayout.NORTH);
		rankFrame.add(new JLabel("  "), BorderLayout.WEST);
		rankFrame.add(new JLabel("  "), BorderLayout.EAST);
		rankFrame.add(butPanel, BorderLayout.SOUTH);
		rankFrame.add(boardPanel, BorderLayout.CENTER);
		

		rankFrame.setSize(200, 300);
		rankFrame.setResizable(true);
		
		rankFrame.setLocationRelativeTo(null);
		
		rankFrame.setVisible(true);
		
		while(true)
		{
			if (butPanel.getIsAgain() == true)
			{
				rankFrame.dispose();
				return 1;
			}
			if (butPanel.getIsQuit() == true)
			{
				rankFrame.dispose();
				return 2;
			}
			try {
				Thread.currentThread().sleep(50);// 毫秒
				//System.out.print(".");
			} catch (Exception e) {
			}
		}
	}
}

/**
 * ButPanel是按钮面板.
 * 包括两个按钮,again和quit.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
@SuppressWarnings("serial")
class ButPanel extends JPanel
{
	/**
	 * isAgain记录是不是点击了again按钮.
	 */
	private boolean isAgain = false;
	
	/**
	 * isQuit记录是不是点击了quit按钮.
	 */
	private boolean isQuit = false;
	
	/**
	 * 获取isAgain的值.
	 * 
	 * @return isAgain的值,是不是点击了again按钮.
	 */
	public boolean getIsAgain()
	{
		return isAgain;
	}
	
	/**
	 * 获取isQuit的值
	 * 
	 * @return isQuit,是不是点击了quit按钮.
	 */
	public boolean getIsQuit()
	{
		return isQuit;
	}
	
	/**
	 * 构造方法.
	 * 加入两个按钮,各增加一个ActionListener,一旦点击就置isAgain或isQuit为true.
	 */
	public ButPanel() {
		JButton againBut = new JButton("again");
		JButton quitBut = new JButton("quit");
		
		againBut.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				isAgain = true;
			}
		});
		
		quitBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isQuit = true;
			}
		});
		
		setLayout(new GridLayout(1, 2));
		add(againBut);
		add(quitBut);
	}
}

/**
 *BoardPanel是排行榜的面板.
 *根据record.txt中的内容,显示排行榜.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
@SuppressWarnings("serial")
class BoardPanel extends JPanel
{
	/**
	 * record.txt中的分数数组.
	 */
	private int[] record;
	
	/**
	 * record.txt中的名字数组.
	 */
	private String[] nameList;
	
	/**
	 * 以一个Info类进行构造.
	 * 依据Info给record和nameList赋值.
	 * 
	 * @param info 提供分数和名字的Info类
	 */
	public BoardPanel(Info info)
	{
		record = info.score;
		nameList = info.name;

		setLayout(new GridLayout(10, 1));
		
		String str;
		
		str = new String("Rank" + "           " + "Score" + "        " + "Name");
		add(new JLabel(str));
		
		int num = record[0];
		for (int i = 1; i <= num; ++i)
		{
			str = new String("No." + i + "              " + record[i] + "            " + nameList[i]);
			add(new JLabel(str));
		}
	}
}
