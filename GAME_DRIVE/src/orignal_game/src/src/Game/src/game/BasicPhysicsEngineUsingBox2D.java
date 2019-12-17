package orignal_game.src.src.Game.src.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.dynamics.joints.WheelJoint;
import org.jbox2d.dynamics.joints.WheelJointDef;


public class BasicPhysicsEngineUsingBox2D {									//////////Running particles as world
	
	
	// frame dimensions
	public static final int SCREEN_HEIGHT =250;
	public static final int SCREEN_WIDTH = 220;
	public static final Dimension FRAME_SIZE = new Dimension(
			SCREEN_WIDTH*6, SCREEN_HEIGHT);
	public static final float WORLD_WIDTH=10;//metres
	public static final float WORLD_HEIGHT=SCREEN_HEIGHT*(WORLD_WIDTH/SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
	public static final float GRAVITY=9.8f;
	public static final boolean ALLOW_MOUSE_POINTER_TO_DRAG_BODIES_ON_SCREEN=true;// There's a load of code in basic mouse listener to process this, if you set it to true
	
	public static World world; // Box2D container for all bodies and barriers 

	// sleep time between two drawn frames in milliseconds 
	public static final int DELAY = 20;
	public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH=10;
	// estimate for time between two frames in seconds 
	public static final float DELTA_T = DELAY / 1000.0f;
	
	
	public static int convertWorldXtoScreenX(float worldX) {
		return (int) (worldX/WORLD_WIDTH*SCREEN_WIDTH);
	}
	public static int convertWorldYtoScreenY(float worldY) {
		// minus sign in here is because screen coordinates are upside down.
		return (int) (SCREEN_HEIGHT-(worldY/WORLD_HEIGHT*SCREEN_HEIGHT));
	}
	public static float convertWorldLengthToScreenLength(float worldLength) {
		return (worldLength/WORLD_WIDTH*SCREEN_WIDTH);
	}
	public static float convertScreenXtoWorldX(int screenX) {
		return screenX*WORLD_WIDTH/SCREEN_WIDTH;
	}
	public static float convertScreenYtoWorldY(int screenY) {
		return (SCREEN_HEIGHT-screenY)*WORLD_HEIGHT/SCREEN_HEIGHT;
	}
	
	//public List<AnchoredBarrier2> barriers2;
	public List<AnchoredBarrier> barriers;
	public List<BasicParticle> BasicParticle;
	public List<static_particles> static_particles;
	//public List<testfile> tf;
	//public List<Car> car;
	//public List<Wheel> wheel;
	public List<BasicPolygon> BasicPolygon;
	public List<mount> mount;
	public List<random> random;
	public List<clock> clock;
	public List<chain> chain;
	public List<base> base;
	public List<STAR> star;
	public List<hole> hole;
//	public List<Camera> camera1;
	private Camera camera1;
	//public List<testfile> testfile;
	
	private Body body;
	//public List<BasicParticle> BasicParticle;
	public static MouseJoint mouseJointDef;
	//public Camera camera;
	//public collision c;
	public final collision c = new collision();
	
	public static int count = 5;

	
	public BasicPhysicsEngineUsingBox2D() {
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int X=(int) screenSize.getHeight();
		//System.out.println("Screen height: " + X + "," + SCREEN_HEIGHT);
		world = new World(new Vec2(0, -GRAVITY));
		world.setContinuousPhysics(true);
		
		BasicParticle = new ArrayList<BasicParticle>();
		static_particles = new ArrayList<static_particles>();
		//testfile = new ArrayList<testfile>();
		barriers = new ArrayList<AnchoredBarrier>();
		//barriers2 = new ArrayList<AnchoredBarrier2>();
		random = new ArrayList<random>();
		//tf = new ArrayList<testfile>();
		 
		BasicPolygon = new ArrayList<BasicPolygon>();
		mount = new ArrayList<mount>();
		hole = new ArrayList<hole>();
		clock = new ArrayList<clock>();
		chain = new ArrayList<chain>();
		base = new ArrayList<base>();
		star = new ArrayList<STAR>();
		//camera1= new ArrayList<Camera>();
		boolean improvedEuler =false;
		float rollingFriction=.02f;
		float s=0.1f;
		float r= 0.3f;
		camera1=new Camera(body);
		BasicParticle.add(new BasicParticle(0.3f,3.3f,0,3f, r,Color.BLUE, 3, rollingFriction, BodyType.DYNAMIC, 2f, 10f));////wheel	BasicParticle(0)
		BasicParticle.add(new BasicParticle(0.3f,3.3f,0,3f, r,Color.BLUE, 3, rollingFriction,  BodyType.DYNAMIC, 2f, 10f));///wheel	BasicParticle(1)
		BasicPolygon.add(new BasicPolygon(WORLD_WIDTH/10,4f,0,0, r*6,r*4,Color.RED, 30, rollingFriction,4));///car	BasicPolygon(0)
		clock.add(new clock(WORLD_WIDTH+0.1f,2f,0,0, r*20,r,Color.BLUE, 30, 0,4, -0.3f, BodyType.DYNAMIC, 0));
		clock.add(new clock((WORLD_WIDTH*2)+(r*15),3f,0,0, r*5,r,Color.WHITE, 30, 0,4, 2f, BodyType.DYNAMIC, 0));////all direction rotation	clock(1)
		//clock.add(new clock((WORLD_WIDTH*3.25f)-(r*9),-2f,0,0, r*18,r,Color.BLUE, 30, 0,4, -0.3f, BodyType.KINEMATIC));////all direction rotation from mid-point	clock(2)
		//clock.add(new clock((WORLD_WIDTH*3.75f)-(r*9),-2f,0,0, r*18,r,Color.BLUE, 30, 0,4, -0.3f, BodyType.KINEMATIC));////all direction rotation from mid-point	clock(3)
		star.add(new STAR(5.5f*WORLD_WIDTH,4.5f,-1.5f*r,1.2f*r, r*2,Color.RED, 1, rollingFriction,3)); /// STAR (0)
		//star.add(new STAR(WORLD_WIDTH/2-2,WORLD_HEIGHT/2+1.4f,-1.5f*r,1.2f*r, r*2,Color.RED, 1, rollingFriction,3)); /// STAR (1)
		base.add(new base(WORLD_WIDTH+3f,2f,0,0, 0f,0f,Color.RED, 30, rollingFriction,4));/////centre point for clock	base(0)
		base.add(new base((WORLD_WIDTH*2)+(r*15),3f,0,0,0f,0f,Color.RED, 30, rollingFriction,4));/////centre point for clock	base(1)
		for(int i=0; i<=10; i++) {
			
			float rand = (float) Math.random()*50f;
			random.add(new random(0.3f+rand,15.3f,0,3f, r,Color.BLACK, 0.3f, rollingFriction, BodyType.DYNAMIC, 2f, 10f));
		}
		//BasicPolygon.add(new BasicPolygon((4f*WORLD_WIDTH)+2f,2f,0,0, r*6,r*4,Color.RED, 30, rollingFriction,4));///car	BasicPolygon(1)
		//base.add(new base(4f*WORLD_WIDTH,-3f,3f,0,0.1f,0.1f,Color.GREEN, 30, rollingFriction,4));/////centre point for clock	base(3)
		
		mount.add(new mount(WORLD_WIDTH*2f,0f,0f,0, r*10,r*4,Color.RED, 30, rollingFriction,4));///mount	mount(0)
		hole.add(new hole(WORLD_WIDTH,-4f,0f,0, r*10,r*4,Color.BLACK, 30, rollingFriction,4));///mount	hole(0)
		hole.add(new hole((4f*WORLD_WIDTH)+(s*4),3f,0f,0, r*10,r*4,Color.BLACK, 30, rollingFriction,4));///mount	hole(1)
		
		
		for(int x= ((int) ((3f*WORLD_WIDTH)+0.5f)); x<(4f*WORLD_WIDTH); x++) {
	
		for(int i=2; i<=50; i++ ) {
			
			
			BasicParticle.add(new BasicParticle((x),-1f,0,0, s,Color.CYAN, 30f, rollingFriction, BodyType.DYNAMIC, 100f, 0.1f));////wheel	BasicParticle(2)
			
			}
		}
		BasicParticle.add(new BasicParticle((4f*WORLD_WIDTH)+(s*2),0,0,0, (s*2),Color.YELLOW, 30f, rollingFriction, BodyType.STATIC, 100f, 0.1f));////wheel	BasicParticle(3)
		BasicParticle.add(new BasicParticle((4f*WORLD_WIDTH)+(s*8),0,0,0, (s*5),Color.YELLOW, 30f, rollingFriction, BodyType.STATIC, 100f, 0.1f));////wheel	BasicParticle(3)
		BasicParticle.add(new BasicParticle((4f*WORLD_WIDTH)+1f+(s*8),0,0,0, (s*7),Color.YELLOW, 30f, rollingFriction, BodyType.STATIC, 100f, 0.1f));////wheel	BasicParticle(3)
		for(int i=0; i<=6; i++ ) {
		
			BasicParticle.add(new BasicParticle((4f*WORLD_WIDTH)+3f+(s*10*i),0,0,0, (s*10),Color.YELLOW, 30f, rollingFriction, BodyType.STATIC, 100f, 0.1f));////wheel	BasicParticle(3)
			
		}
		
		for(int i=0; i<=10; i++ ) {
			
			chain.add(new chain((WORLD_WIDTH/12)-(i*s*2),2.5f,0,0, s,s,Color.GREEN, 0.01f, 0,4));////all direction rotation	chain(0 to 20)
				}
			
			
			for(int i=0; i<10; i++ ) {
				
				
				chain p1x =chain.get(i);
				chain p2x =chain.get(i+1);
				RevoluteJointDef jointDefxx=new RevoluteJointDef();
				jointDefxx.bodyA = p1x.body;
				jointDefxx.bodyB = p2x.body;
				jointDefxx.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
				//jointDefxx.localAnchorA=new Vec2(2*s,0);
				//jointDefxx.localAnchorB=new Vec2(0f,0f);
				jointDefxx.localAnchorA.set(s,s);
				jointDefxx.localAnchorB.set(0f,0f);
				world.createJoint(jointDefxx);
		}
			
		
	/*	base p88 = base.get(2);
		clock p1xx = clock.get(2);
		//BasicParticle p2x =BasicParticle.get(i+1);
		RevoluteJointDef jointDefx=new RevoluteJointDef();
		jointDefx.bodyA = p88.body;
		jointDefx.bodyB = p1xx.body;
		jointDefx.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDefx.localAnchorA=new Vec2(0,0);
		jointDefx.localAnchorB=new Vec2(-s,0f);
		world.createJoint(jointDefx);*/
		
	/*	base p88b = base.get(3);
		clock p1b = clock.get(80);
		//BasicParticle p2x =BasicParticle.get(i+1);
		RevoluteJointDef jointDefb=new RevoluteJointDef();
		jointDefb.bodyA = p88b.body;
		jointDefb.bodyB = p1b.body;
		jointDefb.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDefb.localAnchorA=new Vec2(0,0);
		jointDefb.localAnchorB=new Vec2(s,0f);
		world.createJoint(jointDefb);*/
		
		BasicParticle.add(new BasicParticle((4f*WORLD_WIDTH)+(s*2),0,0,0, (s*2),Color.YELLOW, 30f, rollingFriction, BodyType.STATIC, 100f, 0.1f));////wheel	BasicParticle(3)     coins
		
		barriers.add(new AnchoredBarrier(-2.5f, 0, -2.5f, 3, Color.RED));////first
		barriers.add(new AnchoredBarrier(-5, 2, WORLD_WIDTH, 2, Color.RED));////first
		//barriers2.add(new AnchoredBarrier2(0, 2,WORLD_WIDTH, -4, Color.GREEN));////first
		
		barriers.add(new AnchoredBarrier( WORLD_WIDTH, 2, WORLD_WIDTH, -6, Color.RED));////first
		
		barriers.add(new AnchoredBarrier(WORLD_WIDTH+6f, 0, WORLD_WIDTH+6f, -6, Color.RED));////second
		
		barriers.add(new AnchoredBarrier(WORLD_WIDTH+6f, 0,3*WORLD_WIDTH, 0, Color.RED));/////second
		//barriers2.add(new AnchoredBarrier2(WORLD_WIDTH+6f, 0, WORLD_WIDTH+4f, 0, Color.GREEN));/////second
		
		barriers.add(new AnchoredBarrier(3*WORLD_WIDTH, 0,3*WORLD_WIDTH, -2, Color.RED));/////second to third
		
		barriers.add(new AnchoredBarrier(3*WORLD_WIDTH, -2,4*WORLD_WIDTH, -2, Color.RED));/////third
		//barriers2.add(new AnchoredBarrier2(3*WORLD_WIDTH, -2, WORLD_WIDTH, -2, Color.GREEN));/////third
		
		barriers.add(new AnchoredBarrier(4*WORLD_WIDTH, -2,4*WORLD_WIDTH, 0, Color.RED));/////third to fourth
		barriers.add(new AnchoredBarrier(3.8f*WORLD_WIDTH, -1,4*WORLD_WIDTH, 0, Color.RED));/////third to fourth
		
		barriers.add(new AnchoredBarrier(4*WORLD_WIDTH, 0,5*WORLD_WIDTH, 0, Color.RED));/////fourth
		//barriers2.add(new AnchoredBarrier2(4*WORLD_WIDTH, 0, WORLD_WIDTH, 0, Color.GREEN));/////fourth
		
		barriers.add(new AnchoredBarrier(5*WORLD_WIDTH, 0,6*WORLD_WIDTH, 5f, Color.RED));/////fourth
		barriers.add(new AnchoredBarrier(6*WORLD_WIDTH, 5f,6*WORLD_WIDTH, 10f, Color.RED));/////fourth
	
		BasicPolygon p1 = BasicPolygon.get(0);
		BasicParticle p2 =BasicParticle.get(0);
		RevoluteJointDef jointDef=new RevoluteJointDef();
		jointDef.bodyA = p1.body;
		jointDef.bodyB = p2.body;
		jointDef.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef.localAnchorA=new Vec2(-0.7f,-r);
		jointDef.localAnchorB=new Vec2(0f,0f);
		world.createJoint(jointDef);
		
		
		chain p1xx = chain.get(0);
		//BasicParticle p2x =BasicParticle.get(i+1);
		RevoluteJointDef jointDefx=new RevoluteJointDef();
		jointDefx.bodyA = p1.body;
		jointDefx.bodyB = p1xx.body;
		jointDefx.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDefx.localAnchorA=new Vec2(-1.5f,0);
		jointDefx.localAnchorB=new Vec2(0f,0f);
		world.createJoint(jointDefx);

		BasicParticle p3 = BasicParticle.get(1);
		RevoluteJointDef jointDef2=new RevoluteJointDef();
		jointDef2.bodyA = p1.body;
		jointDef2.bodyB = p3.body;
		jointDef2.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef2.localAnchorA=new Vec2(2.5f*r,-r);
		jointDef2.localAnchorB=new Vec2(0f,0f);
		world.createJoint(jointDef2);
		
		base p7 = base.get(1);
		clock p5 = clock.get(1);
		RevoluteJointDef jointDef3=new RevoluteJointDef();
		jointDef3.bodyA = p7.body;
		jointDef3.bodyB = p5.body;
		jointDef3.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef3.localAnchorA=new Vec2(0,0);
		jointDef3.localAnchorB=new Vec2(0,r*0.25f);
		world.createJoint(jointDef3);
		
		base p4 = base.get(0);
		clock p6 = clock.get(0);
		//WheelJointDef jointDef4 = new WheelJointDef();
		RevoluteJointDef jointDef4=new RevoluteJointDef();
		jointDef4.bodyA = p4.body;
		jointDef4.bodyB = p6.body;
		jointDef4.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef4.localAnchorA=new Vec2(0.05f,0.05f);
		jointDef4.localAnchorB=new Vec2(r*10f,r*0.25f);
		world.createJoint(jointDef4);
		
		/*base p8 = base.get(2);
		chain p9 = chain.get(0);
		RevoluteJointDef jointDef5=new RevoluteJointDef();
		jointDef5.bodyA = p8.body;
		jointDef5.bodyB = p9.body;
		jointDef5.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef5.localAnchorA=new Vec2(0,0);
		jointDef5.localAnchorB=new Vec2(r*10,0f);
		world.createJoint(jointDef5);*/
		//camera.update();
		
		
		
		
	}
	public static void main(String[] args) throws Exception {
		final BasicPhysicsEngineUsingBox2D game = new BasicPhysicsEngineUsingBox2D();
		final GameView view = new GameView(game);
		JEasyFrame frame = new JEasyFrame(view, "Basic Physics Engine");
		frame.addKeyListener(new BasicKeyListener());
		//view.addMouseMotionListener(new BasicButtonListener(null));
		game.startThread(view);
		
	}
	private void startThread(final GameView view) throws InterruptedException {
		final BasicPhysicsEngineUsingBox2D game=this;
		while (true) {
			game.update();
			view.repaint();
			
			world.setContactListener(c);
			System.out.println("basic2D : in");
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
			}
		}
	}
		
	public void update() {
		camera1.update();
		int VELOCITY_ITERATIONS=NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
		int POSITION_ITERATIONS=NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
		
		world.setContactListener(c);
		
	for (BasicParticle p:BasicParticle) {
		if (BasicKeyListener.isRotateLeftKeyPressed()) 			
			BasicParticle.get(0).body.applyTorque(5000);		
			BasicParticle.get(1).body.applyTorque(5000);
		if (BasicKeyListener.isRotateRightKeyPressed()) 
			BasicParticle.get(0).body.applyTorque(-5000);
			BasicParticle.get(1).body.applyTorque(-5000);
		p.notificationOfNewTimestep();
		}
	for (BasicPolygon p:BasicPolygon) {
		p.update();
	}
	
/*	for(chain c: chain) {
		Vec2 wind = new Vec2(-0.5f,0);
		chain.get(0).body.applyForceToCenter(wind);
	}*/
	world.step(DELTA_T, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
}
	public void draw(Graphics2D g) {
		//((Body) camera1).getPosition();
	//	g.translate(-camera1.getPosition().x + ((BasicPolygon) BasicPolygon).update().x, -camera1.getPosition().y + ((BasicPolygon) BasicPolygon).update().y);
		g.translate(50, -50);
	
		// System.out.println("cam x : " +(-camera.setPosition().x +400 )+ "		cam y : " +  -camera.setPosition().y );
	}
	/*public static int count() {
		count --;
		System.out.println("COUNT : " + count);
		return count;
		
	}*/
}


