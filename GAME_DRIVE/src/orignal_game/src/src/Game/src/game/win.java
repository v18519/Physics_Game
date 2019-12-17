package orignal_game.src.src.Game.src.game;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class win  {							//////// test particle
	
	public final int SCREEN_RADIUS;

	private final float rollingFriction,mass;
	public Color col;
	protected final Body body;
	//private static final BodyType BodyType = null;


	public win(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, BodyType x, float linearDamping, float friction) {
		
		World w=BasicPhysicsEngineUsingBox2D.world; // a Box2D object
		BodyDef bodyDef = new BodyDef();  // a Box2D object
		bodyDef.type = x; // this says the physics engine is to move it automatically
		bodyDef.position.set(sx, sy);
		bodyDef.linearVelocity.set(vx, vy);
		bodyDef.linearDamping=2f;
		this.body = w.createBody(bodyDef);
		CircleShape circleShape = new CircleShape();// This class is from Box2D
		//EdgeShape circleShape = new EdgeShape();
		circleShape.m_radius = radius;
		FixtureDef fixtureDef = new FixtureDef();// This class is from Box2D
		fixtureDef.shape = circleShape;
		fixtureDef.density =  (float) (mass/(Math.PI*radius*radius));
		fixtureDef.friction =friction;// this is surface friction;
		fixtureDef.restitution = 1.0f;
		body.createFixture(fixtureDef);
		this.rollingFriction=rollingFriction;
		this.mass=mass;
		//this.position.set(sx, sy);
		this.SCREEN_RADIUS=(int)Math.max(BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(radius),1);
		this.col=col;
		//body.setUserData(this);
		
		
		 
	}
	

	public void draw(Graphics2D g) {
		int x = BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(body.getPosition().x);
		int y = BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(body.getPosition().y);
		g.setColor(col);
		g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
		g.setColor(Color.WHITE);
		//g.drawLine(x, y, x+8, y+8);
		
	}


	public void notificationOfNewTimestep() {
		if (rollingFriction>0) {
			Vec2 rollingFrictionForce=new Vec2(body.getLinearVelocity());
			rollingFrictionForce=rollingFrictionForce.mul(-rollingFriction*mass);
			body.applyForceToCenter(rollingFrictionForce);
			
		}
		
	}
	public void change() {
		// TODO Auto-generated method stub
		col = Color.WHITE;
	}
	
}
