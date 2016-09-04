package fr.sebastien.LD29.system;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainClass {
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
		try
		 { 
			AppGameContainer container = new AppGameContainer(new Game("Enester, an unexpected Journey")); 
			container.setDisplayMode(WIDTH, HEIGHT, false); // fenêtre de 1280*768 fullscreen = false ! 
			container.setTargetFrameRate(60); // on règle le FrameRate 
			container.setShowFPS(false);
			container.start(); //on démarre le container 
		 }
		 	catch (SlickException e) {e.printStackTrace();} // l'exception de base de slick !! 

	}

}
