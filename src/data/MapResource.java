package data;

import sprites.Sprites;

import anim.AnimationType;

public class MapResource extends Resource {
	
	public static MapResource Grass1; 		
	public static MapResource Grass2; 	
	public static MapResource Grass3; 	
	public static MapResource Sand1;	  
	public static MapResource Sand2;  	
	public static MapResource Sand3;  	
	public static MapResource Sand4;  
	public static MapResource Sand5;  	
	public static MapResource Sand6;  	
	public static MapResource Sand7;  	
	public static MapResource Sand8;  	
	public static MapResource Sand9;  	
	public static MapResource Sand10; 
	public static MapResource Sand11; 
	public static MapResource Sand12; 	
	public static MapResource Sand13; 	
	public static MapResource Flower1; 	
	public static MapResource Flower2; 	
	public static MapResource Flower3; 	
	public static MapResource Flower4; 		
	public static MapResource Water1; 	
	public static MapResource Water2; 	
	public static MapResource Water3; 	
	public static MapResource Water4;
	public static MapResource Explosion;
	
	private static MapResource[] res;
	
	public static MapResource create(){		
		Grass1 		= new MapResource(101, Sprites.Ground		,AnimationType.NONE 	, 0,  1);	
		Grass2 		= new MapResource(102, Sprites.Ground		,AnimationType.NONE 	, 0,  13);	
		Grass3 		= new MapResource(103, Sprites.Ground		,AnimationType.NONE 	, 0,  25);	
		Sand1  		= new MapResource(104, Sprites.Ground		,AnimationType.NONE 	, 0,  37);	
		Sand2  		= new MapResource(105, Sprites.Ground		,AnimationType.NONE 	, 1,  0);	
		Sand3  		= new MapResource(106, Sprites.Ground		,AnimationType.NONE 	, 1,  12);
		Sand4  		= new MapResource(107, Sprites.Ground		,AnimationType.NONE 	, 1,  24);	
		Sand5  		= new MapResource(108, Sprites.Ground		,AnimationType.NONE 	, 1,  36);	
		Sand6  		= new MapResource(109, Sprites.Ground		,AnimationType.NONE 	, 1,  48);	
		Sand7 		= new MapResource(110, Sprites.Ground		,AnimationType.NONE 	, 1,  60);	
		Sand8 		= new MapResource(111, Sprites.Ground		,AnimationType.NONE 	, 1,  72);	
		Sand9 		= new MapResource(112, Sprites.Ground		,AnimationType.NONE 	, 1,  84);	
		Sand10 		= new MapResource(113, Sprites.Ground		,AnimationType.NONE 	, 1,  96);	
		Sand11 		= new MapResource(114, Sprites.Ground		,AnimationType.NONE 	, 1, 108);	
		Sand12 		= new MapResource(115, Sprites.Ground		,AnimationType.NONE 	, 1, 120);	
		Sand13 		= new MapResource(116, Sprites.Ground		,AnimationType.NONE 	, 1, 132);	
		Flower1		= new MapResource(117, Sprites.Ground		,AnimationType.NONE 	, 1,  11);	
		Flower2		= new MapResource(118, Sprites.Ground		,AnimationType.NONE 	, 1,  23);	
		Flower3		= new MapResource(119, Sprites.Ground		,AnimationType.NONE 	, 1,  35);	
		Flower4		= new MapResource(120, Sprites.Ground		,AnimationType.NONE 	, 1,  47);			
		Water1 		= new MapResource(121, Sprites.Ground		,AnimationType.WATER	, 0,   2,  3,  4,  5,  6,  7,  8,  9);	
		Water2 		= new MapResource(122, Sprites.Ground		,AnimationType.WATER	, 0,  14, 15, 16, 17, 18, 19, 20, 21);	
		Water3 		= new MapResource(123, Sprites.Ground		,AnimationType.WATER	, 0,  26, 27, 28, 29, 30, 31, 32, 33);	
		Water4 		= new MapResource(124, Sprites.Ground		,AnimationType.WATER	, 0,  38, 39, 40, 41, 42, 43, 44, 45);
//		Explosion	= new MapResource(125, Sprites.Explosion	,AnimationType.CANCEL		, false,0,   0,  1,  2,  3,  4,  5,  6,  7,  8);		
		
		res = new MapResource[24];
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
	
	private AnimationType animType;
	private int layerUp;
	
	private MapResource(int id, Sprites sprites, AnimationType animType, int layerUp, int... spriteIDs){
		super(id, sprites, spriteIDs);
		this.animType = animType;
		this.layerUp = layerUp;
	}

	public static MapResource getMapResource(int res) {
		for(MapResource r: MapResource.res){
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

	public static MapResource[] getMapResource() {
		return MapResource.res;
	}
}
