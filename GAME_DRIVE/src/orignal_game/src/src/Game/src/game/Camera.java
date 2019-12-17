package orignal_game.src.src.Game.src.game;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

public class Camera {
 /*   private Body body;
    private static Vec2 position;
    private Vec2 vec2Tmp;
	private Vec2 newPosition;
	private Vec2 camera_pos;
	private Vec2 camera_pos2;
	 
  public Camera(Body body) {
        this.body = body;
        System.out.println("camera : 1"); 
        BodyDef bodyDef = new BodyDef();
        //bodyDef.position.set(sx, sy);
    }
 public static Vec2 getPosition() {
	// setPosition();
	 System.out.println("camera : "+ position);
	 //System.out.println("camera_pos : "+ camera_pos);
       // return camera_pos;
	 return position;
        }
 
   
	public void update() {
	//	position.set(BasicPolygon.getPosition());
		System.out.println("called");
    	//vec2Tmp.set(body.getPosition());
        vec2Tmp.sub(position);
       // vec2Tmp.scale(0.2);
        position.add(vec2Tmp);
       
    
    }

   /* public Vec2 setPosition() {
		final BasicPhysicsEngineUsingBox2D game = new BasicPhysicsEngineUsingBox2D();
		for (BasicPolygon p : game.BasicPolygon) {
			camera_pos=p.update();
			camera_pos2 = camera_pos.addLocal(5, 5);
		}
		System.out.println("camera : ");
		return camera_pos;
		
	}
	public void draw(Graphics2D g) {
		Vec2 pos = body.getPosition();
		//((Body) camera1).getPosition();
		//AffineTransform af = new AffineTransform();
		//af.translate( 400,  300);
		
		System.out.println("camera : 2" ); 
		// System.out.println("cam x : " +(-camera.setPosition().x +400 )+ "		cam y : " +  -camera.setPosition().y );
	}*/

	
	    private Body body;
	    private Vec2 position = new Vec2();
	    private Vec2 vec2Tmp = new Vec2();

	    public Camera(Body body) {
	        this.body = body;
	    }

	    public Vec2 getPosition() {
	    	System.out.println(position);
	        return position;
	        
	    }

	    public  void update() {
	    	if(position != null) {
	    		System.out.println("vec2Tmp");
	    		//vec2Tmp=vec2Tmp.set(BasicPolygon.pos1.x,BasicPolygon.pos1.y);
		    	//System.out.println(vec2Tmp);
	    	}
	    	
	     //   vec2Tmp.set(body.getPosition());
	      //  vec2Tmp.sub(position);
	       // vec2Tmp.scale(0.2);
	        //position.add(vec2Tmp);
	    	
	    	position.add(vec2Tmp);
	    }
	    
	}
	
	
   

