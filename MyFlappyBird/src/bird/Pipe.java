package bird;

import java.util.Random;

/**
 * Pipe类,记录水管的相关数据.
 * 一个Pipe类包括了一上一下两个水管.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Pipe
{
	/**
	 * gap是上下两个水管之间的距离.
	 */
	private int gap;
	
	/**
	 * 水管的x方向速度.
	 */
	private int speed;
	
	/**
	 * 水管的y方向速度,用于hard模式.
	 */
	private int speedy;
	
	/**
	 * 水管的y坐标范围
	 */
	private int range0, range1;
	
	/**
	 * x和y是水管的坐标.
	 */
	private int x;
	private int y;
	
	/**
	 * sizeX和sizeY描述上或下的一根水管的图片大小.
	 */
	private int sizeX;
	private int sizeY;
	
	/**
	 * isPassed记录鸟有没有飞过这两根水管.
	 */
	private boolean isPassed;
	
	/**
	 * 依据图片大小和x向速度构造Pipe类
	 * 
	 * @param sx 图片x向大小
	 * @param sy 图片y向大小
	 * @param s x向速度
	 */
	public Pipe(int sx, int sy, int s)
	{
		sizeX = sx;
		sizeY = sy;
		speed = s;
	}
	
	/**
	 * 设置y方向的速度,用于hard模式.
	 * 方向随机给出.
	 * 
	 * @param s y向的速度值
	 */
	public void setSpeedy(int s)
	{
		Random r = new Random();
		if (r.nextBoolean() == true)
			speedy = s;
		else
			speedy = -s;
	}
	
	/**
	 * 设置y坐标的范围.
	 * 需要确保水管的贴图显示正常.
	 * 
	 * @param a y坐标的下界
	 * @param b y坐标的上界
	 */
	public void setRange(int a, int b)
	{
		range0 = a;
		range1 = b;
	}
	
	/**
	 * 依据y方向速度同步水管的y坐标,用于hard模式.
	 */
	public void synY()
	{
		y += speedy;
		if (y > range1 || y < range0)
		{
			speedy = -speedy;
		}
	}
	
	/**
	 * 设置水管的坐标,设置x和y
	 * 
	 * @param x x坐标
	 * @param y y坐标
	 */
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 设置gap,即上下水管之间的距离.
	 * 
	 * @param g gap的大小
	 */
	public void setGap(int g)
	{
		gap = g;
	}
	
	/**
	 * 设置x向的速度.
	 * 
	 * @param s x向的速度
	 */
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	/**
	 * 获取gap值
	 * 
	 * @return 上下水管的距离,gap
	 */
	public int getGap()
	{
		return gap;
	}
	
	/**
	 * 获取图片的x向大小
	 * 
	 * @return 图片的x向大小
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * 获取图片的y向大小
	 * 
	 * @return 图片的y向大小
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * 获取上面水管x坐标
	 * 
	 * @return 上面水管的x坐标
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 获取上面水管y坐标
	 * 
	 * @return 上面水管y坐标
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * 获取下面水管的x坐标
	 * 
	 * @return 下面水管的x坐标
	 */
	public int getX2()
	{
		return x;
	}
	
	/**
	 * 获取下面水管的y坐标
	 * 
	 * @return 下面水管的y坐标
	 */
	public int getY2()
	{
		return y + sizeY + gap;
	}
	
	/**
	 * 同步水管的x向位置.
	 */
	public void synPosition()
	{
		x -= speed;
	}
	
	/**
	 * 判断某个点是不是在水管内.
	 * 分别判断点是不是在上下两个水管内即可.
	 * 
	 * @param bx 要判断的点的x坐标
	 * @param by 要判断的点的y坐标
	 * @return 如果在水管内就返回true,否则返回false
	 */
	private boolean isInPipe(int bx, int by)
	{
		if (bx > x && bx < x + sizeX && by > y && by < y + sizeY)
		{
			return true;
		}
		
		int x1 = x;
		int y1 = y + sizeY + gap;
		if (bx > x1 && bx < x1 + sizeX && by > y1 && by < y1 + sizeY)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 碰撞检测,判断某个bird有没有与水管发生碰撞.
	 * 
	 * @param bird 需要判断的bird
	 * @return 碰撞就返回true,否则返回false
	 */
	public boolean isCollision(Bird bird)
	{
		int del = 3;
		int birdX = bird.getX();
		int birdY = bird.getY();
		int birdSX = bird.getSX();
		int birdSY = bird.getSY();
		
		if (isInPipe(birdX + del, birdY + del) || isInPipe(birdX + birdSX - del, birdY + del) || 
				isInPipe(birdX + del, birdY + birdSY - del) || 
				isInPipe(birdX + birdSX - del, birdY + birdSY - del))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 设置isPassed
	 * 
	 * @param b 要设置成的boolean值
	 */
	public void setIsPassed(boolean b)
	{
		isPassed = b;
	}
	
	/**
	 * 判断某个bird有没有通过水管.
	 * 通过判断bird的x坐标有没有大于水管的x坐标.
	 * 
	 * @param bird 要判断的bird
	 * @return 已经通过就返回true,否则返回false.
	 */
	public boolean pass(Bird bird)
	{
		if (!isPassed && bird.getX() >= x)
		{
			isPassed = true;
			return true;
		}
		return false;
	}
}
