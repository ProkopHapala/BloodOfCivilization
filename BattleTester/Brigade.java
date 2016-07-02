package BattleTester;

public class Brigade {
	int ntot=100,n=100;
	int ammo=1000;
	
	float kill( float dn ){
		if( dn > n ) dn = n;
		n    -= dn;
		//ntot -= hard_factor * dn;
		return dn;
	}
}
