package orignal_game.src.src.Game.src.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BasicKeyListener extends KeyAdapter {
	
	private static boolean rotateRightKeyPressed, rotateLeftKeyPressed, thrustKeyPressed; 

	public static boolean isRotateRightKeyPressed() {
		return rotateRightKeyPressed;
	}

	public static boolean isRotateLeftKeyPressed() {
		return rotateLeftKeyPressed;
	}

	public static boolean isThrustKeyPressed() {
		return thrustKeyPressed;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			thrustKeyPressed=true;
			break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=true;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			thrustKeyPressed=false;
			break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=false;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=false;
			break;
		}
	}
}
