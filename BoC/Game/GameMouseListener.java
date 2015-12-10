
package BoC.Game;

import BoC.Engine.GraphicsCanvas;

import java.awt.Point;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.SwingUtilities;

public class GameMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	GraphicsCanvas canvas;

	public GameMouseListener(GraphicsCanvas GC) {
		this.canvas = GC;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		//
	}

	@Override
	public void mousePressed(MouseEvent me) {
		Point pos = canvas.getParent().getMousePosition();
		GameUI.mousePos_pressed.setLocation(pos);
		if (pos != null) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				GameUI.LMB_pressed( );
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				GameUI.RMB_pressed( );
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		Point pos = canvas.getParent().getMousePosition();
		GameUI.mousePos_released.setLocation( pos );
		if (pos != null) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				GameUI.LMB_released( );
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				GameUI.RMB_released( );
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		/*
		Point pos = canvas.getParent().getMousePosition();
		System.out.println("Eited position "+pos.toString());
		*/
	}

	@Override
	public void mouseExited(MouseEvent me) {
		/*
		// this leads to null pointer exception
		Globals.PLAYER.MOUSEANDKEYS.mousePointEnd=Globals.PLAYER.MOUSEANDKEYS.mousePointStart;
		Globals.PLAYER.MOUSEANDKEYS.mousePressed=false;
		*/
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		/*
		Point pos = canvas.getParent().getMousePosition();
		if (pos != null) {
			UI.mousePos = pos.getLocation();//GC.getParent().getMousePosition();    
			if (SwingUtilities.isLeftMouseButton(me)) {
				UI.PLAYER.MOUSEANDKEYS.mouseDraggedLeft(pos);
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				UI.PLAYER.MOUSEANDKEYS.mouseDraggedRight(pos);
			}
		}
		*/	
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		if( GameUI.recordMouseMove ){
			Point pos = canvas.getParent().getMousePosition();
			GameUI.mousePos_moved.setLocation( pos );
		}
		/*
		Point pos = canvas.getParent().getMousePosition();
		if (pos != null) {
			UI.mousePos = pos.getLocation();//GC.getParent().getMousePosition();       
			//GlobalVars.PLAYER.MOUSEANDKEYS.mouseMoved(pos);
		}
		*/
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		//int notches = mwe.getWheelRotation();
		//GlobalVars.PLAYER.MOUSEANDKEYS.mouseWheelMoved(notches);
	}

}
