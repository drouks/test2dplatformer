package com.drouks.cpg.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.drouks.cpg.window.Handler;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	private boolean[] keys = new boolean[2];
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if (key == KeyEvent.VK_A)
			    {
			        keys[0] = true;
			       
			        tempObject.setVelX(-5);
			    }
			   
			    if (key == KeyEvent.VK_D)
			    {
			        keys[1] = true;
			       
			        tempObject.setVelX(5);
			    }
				if(key == KeyEvent.VK_SPACE) 
					if(!tempObject.onGround) tempObject.setVelY(-10);
				
			 
			}
		}
		
		
		
		if(key==KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if (key == KeyEvent.VK_A)
			    {
			        keys[0] = false;
			    }
			   
			    if (key == KeyEvent.VK_D)
			    {
			        keys[1] = false;
			    }
			   
			    if (!keys[0] && !keys[1])
			    {
			        tempObject.setVelX(0);
			    }
				if(key == KeyEvent.VK_SPACE) {
					
					if(!tempObject.onGround) tempObject.setVelY(-10);
					}
				
			 
			}
		}
	}
}
