package fr.sebastien.LD29.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;

import fr.sebastien.LD29.basicObjects.Button;
import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class Menu extends Scene {
	
	private Button start;
	private Image wallpaper;
	private boolean startIsActived=false;
	private Sound soundButton;
	private TrueTypeFont font;
	private TrueTypeFont font2;
	
	public Menu ()
	{
		super();
		setPriority(1);
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(new Color(31,29,24,255));
		g.fillRect(0, 0, 800, 600);
		wallpaper.draw(-200, 0);
		
		font.drawString(MainClass.WIDTH/2-font.getWidth("Enester")/2, 200, "Enester");
		font2.drawString(MainClass.WIDTH/2-font2.getWidth("Instructions :")/2, 470, "Instructions :");
		font2.drawString(250, 490, "  Use left - right arrow keys to move");
		font2.drawString(250, 510, "  Press UP arrow key to jump");
		
		start.render();
		
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		
		if(start.CollisionWithMouse(gc))
		{
			start.setFont(Game.rm.getFont("TEXTE2"));
			if(startIsActived==false) soundButton.play();
			startIsActived=true;
		}
		else 
		{
			start.setFont(Game.rm.getFont("TEXTE1"));
			startIsActived=false;
		}
			
		start.update();
		
		if(gc.getInput().isMouseButtonDown(0)){
			if(start.CollisionWithMouse(gc)){
				Game.music.loop();
				Game.manager.addScene(new DialogBox());
				Game.manager.addScene(new TopInformationBox());
				Game.manager.addScene(new Transition());
				Game.levels.add("INTRO");
				Game.levels.add("START");
				Game.levels.add("JUMP");
				Game.levels.add("MONSTERS");
				Game.levels.add("HOPE");
				Game.levels.add("GOOD LUCK!");
				Game.levels.add("END");
				Game.manager.addScene(new Play(Game.levels.get(0)));
		    	Game.levels.remove(0);
				Game.manager.removeScene("Menu");
				Game.time=System.currentTimeMillis();
				Game.deathCount=0;
			}
		}
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		start = new Button("Start",Game.rm.getFont("TEXTE1"),fr.sebastien.LD29.system.MainClass.WIDTH/2,fr.sebastien.LD29.system.MainClass.HEIGHT/2);
		wallpaper = Game.rm.getImage("MENU");
		soundButton = Game.rm.getSound("BUTTON");
		font = Game.rm.getFont("TEXTE2");
		font2 = Game.rm.getFont("TEXTE1");
	}
	
	public String toString()
	{
		return "Menu";
	}

}
