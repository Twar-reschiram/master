package data;

import sprites.Sprites;

public enum Resources {
	
	Grass(101, Sprites.Ground, 0);
	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	
	private Resources(int id, Sprites sprites, int... spriteIDs){
		this.id = id;
		this.spriteIds = spriteIDs;
		this.sprites = sprites;
	}
	
	public int getID(){
		return id;
	}
	
	public Sprites getSprites(){
		return sprites;
	}
	
	public int[] getSpriteIDs(){
		return spriteIds;
	}

}
