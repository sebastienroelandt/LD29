package fr.sebastien.LD29.states;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.sebastien.LD29.ressources.RessourcesManager;
import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class SplashScreen extends Scene {
	
	
	private Image screen;
	private boolean isLoad;
	private int time;
	
	public SplashScreen () throws SlickException
	{
		super();
		setPriority(1);
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		screen.draw(0, 0, MainClass.WIDTH, MainClass.HEIGHT);
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		if(!isLoad) isLoad = Game.rm.loadRessources();
		else if(time==0)
		{
			Game.music=Game.rm.getMusic("MUSIC");
			Game.manager.addScene(new Menu());
			Game.manager.removeScene("SplashScreen");
		}
		else time--;
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		screen = new Image("res/image/SplashScreen1.png");
		time = 100;
	}
	
	public String toString()
	{
		return "SplashScreen";
	}

}
