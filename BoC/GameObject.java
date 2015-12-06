/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoC;

interface StringIO {
	public void fromString( String s );
}

interface Named {
	public String getName( );
}

public class GameObject implements Named, StringIO {
	String name;
	
	// ========== IO
	
	@Override
	public String getName(){ return name; };
	
	@Override
	public String toString(){ return name; }
	
	@Override
	public void fromString( String s ){ name = s;	}
	
	// ========== Constructor
	
	public GameObject ( ){	} 
	//public GameObject ( String s ){ name=name; }
}
