package orignal_game.src.src.Game.src.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.WheelJoint;



public class hole  {
	
	public final float ratioOfScreenScaleToWorldScale;

	private final float rollingFriction,mass;
	public final Color col;
	protected final Body body;
	private final Path2D.Float polygonPath;
	private Camera camera;

	private BufferedImage image;

	private BufferedImage bufferedImage;

	public hole(float sx, float sy, float vx, float vy, float width, float height, Color col, float mass, float rollingFriction, int numSides) {
		this(sx, sy, vx, vy, width, height, col, mass, rollingFriction,mkRegularPolygon(width, height),numSides);
	}
	public hole(float sx, float sy, float vx, float vy, float width, float height, Color col, float mass, float rollingFriction, Path2D.Float polygonPath, int numSides) {
		World w=BasicPhysicsEngineUsingBox2D.world; // a Box2D object
		BodyDef bodyDef = new BodyDef();  // a Box2D object
		bodyDef.type = BodyType.STATIC; 
		bodyDef.position.set(sx, sy);
		//bodyDef.linearVelocity.set(vx, vy);
		bodyDef.angularDamping = 0.1f;
		this.body = w.createBody(bodyDef);
		 //camera = new Camera(body);
		PolygonShape shape = new PolygonShape();
		Vec2[] vertices = verticesOfPath2D(polygonPath, numSides);
		shape.set(vertices, numSides);
		//shape.setAsBox(1, 2);
		FixtureDef fixtureDef = new FixtureDef();// This class is from Box2D
		fixtureDef.shape = shape;
		//shape.setAsBox(hx, hy);
		fixtureDef.density = 1.0f;//(float) (mass/((float) numSides)/2f*(width*height)*Math.sin(2*Math.PI/numSides));
		fixtureDef.friction = 0.1f;// this is surface friction;
		fixtureDef.restitution = 1f;
		body.createFixture(fixtureDef);
		body.setUserData(this);

		this.rollingFriction=rollingFriction;
		this.mass=mass;
		this.ratioOfScreenScaleToWorldScale=BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(1);
		//System.out.println("Screenradius="+ratioOfScreenScaleToWorldScale);
		this.col=col;
		this.polygonPath=polygonPath;
		// camera.update();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(col);
		Vec2 position = body.getPosition();
		float angle = body.getAngle(); 
		AffineTransform af = new AffineTransform();
		af.translate(BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(position.x), BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(position.y));
		af.scale(ratioOfScreenScaleToWorldScale, -ratioOfScreenScaleToWorldScale);// there is a minus in here because screenworld is flipped upsidedown compared to physics world
		af.rotate(angle); 
		Path2D.Float p = new Path2D.Float (polygonPath,af);
		g.fill(p);
	
		//g.translate(-(BasicPhysicsEngineUsingBox2D.cam).getPosition().x+ 400, -BasicPhysicsEngineUsingBox2D.cam.getPosition().y + 300);
	}
		
	public void notificationOfNewTimestep() {
		if (rollingFriction>0) {
			Vec2 rollingFrictionForce=new Vec2(body.getLinearVelocity());
			rollingFrictionForce=rollingFrictionForce.mul(-rollingFriction*mass);
			body.applyForceToCenter(rollingFrictionForce);
		}
	}
	
	// Vec2 vertices of Path2D
	public static Vec2[] verticesOfPath2D(Path2D.Float p, int n) {
		Vec2[] result = new Vec2[n];
		float[] values = new float[6];
		PathIterator pi = p.getPathIterator(null);
		int i = 0;
		while (!pi.isDone() && i < n) {
			int type = pi.currentSegment(values);
			if (type == PathIterator.SEG_LINETO) {
				result[i++] = new Vec2(values[0], values[1]);
			}
			pi.next();
		}
		return result;
	}
	public static Path2D.Float mkRegularPolygon(float width, float height) {
		Path2D.Float p = new Path2D.Float();
			//width = width*3;
		
		p.moveTo(0, 0);
		
		p.lineTo(width*3,0);
		p.lineTo(width*3, height/2);
		p.lineTo(0, height/2);
		p.lineTo(0, 0);
		//p.lineTo(width, 0);
		
		p.closePath();
		return p;
	}
	public void change() {
		// TODO Auto-generated method stub
		//BasicPhysicsEngineUsingBox2D.count();
		
	//col = Color.RED;
		
		ThreadedGuiForPhysicsEngine.label.setText("LOSE");
		//DestroyBody(this);
		try 
		{
		   Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
		   // log the exception.
		}
		System.exit(1);
	
		
		
	}
	
}
