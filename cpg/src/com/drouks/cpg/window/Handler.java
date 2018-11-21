package com.drouks.cpg.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.drouks.cpg.framework.GameObject;
import com.drouks.cpg.framework.ObjectId;
import com.drouks.cpg.objects.Block;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public GameObject tempObject;
	
	public void tick()
	{
		for(int i=0; i<object.size();i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i=0; i<object.size();i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
