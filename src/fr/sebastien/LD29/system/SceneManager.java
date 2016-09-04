package fr.sebastien.LD29.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fr.sebastien.LD29.states.Scene;

public class SceneManager  {
	
	private List<Scene> scenes;
	private GameContainer gc;
	
	public SceneManager (GameContainer gc)
	{
		this.gc = gc;
		this.scenes = new ArrayList<Scene>();
	}
	
	public void addScene (Scene scene)
	{
		scenes.add(scene);
		try {
			scene.init(gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Collections.sort(scenes);
	}
	
	public boolean removeScene (String scene)
	{
		for(int i=0; i < scenes.size();i++)
		{
			if(scenes.get(i).toString().equals(scene))
			{
				scenes.remove(i);
				return true;
			}
		}
		return false;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(int i=0; i<scenes.size();i++)
		{
			scenes.get(i).render(gc, g);
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		for(int i=0; i<scenes.size();i++)
		{
			scenes.get(i).update(gc, delta);
		}
	}
	
	public Scene getScene(String scene)
	{
		for(int i=0; i < scenes.size();i++)
		{
			if(scenes.get(i).toString().equals(scene))
			{
				return scenes.get(i);
			}
		}
		return null;
	}

}
