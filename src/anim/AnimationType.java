package anim;

import java.lang.reflect.InvocationTargetException;

import Data.Animation.Animation;
import Data.Image.Image;
import data.Resources;

public class AnimationType {
	
	public static AnimationType NORMAL;
	public static AnimationType CANCEL;
	public static AnimationType WATER;
	public static AnimationType	NONE;

	public static AnimationType create() {
		NORMAL 	= new AnimationType(1000, "Normal", NormalAnimation.class);
		CANCEL 	= new AnimationType(100, "Cancel", CancelAnimation.class);
		WATER 	= new AnimationType(100, "Water" , WaterAnimation.class );
		NONE 	= new AnimationType(000, "None"  , null				    );		
		return null;
	}
	
	
	private String type;
	private Class<?> animationClass;
	private double duration;
	
	private AnimationType(double duration, String type, Class<?> animationClass){
		this.type = type;
		this.animationClass = animationClass;
		this.duration = duration;
	}
	
	public String getType(){
		return type;
	}
	
	public static AnimationType getAnimationType(String type){
		if(type.equals(NONE.type))       return NONE;
		else if(type.equals(WATER.type)) return WATER;
		return null;
	}
	
	public Animation newAnimation(boolean newImage, int layer, Image image, Resources resource){
		if(this.animationClass!=null){
			try {
				return (Animation) this.animationClass.getDeclaredConstructor(Boolean.class, Double.class, Integer.class, Image.class, Resources.class).newInstance(newImage, this.duration, layer, image, resource);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}
