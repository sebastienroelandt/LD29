package fr.sebastien.LD29.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Bat {
	
	private int positionX, positionY;
	private boolean direction;
	private int time;
	private Rectangle hitBox;
	private boolean isDead;
	
	private Animation current, right, left;
	
	public Bat(int positionX, int positionY, int time)
	{
		try {
			left = new Animation(new SpriteSheet("res/image/bat_left.png",44,42),80);
			right = new Animation(new SpriteSheet("res/image/bat_right.png",44,42),80);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		current = right;
		this.positionX=positionX;
		this.positionY=positionY;
		direction = false;
		this.time=time;
		this.isDead=false;
		hitBox= new Rectangle(positionX+6, positionY+8,positionX+6+26,positionX+8+24);
	}
	
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public void update()
	{
		if(time==0)
		{
			if (direction) direction=false;
			else direction=true;
			time=100;
		}
		else time--;
		
		int deltaX = 2;
		if(!direction) deltaX=-deltaX; 
		
		this.positionX+=deltaX;
		
		if(direction) {
			hitBox= new Rectangle(positionX+6, positionY+8,26,24);
			current=right;
		}
		else{
			hitBox= new Rectangle(positionX+12, positionY+8,26,24);
			current=left;
		}	
	}
	
	public void render(Graphics g, int X, int Y)
	{
		current.draw(X,Y);
		g.setColor(Color.white);
		//if(current==right)g.draw(new Rectangle(X+12,Y+8,26,24));
		//else g.draw(new Rectangle(X+6,Y+8,26,24));
		
	}
	
	public int getX(){return positionX;}
	public int getY(){return positionY;}

}
