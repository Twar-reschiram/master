package data;

import sprites.Sprites;

public enum Resources {
	
	Grass1(101, Sprites.Ground , false, 0),	
	Grass2(102, Sprites.Ground , false, 1),	
	Grass3(105, Sprites.Ground , false, 4),	
	Sand1 (103,  Sprites.Ground, false, 2),	
	Sand2 (104,  Sprites.Ground, false, 3),	
	Water1(106,  Sprites.Water1, true,  0,1,2,3,4,5,6,7,8),	
	Water2(107,  Sprites.Water2, true,  0,1,2,3,4,5,6,7,8),	
	Water3(108,  Sprites.Water3, true,  0,1,2,3,4,5,6,7,8),	
	Water4(109,  Sprites.Water4, true,  0,1,2,3,4,5,6,7,8);	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	private boolean hasAnimation;
	
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
	
	private Resources(int id, Sprites sprites, boolean hasAnimation, int... spriteIDs){
		this.id = id;
		this.spriteIds = spriteIDs;
		this.sprites = sprites;
		this.hasAnimation = hasAnimation;
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
		return hasAnimation;
	}

}
