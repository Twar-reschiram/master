package data;

import sprites.Sprites;

public abstract class Resource {

	private int id;
	private Sprites sprites;
	private int[] spriteIds;

	public Resource(int id, Sprites sprites, int[] spriteIDs) {
		this.id = id;
		this.sprites = sprites;
		this.spriteIds = spriteIDs;
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
