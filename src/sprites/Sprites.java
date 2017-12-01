package sprites;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Data.Image.Image;
import Data.Image.SpriteSheet;
import game.map.Map;

public class Sprites {


	private static HashMap<String,Sprites> sprites = null;
	
	//public static Sprites BEISPIEL = new Sprites(new Dimension(50,50), "", "Beispiel");
	public static Sprites Slot = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/slot.png", "slot");
	public static Sprites Mouse = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), Image.createEmptyImage(40, 40), "mouse");
	public static Sprites Arrows = new Sprites(new Dimension(30, 30), "/sprites/arrows.png", "arrows");
	public static Sprites Buttons = new Sprites(new Dimension(200, 50), "/sprites/buttons.png", "buttons");
	public static Sprites Sidebar = new Sprites(new Dimension(300, 1049), "/sprites/sidebar3.png", "sidebar");
	public static Sprites Ground = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/ground.png", "ground");
	public static Sprites Water1 = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/water1.png", "water");
	public static Sprites Water2 = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/water2.png", "water");
	public static Sprites Water3 = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/water3.png", "water");
	public static Sprites Water4 = new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "/sprites/water4.png", "water");
	
	private SpriteSheet spritesheet;
	private SpriteSheet shadowSpriteSheet;
	
	public Sprites(Dimension d, Object image, String name){
		if(Sprites.sprites==null)Sprites.sprites = new HashMap<>();
		Sprites.sprites.put(name, this);
		this.spritesheet = new SpriteSheet(image, d);
		this.shadowSpriteSheet = SpriteSheet.createShadowSpriteSheet(this.spritesheet);
//		System.out.println(this.shadowSpriteSheet);
	}
	
	public Sprites(String name, BufferedImage... bis){
		if(Sprites.sprites==null)Sprites.sprites = new HashMap<>();
		Sprites.sprites.put(name, this);
		this.spritesheet = new SpriteSheet(bis);
		this.shadowSpriteSheet = SpriteSheet.createShadowSpriteSheet(this.spritesheet);
	}
	
	public SpriteSheet getSpriteSheet(){
		return spritesheet;	
	}
	
	public SpriteSheet getShadowSpriteSheet(){
		return shadowSpriteSheet;
	}
	
	public static Sprites getSprite(String name){
		return Sprites.sprites.get(name);
	}
}
