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
 * ShowRankBoard�����ڵ���һ��������ʾ���а�.
 * ͬʱͨ��������ť,�ж���again����quit.
 * �������BorderLayout����.
 * ��ť��������GridLayout����.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class ShowRankBoard 
{
	/**
	 * ����һ������,�������а��������ť.
	 * �����жϰ�ť��û�б����,һ������ͷ�����Ӧ��Ϣ.
	 * 
	 * @return �����again��ť,����1;�����quit��ť,����2.
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
				Thread.currentThread().sleep(50);// ����
				//System.out.print(".");
			} catch (Exception e) {
			}
		}
	}
}

/**
 * ButPanel�ǰ�ť���.
 * ����������ť,again��quit.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
@SuppressWarnings("serial")
class ButPanel extends JPanel
{
	/**
	 * isAgain��¼�ǲ��ǵ����again��ť.
	 */
	private boolean isAgain = false;
	
	/**
	 * isQuit��¼�ǲ��ǵ����quit��ť.
	 */
	private boolean isQuit = false;
	
	/**
	 * ��ȡisAgain��ֵ.
	 * 
	 * @return isAgain��ֵ,�ǲ��ǵ����again��ť.
	 */
	public boolean getIsAgain()
	{
		return isAgain;
	}
	
	/**
	 * ��ȡisQuit��ֵ
	 * 
	 * @return isQuit,�ǲ��ǵ����quit��ť.
	 */
	public boolean getIsQuit()
	{
		return isQuit;
	}
	
	/**
	 * ���췽��.
	 * ����������ť,������һ��ActionListener,һ���������isAgain��isQuitΪtrue.
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
 *BoardPanel�����а�����.
 *����record.txt�е�����,��ʾ���а�.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
@SuppressWarnings("serial")
class BoardPanel extends JPanel
{
	/**
	 * record.txt�еķ�������.
	 */
	private int[] record;
	
	/**
	 * record.txt�е���������.
	 */
	private String[] nameList;
	
	/**
	 * ��һ��Info����й���.
	 * ����Info��record��nameList��ֵ.
	 * 
	 * @param info �ṩ���������ֵ�Info��
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
