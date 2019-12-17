package orignal_game.src.src.Game.src.game;

import javax.jws.WebParam.Mode;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

public class collision implements ContactListener {
	

	@Override
	public void beginContact(Contact arg0) {
		// TODO Auto-generated method stub
		Fixture f1 = arg0.getFixtureA();
		Fixture f2 = arg0.getFixtureB();
	
		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		
		Object o1 = b1.getUserData();
		Object o2 = b2.getUserData();
	
         System.out.println(o1);
		System.out.println(o2);
		
		if(o1!=null && o2!=null)
		{
			System.out.println("collision");
			//////////////////////////CAR AND CLOCK///////////////////
			if(o1.getClass() == BasicPolygon.class)//if(o1. equals(BasicPolygon.class))//if(o1.getClass() == BasicPolygon.class || o1.getClass()  == clock.class)
			{
				System.out.println("collision : 1");
				
				if( o2.getClass()  == clock.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 11	" );
					//Class<clock> c = clock.class;
					BasicPolygon c1 = (BasicPolygon) o1;
					clock c2 =(clock) o2;
					//c1.pos();
					c2.change();
				}

			}
			else if(o1.getClass()  == clock.class)
			{
				System.out.println("collision : 2");
				
				if( o2.getClass() == BasicPolygon.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 21	" );
					BasicPolygon c2 = (BasicPolygon) o2;
					clock c1 =(clock) o1;
					
					c1.change();
					
				}
			}
			/////////////////////////CAR AND STAR///////////////////
			if(o1.getClass() == BasicPolygon.class)//if(o1. equals(BasicPolygon.class))//if(o1.getClass() == BasicPolygon.class || o1.getClass()  == clock.class)
			{
				System.out.println("collision : 1");
				
				if( o2.getClass()  == STAR.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 11	" );
					//Class<clock> c = clock.class;
					BasicPolygon c1 = (BasicPolygon) o1;
					 STAR c2 =( STAR) o2;
					 c2.change();
				}

			}
			else if(o1.getClass()  ==  STAR.class)
			{
				System.out.println("collision : 2");
				
				if( o2.getClass() == BasicPolygon.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 21	" );
					BasicPolygon c2 = (BasicPolygon) o2;
					STAR c1 =(STAR) o1;
					
					c1.change();
					
				}
			}
			/////////////////////////CAR AND HOLE///////////////////
			if(o1.getClass() == BasicPolygon.class)//if(o1. equals(BasicPolygon.class))//if(o1.getClass() == BasicPolygon.class || o1.getClass()  == clock.class)
			{
				System.out.println("collision : 1");
	
				if( o2.getClass()  == hole.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 11	" );
					//Class<clock> c = clock.class;
					BasicPolygon c1 = (BasicPolygon) o1;
					hole c2 =(hole) o2;
					c2.change();
				}

			}
			else if(o1.getClass()  ==  hole.class)
			{
				System.out.println("collision : 2");
	
				if( o2.getClass() == BasicPolygon.class) {//if(o1.getClass() == clock.class || o1.getClass()  == BasicPolygon.class)
					System.out.println("collision : 21	" );
					BasicPolygon c2 = (BasicPolygon) o2;
					hole c1 =(hole) o1;
		
					c1.change();
		
				}
			}
			/////////////////////////END///////////////////
			
		}
		else
			System.out.println("NULL");
		
		
		System.out.println("----------------------------------------------------");
		
		}
		

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
