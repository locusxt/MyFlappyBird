package bird;

/**
 * Ground���ǵذ���,���ڼ�¼�ذ�������Ϣ.
 * �ƶ��ĵذ�,ʵ������ͨ�����ŵذ�ͼƬƴ������.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Ground 
{
	/**
	 * speed�ǵذ���ƶ��ٶ�
	 */
	private int speed;
	
	/**
	 * x��y������
	 */
	private int x;
	private int y;
	
	/**
	 * sizeX��sizeY��ͼƬ�Ĵ�С
	 */
	private int sizeX;
	private int sizeY;
	
	
	/**
	 * ����ͼƬ��С,�Լ��ٶ�,�����й���.
	 * 
	 * @param sx ͼƬx���С
	 * @param sy ͼƬy���С
	 * @param s �ٶ�
	 */
	public Ground(int sx, int sy, int s)
	{
		sizeX = sx;
		sizeY = sy;
		speed = s;
	}
	
	/**
	 * ����λ��,��������x��y����.
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
	 * �����ٶ�.
	 * 
	 * @param s Ҫ��ɵ��ٶ�
	 */
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	/**
	 * ��ȡx����.
	 * 
	 * @return x����
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * ��ȡy����.
	 * 
	 * @return y����
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * ��ȡͼƬx���С
	 * 
	 * @return ͼƬx�����С
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * ��ȡͼƬy���С
	 * 
	 * @return ͼƬy�����С
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * �����ٶ�,ͬ���ذ�λ��
	 */
	public void synPosition()
	{
		x -= speed;
	}
	
	/**
	 * ��ײ���,�ж��ǲ�����ĳ��bird������ײ.
	 * 
	 * @param bird ��Ҫ������ײ����bird
	 * @return ������ײ�ͷ���true,���򷵻�false.
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