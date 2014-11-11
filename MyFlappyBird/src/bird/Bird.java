package bird;

/**
 * Bird类记录鸟的相关数据
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Bird
{
	/**
	 * x和y是坐标
	 */
	private int x;
	private int y;
	
	/**
	 * speed是y方向的速度.x方向鸟没有速度.
	 */
	private float speed;
	
	/**
	 * gravity是重力加速度,每帧鸟的速度改变量.
	 */
	private float gravity;
	
	/**
	 * sizeX和sizeY是鸟的图片的大小.
	 */
	private int sizeX;
	private int sizeY;
	
	/**
	 * 构造函数.根据鸟的图片大小进行构造
	 * 
	 * @param sx 鸟的图片的x方向长度
	 * @param sy 鸟的图片的y方向长度
	 */
	public Bird(int sx, int sy)
	{
		sizeX = sx;
		sizeY = sy;
	}
	
	/**
	 * 设置鸟的位置,即设置坐标.
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
	 * 设置鸟的速度
	 * 
	 * @param s 速度的值
	 */
	public void setSpeed(float s)
	{
		speed = s;
	}
	
	/**
	 * 设置重力加速度gravity
	 * 
	 * @param d 重力加速度的大小
	 */
	public void setGravity(float d)
	{
		gravity = d;
	}
	
	/**
	 * 设置y坐标的大小
	 * 
	 * @param y y坐标
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * 获取鸟的图片x方向大小
	 * 
	 * @return 鸟的图片x方向大小
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * 获取鸟的图片y方向大小
	 * 
	 * @return 鸟的图片y方向大小
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * 获取鸟的x坐标
	 * 
	 * @return x坐标
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 获取鸟的y坐标
	 * 
	 * @return y坐标
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * applyGravity是重力生效,即依据重力加速度改变鸟的速度.
	 */
	public void applyGravity()
	{
		speed = speed - gravity;
	}
	
	/**
	 * addSpeed给鸟加速,每次按空格,需要增加鸟的速度.
	 * 这里实际上是将鸟的速度增加为一个确定值,相当于setSpeed
	 * 
	 * @param delta 要设成的鸟的速度
	 */
	public void addSpeed(float delta)
	{
		speed = delta;
	}
	
	/**
	 * synPosition同步鸟的位置.
	 * 依据鸟的速度,计算鸟的位置.
	 */
	public void synPosition()
	{
		y = (int)(y - speed);
	}
}
