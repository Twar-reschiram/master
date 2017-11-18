package data;

import Data.Image.SpriteSheet;
import sprites.Sprites;

public enum Resources {
	
	Grass(101, Sprites.Ground, 0);	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	
	private static Resources[] res = loadRescources();
	private static Resources[] loadRescources() {
		Resources[] res = new Resources[1];
		res[0] = Grass;
		return res;
	}
	
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

	public static Resources getResource(int res) {
		for(Resources r: Resources.res){
			if(r.getID()==res)return r;
		}
		return null;
	}

}
