package fr.sebastien.LD29.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import fr.sebastien.LD29.system.Game;

public class TopInformationBox extends Scene{
	
	private int timeSec;
	private int timeAvant;
	
	private TrueTypeFont font;
	
	public TopInformationBox()
	{
		super();
		setPriority(2);
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 50);
		g.setColor(Color.white);
		font.drawString(100, 15, "Time : "+timeSec+"."+timeAvant);
		font.drawString(520, 15, "Death count : "+Integer.toString(Game.deathCount));
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		long time = System.currentTimeMillis()-Game.time;
		timeSec=(int)time/1000;
		timeAvant=((int)time/100)%10;
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		font = Game.rm.getFont("TEXTE1");
	}
	
	public String toString()
	{
		return "TopInformationBox";
	}

}