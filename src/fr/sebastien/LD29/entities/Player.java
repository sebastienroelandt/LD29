package fr.sebastien.LD29.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import fr.sebastien.LD29.states.DialogBox;
import fr.sebastien.LD29.system.Game;

public class Player {
	
	private Sound soundJump;
	
	private int X,Y;
	private int width, height;
	private Rectangle rect;
	
	private int beginningX, beginningY;
	private int timeToDeath;
	
	boolean direction = true;
	
	Animation walkRight,waitRight, current,walkLeft,waitLeft,deathRight,deathLeft;
	
	private int timeJump;
	
	public Player(int X, int Y)
	{
		this.timeToDeath=-1;
		this.beginningX=X;
		this.beginningY=Y;
		this.X=X;
		this.Y=Y;
		this.width=20;
		this.height=50;
		rect = new Rectangle(X,Y,width,height);
		
		soundJump = Game.rm.getSound("SAUT");
		
		timeJump=0;
		try {
			walkRight = new Animation(new SpriteSheet("res/image/walk_right.png",40,64),50);
			waitRight = new Animation(new SpriteSheet("res/image/wait_right.png",40,64),300);
			walkLeft = new Animation(new SpriteSheet("res/image/walk_left.png",40,64),50);
			waitLeft = new Animation(new SpriteSheet("res/image/wait_left.png",40,64),300);
			deathRight = new Animation(new SpriteSheet("res/image/death_right.png",40,64),50);
			deathRight.setLooping(false);
			deathLeft = new Animation(new SpriteSheet("res/image/death_left.png",40,64),50);
			deathLeft.setLooping(false);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		current=waitRight;
	}
	
	public void jump(int collisionTable[][]){
		if((collisionTable[(X+2)/32][((Y+height+5)/32)]!=0 
			|| collisionTable[(X+width-2)/32][((Y+height+5)/32)]!=0)
			&&(collisionTable[(X+2)/32][((Y+height+5)/32)]!=4 
			|| collisionTable[(X+width-2)/32][((Y+height+5)/32)]!=4))
		{
			soundJump.play();
			timeJump=20;
		}
	}
	
	public void gravity(int collisionTable[][]){
		//if(collisionTable[X/32][((Y+height+1)/32)]==0 && collisionTable[(X+width)/32][((Y+height+1)/32)]==0)
		{
			Y=Y+7;
		}
		if (timeJump!=0)
		{
			Y=(int)(Y-20*(float)timeJump/20);
			timeJump--;
		}
	}
	
	public void isVerticalCollision(int collisionTable[][],boolean left,boolean right,boolean up,boolean down)
	{
		//On check que les environs
		int i2 = X/32;
		int j2 = Y/32;
		
		int i3=i2-2;
		if(i3<0)i3=0;
		
		int j3=j2-2;
		if(j3<0)j3=0;
		
		for(int i=i3;i<i2+4;i++)
		{
			for(int j=j3;j<j2+4;j++)
			{
				if(checkCollision(collisionTable[i][j],i,j)==1)
				{
					if(collisionTable[(X)/32][((Y+height)/32)]!=0 || collisionTable[(X+width)/32][((Y+height)/32)]!=0){
						Y=j*32-height-1;
					}
					else if(collisionTable[(X)/32][((Y)/32)]!=0 || collisionTable[(X+width)/32][((Y)/32)]!=0){
						Y=j*32+32+3;
						timeJump=0;
					}
				}
				else if(checkCollision(collisionTable[i][j],i,j)==4)
				{
					if(timeToDeath==-1)timeToDeath=20;
				}
					
				else if(checkCollision(collisionTable[i][j],i,j)==2)
				{
					int decalage = (X+width-i*32);
					if(decalage>=32 || decalage==31) decalage=32;
					Y=j*32-decalage-1-32+14;
				}
				else if(checkCollision(collisionTable[i][j],i,j)==3)
				{
					int decalage = (X-i*32);
					if(decalage>=32 || decalage==31) decalage=32;
					if(decalage<=1 || decalage==2) decalage=0;
					Y=j*32+decalage-height;
				}
			}
		}
	}
	
	public void isHorizontalCollision(int collisionTable[][],boolean left,boolean right,boolean up,boolean down)
	{
		//On check que les environs
		int i2 = X/32;
		int j2 = Y/32;
		
		int i3=i2-2;
		if(i3<0)i3=0;
		
		int j3=j2-2;
		if(j3<0)j3=0;
		
		for(int i=i3;i<i2+2;i++)
		{
			for(int j=j3;j<j2+2;j++)
			{
				if(checkCollision(collisionTable[i][j],i,j)==1)
				{
					if(left){
						X=i*32+32+1;
					}
					if(right){
						X=i*32-width-1;
					}
				}
				else if(checkCollision(collisionTable[i][j],i,j)==4)
				{
					if(timeToDeath==-1)timeToDeath=20;			
				}
			}
		}
	}
	
	public int checkCollision(int collisionTable,int i,int j)
	{
		
		if(collisionTable==1||collisionTable==4)
		{
			int Xb = i*32;
			int Yb = j*32;
			
			if(Y+height < Yb) return 0;
			if(X+width < Xb) return 0;
			if(Y > Yb+32) return 0;
			if(X > Xb+32) return 0;
			
			return collisionTable;
		}
		else if(collisionTable==2)
		{
			
			if (collisionBox(i,j))
				{
					int pointAX=i*32;
					int pointAY=j*32+32;
					int pointBX=i*32+32;
					int pointBY=j*32;
					int pointPx = X+width;
					int pointPy = Y+height;
					int vecteurDX, vecteurDY;
					int vecteurTX, vecteurTY;
					
					
					vecteurDX = pointBX - pointAX;
					vecteurDY = pointBY - pointAY;
					vecteurTX = pointPx - pointAX;
					vecteurTY = pointPy - pointAY;
				    float d = vecteurDX*vecteurTY - vecteurDY*vecteurTX;
				    if (d<0)
				        return 0;  // un point à droite et on arrête tout.
					
					return 2;
				}
			else return 0;
		}
		else if(collisionTable==3)
		{
			
			if (collisionBox(i,j))
				{
					int pointAX=i*32;
					int pointAY=j*32;
					int pointBX=i*32+32;
					int pointBY=j*32+32;
					int pointPx = X;
					int pointPy = Y+height;
					int vecteurDX, vecteurDY;
					int vecteurTX, vecteurTY;
					
					
					vecteurDX = pointBX - pointAX;
					vecteurDY = pointBY - pointAY;
					vecteurTX = pointPx - pointAX;
					vecteurTY = pointPy - pointAY;
				    float d = vecteurDX*vecteurTY - vecteurDY*vecteurTX;
				    if (d<0)
				        return 0;  // un point à droite et on arrête tout.
					
					return 3;
				}
			else return 0;
		}
		else return 0;
	}
	
	public boolean collisionBox(int i,int j)
	{
		int Xb = i*32;
		int Yb = j*32;
		
		if(Y+height < Yb) return false;
		if(X+width < Xb) return false;
		if(Y > Yb+32) return false;
		if(X > Xb+32) return false;

		return true;
	}
	
	public boolean collisionWithRect(Rectangle hitBox)
	{
		int X1 = (int) hitBox.getX();
		int Y1 = (int) hitBox.getY();
		int X2 = (int) (hitBox.getX()+hitBox.getWidth());
		int Y2 = (int) (hitBox.getY()+hitBox.getHeight());
		
		if(Y+height < Y1) return false;
		if(X+width < X1) return false;
		if(X > X2) return false;
		if(Y > Y2) return false;
		
		if(timeToDeath==-1)timeToDeath=20;	
		return true;
	}
	
	public int getTimeToDeath(){return timeToDeath;}
	
	public void futurDeath()
	{
		if(timeToDeath==0) death();
		else if (timeToDeath!=-1) timeToDeath--;
	}
	
	public void death()
	{
		X=beginningX;
		Y=beginningY;
		DialogBox scene = (DialogBox)Game.manager.getScene("DialogBox");
		scene.setMessage(Game.rm.getText());
		Game.deathCount++;
		timeToDeath = -1;
	}
	
	public void update(int delta, Input input, int collisionTable[][])
	{
		boolean canWalking = false;
		if(collisionTable[X/32][((Y+height+5)/32)]!=0 || collisionTable[(X+width)/32][((Y+height+5)/32)]!=0)
			canWalking = true;
		
		boolean left = input.isKeyDown(input.KEY_LEFT);
		boolean right =input.isKeyDown(input.KEY_RIGHT);
		
		
		if(left) direction=false; 
		if(right) direction=true; 
		
		if(right&&!left&&canWalking) current = walkRight;
		else if(left&&!right&&canWalking) current = walkLeft;
		else if(direction) current = waitRight;
		else current = waitLeft;
		
		if (timeToDeath!=-1)
		{
			if(direction)current = deathRight;
			else current = deathLeft;
		}
		else if(deathRight.getCurrentFrame()==deathRight.getImage(3))deathRight.restart();
		else if(deathLeft.getCurrentFrame()==deathLeft.getImage(3))deathLeft.restart();
	}
	
	public void render(Graphics g, int X, int Y)
	{
		rect = new Rectangle(X,Y,width,height);
		current.draw(X-11,Y-14);
		g.setColor(Color.white);
		//g.drawString(collision, 10, 200);
		//g.draw(rect);
	}
	
	public int getX(){return X;}
	public int getY(){return Y;}
	public void setX(int X){this.X=X;}
	public void setY(int Y){this.Y=Y;}

}
