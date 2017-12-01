package data;

import sprites.Sprites;

import java.util.ArrayList;

import anim.AnimationType;

public enum Resources {
	
	Grass1(101, Sprites.Ground, AnimationType.NONE , true, 0, 0),	
	Grass2(102, Sprites.Ground, AnimationType.NONE , true, 0, 1),	
	Grass3(105, Sprites.Ground, AnimationType.NONE , true, 0, 4),	
	Sand1 (103, Sprites.Ground, AnimationType.NONE , true, 0, 2),	
	Sand2 (104, Sprites.Ground, AnimationType.NONE , true, 1, 3),	
	Water1(106, Sprites.Water1, AnimationType.WATER, true, 0, 0,1,2,3,4,5,6,7),	
	Water2(107, Sprites.Water2, AnimationType.WATER, true, 0, 0,1,2,3,4,5,6,7),	
	Water3(108, Sprites.Water3, AnimationType.WATER, true, 0, 0,1,2,3,4,5,6,7),	
	Water4(109, Sprites.Water4, AnimationType.WATER, true, 0, 0,1,2,3,4,5,6,7);	
	
	private int id;
	private Sprites sprites;
	private int[] spriteIds;
	private AnimationType animType;
	private int layerUp;
	private boolean ground;
	
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

	private static Resources[] downRes = loadDownRescources();
	private static Resources[] loadDownRescources() {
		ArrayList<Resources> resources = new ArrayList<>();
		for(Resources resource: Resources.res){
			if(resource.layerUp==0)resources.add(resource);
		}
		Resources[] downRes = new Resources[resources.size()];
		downRes = resources.toArray(downRes);
		return downRes;
	}
	
	private static Resources[] upRes = loadUpRescources();
	private static Resources[] loadUpRescources() {
		ArrayList<Resources> resources = new ArrayList<>();
		for(Resources resource: Resources.res){
			if(resource.layerUp==1)resources.add(resource);
		}
		Resources[] upRes = new Resources[resources.size()];
		upRes = resources.toArray(upRes);
		return upRes;
	}
	
	private Resources(int id, Sprites sprites, AnimationType animType, boolean ground, int layerUp, int... spriteIDs){
		this.id = id;
		this.spriteIds = spriteIDs;
		this.sprites = sprites;
		this.animType = animType;
		this.layerUp = layerUp;
		this.ground = ground;
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
	
	public boolean isGround(){
		return ground;
	}
}
