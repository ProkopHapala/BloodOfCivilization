package BoC.Game;

import BoC.Engine.*;

import BoC.GUI.*;



import java.awt.Point;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.lang.Thread;
import javax.swing.SwingUtilities;

public class GameMain extends JFrame implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	
	// ========== Static part
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";
	
	// ========== Instanciated part
	
	public static GraphicsCanvas    canvas;
	//public static GameKeyListener   keyListener;
	//public static GameMouseListener mouseListener;
	
	int iframe = 0;
	
	
	CityView cityView;
	
	
	// =============== INITIALIZATION
	
	public void start() throws FileNotFoundException, IOException { 
		//Graphics2D.drawLine(20, 100, 120, 100);
		
		this.setFocusTraversalKeysEnabled(false);
		setTitle( INFO );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Globals.form = this;		
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
		
		this.getContentPane().add   (canvas);
		
		//keyListener = new GameKeyListener (    );
		//this.addKeyListener         ( keyListener );
		
		//mouseListener = new GameMouseListener (canvas);
		//this.addMouseListener       (mouseListener);
		//this.addMouseMotionListener (mouseListener);
		//this.addMouseWheelListener  (mouseListener);
		
		this.addKeyListener         ( this );
		this.addMouseListener       ( this );
		this.addMouseMotionListener ( this );
		this.addMouseWheelListener  ( this );
		
		setVisible(true);	
		canvas.setTileSize( 16 );
		
		// ====== this is testing playgorund
		
		cityView = new CityView();
		
		City city = Globals.cities.values().iterator().next();
		System.out.print( city );
		cityView.setCity( city );
		
	}
	
	// =============== PER FRAME
	
	public void run() {
		
		while (true) {
			iframe ++;
			//System.out.println( " ============= frame: "+iframe );
			Globals.update();  
			//System.exit(0);
			
			//cpu_timeStart = System.nanoTime();
			//map.run();
			//Graphics2D.drawLine(20, 100, 120, 100);
			canvas.repaint();
			//GlobalVars.ACTUAL_SPEED = GlobalVars.GAME_SPEED - timeWait;
			//GlobalVars.INFO = "CPU used: " + GlobalVars.getProfile().CPUs + " desired speed: " + GlobalVars.GAME_SPEED + " act. speed: " + GlobalVars.ACTUAL_SPEED + " wait:" + timeWait + " real game speed: " + diff2 + " comp: " + diff + " FPS: " + 1000 / (1 + GlobalVars.PLAYER.graphics.actual_fps);
			
			try{ Thread.sleep(10); }catch (Exception e){};
			//break;
		}
	}
	
	
	// =============== Keyboard Handling
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		GameUI.keyPressed(keyCode);
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
	public void mouseDragged(MouseEvent me) {
		if( GameUI.recordMouseDrag ){
			Point pos = canvas.getParent().getMousePosition();
			if (pos != null) {  
				if (SwingUtilities.isLeftMouseButton(me)) {
					GameUI.LMB_dragged( pos.x, pos.y );
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					GameUI.RMB_dragged( pos.x, pos.y );
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		if( GameUI.recordMouseMove ){
			Point pos = canvas.getParent().getMousePosition();
			if (pos != null) {  
				if (SwingUtilities.isLeftMouseButton(me)) {
					GameUI.LMB_moved( pos.x, pos.y );
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					GameUI.RMB_moved( pos.x, pos.y );
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
	
	
	// =============== Common functions
	
	/*
	public void setFullscreen() {
		setVisible(false);
		setExtendedState( this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		dispose();
		setUndecorated(true);
		setVisible(true);
	}

	public void setWindowed(int width, int height) {
		setVisible(false);
		setExtendedState( this.getExtendedState() );
		setSize(width, height);
		setLocationRelativeTo(null);
		dispose();
		setUndecorated(false);
		setVisible(true);
	}
	*/
	
    public static void main(String[] args) throws IOException {
		//System.setOut(outputFile("output.log"));
		GameMain instance = new GameMain();
		instance.start();
		instance.run();
	}
    
}