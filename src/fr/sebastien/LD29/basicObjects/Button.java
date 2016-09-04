package fr.sebastien.LD29.basicObjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;

public class Button {

	private TrueTypeFont font;
	private String string;
	private int X, Y;
	private int stringX,stringY;
	
	
	private float	x1,y1,
					x2,y2;
	
	public Button(String string,TrueTypeFont font, int X, int Y){
		
		this.font=font;
		this.string=string;
		this.X=X;
		this.Y=Y;
		
		// Box collision
		this.x1=X-font.getWidth(string)/2-10;
		this.y1=Y-font.getHeight(string)/2-10;
		this.x2=X+font.getWidth(string)/2+10;
		this.y2=Y+font.getHeight(string)/2+10;
		
		// 
		stringX=X-font.getWidth(string)/2;
		stringY=Y-font.getHeight(string)/2;
	}
	

	public void render(){
		font.drawString(stringX, stringY, string, Color.white);
	}
	
	public void update(){
		stringX=X-font.getWidth(string)/2;
		stringY=Y-font.getHeight(string)/2;
	}
	
	public boolean CollisionWithMouse(GameContainer gc){
		
		int positionX=gc.getInput().getMouseX();
		int positionY=gc.getInput().getMouseY();
		
		if(positionY < y1) return false;
		if(positionX < x1) return false;
		if(positionY > y2) return false;
		if(positionX > x2) return false;
		return true;
	}
	
	public void setFont(TrueTypeFont font){
		this.font=font;
	}
	
	public float getX1() { return x1;}
	public float getY1() { return y1;}
	public float getX2() { return x2;}
	public float getY2() { return y2;}
	
}
