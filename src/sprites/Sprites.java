package sprites;

import java.awt.Dimension;

import Data.Image.Image;
import Data.Image.SpriteSheet;
import game.map.Map;

public class Sprites {


	public static Sprites Explosion;
	public static Sprites Slot;
	public static Sprites Mouse;	
	public static Sprites Arrows;	
	public static Sprites Buttons; 
	public static Sprites Sidebar ;
	public static Sprites Ground; 

	public static Sprites create() {
		Slot 		= new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE	, Map.DEFAULT_SQUARESIZE	), "/sprites/slot.png"				, "slot"		);
		Mouse		= new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE	, Map.DEFAULT_SQUARESIZE	), Image.createEmptyImage(40, 40)	, "mouse"		);
		Arrows		= new Sprites(new Dimension(30					 	, 30					  	), "/sprites/arrows.png"			, "arrows"		);
		Buttons 	= new Sprites(new Dimension(123					 	, 50					  	), "/sprites/buttons.png"			, "buttons"		);
		Sidebar 	= new Sprites(new Dimension(300					 	, 1049					  	), "/sprites/sidebar3.png"			, "sidebar"		);
		Ground  	= new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE	, Map.DEFAULT_SQUARESIZE	), "/sprites/ground.png"			, "ground"		);
		Explosion	= new Sprites(new Dimension(Map.DEFAULT_SQUARESIZE	, Map.DEFAULT_SQUARESIZE	), "/sprites/explosion.png"			, "explosion"	);
		return null;
	}
	
	private SpriteSheet spritesheet;
	private SpriteSheet shadowSpriteSheet;
	
	private Sprites(Dimension d, Object image, String name){
		this.spritesheet = new SpriteSheet(image, d);
		System.out.println(this.spritesheet.getSpriteAmount());
		//this.shadowSpriteSheet = SpriteSheet.createShadowSpriteSheet(this.spritesheet);
	}
	
	public SpriteSheet getSpriteSheet(){
		return spritesheet;	
	}
	
	public SpriteSheet getShadowSpriteSheet(){
		if(this.shadowSpriteSheet == null)this.shadowSpriteSheet = SpriteSheet.createShadowSpriteSheet(spritesheet);
		return shadowSpriteSheet;
	}
}
