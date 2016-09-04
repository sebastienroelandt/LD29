package fr.sebastien.LD29.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Scene implements Comparable<Scene> {
	
	public enum STATE { ON, FREEZE }
	private STATE currentState;
		
	private int priority = 0;
		
	public Scene ()
	{
		currentState = STATE.ON;
	}
		
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
		
	}
		
	protected void customUpdate(GameContainer gc, int delta) throws SlickException
	{
		
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		
	}
		
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		customRender(gc,g);
	}
		
	public void update(GameContainer gc, int delta) throws SlickException
	{
		if(currentState==STATE.ON)
			customUpdate(gc, delta);
	}
	
	public int getPriority()
	{
		return priority;
	}
		
	public void setState(STATE state)
	{
		currentState = state;
	}
		
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	@Override
	public int compareTo(Scene compareScene) {
		
		if(this.getPriority()<compareScene.getPriority())
			return -1;
		else if (this.getPriority()==compareScene.getPriority())
			return 1;
		else 
			return 0;
	}

}
