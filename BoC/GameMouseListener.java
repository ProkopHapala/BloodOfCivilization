
package BoC;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.SwingUtilities;

public class GameMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	GraphicsCanvas GC;

	public GameMouseListener(GraphicsCanvas GC) {
		this.GC = GC;
	}

	@Override
	public void mouseClicked(MouseEvent me) {

		//
	}

	@Override
	public void mousePressed(MouseEvent me) {

		Point pos = GC.getParent().getMousePosition();
		if (pos != null) {
			Globals.mousePointOnScreen = pos.getLocation();
			if (SwingUtilities.isLeftMouseButton(me)) {
				Globals.PLAYER.MOUSEANDKEYS.mouseLeftClick(pos);
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				Globals.PLAYER.MOUSEANDKEYS.mouseRightClick(pos);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {

		Point pos = GC.getParent().getMousePosition();

		if (pos != null) {
			Globals.mousePointOnScreen = pos.getLocation();//GC.getParent().getMousePosition();    
			Globals.PLAYER.MOUSEANDKEYS.mouseReleased(pos);
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		Point pos = GC.getParent().getMousePosition();
		System.out.println("Eited position "+pos.toString());
	}

	@Override
	public void mouseExited(MouseEvent me) {
		Globals.PLAYER.MOUSEANDKEYS.mousePointEnd=Globals.PLAYER.MOUSEANDKEYS.mousePointStart;
		Globals.PLAYER.MOUSEANDKEYS.mousePressed=false;
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		Point pos = GC.getParent().getMousePosition();

		if (pos != null) {
			Globals.mousePointOnScreen = pos.getLocation();//GC.getParent().getMousePosition();    
			if (SwingUtilities.isLeftMouseButton(me)) {
				Globals.PLAYER.MOUSEANDKEYS.mouseDraggedLeft(pos);
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				Globals.PLAYER.MOUSEANDKEYS.mouseDraggedRight(pos);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {

		Point pos = GC.getParent().getMousePosition();

		if (pos != null) {
			Globals.mousePointOnScreen = pos.getLocation();//GC.getParent().getMousePosition();       
			//GlobalVars.PLAYER.MOUSEANDKEYS.mouseMoved(pos);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		int notches = mwe.getWheelRotation();
		//GlobalVars.PLAYER.MOUSEANDKEYS.mouseWheelMoved(notches);
	}

}
