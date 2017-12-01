package data;

import sprites.Sprites;
import anim.AnimationType;

public enum Resources {
	
	Grass1(101, Sprites.Ground , AnimationType.NONE ,  0),	
	Grass2(102, Sprites.Ground , AnimationType.NONE ,  1),	
	Grass3(105, Sprites.Ground , AnimationType.NONE ,  4),	
	Sand1 (103,  Sprites.Ground, AnimationType.NONE ,  2),	
	Sand2 (104,  Sprites.Ground, AnimationType.NONE ,  3),	
	Water1(106,  Sprites.Water1, AnimationType.WATER,  0,1,2,3,4,5,6,7),	
	Water2(107,  Sprites.Water2, AnimationType.WATER,  0,1,2,3,4,5,6,7),	
	Water3(108,  Sprites.Water3, AnimationType.WATER,  0,1,2,3,4,5,6,7),	
	Water4(109,  Sprites.Water4, AnimationType.WATER,  0,1,2,3,4,5,6,7);	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	private AnimationType animType;
	
	private static Resources[] res = loadRescources();
	private static Resources[] loadRescources() {
		Resources[] res = new Resources[9];
		res[0] = Grass1;
		res[1] = Grass2;
		res[2] = Grass3;
		res[3] = Sand1;
		res[4] = Sand2;
		res[5] = Water1;
		res[6] = Water2;
		res[7] = Water3;
		res[8] = Water4;
		return res;
	}
	
	private Resources(int id, Sprites sprites, AnimationType animType, int... spriteIDs){
		this.id = id;
		this.spriteIds = spriteIDs;
		this.sprites = sprites;
		this.animType = animType;
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
	
	public boolean hasAnimation(){
		if(this.animType==null)return false;
		else if(this.animType.equals(AnimationType.NONE)) return false;
		return true;
	}
	
	public AnimationType getAnimationType(){
		return this.animType;
	}

}
