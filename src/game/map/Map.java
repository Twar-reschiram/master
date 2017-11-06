package game.map;

public class Map {
	
	public static int DEFAULT_SQUARESIZE = 30;
	
	private int Width;
	private int Height;
	
	private int[][] ground;
	private int[][] build;
	
	public Map(int width, int height){
		this.Width = width;
		this.Height = height;
		this.ground = new int[this.Width][this.Height];
		this.build = new int[this.Width][this.Height];
	}
	
	public Map(int[][] ground, int[][] build){
		this.ground = ground;
		this.build = build;
		this.Width = build.length;
		this.Height = build[0].length;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	public int[][] getGround() {
		return ground;
	}

	public int[][] getBuild() {
		return build;
	}

}
