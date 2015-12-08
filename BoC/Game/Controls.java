
package BoC.Game;

import BoC.Engine.*;
//import goa.Sounds;
//import goa.GlobalVars;
//import goa.DialogWindows;
//import goa.Obj;
//import goa.gui.GameMenu;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controls {

	public Player player;
	public boolean GuiClicked = false;
	public String eventScript = "";
	//public Profile profile;

	public boolean cmdLine = false;
	public String command = "";

	public boolean leftMouseOn = false;
	public boolean rightMouseOn = false;

	public boolean ctrlOn = false;
	public boolean shiftOn = false;
	public boolean mousePressed = false;

	public Point mousePointStart = new Point(0, 0);
	public Point mousePointEnd = new Point(0, 0);

	public Point absoluteMousePoint = null;

	public Rectangle dragBox = null;

	public boolean screenMoved = false;
	public long screenMovedTime = 0;

	public Controls(Player player) {
		this.player = player;
		//profile = GlobalVars.getProfile();
	}

	public void mouseRightClick(Point pos) {
		//
	}

	public void mouseLeftClick(Point pos) {
		//
	}

	public void mouseReleased(Point pos) {
		//
	}

	public void mouseDraggedRight(Point pos) {
		//
	}

	public void checkScreenMove() {
		//
	}

	public void mouseDraggedLeft(Point pos) {
		//
	}

	public void mouseMoved(Point pos) {
		//
	}

	public void mouseWheelMoved(int rot) {
		//
	}

	public synchronized void keyPressed(int keyCode) {
		//
	}

	public synchronized void keyReleased(int keyCode) {
		//
	}

	public void setMouseAbsoluteMousePoint() {
		absoluteMousePoint = MouseInfo.getPointerInfo().getLocation();
	}

}
