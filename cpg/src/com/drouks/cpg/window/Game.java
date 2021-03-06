package com.drouks.cpg.window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.drouks.cpg.framework.KeyInput;
import com.drouks.cpg.framework.ObjectId;
import com.drouks.cpg.framework.Texture;
import com.drouks.cpg.objects.Block;
import com.drouks.cpg.objects.Player;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 3666500642956540847L;

	private boolean running = false;
	private Thread thread;
	Handler handler;
	Camera cam;
	static Texture tex;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null, levelbg = null;
	
    
	Block block;
	Random rand = new Random();
    
    public synchronized void start()
    {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
    }
    
    private void init()
    {
    	WIDTH = getWidth();
    	HEIGHT = getHeight();
    	
    	tex = new Texture();
    	
    	BufferedImageLoader loader = new BufferedImageLoader();
    	level = loader.loadImage("/level.png"); //levelload
    	levelbg = loader.loadImage("/levelbg.png");
    	handler = new Handler();
    	
    	cam = new Camera(0,0);
    	
    	loadImageLevel(level);
//    	handler.addObject(new Player(100,100,handler,ObjectId.Player));
//         
//    	handler.createLevel();
    	
    	this.addKeyListener(new KeyInput(handler));
    	
    }
	
	public void run()
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		handler.tick();
		
		for(int i =0; i<handler.object.size();i++)
		{
			if(handler.object.get(i).getId() == ObjectId.Player)
			{
				cam.tick(handler.object.get(i));
				System.out.println("Player speed X: "+handler.object.get(i).getVelX()+" Y: "+handler.object.get(i).getVelY());
				
				
			}
		}
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//----------------//
		
			
		g.setColor(new Color(0, 10, 28));
		g.fillRect(0, 0, getWidth(), getHeight());
	
		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam
		
		for(int xx= -levelbg.getWidth(); xx<levelbg.getWidth()*5;xx+= levelbg.getWidth())
		{
			g.drawImage(levelbg, xx, 0, null);
		}
		
		handler.render(g);
		
	
		
		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		//===============//
		
		g.dispose();
		bs.show();
	}
	
	private void loadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx<w; xx++)
		{
			for(int yy=0;yy<h;yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255 & green == 255 & blue == 255) //checks if block
				{
					handler.addObject(new Block(xx*32,yy*32,0,ObjectId.Block));
				}
				if(red==0 & green == 0 & blue == 255) //checks if player
				{
					handler.addObject(new Player(xx*32,yy*32,handler,ObjectId.Player));
				}
				if(red==164 & green == 164 & blue == 164) 
				{
					handler.addObject(new Block(xx*32,yy*32,1,ObjectId.Block));
				}
				if(red==53 & green == 53 & blue == 53) 
				{
					handler.addObject(new Block(xx*32,yy*32,2,ObjectId.Block));
				}
			
			
			}

		}
		
	}
	
	public static Texture getInstance()
	{
		return tex;
	}
	
	public static void main(String args[])
	{
		  Game game = new Game(); 
		  new Window(600,800,"CPG", game);
		  game.start();
	}
	
}
