package game.map;

import java.util.ArrayList;

import File.File;

public class MapLoader {
	
	private Map map;
	private File mapFile;
	
	public MapLoader(File f){
		this.mapFile = f;
		loadMap();
	}
	
	public MapLoader(int width, int height, File f){
		this.mapFile = f;
		createMap(width, height);
	}
	
	private void createMap(int width, int height) {
		this.map = new Map(width, height);
		saveMap();
	}

	public void saveMap() {
		mapFile.set("Size.Width", this.map.getWidth()+"");
		mapFile.set("Size.Height", this.map.getHeight()+"");
		mapFile.add("Ground");
		mapFile.add("Build");
		for(int x = 0; x<this.map.getGround().length; x++){
			for(int y = 0; y<this.map.getGround()[x].length; y++){
				if(this.map.getGround()[x][y]!=0){
					mapFile.set("Ground."+y+"."+x, map.getGround()[x][y]+"");
				}else{
					mapFile.remove("Ground."+y+"."+x);
				}
			}
		}
		for(int x = 0; x<this.map.getBuild().length; x++){
			for(int y = 0; y<this.map.getBuild()[x].length; y++){
				if(this.map.getBuild()[x][y]!=0){
					mapFile.set("Build."+y+"."+x, map.getGround()[x][y]+"");
				}else{
					mapFile.remove("Build."+y+"."+x);
				}
			}
		}
	}

	public void loadMap() {
		int[][] ground = new int[Integer.parseInt(mapFile.get("Size.Width").get(0))][Integer.parseInt(mapFile.get("Size.Height").get(0))];
		int[][] build  = new int[ground.length][ground[0].length];
		for(String y: mapFile.getSubkey("Ground")){
			for(String x: mapFile.getSubkey("Ground."+y)){
				ArrayList<String> ids = mapFile.get("Ground."+y+"."+x);
				if(ids!=null && ids.size()==0)ground[Integer.parseInt(x)][Integer.parseInt(y)] = Integer.parseInt(ids.get(0));
			}
		}
		for(String y: mapFile.getSubkey("Build")){
			for(String x: mapFile.getSubkey("Build."+y)){
				ArrayList<String> ids = mapFile.get("Build."+y+"."+x);
				if(ids!=null && ids.size()==0)build[Integer.parseInt(x)][Integer.parseInt(y)] = Integer.parseInt(ids.get(0));
			}
		}
		this.map = new Map(ground, build);
	}

	public Map getMap(){
		return map;
	}

}
