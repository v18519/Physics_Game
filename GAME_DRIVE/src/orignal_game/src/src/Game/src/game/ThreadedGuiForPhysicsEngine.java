package orignal_game.src.src.Game.src.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ThreadedGuiForPhysicsEngine {							private int SCREEN_HEIGHT;

	public ThreadedGuiForPhysicsEngine() {
		
	}
	
	private static JButton jButton_go;
	private static Thread theThread;
	public static JLabel label;
	
	
	//public static float speed_;
	//public static float angle_;
	
	public static void main(String[] args) throws Exception {
		BasicPhysicsEngineUsingBox2D game = new BasicPhysicsEngineUsingBox2D ();
		final GameView view = new GameView(game);
		JComponent mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(view, BorderLayout.CENTER);
		JPanel sidePanel=new JPanel();
		sidePanel.setLayout(new FlowLayout());
		jButton_go=new JButton("Go");
		sidePanel.add(jButton_go);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		// add any new buttons or textfields to side panel here...
		
/*	JTextField VelText = new JTextField(5);             
        JTextField AngleText = new JTextField(5);
        sidePanel.add(VelText);   
        sidePanel.add(AngleText);		*/

        
		JComponent topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("GAME"));
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		label = new JLabel();
		//JLabel label = new JLabel();
		sidePanel.add(label);
		System.out.println("Thread : Display ho nh ra	" + label);
		JEasyFrame frame = new JEasyFrame(mainPanel, "Basic Physics Engine");
		view.addKeyListener(new BasicKeyListener());
		//view.addMouseMotionListener(new MouseMotionListener());

		
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==jButton_go) {
					try {
						// recreate all particles in their original positions:
						final BasicPhysicsEngineUsingBox2D game2 = new BasicPhysicsEngineUsingBox2D ();
						// Tell the view object to start displaying this new Physics engine instead:
						view.updateGame(game2);
						view.requestFocus();// needed for keyboard listener to work - it would be
						// better off to rewrite using Swing's "Key Bindings" apparently as this
						// will remove the need for focus.
						//
						/*String speedtext = VelText.getText();
				        float speedd = Float.parseFloat(speedtext);
				        speed_ = speedd;
				        System.out.println(speedd);
				        
				        String angletext = AngleText.getText();
				        float anglee = Float.parseFloat(angletext);
				        angle_ = anglee;
				        System.out.println(anglee);*/
			
						startThread(game2, view); // start a new thread for the new game object:
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		jButton_go.addActionListener(listener);
	}
	
	
	static void startThread(final BasicPhysicsEngineUsingBox2D game, final GameView view) throws InterruptedException {
	    Runnable r = new Runnable() {
	         public void run() {
	        	//game.setSpeed(speed_, angle_);
	        	// this while loop will exit any time this method is called for a second time, because 
	    		while (theThread==Thread.currentThread()) {
    				game.update();
    				view.repaint();
	    			try {
						Thread.sleep(BasicPhysicsEngineUsingBox2D.DELAY);
					} catch (InterruptedException e) {
					}
	    		}
	         }
	     };

	     theThread=new Thread(r);// this will cause any old threads running to self-terminate
	    theThread.start();
	}
/*	public static JLabel updatemylable(String string) {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel(string);
		//sidePalel.add(lable);
		lable.setText(string);
		System.out.println("Thread : Display ho nh ra	" + string);
		return lable;
	}*/
	


	
	

}


