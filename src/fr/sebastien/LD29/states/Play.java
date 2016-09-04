package fr.sebastien.LD29.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;

import fr.sebastien.LD29.entities.Bat;
import fr.sebastien.LD29.entities.Player;
import fr.sebastien.LD29.system.Game;
import fr.sebastien.LD29.system.MainClass;

public class Play extends Scene {
	
	private ArrayList<Bat> bats;
	
	public String level;
	
	public ArrayList<String> messages;
	public ArrayList<Integer> messagesPosition;
	
	private Rectangle End;
	
	private int tileX, tileY;
	
	public int x,y;
	
	private TiledMap map;
	private Player player;
	private int collisionTable[][];
	
	private int cameraX, cameraY;
	private int playerScreenX, playerScreenY;
	
	private int mapWidth, mapHeight;

	public Play (String level)
	{
		super();
		setPriority(1);
		this.level=level;
	}
	
	protected void customRender(GameContainer gc, Graphics g) throws SlickException
	{
	    map.render(cameraX,cameraY);
	    /*
	    for(int i=0;i<50;i++)
		{
			for(int j=0;j<40;j++)
			{
				g.drawString(Integer.toString(collisionTable[i][j]), i*32+cameraX, j*32+cameraY);
			}
		}*/
		
		//map.render(0, 0);
		player.render(g,playerScreenX,playerScreenY);
		
		for(int i = 0; i < bats.size(); ++i)
	    {
	    	bats.get(i).render(g,bats.get(i).getX() + cameraX,bats.get(i).getY() + cameraY);
	    }
	}
	
	protected void customUpdate(GameContainer gc, int t) throws SlickException
	{
		// Get touch
		Input input = gc.getInput();
		boolean left = input.isKeyDown(input.KEY_LEFT);
		boolean right =input.isKeyDown(input.KEY_RIGHT);
		boolean up = input.isKeyPressed(input.KEY_UP);
		boolean down =input.isKeyDown(input.KEY_DOWN);
		
		//Change sprite
		player.update(t, input, collisionTable);
		
		//Check collision
		if((player.getTimeToDeath()==-1))
		{
			if(up){
				player.jump(collisionTable);
			}
		}
		
		player.gravity(collisionTable);
		player.isVerticalCollision(collisionTable, left, right, up, down);
		
		if((player.getTimeToDeath()==-1))
		{
			if(left){
				player.setX(player.getX()-5);
			}
			if(right){
				player.setX(player.getX()+5);
			}
		}
		
		player.isHorizontalCollision(collisionTable, left, right, up, down);
		
		//Change the position of the camera
		cameraX =  MainClass.WIDTH / 2 -player.getX();
		cameraY =  MainClass.HEIGHT / 2 -player.getY();
	      
	     //if the camera reaches the left or right edge of the screen, lock it      
	    if(cameraX > 0) cameraX = 0;
	    if(cameraX <  MainClass.WIDTH - mapWidth) cameraX = MainClass.WIDTH - mapWidth;
	      
	      //if the camera reaches the top or bottom edge of the screen, lock it      
	    if(cameraY > 0) cameraY = 0;
	    if(cameraY < MainClass.HEIGHT - mapHeight) cameraY = MainClass.HEIGHT - mapHeight;
	      
	      //calculate the player's drawing position on the screen
	    playerScreenX = player.getX() + cameraX;
	    playerScreenY = player.getY() + cameraY;
	    
	    //Check if the player is at the end
	    if(player.getX()+50>End.getX()
	    		&&player.getX()<End.getX()+End.getWidth()
	    		&&player.getY()+20>End.getY()
	    		&&player.getY()<End.getY()+End.getHeight())
	    {
	    	if(Game.levels.isEmpty()){
	    		Game.manager.removeScene("Play");
	    		Game.manager.removeScene("Transition");
	    		Game.manager.removeScene("TopInformationBox");
	    		Game.manager.removeScene("DialogBox");
	    		Game.manager.addScene(new EndScreen());
	    	}
	    	else
	    	{
	    		Game.manager.removeScene("Play");
		    	Game.manager.addScene(new Play(Game.levels.get(0)));
		    	Game.levels.remove(0);
	    	}
	    }
	    
	    //Check messages
	    for (int i = 0; i < messages.size(); ++i) 
		{
		    if(player.getX()>messagesPosition.get(i))
		    {
		    	DialogBox scene = (DialogBox)Game.manager.getScene("DialogBox");
		    	scene.setMessage(messages.get(i));
		    	messages.remove(i);
		    	messagesPosition.remove(i);
				i--;
			}
		}
	    
	    player.futurDeath();
	    
	    for(int i = 0; i < bats.size(); ++i)
	    {
	    	bats.get(i).update();
	    	player.collisionWithRect(bats.get(i).getHitBox());
	    }
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		Transition transition = (Transition) Game.manager.getScene("Transition");
    	transition.setMessage(level);
		
		map= Game.rm.getTiledMap(level);
		tileX=map.getWidth();
		tileY=map.getHeight();
		collisionTable = new int[tileX][tileY];
		mapWidth = tileX*32;
		mapHeight = tileY*32;
		
		player = new Player(map.getObjectX(0, 0),map.getObjectY(0, 0));
		End = new Rectangle(map.getObjectX(1, 0),map.getObjectY(1, 0),map.getObjectHeight(1,0),map.getObjectWidth(1,0));

		for(int i=0;i<tileX;i++)
		{
			for(int j=0;j<tileY;j++)
			{
				if(map.getTileId(i, j, map.getLayerIndex("background"))<=16
						||map.getTileId(i, j, map.getLayerIndex("background"))==35)
				{
					collisionTable[i][j]=1;
				}
				if(map.getTileId(i, j, map.getLayerIndex("background"))==33
						||map.getTileId(i, j, map.getLayerIndex("background"))==34)
				{
					collisionTable[i][j]=4;
				}
				else if(map.getTileId(i, j, map.getLayerIndex("background"))==18)
				{
					collisionTable[i][j]=2;
				}
				else if(map.getTileId(i, j, map.getLayerIndex("background"))==17)
				{
					collisionTable[i][j]=3;
				}
			}
		}
		
		//Message management
		int objectCount = map.getObjectCount(2);
		messages = new ArrayList<String>();
		messagesPosition = new ArrayList<Integer>();
	    for( int oi=0; oi < objectCount; oi++ ) // oi = object index
	    {
	    	messages.add(map.getObjectProperty(2, oi, "message"," "));
	    	messagesPosition.add(map.getObjectX(2, oi));
	    }
	    
	    objectCount = map.getObjectCount(3);
		bats = new ArrayList<Bat>();
	    for( int oi=0; oi < objectCount; oi++ ) // oi = object index
	    {
	    	bats.add(new Bat(map.getObjectX(3, oi),map.getObjectY(3, oi),Integer.parseInt(map.getObjectProperty(3, oi, "time","100"))));
	    }
	}
	
	public String toString()
	{
		return "Play";
	}
}
