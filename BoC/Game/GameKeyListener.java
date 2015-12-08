
package BoC.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameKeyListener implements KeyListener {

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//GlobalVars.PLAYER.MOUSEANDKEYS.keyPressed(keyCode);
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//GlobalVars.PLAYER.MOUSEANDKEYS.keyReleased(keyCode);
	}

	@Override
	public void keyTyped(KeyEvent e) {/* Not used */ }

}
