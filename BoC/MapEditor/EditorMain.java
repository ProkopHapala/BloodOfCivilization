
package BoC.MapEditor;

import BoC.Engine.*;

import java.awt.Point;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

import java.lang.Thread;
import javax.swing.SwingUtilities;

public class EditorMain extends JFrame implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	
	// ========== Static part
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";
	
	// ========== Instanciated part
	
	public static GraphicsCanvas      canvas;
	
	public void start() throws FileNotFoundException, IOException {
		
		this.setFocusTraversalKeysEnabled(false);
		setTitle( INFO );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit( 0 );
			}
		});
				
		this.setSize(800,800);
		this.getContentPane().setSize(800,800);
		
		// ====== Initialization of game content
				
		Globals.initGame();
		
		canvas     = new GraphicsCanvas();		
		this.getContentPane().add( canvas );
		
		this.addKeyListener         ( this ); 
		this.addMouseListener       ( this );
		this.addMouseMotionListener ( this );
		this.addMouseWheelListener  ( this );
		
		setVisible(true); // it is important to put this in right place 
		
		canvas.setTileSize( 16 );
		
		EditorUI.selected_siteType = Globals.siteTypes.get( "plain" );
					
	}
	
	public void run() {
		while (true) {
			canvas.repaint();
			try{ Thread.sleep(10); }catch (Exception e){};
		}
	}
	
	
		// =============== Keyboard Handling
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		EditorUI.keyPressed(keyCode);
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	// =============== MOUSE HANDLING
	
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
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		//int notches = mwe.getWheelRotation();
		//GlobalVars.PLAYER.MOUSEANDKEYS.mouseWheelMoved(notches);
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {}
	
	@Override
	public void mouseEntered(MouseEvent me) {}

	@Override
	public void mouseExited(MouseEvent me) {}
	
	
	
	public static void main(String[] args) throws IOException {
		//System.setOut(outputFile("output.log"));
		EditorMain instance = new EditorMain();
		instance.start();
		instance.run();
	}
}
