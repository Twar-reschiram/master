package game.vehicle;

import Data.Location;
import Engine.Engine;
import data.VehicleResource;
import game.map.Map;

public class Vehicle {
	
	private VehiclePart[][][] vehicleParts;
	private Location location;
	private int layer;
	
	public Vehicle(int width, int height, Location location, int layer){
		this.location = location;
		this.vehicleParts = new VehiclePart[width][height][1];
		this.layer = layer;
	}
	
	public VehiclePart getPart(int x, int y){
		return vehicleParts[x][y][0];
	}
	
	public void setVehiclePart(int x, int y, int partID){
		VehicleResource res = VehicleResource.getVehicleResource(partID);
		if(vehicleParts[x][y][0] == null){
			if(res==null) return;
			vehicleParts[x][y][0] = new VehiclePart(res, new Location(this.location.x+x*Map.DEFAULT_SQUARESIZE, this.location.y+y*Map.DEFAULT_SQUARESIZE));
			Engine.getEngine(this, this.getClass()).addImage(this.vehicleParts[x][y][0].getImage(), this.layer);
		}else{
			if(vehicleParts[x][y][0].getVehicleResource().equals(res))return;
			if(res!=null){
				vehicleParts[x][y][0].getImage().setSpriteSheet(res.getSprites().getSpriteSheet());
				vehicleParts[x][y][0].getImage().setSpriteState(res.getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).addImage(this.vehicleParts[x][y][0].getImage(), this.layer);
			}else{
				Engine.getEngine(this, this.getClass()).removeImage(this.layer, this.vehicleParts[x][y][0].getImage());
				vehicleParts[x][y][0] = null;
			}
		}
	}
}
