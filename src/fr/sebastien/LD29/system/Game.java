package fr.sebastien.LD29.system;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import fr.sebastien.LD29.ressources.RessourcesManager;
import fr.sebastien.LD29.states.Menu;
import fr.sebastien.LD29.states.SplashScreen;

public class Game extends BasicGame {
	
	public static int deathCount;
	public static long time;
	
	public static SceneManager manager;
	public static RessourcesManager rm;
	public static List<String> levels;
	
	public static Music music;
	
	public Game(String title) {
		super(title);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		manager.render(gc, g);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		manager.update(gc, delta);	
	}
	
	public void init(GameContainer gc) throws SlickException {
		
		rm = new RessourcesManager();
		
		manager = new SceneManager(gc);
		manager.addScene(new SplashScreen());
		
		levels = new LinkedList<String>();
		
		
	}
	
}
