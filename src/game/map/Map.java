package game.map;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import Data.Location;
import Data.Image.Image;
import Engine.Engine;
import data.Resources;

public class Map {
	
	public static int DEFAULT_SQUARESIZE = 40;
	private int groundLayer = 0;
	private int buildLayer = 1;
	
	private int Width;
	private int Height;
	
	private int[][] ground;
	private HashMap<Point, Image> groundImages = new HashMap<>();
	private int[][] build;
	private HashMap<Point, Image> buildImages = new HashMap<>();
	
	public Map(int width, int height){
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 0);
		Engine.getEngine(this, this.getClass()).addLayer(true, false, false, 1);
		this.Width = width;
		this.Height = height;
		this.ground = new int[this.Width][this.Height];
		this.build = new int[this.Width][this.Height];
	}
	
	public Map(int[][] ground, int[][] build){
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 0);
		Engine.getEngine(this, this.getClass()).addLayer(true, false, false, 1);
		this.Width = build.length;
		this.Height = build[0].length;
		this.ground = new int[this.Width][this.Height];
		this.build = new int[this.Width][this.Height];
		
		for(int x = 0; x<this.Width; x++){
			for(int y = 0; y<this.Height; y++){
				addToGround(ground[x][y], x, y);
				addToBuild(build[x][y], x, y);
			}
		}
	}

	public int[][] getGround() {
		return ground;
	}	

	public int[][] getBuild() {
		return build;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}
	
	public void addToGround(int res, int x, int y) {
		Point p = new Point(x, y);
		if(res==0){
			if(ground[x][y]!=0){
				System.out.println("remove");
				Engine.getEngine(this, this.getClass()).removeImage(groundLayer, groundImages.get(p));
				groundImages.remove(p);
			}
		}else{
			if(ground[x][y]!=0){
				groundImages.get(p).setSpriteSheet(Resources.getResource(res).getSprites().getSpriteSheet());
				groundImages.get(p).setSpriteState(Resources.getResource(res).getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).update();
			}else{
				groundImages.put(p,new Image(new Location(p.x*Map.DEFAULT_SQUARESIZE, p.y*Map.DEFAULT_SQUARESIZE), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", Resources.getResource(res).getSprites().getSpriteSheet(), null));
				groundImages.get(p).setSpriteState(Resources.getResource(res).getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).addImage(groundImages.get(p), groundLayer);
			}
		}
		ground[x][y] = res;
	}
	
	private void addToBuild(int res, int x, int y) {
		Point p = new Point(x, y);
		if(res==0){
			if(build[x][y]!=0){
				Engine.getEngine(this, this.getClass()).removeImage(buildLayer, buildImages.get(p));
				buildImages.remove(p);
			}
		}else{
			if(build[x][y]!=0){
				buildImages.get(p).setSpriteSheet(Resources.getResource(res).getSprites().getSpriteSheet());
				buildImages.get(p).setSpriteState(Resources.getResource(res).getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).update();
			}else{
				buildImages.put(p,new Image(new Location(p.x*Map.DEFAULT_SQUARESIZE, p.y*Map.DEFAULT_SQUARESIZE), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", Resources.getResource(res).getSprites().getSpriteSheet(), null));
				buildImages.get(p).setSpriteState(Resources.getResource(res).getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).addImage(buildImages.get(p), buildLayer);
			}
		}
		build[x][y] = res;
	}


}
