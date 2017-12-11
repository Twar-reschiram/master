package game.vehicle;

import java.awt.Dimension;

import Data.Location;
import Data.Image.Image;
import data.VehicleResource;
import game.map.Map;

public class VehiclePart {
	
	private Image image;	
	private int hp;
	private VehicleResource vehicleResource;

	public VehiclePart(VehicleResource vehicleResource, Location location){
		this.vehicleResource = vehicleResource;
		hp = vehicleResource.getHP();
		this.image = new Image(location, new Dimension(vehicleResource.getSize().width*Map.DEFAULT_SQUARESIZE, vehicleResource.getSize().height*Map.DEFAULT_SQUARESIZE), "", vehicleResource.getSprites().getSpriteSheet(), null);
		this.image.setSpriteState(this.vehicleResource.getSpriteIDs()[0]);
	}
	
	public boolean isDead(){
		return hp==0;
	}
	
	public int getHP(){
		return hp;
	}
	
	public int getMaxHP(){
		return vehicleResource.getHP();
	}
	
	public void damage(int amount){
		hp-=amount;
		if(hp<0)hp=0;
	}
	
	public void heal(int amount){
		hp+=amount;
		if(hp>vehicleResource.getHP())hp = vehicleResource.getHP();
	}

	public Image getImage() {
		return image;
	}

	public VehicleResource getVehicleResource() {
		return vehicleResource;
	}
}
