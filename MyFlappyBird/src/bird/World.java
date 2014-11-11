package bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * World类控制游戏界面的显示.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
@SuppressWarnings("serial")
public class World extends JPanel {
	/**
	 * cnt用来记录当前鸟应该显示哪张图.
	 * 鸟的动画通过三张图片的不断切换实现.
	 */
	private int cnt;
	
	/**
	 * isHardMode记录是不是hard模式.
	 */
	private boolean isHardMode;
	
	/**
	 * score是得分.
	 */
	private int score;
	
	/**
	 * 记录键盘是不是松开.
	 */
	private boolean isReleased;
	
	/**
	 * edgeLen用于水管的显示.
	 */
	private int edgeLen = 80;
	
	/**
	 * 游戏状态是不是结束.
	 */
	private boolean isGameOver;
	
	/**
	 * 游戏状态是不是开始
	 */
	private boolean isGameStart;
	
	/**
	 * 鸟的数据记录类.
	 */
	Bird flappyBird;
	
	/**
	 * 水管的数据记录类.
	 * 实际只需要两个Pipe类,一个走出左边界立刻将其移到右边界即可.
	 */
	Pipe[] pipe = new Pipe[2];
	
	/**
	 * 地板的数据记录类.
	 */
	Ground[] ground = new Ground[2];

	/**
	 * xxxImg是各种图片.
	 */
	Image bgImg;
	Image groundImg;
	Image[] birdImg = new Image[3];
	Image pipe1Img;
	Image pipe2Img;
	
	/**
	 * 设置游戏模式.
	 * hard模式就设成true.
	 * 
	 * @param b 游戏模式是不是hard,是则为true
	 */
	public void setIsHard(boolean b)
	{
		isHardMode = b;
	}
	
	/**
	 * 获取a到b之间的一个随机值.
	 * 用于随机Pipe的高度.
	 * 
	 * @param a 区间上界.
	 * @param b 区间下界
	 * @return 返回随机值,出错就返回-1
	 */
	private int getRandom(int a, int b) {
		if (a < b) {
			System.out.println("error");
			return -1;
		}
		Random r = new Random();
		int len = a - b;
		return b + (int) (len * r.nextDouble());
	}

	/**
	 * 初始化函数.
	 * score清零,初始化鸟,水管等的位置等.
	 */
	public void init() {
		cnt = 0;
		score = 0;
		setIsReleased(true);
		isGameOver = false;
		isGameStart = false;

		ground[0] = new Ground(384, 64, 2);
		ground[1] = new Ground(384, 64, 2);
		ground[0].setPos(0, 448);
		ground[1].setPos(384, 448);

		pipe[0] = new Pipe(70, 250, 2);
		pipe[1] = new Pipe(70, 250, 2);
		pipe[0].setGap(130);
		pipe[1].setGap(130);
		pipe[0].setIsPassed(false);
		pipe[1].setIsPassed(false);
		pipe[0].setRange(edgeLen - pipe[0].getSY(), (448 - edgeLen - pipe[0].getGap()) - pipe[0].getSY());
		pipe[1].setRange(edgeLen - pipe[1].getSY(), (448 - edgeLen - pipe[1].getGap()) - pipe[1].getSY());
		if (isHardMode)
		{
			pipe[0].setSpeedy(1);
			pipe[1].setSpeedy(1);
		}
		else
		{
			pipe[0].setSpeedy(0);
			pipe[1].setSpeedy(0);
		}
		pipe[0].setPos(
				541,
				-(getRandom(pipe[0].getSY() - edgeLen, pipe[0].getSY()
						- (448 - edgeLen - pipe[0].getGap()))));
		pipe[1].setPos(
				768,
				-(getRandom(pipe[1].getSY() - edgeLen, pipe[1].getSY()
						- (448 - edgeLen - pipe[1].getGap()))));

		flappyBird = new Bird(45, 30);
		flappyBird.setPos(100, 220);
		flappyBird.setSpeed(3);
		flappyBird.setGravity(0.75f);
	}

	/**
	 * World的构造方法.
	 * 主要是加载各种图片,并进行一次初始化.
	 */
	public World() {
		bgImg = new ImageIcon("Images/bg.png").getImage();
		groundImg = new ImageIcon("Images/ground.png").getImage();
		pipe1Img = new ImageIcon("Images/pipe1.png").getImage();
		pipe2Img = new ImageIcon("Images/pipe2.png").getImage();
		for (int i = 0; i < 3; ++i) {
			String tmp = "Images/bird" + (i + 1) + ".png";
			birdImg[i] = new ImageIcon(tmp).getImage();
		}

		init();
	}

	/**
	 * 获取Score.
	 * 
	 * @return 得分
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * 设置isRelease
	 * @param b 要设成的boolean值.
	 */
	private void setIsReleased(boolean b) {
		isReleased = b;
	}

	/**
	 * 获取当前键盘是不是松开的.
	 * 
	 * @return isReleased
	 */
	private boolean getIsReleased() {
		return isReleased;
	}

	/**
	 * paint方法,绘制背景,依据坐标绘制鸟等的图片.
	 */
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, null);

		g.drawImage(pipe1Img, pipe[0].getX(), pipe[0].getY(), null);
		g.drawImage(pipe2Img, pipe[0].getX2(), pipe[0].getY2(), null);
		g.drawImage(pipe1Img, pipe[1].getX(), pipe[1].getY(), null);
		g.drawImage(pipe2Img, pipe[1].getX2(), pipe[1].getY2(), null);

		g.drawImage(groundImg, ground[0].getX(), ground[0].getY(), null);
		g.drawImage(groundImg, ground[1].getX(), ground[1].getY(), null);

		int tmp = 0;
		if (cnt % 2 == 0)
			tmp = 2;
		else if (cnt == 1)
			tmp = 0;
		else
			tmp = 2;
		g.drawImage(birdImg[tmp], flappyBird.getX(), flappyBird.getY(), null);

		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.BLUE);
		g.drawString("score:" + score, 10, 30);
	}

	/**
	 * gameStage游戏阶段,不断循环,更新鸟等的位置,不断重新绘制.
	 */
	@SuppressWarnings("static-access")
	public void gameStage() {
		int frameTime = 0;

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (getIsReleased() && e.getKeyCode() == KeyEvent.VK_SPACE) {
					isGameStart = true;
					System.out.println("hit space");
					if (!isGameOver)
						flappyBird.addSpeed(10);
					setIsReleased(false);
				} else if (getIsReleased()) {
					System.out.println("not release");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("release");
				setIsReleased(true);
			}
		});

		while (true) {
			// requestFocus();
			requestFocusInWindow();
			boolean tmp = isFocusOwner();
			if (tmp)
				break;
			System.out.println("focus failed");
		}

		while (true) {
			if (isGameStart) {

				int fby = flappyBird.getY();
				if (fby < 0) {
					flappyBird.setY(0);
				} else if (fby + flappyBird.getSY() > 448) {
					flappyBird.setY(448 - flappyBird.getSY());
					break;
				}

				for (int i = 0; i < 2; ++i) {
					if (pipe[i].isCollision(flappyBird)) {
						isGameOver = true;
					}

					if (pipe[i].pass(flappyBird)) {
						++score;
						if (isHardMode)
							++score;
					}

					pipe[i].synPosition();
					if (isHardMode)
						pipe[i].synY();
					int px = pipe[i].getX();
					if (px + pipe[i].getSX() < 0) {
						pipe[i].setIsPassed(false);
						pipe[i].setPos(
								384,
								-(getRandom(
										pipe[0].getSY() - edgeLen,
										pipe[i].getSY()
												- (448 - edgeLen - pipe[0]
														.getGap()))));
					}
				}

				for (int i = 0; i < 2; ++i) {
					if (ground[i].isCollision(flappyBird)) {
						flappyBird.setSpeed(0);
						isGameOver = true;
					}

					ground[i].synPosition();
					int gx = ground[i].getX();
					if (gx + ground[i].getSX() < 0) {
						ground[i].setPos(384, 448);
					}
				}

				flappyBird.applyGravity();
				flappyBird.synPosition();
			}

			if (frameTime > 100) {
				++cnt;
				cnt %= 3;
				frameTime = 0;
			}

			repaint();
			try {
				Thread.currentThread().sleep(30);// 毫秒
				frameTime += 30;
			} catch (Exception e) {
			}
		}
		System.out.println(score);
	}
}
