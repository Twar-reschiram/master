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
		
		this.res = res;
		
		start();
		
		this.setAction(new Action() {
			@Override
			public void act(Object caller, Object... data) {
					if(index >= spriteIDs.length){
	//					System.out.println("-----");
						index = 0;
					}
//					System.out.println(index+"|"+spriteIDs[index]);
	//				System.out.println(index+"|"+System.currentTimeMillis()+" <--- "+this);
	//				System.out.println(Data.get(res).size());
					ArrayList<WaterAnimation> animations = Data.get(res);
					Image.setSpriteState(index);
					for(int i = 0; i<animations.size(); i++){
//						System.out.println(animations.size()+"|"+i);
						if(animations.get(i).res.getID()!=res.getID())System.out.println("Error");
//						System.out.println(index);
						animations.get(i)
						.setImage(index);
					}
					index++;
			}
		});
		
	}
	
	@Override
	public boolean tick(){
		time += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(duration<=time){
			anim.act(this);
			time-=duration;
			return true;
		}
		return false;
	}
	
	@Override
	public Animation start(){
//		System.out.println("Start");
		if(!Data.containsKey(res)) Data.put(res, new ArrayList<>());
		if(Data.get(res).size()==0) this.id = AnimationManager.register(this);
		else if(Data.get(res).size()>0) AnimationManager.remove(this);
		Data.get(res).add(this);
		active = true;
		lastTime = System.currentTimeMillis();
		return this;
	}
	
	@Override
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
	
	@Override
	public void stop(){
//		System.out.println("Stop");
		if(Data.containsKey(res) && Data.get(res).size()>0){
			if(Data.get(res).get(0).equals(this) && Data.get(res).size()>1){
				Data.get(res).get(1).id = AnimationManager.register(Data.get(res).get(1));
			}
			Data.get(res).remove(this);
			AnimationManager.remove(this);
		}
	//	active = false;
		image.setSpriteState(0);
	}

	@Override
	public void setImage(int index) {
		if(Data.containsKey(res) && Data.get(res).size()>0){
			Data.get(res).get(0).index = index;
		}
		image.setSpriteState(spriteIDs[index]);
	}

}
