package com.drouks.cpg.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.drouks.cpg.framework.GameObject;
import com.drouks.cpg.framework.ObjectId;

public class Block extends GameObject {

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	
	public void tick(LinkedList<GameObject> object) {
		
	}

	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);
		
		
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}


	

}
