package game.map;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import Data.Location;
import Data.Animation.Animation;
import Data.Image.Image;
import Engine.Engine;
import data.Resources;

public class Map {
	
	public static int DEFAULT_SQUARESIZE = 40;
	private int groundLayer = 0;
	private int buildLayer = 2;
	
	private int Width;
	private int Height;
	
	private int[][][] ground;
	private HashMap<Point, Block> groundBlocks = new HashMap<>();
	private int[][][] build;
	private HashMap<Point, Block> buildBlocks = new HashMap<>();
	
	public Map(int width, int height){
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 0,1);
		Engine.getEngine(this, this.getClass()).addLayer(true, false, false, 2,3);
		this.Width = width;
		this.Height = height;
		this.ground = new int[this.Width][this.Height][2];
		this.build = new int[this.Width][this.Height][2];
	}
	
	public Map(int[][][] ground, int[][][] build){
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 0,1);
		Engine.getEngine(this, this.getClass()).addLayer(true, false, false, 2,3);
		this.Width = build.length;
		this.Height = build[0].length;
		this.ground = new int[this.Width][this.Height][2];
		this.build = new int[this.Width][this.Height][2];
		
		for(int x = 0; x<this.Width; x++){
			for(int y = 0; y<this.Height; y++){
				for(int i = 0; i<2; i++){
					if(ground[x][y][i]!=0)addToGround(ground[x][y][i], x, y);
					if(build [x][y][i]!=0)addToBuild(build[x][y][i], x, y);
				}
			}
		}
	}

	public int[][][] getGround() {
		return ground;
	}	

	public int[][][] getBuild() {
		return build;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}
	
	public void addToGround(int res, int x, int y) {
//		System.out.println(res);
		Point p = new Point(x, y);
		if(res==0){
			for(int i = 0; i<2; i++){
				if(ground[x][y][i]!=0){
					Engine.getEngine(this, this.getClass()).removeImage(groundLayer+Resources.getResource(ground[x][y][i]).getLayerUp(), groundBlocks.get(p).getImage(i));
					if(groundBlocks.get(p).getAnimation(i)!=null){
						groundBlocks.get(p).getAnimation(i).stop();
					}
				}
				ground[x][y][i] = 0;
			}
			groundBlocks.remove(p);
		}else{
//			System.out.println(res);
			Resources resource = Resources.getResource(res);
			if(ground[x][y][resource.getLayerUp()]!=0 && groundBlocks.get(p).getImage(resource.getLayerUp())!=null){
				Block block = groundBlocks.get(p);
				if(block.getAnimation(resource.getLayerUp())!=null)block.getAnimation(resource.getLayerUp()).stop();
				block.getImage(resource.getLayerUp()).setSpriteSheet(resource.getSprites().getSpriteSheet());
				block.getImage(resource.getLayerUp()).setSpriteState(resource.getSpriteIDs()[0]);
				if(resource.hasAnimation()){
					block.set(block.getImage(resource.getLayerUp()), resource.getAnimationType().newAnimation(false, groundLayer+resource.getLayerUp(), block.getImage(resource.getLayerUp()), resource), resource.getLayerUp());
//					block.getAnimation(resource.getLayerUp()).start();
				}
				Engine.getEngine(this, this.getClass()).update();
			}else{
				Image image = new Image(new Location(p.x*Map.DEFAULT_SQUARESIZE, p.y*Map.DEFAULT_SQUARESIZE), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", resource.getSprites().getSpriteSheet(), null);
				image.setSpriteState(resource.getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).addImage(image, groundLayer+resource.getLayerUp());
				Animation anim = null;
				if(resource.hasAnimation())anim = resource.getAnimationType().newAnimation(false, groundLayer+resource.getLayerUp(), image, resource);
				if(groundBlocks.containsKey(p)){
					groundBlocks.get(p).set(image, anim, resource.getLayerUp());
				}else{
					if(resource.getLayerUp()==0){
						groundBlocks.put(p, new Block(image, null, anim, null));
					}else{
						groundBlocks.put(p, new Block(null, image, null, null));					
					}
				}
			}
			ground[x][y][resource.getLayerUp()] = res;
		}
	}
	
	private void addToBuild(int res, int x, int y) {
		Point p = new Point(x, y);
		if(res==0){
			for(int i = 0; i<2; i++){
				if(build[x][y][i]!=0){
					Engine.getEngine(this, this.getClass()).removeImage(buildLayer+Resources.getResource(build[x][y][i]).getLayerUp(), buildBlocks.get(p).getImage(i));
					if(buildBlocks.get(p).getAnimation(i)!=null){
						buildBlocks.get(p).getAnimation(i).stop();
					}
				}
				build[x][y][i] = 0;
			}
			buildBlocks.remove(p);
		}else{
//			System.out.println(res);
			Resources resource = Resources.getResource(res);
			if(build[x][y][resource.getLayerUp()]!=0 && buildBlocks.get(p).getImage(resource.getLayerUp())!=null){
				Block block = buildBlocks.get(p);
				if(block.getAnimation(resource.getLayerUp())!=null)block.getAnimation(resource.getLayerUp()).stop();
				block.getImage(resource.getLayerUp()).setSpriteSheet(resource.getSprites().getSpriteSheet());
				block.getImage(resource.getLayerUp()).setSpriteState(resource.getSpriteIDs()[0]);
				if(resource.hasAnimation()){
					block.set(block.getImage(resource.getLayerUp()), resource.getAnimationType().newAnimation(false, buildLayer+resource.getLayerUp(), block.getImage(resource.getLayerUp()), resource), resource.getLayerUp());
//					block.getAnimation(resource.getLayerUp()).start();
				}
				Engine.getEngine(this, this.getClass()).update();
			}else{
				Image image = new Image(new Location(p.x*Map.DEFAULT_SQUARESIZE, p.y*Map.DEFAULT_SQUARESIZE), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", resource.getSprites().getSpriteSheet(), null);
				image.setSpriteState(resource.getSpriteIDs()[0]);
				Engine.getEngine(this, this.getClass()).addImage(image, buildLayer+resource.getLayerUp());
				Animation anim = null;
				if(resource.hasAnimation())anim = resource.getAnimationType().newAnimation(false, buildLayer+resource.getLayerUp(), image, resource);
				if(buildBlocks.containsKey(p)){
					buildBlocks.get(p).set(image, anim, resource.getLayerUp());
				}else{
					if(resource.getLayerUp()==0){
						buildBlocks.put(p, new Block(image, null, anim, null));
					}else{
						buildBlocks.put(p, new Block(null, image, null, null));					
					}
				}
			}
			build[x][y][resource.getLayerUp()] = res;
		}
	}


}
