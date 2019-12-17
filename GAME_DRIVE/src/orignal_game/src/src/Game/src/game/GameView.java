package orignal_game.src.src.Game.src.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;



public class GameView extends JComponent {				
	public static final Color BG_COLOR =Color.lightGray;
	private BasicPhysicsEngineUsingBox2D game;
	
	//
		public GameView(BasicPhysicsEngineUsingBox2D game) {
			this.game = game;
			//System.out.println("GAMEVIEW CLASS : fine till here");
		// TODO Auto-generated constructor stub
	}
		
		@Override
	public void paintComponent(Graphics g0) {
		BasicPhysicsEngineUsingBox2D game;
		
		synchronized(this) {
			game=this.game;
		}
		Graphics2D g = (Graphics2D) g0;
		g0.setColor(BG_COLOR);
		g0.fillRect(0, 0, getWidth(), getHeight());
		//g0.drawString("GAME OVER", 200, 200);
		game.draw(g);
		/*for (Camera b : game.camera1)
			b.draw(g);*/
		for (BasicParticle p : game.BasicParticle)
			p.draw(g);
		for (static_particles p : game.static_particles)
			p.draw(g);
		for (BasicPolygon p : game.BasicPolygon)
			p.draw(g);
		for (clock p : game.clock)
			p.draw(g);
		for (AnchoredBarrier b : game.barriers)
			b.draw(g);
		//for (AnchoredBarrier2 b : game.barriers2)
			//b.draw(g);
		for (base c : game.base)
			c.draw(g);
		for (mount p : game.mount)
			p.draw(g);
		for (chain b : game.chain)
			b.draw(g);
		for (random b : game.random)
			b.draw(g);
		for (STAR b : game.star)
			b.draw(g);
		for (hole b : game.hole)
			b.draw(g);
		
	}
		@Override
		public Dimension getPreferredSize() {
			return BasicPhysicsEngineUsingBox2D.FRAME_SIZE;
			
		}
		
		public synchronized void updateGame(BasicPhysicsEngineUsingBox2D game) {
			this.game=game;
		}
	}