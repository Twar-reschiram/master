package anim;

import java.util.ArrayList;
import java.util.HashMap;

import Data.Animation.Animation;
import Data.Animation.AnimationManager;
import Data.Events.Action;
import Data.Image.Image;
import data.Resources;

public class WaterAnimation extends Animation{
	
	public static HashMap<Resources, ArrayList<WaterAnimation>> Data = new HashMap<>();
	
	private Resources res;

	public WaterAnimation(Boolean newImage, Double duration, Integer layer, Image Image, Resources res) {
		super(newImage, duration, layer, Image, res.getSpriteIDs());
		
		if(!Data.containsKey(res)) Data.put(res, new ArrayList<>());
		else if(Data.get(res).size()>0) AnimationManager.remove(this);
		Data.get(res).add(this);
		
		this.setAction(new Action() {
			
			@Override
			public void act(Object caller, Object... data) {
				if(index >= spriteIDs.length){
//					System.out.println("-----");
					index = 0;
				}
//				System.out.println(index+"|"+System.currentTimeMillis()+" <--- "+this);
				for(Animation anim: Data.get(res))anim.setImage(index);
				index++;
			}
		});
		
	}
	
	
	public Animation start(){
		if(!Data.containsKey(res)) Data.put(res, new ArrayList<>());
		else if(Data.get(res).size()==0) this.id = AnimationManager.register(this);
		Data.get(res).add(this);
		active = true;
		lastTime = System.currentTimeMillis();
		return this;
	}
	
	public void setIds(int... ids){
		if(Data.containsKey(res) && Data.get(res).size()>0){
			Data.get(res).get(0).setIdsIndependent(ids);
		}
		index = 0;
		spriteIDs = ids;
	}
	
	private void setIdsIndependent(int[] ids){
		index = 0;
		spriteIDs = ids;		
	}
	
	public void stop(){
		if(Data.containsKey(res) && Data.get(res).size()>0){
			if(Data.get(res).get(0).equals(this) && Data.get(res).size()>1){
				AnimationManager.remove(this);
				Data.get(res).get(1).id = AnimationManager.register(Data.get(res).get(1));
			}
			Data.get(res).remove(this);
		}
		active = false;
		image.setSpriteState(0);
	}

	public void setImage(int index) {
		if(Data.containsKey(res) && Data.get(res).size()>0){
			Data.get(res).get(0).index = index;
		}
		image.setSpriteState(index);
	}

}
