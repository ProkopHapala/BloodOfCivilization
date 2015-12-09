
package BoC.MapEditor;

import BoC.Engine.GraphicsCanvas;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.SwingUtilities;

public class EditorMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	GraphicsCanvas canvas;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		//int notches = mwe.getWheelRotation();
		//GlobalVars.PLAYER.MOUSEANDKEYS.mouseWheelMoved(notches);
	}

	@Override
	public void mouseClicked(MouseEvent me) {	}

	@Override
	public void mousePressed(MouseEvent me) {
		Point pos = canvas.getParent().getMousePosition();
		EditorUI.mousePos_pressed.setLocation(pos);
		if (pos != null) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				EditorUI.LMB_pressed( );
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				EditorUI.RMB_pressed( );
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		Point pos = canvas.getParent().getMousePosition();
		EditorUI.mousePos_released.setLocation( pos );
		if (pos != null) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				EditorUI.LMB_released( );
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				EditorUI.RMB_released( );
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		if( EditorUI.recordMouseDrag ){
			Point pos = canvas.getParent().getMousePosition();
			if (pos != null) {  
				if (SwingUtilities.isLeftMouseButton(me)) {
					EditorUI.LMB_dragged( pos.x, pos.y );
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					EditorUI.RMB_dragged( pos.x, pos.y );
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		if( EditorUI.recordMouseMove ){
			Point pos = canvas.getParent().getMousePosition();
			if (pos != null) {  
				if (SwingUtilities.isLeftMouseButton(me)) {
					EditorUI.LMB_moved( pos.x, pos.y );
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					EditorUI.RMB_moved( pos.x, pos.y );
				}
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

	public EditorMouseListener(GraphicsCanvas GC) {
		this.canvas = GC;
	}
}
