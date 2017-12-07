package data;

import sprites.Sprites;

import anim.AnimationType;

public class Resources {
	
	public static Resources Grass1; 		
	public static Resources Grass2; 	
	public static Resources Grass3; 	
	public static Resources Sand1;	  
	public static Resources Sand2;  	
	public static Resources Sand3;  	
	public static Resources Sand4;  
	public static Resources Sand5;  	
	public static Resources Sand6;  	
	public static Resources Sand7;  	
	public static Resources Sand8;  	
	public static Resources Sand9;  	
	public static Resources Sand10; 
	public static Resources Sand11; 
	public static Resources Sand12; 	
	public static Resources Sand13; 	
	public static Resources Flower1; 	
	public static Resources Flower2; 	
	public static Resources Flower3; 	
	public static Resources Flower4; 		
	public static Resources Water1; 	
	public static Resources Water2; 	
	public static Resources Water3; 	
	public static Resources Water4;
	public static Resources Explosion;
	
	private static Resources[] res;
	
	public static Resources create(){		
		Grass1 		= new Resources(101, Sprites.Ground		,AnimationType.NONE 	, 0,  1);	
		Grass2 		= new Resources(102, Sprites.Ground		,AnimationType.NONE 	, 0,  13);	
		Grass3 		= new Resources(103, Sprites.Ground		,AnimationType.NONE 	, 0,  25);	
		Sand1  		= new Resources(104, Sprites.Ground		,AnimationType.NONE 	, 0,  37);	
		Sand2  		= new Resources(105, Sprites.Ground		,AnimationType.NONE 	, 1,  0);	
		Sand3  		= new Resources(106, Sprites.Ground		,AnimationType.NONE 	, 1,  12);
		Sand4  		= new Resources(107, Sprites.Ground		,AnimationType.NONE 	, 1,  24);	
		Sand5  		= new Resources(108, Sprites.Ground		,AnimationType.NONE 	, 1,  36);	
		Sand6  		= new Resources(109, Sprites.Ground		,AnimationType.NONE 	, 1,  48);	
		Sand7 		= new Resources(110, Sprites.Ground		,AnimationType.NONE 	, 1,  60);	
		Sand8 		= new Resources(111, Sprites.Ground		,AnimationType.NONE 	, 1,  72);	
		Sand9 		= new Resources(112, Sprites.Ground		,AnimationType.NONE 	, 1,  84);	
		Sand10 		= new Resources(113, Sprites.Ground		,AnimationType.NONE 	, 1,  96);	
		Sand11 		= new Resources(114, Sprites.Ground		,AnimationType.NONE 	, 1, 108);	
		Sand12 		= new Resources(115, Sprites.Ground		,AnimationType.NONE 	, 1, 120);	
		Sand13 		= new Resources(116, Sprites.Ground		,AnimationType.NONE 	, 1, 132);	
		Flower1		= new Resources(117, Sprites.Ground		,AnimationType.NONE 	, 1,  11);	
		Flower2		= new Resources(118, Sprites.Ground		,AnimationType.NONE 	, 1,  23);	
		Flower3		= new Resources(119, Sprites.Ground		,AnimationType.NONE 	, 1,  35);	
		Flower4		= new Resources(120, Sprites.Ground		,AnimationType.NONE 	, 1,  47);			
		Water1 		= new Resources(121, Sprites.Ground		,AnimationType.WATER	, 0,   2,  3,  4,  5,  6,  7,  8,  9);	
		Water2 		= new Resources(122, Sprites.Ground		,AnimationType.WATER	, 0,  14, 15, 16, 17, 18, 19, 20, 21);	
		Water3 		= new Resources(123, Sprites.Ground		,AnimationType.WATER	, 0,  26, 27, 28, 29, 30, 31, 32, 33);	
		Water4 		= new Resources(124, Sprites.Ground		,AnimationType.WATER	, 0,  38, 39, 40, 41, 42, 43, 44, 45);
//		Explosion	= new Resources(125, Sprites.Explosion	,AnimationType.CANCEL		, false,0,   0,  1,  2,  3,  4,  5,  6,  7,  8);		
		
		res = new Resources[24];
		res[0]  = Grass1;
		res[1]  = Grass2;
		res[2]  = Grass3;
		res[3]  = Sand1;
		res[4]  = Sand2;
		res[5]  = Sand3;
		res[6]  = Sand4;
		res[7]  = Sand5;
		res[8]  = Sand6;
		res[9]  = Sand7;
		res[10] = Sand8;
		res[11] = Sand9;
		res[12] = Sand10;
		res[13] = Sand11;
		res[14] = Sand12;
		res[15] = Sand13;
		res[16] = Flower1;
		res[17] = Flower2;
		res[18] = Flower3;
		res[19] = Flower4;
		res[20] = Water1;
		res[21] = Water2;
		res[22] = Water3;
		res[23] = Water4;
		return null;
	} 	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	private AnimationType animType;
	private int layerUp;
	
	private Resources(int id, Sprites sprites, AnimationType animType, int layerUp, int... spriteIDs){
		this.id = id;
		this.spriteIds = spriteIDs;
		this.sprites = sprites;
		this.animType = animType;
		this.layerUp = layerUp;
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
	
	public int getLayerUp(){
		return layerUp;
	}

	public static Resources[] getResources() {
		return Resources.res;
	}
}
