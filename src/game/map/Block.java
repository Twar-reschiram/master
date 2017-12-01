package game.map;

import Data.Animation.Animation;
import Data.Image.Image;

public class Block {
	
	private Image image[] = new Image[2];
	private Animation anim[] = new Animation[2];
	
	public Block(Image img1, Image img2, Animation anim1, Animation anim2){
		image[0] = img1;
		image[1] = img1;
		anim[0] = anim1;
		anim[1] = anim1;
	}
	
	public Image getImage(int index){
		return image[index];
	}
	
	public Animation getAnimation(int index){
		return anim[index];
	}

	public void set(Image image, Animation anim, int index) {
//		System.out.println(index+"|"+anim+"|"+image);
		this.image[index] = image;
		this.anim[index] = anim;
	}
}
