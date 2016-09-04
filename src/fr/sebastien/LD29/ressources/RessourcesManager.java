package fr.sebastien.LD29.ressources;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class RessourcesManager {
	
	//Ressouces
	private Map <String, TrueTypeFont> fontMap;
	private Map <String, TiledMap> tiledMap;
	private Map <String, Image> imageMap;
	private ArrayList <String> deathMap;
	private Map <String,Sound> soundMap;
	private Map <String,Music> musicMap;
	
	public RessourcesManager()
	{
		fontMap = new HashMap<String, TrueTypeFont>();
		tiledMap = new HashMap<String, TiledMap>();
		imageMap = new HashMap<String, Image>();
		deathMap = new ArrayList<String>();
		soundMap = new HashMap<String, Sound>();
		musicMap = new HashMap<String, Music>();
	}
	
	//Load Ressouces
	public boolean loadRessources(){
		
		try {
			addFont("TEXTE1","res/font/AldotheApache.ttf",20);
			addFont("TEXTE2","res/font/AldotheApache.ttf",36);
			addTiledMap("START","res/map/Start.tmx");
			addTiledMap("INTRO","res/map/Intro.tmx");
			addTiledMap("JUMP","res/map/Jump.tmx");
			addTiledMap("MONSTERS","res/map/Monster.tmx");
			addTiledMap("HOPE","res/map/Hope.tmx");
			addTiledMap("GOOD LUCK!","res/map/Cheerup.tmx");
			addTiledMap("END","res/map/End.tmx");
			addImage("MENU","res/image/menu.png");
			addText("Again ?!");
			addText("F***");
			addText("If we continue like this, we will breaking the record of deaths");
			addText("....");
			addText("I don't like dying !");
			addText("Dying is just an excuse to restart the level... :/ ");
			addSound("BUTTON","res/sound/button.wav");
			addSound("SAUT","res/sound/saut3.wav");
			addMusic("MUSIC","res/music/music.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private TrueTypeFont addFont(String id, String path, float height) throws SlickException 
	{
	     TrueTypeFont font = null;
	     try{
	    	InputStream inputStream	= ResourceLoader.getResourceAsStream(path);
	    	
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(height);
			font = new TrueTypeFont(awtFont, true);
	     } catch (Exception e) {
	          throw new SlickException("Could not load font", e);
	     }
	
	     this.fontMap.put(id, font);
	
	     return font;
	 }
	
	public TrueTypeFont getFont(String nameFont){
		return fontMap.get(nameFont);
	}
	
	private void addTiledMap(String id, String path) throws SlickException {

	     TiledMap map = new TiledMap(path);
	     this.tiledMap.put(id, map);//on ajoute l'image à la liste
	 }
	
	public TiledMap getTiledMap(String nameTiledMap){
		return tiledMap.get(nameTiledMap);
	}
	
	private void addImage(String id, String path) throws SlickException {

	     Image image = new Image(path);
	     this.imageMap.put(id, image);//on ajoute l'image à la liste
	 }
	
	public Image getImage(String nameImage){
		return imageMap.get(nameImage);
	}
	
	private void addText(String message){
	     this.deathMap.add(message);//on ajoute l'image à la liste
	 }
	
	public String getText(){
		int rand = (int) (Math.random()*deathMap.size());
		return deathMap.get(rand);
	}
	
	private void addSound(String id, String path){

		Sound sound;
		try {
			sound = new Sound (path);
			this.soundMap.put(id, sound);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Sound getSound(String soundName){
		return soundMap.get(soundName);
	}
	
	private void addMusic(String id, String path){
			Music music;
			try {
				music = new Music(path);
				music.setVolume(0.3f);
				this.musicMap.put(id, music);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Music getMusic(String musicName){
		return musicMap.get(musicName);
	}
}
