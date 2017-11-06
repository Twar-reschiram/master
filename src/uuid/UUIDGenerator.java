package uuid;

import java.util.ArrayList;
import java.util.UUID;

import files.Files;

public class UUIDGenerator {
	
	private static UUIDGenerator generator = null;
	public static UUIDGenerator getUUIDGenerator(){
		if(generator == null)generator = new UUIDGenerator();
		return UUIDGenerator.generator;
	}
	
	private int id = Integer.parseInt(Files.UUID.getFile().get("Anzahl").get(0));
	
	public UUID newUUID(int... pID){
		if(pID.length == 1&&id == pID[0])id++;
		int mID = id;
		if(pID.length == 1)mID = pID[0];
		String[] ids = Files.UUID.getFile().getSubkey("Used");
		boolean found = true;
		while(found){
			found = false;
			for(String s: ids) if(s.equals(mID+"")){
				if(pID[0] == mID)return null;
				id++;
				mID++;
				found = true;
			}
		}
		Files.UUID.getFile().set("Anzahl", id+"");
		UUID uuid = new UUID(0,mID);
		Files.UUID.getFile().add("Used."+mID, uuid.toString());
		return uuid;
	}
	
	public UUID getUUID(int id){
		ArrayList<String> uuid = Files.UUID.getFile().get("Used."+id);
		if(uuid!=null&&uuid.size()>0)return UUID.fromString(uuid.get(0));
		else return newUUID(id);
	}

}
