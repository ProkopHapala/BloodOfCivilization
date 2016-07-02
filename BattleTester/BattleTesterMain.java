
package BattleTester;

import BoC.Engine.Military.*;

import java.io.IOException;

public class BattleTesterMain {
	
	public void start() {

	}
		
	public void run() {
		System.out.println("Hello BattleTesterMain ");
	}
		
	public static void main(String[] args) throws IOException {
		//System.setOut(outputFile("output.log"));
		BattleTesterMain instance = new BattleTesterMain();
		instance.start();
		instance.run();
	}
	
}
