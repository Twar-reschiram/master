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
				mapFile.remove("Ground."+y+"."+x);
				mapFile.add("Ground."+y+"."+x, map.getGround()[x][y][0]+"");
				mapFile.add("Ground."+y+"."+x, map.getGround()[x][y][1]+"");
			}
		}
		for(int x = 0; x<this.map.getBuild().length; x++){
			for(int y = 0; y<this.map.getBuild()[x].length; y++){
				mapFile.remove("Build."+y+"."+x);
				mapFile.add("Build."+y+"."+x, map.getBuild()[x][y][0]+"");
				mapFile.add("Build."+y+"."+x, map.getBuild()[x][y][1]+"");
			}
		}
	}

	public void loadMap() {
		System.out.println(mapFile.getSubkey("Size")[0]);
		int[][][] ground = new int[Integer.parseInt(mapFile.get("Size.Width").get(0))][Integer.parseInt(mapFile.get("Size.Height").get(0))][2];
		int[][][] build  = new int[ground.length][ground[0].length][2];
		for(String y: mapFile.getSubkey("Ground")){
			for(String x: mapFile.getSubkey("Ground."+y)){
				ArrayList<String> ids = mapFile.get("Ground."+y+"."+x);
				if(ids!=null && ids.size()>0){
					for(int i = 0; i< ids.size(); i++)ground[Integer.parseInt(x)][Integer.parseInt(y)][i] = Integer.parseInt(ids.get(i));
				}
			}
		}
		for(String y: mapFile.getSubkey("Build")){
			for(String x: mapFile.getSubkey("Build."+y)){
				ArrayList<String> ids = mapFile.get("Build."+y+"."+x);
				if(ids!=null && ids.size()>0){
					for(int i = 0; i< ids.size(); i++)build[Integer.parseInt(x)][Integer.parseInt(y)][i] = Integer.parseInt(ids.get(i));
				}
			}
		}
		this.map = new Map(ground, build);
	}

	public Map getMap(){
		return map;
	}

}
