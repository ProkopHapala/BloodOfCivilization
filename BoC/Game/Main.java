package BoC.Game;

import BoC.Engine.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

import java.lang.Thread;

public class Main extends JFrame {
	
	// ========== Static part
	
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";
	
	// ========== Instanciated part
	
	public static GraphicsCanvas    canvas;
	public static GameKeyListener   gkl;
	public static GameMouseListener gml;
	
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
		//this.getContentPane().setSize(800,400);
		
		
		// ====== Initialization of game content
				
		Globals.initGame();
					
		canvas     = new GraphicsCanvas();		
		
		this.getContentPane().add   (canvas);
		gkl = new GameKeyListener   (    );
		this.addKeyListener         (gkl );
		
		gml = new GameMouseListener (canvas);
		 
		this.addMouseListener       (gml);
		this.addMouseMotionListener (gml);
		this.addMouseWheelListener  (gml);
		
			
		setVisible(true);	
		canvas.setTileSize( 16 );
		
		// ====== this is testing playgorund
		
	}
	
	public void run() {
		while (true) {
			
			Globals.update();  
			//System.exit(0);
			
			//cpu_timeStart = System.nanoTime();
			//map.run();
			//Graphics2D.drawLine(20, 100, 120, 100);
			canvas.repaint();
			//GlobalVars.ACTUAL_SPEED = GlobalVars.GAME_SPEED - timeWait;
			//GlobalVars.INFO = "CPU used: " + GlobalVars.getProfile().CPUs + " desired speed: " + GlobalVars.GAME_SPEED + " act. speed: " + GlobalVars.ACTUAL_SPEED + " wait:" + timeWait + " real game speed: " + diff2 + " comp: " + diff + " FPS: " + 1000 / (1 + GlobalVars.PLAYER.graphics.actual_fps);
			try{ Thread.sleep(10); }catch (Exception e){};
		}
	}
	
    public static void main(String[] args) throws IOException {
		//System.setOut(outputFile("output.log"));
		Main instance = new Main();
		instance.start();
		instance.run();
	}
    
}