
package BoC.Engine;

import BoC.utils.StringIO;

public class GameObject implements StringIO {
	public String name;
	
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
