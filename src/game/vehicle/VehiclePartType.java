package game.vehicle;

public class VehiclePartType {
	
	public static VehiclePartType ARMOR;
	
	private static VehiclePartType[] partTypes;

	public static VehiclePartType create(){
		ARMOR = new VehiclePartType("Armor", 301);
		
		partTypes = new VehiclePartType[1];
		partTypes[0] = ARMOR;
		
		return null;
	}
	
	public static VehiclePartType[] getVehiclePartTypes(){
		return VehiclePartType.partTypes;
	}

	private String name;
	private int typeID;
	
	public VehiclePartType(String name, int typeID) {
		this.name = name;
		this.typeID = typeID;
	}
	
	public String getName(){
		return name;
	}

	public int getTypeID() {
		return typeID;
	}
}
