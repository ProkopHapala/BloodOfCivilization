
package BoC.GUI;

import BoC.Engine.City;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CityView extends JPanel {
	
	City               city;
	ComodityTableModel model;
	
	public void setCity( City city_ ){
		if( city != city_ ){
			city  = city_;
			model.setData( city.comodities.values() );
			//System.out.println( " CityView.setCity: "+city+" "+city.comodities );
			System.out.println( " CityView.setCity: "+city+" "+model.data );
		}
	}
	
	public CityView() {
        super(new GridLayout(1,0));

		model = new ComodityTableModel();
		
        JTable table = new JTable( model );
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
		
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        this.setOpaque(true);
        frame.setContentPane(this);

        frame.pack();
        frame.setVisible(true);
		
    }
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CityView();
            }
        });
    }
	
}
