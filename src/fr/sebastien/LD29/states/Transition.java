package fr.sebastien.LD29.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class Transition extends Scene{
	
	private int time;
	private String string;
	private int stringX, stringY;
	TrueTypeFont font;
	
	public Transition()
	{
		super();
		setPriority(3);
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		if(time!=0){
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			font.drawString(stringX, stringY, string, Color.white);
		}
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		if(time!=0)
		{
			Game.manager.getScene("Play").setState(STATE.FREEZE);
			time--;
		}
		else Game.manager.getScene("Play").setState(STATE.ON);
			
			
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		font = Game.rm.getFont("TEXTE2");
		time=0;
	}
	
	public String toString()
	{
		return "Transition";
	}
	
	public void setMessage(String string)
	{
		this.string=string;
		stringX=MainClass.WIDTH/2-font.getWidth(string)/2;
		stringY=MainClass.HEIGHT/2-font.getHeight(string)/2;
		this.time=200;
	}

}