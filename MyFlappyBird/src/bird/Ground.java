package bird;

/**
 * Ground类是地板类,用于记录地板的相关信息.
 * 移动的地板,实际上是通过两张地板图片拼起来的.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Ground 
{
	/**
	 * speed是地板的移动速度
	 */
	private int speed;
	
	/**
	 * x和y是坐标
	 */
	private int x;
	private int y;
	
	/**
	 * sizeX和sizeY是图片的大小
	 */
	private int sizeX;
	private int sizeY;
	
	
	/**
	 * 依据图片大小,以及速度,来进行构造.
	 * 
	 * @param sx 图片x向大小
	 * @param sy 图片y向大小
	 * @param s 速度
	 */
	public Ground(int sx, int sy, int s)
	{
		sizeX = sx;
		sizeY = sy;
		speed = s;
	}
	
	/**
	 * 设置位置,就是设置x和y坐标.
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
	 * 设置速度.
	 * 
	 * @param s 要设成的速度
	 */
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	/**
	 * 获取x坐标.
	 * 
	 * @return x坐标
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 获取y坐标.
	 * 
	 * @return y坐标
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * 获取图片x向大小
	 * 
	 * @return 图片x方向大小
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * 获取图片y向大小
	 * 
	 * @return 图片y方向大小
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * 根据速度,同步地板位置
	 */
	public void synPosition()
	{
		x -= speed;
	}
	
	/**
	 * 碰撞检测,判断是不是与某个bird发生碰撞.
	 * 
	 * @param bird 需要进行碰撞检测的bird
	 * @return 发生碰撞就返回true,否则返回false.
	 */
	public boolean isCollision(Bird bird)
	{
		int birdY = bird.getY();
		int birdSY = bird.getSY();
		
		int birdDown = birdY + birdSY;
		if (birdDown > y)
			return true;
		return false;
	}
}
