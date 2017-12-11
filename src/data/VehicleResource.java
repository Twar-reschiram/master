package data;

import java.awt.Dimension;

import anim.AnimationType;
import game.map.Map;
import game.vehicle.VehiclePartType;
import sprites.Sprites;

public class VehicleResource extends Resource{

	public static VehicleResource Armor1; 
	
	private static VehicleResource[] res;
	
	public static MapResource create(){		
		Armor1 		= new VehicleResource(201, Sprites.Ground, new Dimension(1, 1), AnimationType.NONE, VehiclePartType.ARMOR, 100 ,1);		
		
		res = new VehicleResource[1];
		
		res[0] = Armor1;
		return null;
	} 	
	
	public static VehicleResource[] getVehicleResources(){
		return res;
	}
	

	public static int getNONID() {
		return 300;
	}
	
	private AnimationType animType;
	private VehiclePartType partType;
	private Dimension size;
	private int HP;
	
	public VehicleResource(int id, Sprites sprites,Dimension size, AnimationType animtype, VehiclePartType partType, int HP, int... spriteIDs) {
		super(id, sprites, spriteIDs);
		this.animType = animtype;
		this.partType = partType;
		this.size = size;
		this.HP = HP;
	}
	
	public boolean hasAnimation(){
		if(this.animType==null)return false;
		else if(this.animType.equals(AnimationType.NONE)) return false;
		return true;
	}
	
	public AnimationType getAnimationType(){
		return this.animType;
	}
	
	public VehiclePartType getVehiclePartType(){
		return partType;
	}

	public Dimension getSize() {
		return size;
	}

	public int getHP() {
		return HP;
	}

	public static VehicleResource getVehicleResource(int partID) {
		for(VehicleResource res: VehicleResource.res){
			if(res.getID()==partID)return res;
		}
		return null;
	}


}
