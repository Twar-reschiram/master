package galaxy;

import game.map.Map;

public class Planet {
	
	protected String name;
	protected Map map;
	
	public Planet(String name, Map map){
		this.name = name;
		this.map = map;
	}	
	
	public String getName() {
		return name;
	}
	
	public Map getMap(){
		return map;
	}
}
