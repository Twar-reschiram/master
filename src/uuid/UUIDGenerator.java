package uuid;

import java.util.UUID;

import files.Files;

public class UUIDGenerator {
	
	private static UUIDGenerator generator = null;
	public static UUIDGenerator getUUIDGenerator(){
		if(generator == null)generator = new UUIDGenerator();
		return UUIDGenerator.generator;
	}
	
	private int id = Integer.parseInt(Files.UUID.getFile().get("Anzahl").get(0));
	
	public UUID newUUID(){
		id++;
		Files.UUID.getFile().set("Anzahl", id+"");
		UUID uuid = new UUID(0,id);
		Files.UUID.getFile().add("IDs", uuid.toString());
		return uuid;
	}

}
