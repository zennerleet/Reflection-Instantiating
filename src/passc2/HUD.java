package passc2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private int frames;
	private int objectsCount;
	private Handler handler;
	
	public HUD(Handler h) {
		// TODO Auto-generated constructor stub
		this.handler = h;
	}
	
	public void setFrames(int frames) {
		this.frames = frames;
	}
	
	public void tick() {
		objectsCount = handler.getNumberOfObjects();
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
		g.setColor(Color.RED);
		g.drawString("Frames: " + frames, 0, 20);
		g.drawString("Objects: " + objectsCount, 0, 40);
		g.drawString("Max Objects: " + Game.MAX_OBJECTS, 0, 60);
		g.setColor(Color.WHITE);
		g.setFont(new Font("SANS_SERIF", Font.PLAIN, 10));
		g.drawString("Use UP_ARROW and DOWN_ARROW to modify Max Objects and SPACE to reset", 0, Game.HEIGHT - 50);
	}
}
