package passc2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public abstract class GameObject {
	
	protected int x, y;
	protected int velX, velY;
	protected Handler handler;
	protected Color color;
	protected Random random = new Random();
	
	
	public GameObject (int x, int y, Handler h, Color c) {
		this.x = x;
		this.y = y;
		this.color = c;
		this.handler = h;
		

		velX = ThreadLocalRandom.current().nextInt(1, 5);
		velY = ThreadLocalRandom.current().nextInt(1, 5);
		
		if (random.nextFloat() > 0.5f) velX *= -1;
		if (random.nextFloat() > 0.5f) velY *= -1;
	}
	
	public void tick () {
		x += velX;
		y += velY;
		
		
		if (x < 0 || x > Game.WIDTH) {
			handler.removeObject(this);
		}
		if (y < 0 || y > Game.HEIGHT) {
			handler.removeObject(this);
		}
	}
	public abstract void render(Graphics g);
	public void setX(int x ) {
		this.x = x;
	}
	public void setY (int y) {
		this.y = y;
	}
	public void setVelX (int x) {
		this.velX = x;
	}
	public void setVelY (int x) {
		this.velY = x;
	}
	public int getX () {
		return x;
	}
	public int getY () {
		return y;
	}
	public int getVelX () {
		return velX;
	}
	public int getVelY () {
		return velY;
	}

}
