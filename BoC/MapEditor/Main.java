
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


public class Main {
	
	public static GraphicsCanvas    canvas;
	//public static EditorKeyListener   gkl;
	//public static EditorMouseListener gml;
	
	public void start() throws FileNotFoundException, IOException { 
		
	}
	
	public void run() {
		while (true) {
			
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
