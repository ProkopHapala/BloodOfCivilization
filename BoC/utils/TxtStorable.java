
package BoC.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface TxtStorable {
	public void readFromTxt( BufferedReader reader ) throws IOException;
	public void writeToTxt ( BufferedWriter writer ) throws IOException;
}

