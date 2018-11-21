package com.drouks.cpg.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.drouks.cpg.framework.GameObject;
import com.drouks.cpg.framework.ObjectId;
import com.drouks.cpg.framework.Texture;
import com.drouks.cpg.window.Animation;
import com.drouks.cpg.window.Game;
import com.drouks.cpg.window.Handler;

public class Player extends GameObject {

	private float width = 48, height = 96;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk;
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		playerWalk = new Animation(10,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(onGround == false)
		{
			velY += gravity;
			
			if(velY > MAX_SPEED)
			{
				velY = MAX_SPEED;
			}
		}
		
		Collision(object);
		
		playerWalk.runAnimation();
	}
	
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{
				//top
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY()+32;
					velY = 0;
				}
				
				//bot
				if(getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() - height;

					velY = 0;
					onGround = true;
				}
				else
				{
					onGround = false;
				}
				
				//right
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX()-width;
					velX = 0;
		
				}
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX()+(width-13);
					velX = 0;
				}
			}
		}
	}

	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		if (velX !=0)
		{
			playerWalk.drawAnimation(g, (int)x, (int)y,48,96);
		}
		else
		g.drawImage(tex.player[0], (int)x, (int)y, 48, 96,null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int)((int)y+(height/2)),(int)width/2,(int)height/2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int)y,(int)width/2,(int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5),(int)y+5,(int)5,(int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
	}
	
	
	
	

}
