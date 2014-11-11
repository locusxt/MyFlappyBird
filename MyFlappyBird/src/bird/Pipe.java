package bird;

import java.util.Random;

/**
 * Pipe��,��¼ˮ�ܵ��������.
 * һ��Pipe�������һ��һ������ˮ��.
 * 
 * @author 1200012879
 * @author cnjs.zhuting#gmail.com
 */
public class Pipe
{
	/**
	 * gap����������ˮ��֮��ľ���.
	 */
	private int gap;
	
	/**
	 * ˮ�ܵ�x�����ٶ�.
	 */
	private int speed;
	
	/**
	 * ˮ�ܵ�y�����ٶ�,����hardģʽ.
	 */
	private int speedy;
	
	/**
	 * ˮ�ܵ�y���귶Χ
	 */
	private int range0, range1;
	
	/**
	 * x��y��ˮ�ܵ�����.
	 */
	private int x;
	private int y;
	
	/**
	 * sizeX��sizeY�����ϻ��µ�һ��ˮ�ܵ�ͼƬ��С.
	 */
	private int sizeX;
	private int sizeY;
	
	/**
	 * isPassed��¼����û�зɹ�������ˮ��.
	 */
	private boolean isPassed;
	
	/**
	 * ����ͼƬ��С��x���ٶȹ���Pipe��
	 * 
	 * @param sx ͼƬx���С
	 * @param sy ͼƬy���С
	 * @param s x���ٶ�
	 */
	public Pipe(int sx, int sy, int s)
	{
		sizeX = sx;
		sizeY = sy;
		speed = s;
	}
	
	/**
	 * ����y������ٶ�,����hardģʽ.
	 * �����������.
	 * 
	 * @param s y����ٶ�ֵ
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
	 * ����y����ķ�Χ.
	 * ��Ҫȷ��ˮ�ܵ���ͼ��ʾ����.
	 * 
	 * @param a y������½�
	 * @param b y������Ͻ�
	 */
	public void setRange(int a, int b)
	{
		range0 = a;
		range1 = b;
	}
	
	/**
	 * ����y�����ٶ�ͬ��ˮ�ܵ�y����,����hardģʽ.
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
	 * ����ˮ�ܵ�����,����x��y
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
	 * ����gap,������ˮ��֮��ľ���.
	 * 
	 * @param g gap�Ĵ�С
	 */
	public void setGap(int g)
	{
		gap = g;
	}
	
	/**
	 * ����x����ٶ�.
	 * 
	 * @param s x����ٶ�
	 */
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	/**
	 * ��ȡgapֵ
	 * 
	 * @return ����ˮ�ܵľ���,gap
	 */
	public int getGap()
	{
		return gap;
	}
	
	/**
	 * ��ȡͼƬ��x���С
	 * 
	 * @return ͼƬ��x���С
	 */
	public int getSX()
	{
		return sizeX;
	}
	
	/**
	 * ��ȡͼƬ��y���С
	 * 
	 * @return ͼƬ��y���С
	 */
	public int getSY()
	{
		return sizeY;
	}
	
	/**
	 * ��ȡ����ˮ��x����
	 * 
	 * @return ����ˮ�ܵ�x����
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * ��ȡ����ˮ��y����
	 * 
	 * @return ����ˮ��y����
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * ��ȡ����ˮ�ܵ�x����
	 * 
	 * @return ����ˮ�ܵ�x����
	 */
	public int getX2()
	{
		return x;
	}
	
	/**
	 * ��ȡ����ˮ�ܵ�y����
	 * 
	 * @return ����ˮ�ܵ�y����
	 */
	public int getY2()
	{
		return y + sizeY + gap;
	}
	
	/**
	 * ͬ��ˮ�ܵ�x��λ��.
	 */
	public void synPosition()
	{
		x -= speed;
	}
	
	/**
	 * �ж�ĳ�����ǲ�����ˮ����.
	 * �ֱ��жϵ��ǲ�������������ˮ���ڼ���.
	 * 
	 * @param bx Ҫ�жϵĵ��x����
	 * @param by Ҫ�жϵĵ��y����
	 * @return �����ˮ���ھͷ���true,���򷵻�false
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
	 * ��ײ���,�ж�ĳ��bird��û����ˮ�ܷ�����ײ.
	 * 
	 * @param bird ��Ҫ�жϵ�bird
	 * @return ��ײ�ͷ���true,���򷵻�false
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
	 * ����isPassed
	 * 
	 * @param b Ҫ���óɵ�booleanֵ
	 */
	public void setIsPassed(boolean b)
	{
		isPassed = b;
	}
	
	/**
	 * �ж�ĳ��bird��û��ͨ��ˮ��.
	 * ͨ���ж�bird��x������û�д���ˮ�ܵ�x����.
	 * 
	 * @param bird Ҫ�жϵ�bird
	 * @return �Ѿ�ͨ���ͷ���true,���򷵻�false.
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
