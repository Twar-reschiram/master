package galaxy;

import java.util.ArrayList;

public class SolarSystem {
	
	private ArrayList<Planet> planets = new ArrayList<>();
	private String name;
	
	public SolarSystem(String name){
		this.name = name;
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	public String getName() {
		return name;
	}
	

}
