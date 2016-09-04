package fr.sebastien.LD29.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import fr.sebastien.LD29.basicObjects.Button;
import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class DialogBox extends Scene{
	
	private int time;
	private String message;
	private TrueTypeFont font;
	private int positionX,positionY;
	
	public DialogBox()
	{
		super();
		setPriority(2);
		message="test";
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		g.fillRect(0, 500, 800, 600);
		g.setColor(Color.white);
		font.drawString(positionX, positionY, message);
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		if(time!=0)time--;
		else message=" ";
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		font = Game.rm.getFont("TEXTE1");
	}
	
	public String toString()
	{
		return "DialogBox";
	}
	
	public void setMessage(String message)
	{
		this.message="\""+message+"\"";
		positionX=MainClass.WIDTH/2-font.getWidth(this.message)/2;
		positionY=500+100/2-font.getHeight(this.message)/2;
		this.time=300;
	}

}