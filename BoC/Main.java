package BoC;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

import java.lang.Thread;

import static BoC.Globals.form;

public class Main extends JFrame {
	
	public GraphicsCanvas    canvas;
	public GameKeyListener   gkl;
	public GameMouseListener gml;
	
	public void setFullscreen() {
		setVisible(false);
		setExtendedState(form.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		dispose();
		setUndecorated(true);
		setVisible(true);
	}

	public void setWindowed(int width, int height) {
		setVisible(false);
		setExtendedState(form.getExtendedState());
		setSize(width, height);
		setLocationRelativeTo(null);
		dispose();
		setUndecorated(false);
		setVisible(true);
	}
	
	public void start() throws FileNotFoundException, IOException { 
		//Graphics2D.drawLine(20, 100, 120, 100);
		
		this.setFocusTraversalKeysEnabled(false);
		setTitle(Globals.INFO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Globals.init(this, 1200, 800);
		
		Globals.form = this;
		//winWidth = width;
		//winHeight = height;
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit( 0 );
			}
		});
		
		this.setSize(800,800);
		//this.getContentPane().setSize(800,400);
		
		Globals.initComodityTypes();
		Globals.initMachineTypes();
		Globals.initTechnologies();
		
		Globals.initSiteTypes();
		
		Globals.worldMap = new WorldMap( 4, 4, 3  );
		Globals.worldMap.GenerateRandom( 154545 );
		Globals.initCities();
		Globals.initRoutes();
		
		Globals.initCombatantTypes();
		Globals.initArmies();
		
		//map.saveToTxt( "map.txt" );
		//FileSystem.saveToTxt("map.txt", Globals.worldMap );
		//FileSystem.loadFromTxt( "map.txt", map );
		
		canvas     = new GraphicsCanvas();		
		
		this.getContentPane().add   (canvas);
		gkl = new GameKeyListener   (    );
		this.addKeyListener         (gkl );
		gml = new GameMouseListener (canvas);
		/*
		this.addMouseListener       (gml);
		this.addMouseMotionListener (gml);
		this.addMouseWheelListener  (gml);
		*/	
		setVisible(true);
		run();		
	}
	
	public void run() {
		while (true) {
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
		new Main().start();
	}
    
}