package passc2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 5703745428067499922L;

	public static final int WIDTH = 1280, HEIGHT = 720;
	public static int MAX_OBJECTS = 10;
	
	private Thread thread;
	private boolean running = false;
	
	private HUD hud;
	private Handler handler;
	private Random rand;
	
	private List<String> classes;
	
	
	public Game() {
		// TODO Auto-generated constructor stub
		
		new Window(WIDTH, HEIGHT, "They come they go", this);
		handler = new Handler();
		hud = new HUD (handler);
		rand = new Random();

		this.addKeyListener(new keyInput(handler));

	}


	public synchronized void start () {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop () {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run () {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				hud.setFrames(frames);
				frames = 0;
			}
		}
	
		stop();
	}
	private void getFilesFromFolder(String folderName) {
		try {
		Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+folderName));
		classes = walk
		        .filter(Files::isRegularFile)
		        .map(s -> s.getFileName().toString())
		        .map(s -> s.substring(0,s.lastIndexOf(".")))
		        .collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void tick() {

		handler.tick();
		hud.tick();
		
		
		if (handler.getNumberOfObjects() < MAX_OBJECTS) {
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			
			Color randomColor = new Color(r, g, b);
			
			int randomX = ThreadLocalRandom.current().nextInt(0, WIDTH);
			int randomY = ThreadLocalRandom.current().nextInt(0, HEIGHT);
			

			getFilesFromFolder("\\bin\\\\passc2\\Objects");
			if (classes.size() == 0) return;
			int randomClass = rand.nextInt(classes.size());
			try {
				Class c = Class.forName("passc2."+classes.get(randomClass));
				handler.addObject( (GameObject) c.getConstructor(int.class, int.class, Handler.class, Color.class).newInstance(randomX,randomY, handler, randomColor));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		hud.render(g);
		handler.render(g);
		g.dispose();
		bs.show();
	}

	public static void main (String args[]) {
		new Game();
	}
	
	
}
