
package BoC.Engine;

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
