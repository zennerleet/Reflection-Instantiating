package passc2;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends GameObject {

	
	
	public Square(int x, int y,Handler h, Color c) {
		super(x, y,h,c);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillRect(x, y, 30, 30);
	}

}
