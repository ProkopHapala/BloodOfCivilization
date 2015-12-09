
package BoC.MapEditor;

import BoC.Engine.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

import java.lang.Thread;

public class EditorMain extends JFrame {
	
	// ========== Static part
	public static String version = "0.0.1";
	public static String INFO = " Blood of Civilization v " + version + " - Made by Prokop Hapala ";
	
	// ========== Instanciated part
	
	public static GraphicsCanvas      canvas;
	public static EditorKeyListener   keyListener;
	public static EditorMouseListener mouseListener;
	
	public void start() throws FileNotFoundException, IOException { 
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
		keyListener = new EditorKeyListener   (    );
		this.addKeyListener         ( keyListener );
		
		mouseListener = new EditorMouseListener (canvas);
		 
		this.addMouseListener       (mouseListener);
		this.addMouseMotionListener (mouseListener);
		this.addMouseWheelListener  (mouseListener);
		
		setVisible(true);	
		canvas.setTileSize( 16 );
		
		EditorUI.selected_siteType = Globals.siteTypes.get( "plain" );
		
	}
	
	public void run() {
		while (true) {
			canvas.repaint();
			try{ Thread.sleep(10); }catch (Exception e){};
		}
	}
	
	   public static void main(String[] args) throws IOException {
		//System.setOut(outputFile("output.log"));
		EditorMain instance = new EditorMain();
		instance.start();
		instance.run();
	}
}
