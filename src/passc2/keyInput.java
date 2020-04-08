package passc2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter {
	
	private Handler handler;
	
	public keyInput (Handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	
		
	
		if (key == KeyEvent.VK_UP) 
			Game.MAX_OBJECTS++;
			
		if (key == KeyEvent.VK_DOWN) {
			if (Game.MAX_OBJECTS == 0) 
				return;
			Game.MAX_OBJECTS--;
		}
		if (key == KeyEvent.VK_SPACE) {
			Game.MAX_OBJECTS = 0;
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
}
