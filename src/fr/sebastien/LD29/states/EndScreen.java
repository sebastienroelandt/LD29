package fr.sebastien.LD29.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import fr.sebastien.LD29.states.Scene.STATE;
import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class EndScreen extends Scene{
	
	private int time;
	private String string;
	private String string1;
	private String string2;
	private String string3;
	private int stringX, stringX1,stringX2,stringX3;
	TrueTypeFont font;
	
	public EndScreen()
	{
		super();
		setPriority(3);
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		if(time!=0){
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			font.drawString(stringX, MainClass.HEIGHT/2-60, string, Color.white);
			font.drawString(stringX1, MainClass.HEIGHT/2-20, string1, Color.white);
			font.drawString(stringX2, MainClass.HEIGHT/2+20, string2, Color.white);
			font.drawString(stringX3, MainClass.HEIGHT/2+60, string3, Color.white);
		}
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		if(time!=0)
		{
			time--;
		}
		else
		{
			Game.manager.removeScene("EndScreen");
    		Game.manager.addScene(new Menu());
		}
			
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		Game.music.stop();
		font = Game.rm.getFont("TEXTE2");
		
		int deathCount = Game.deathCount;
		long time = System.currentTimeMillis()-Game.time;
		int timeSec=(int)time/1000;
		int timeAvant=((int)time/100)%10;
		this.string=" Score :";
		this.string1=" Number of death : " + deathCount;
		this.string2=" Time : " + timeSec + "."+timeAvant;
		float score = (float)((time*100*10000)/480000)-50*deathCount;
		this.string3=" Score : " + (int)score;
		
		stringX=MainClass.WIDTH/2-font.getWidth(string)/2;
		stringX1=MainClass.WIDTH/2-font.getWidth(string1)/2;
		stringX2=MainClass.WIDTH/2-font.getWidth(string2)/2;
		stringX3=MainClass.WIDTH/2-font.getWidth(string3)/2;
		this.time=300;
	}
	
	public String toString()
	{
		return "EndScreen";
	}
	
}