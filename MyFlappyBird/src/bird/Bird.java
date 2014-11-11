package bird;

/**
 * Bird���¼����������
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Bird
{
	/**
	 * x��y������
	 */
	private int x;
	private int y;
	
	/**
	 * speed��y������ٶ�.x������û���ٶ�.
	 */
	private float speed;
	
	/**
	 * gravity���������ٶ�,ÿ֡����ٶȸı���.
	 */
	private float gravity;
	
	/**
	 * sizeX��sizeY�����ͼƬ�Ĵ�С.
	 */
	private int sizeX;
	private int sizeY;
	
	/**
	 * ���캯��.�������ͼƬ��С���й���
	 * 
	 * @param sx ���ͼƬ��x���򳤶�
	 * @param sy ���ͼƬ��y���򳤶�
	 */
	public Bird(int sx, int sy)
	{
		sizeX = sx;
		sizeY = sy;
	}
	
	/**
	 * �������λ��,����������.
	 * 
	 * @param x x����
	 * @param y y����
	 */
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * ��������ٶ�
	 * 
	 * @param s �ٶȵ�ֵ
	 */
	public void setSpeed(float s)
	{
		speed = s;
	}
	
	/**
	 * �����������ٶ�gravity
	 * 
	 * @param d �������ٶȵĴ�С
	 */
	public void setGravity(float d)
	{
		gravity = d;
	}
	
	/**
	 * ����y����Ĵ�С
	 * 
	 * @param y y����
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * ��ȡ���ͼƬx�����С
	 * 
	 * @return ���ͼƬx�����С
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * ��ȡ���ͼƬy�����С
	 * 
	 * @return ���ͼƬy�����С
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * ��ȡ���x����
	 * 
	 * @return x����
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * ��ȡ���y����
	 * 
	 * @return y����
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * applyGravity��������Ч,�������������ٶȸı�����ٶ�.
	 */
	public void applyGravity()
	{
		speed = speed - gravity;
	}
	
	/**
	 * addSpeed�������,ÿ�ΰ��ո�,��Ҫ��������ٶ�.
	 * ����ʵ�����ǽ�����ٶ�����Ϊһ��ȷ��ֵ,�൱��setSpeed
	 * 
	 * @param delta Ҫ��ɵ�����ٶ�
	 */
	public void addSpeed(float delta)
	{
		speed = delta;
	}
	
	/**
	 * synPositionͬ�����λ��.
	 * ��������ٶ�,�������λ��.
	 */
	public void synPosition()
	{
		y = (int)(y - speed);
	}
}
