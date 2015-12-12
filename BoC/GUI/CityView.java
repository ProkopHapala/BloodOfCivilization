
package BoC.GUI;

import BoC.Engine.City;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class CityView extends JPanel {
	
	City               city;
	
	ComodityTableModel comodityModel;
	FactoryTableModel factoryModel;
	//ComodityTableModel factoryModel;
	
	JTable      comodityTable;
	JTable      factoryTable;
	JScrollPane comodityScroll;
	JScrollPane factoryScroll;
	JTabbedPane tabbedPane;
	JFrame      frame;
	
	JTextField name_field;
	JFormattedTextField ix_field;
	JFormattedTextField iy_field;
	JFormattedTextField factorySpace_field;
	JFormattedTextField storeSpace_field;
			
	public void setCity( City city_ ){
		if( city != city_ ){
			city  = city_;
			comodityModel.setData( city.comodities.values() );
			factoryModel.setData( city.factories.values() );
			//comodityTable.fireTableDataChanged();
			//factoryTable.fireTableDataChanged();
			//System.out.println( " CityView.setCity: "+city+" "+city.comodities );
			//System.out.println( " CityView.setCity: "+city+" "+model.data );
			
			name_field.setText( city.name   );
			ix_field.setValue ( city.site.ix );
			iy_field.setValue ( city.site.iy );
			factorySpace_field.setValue( city.factorySpace );
			storeSpace_field  .setValue( city.storeSpace   );
		}
	}
	
	
	public void addLabeledBox( String label_str, JTextField valField, JPanel labelPane, JPanel valuePane ){
		JLabel label  = new JLabel( label_str );
		label.setLabelFor( valField );
		labelPane.add( label    );
		valuePane.add( valField );
	}
		
	public JPanel makeCityPanel( ){

		JPanel labelPane = new JPanel(new GridLayout(0,1));
		JPanel valuePane = new JPanel(new GridLayout(0,1));

		name_field = new JTextField( );
		addLabeledBox( "name",       name_field,         labelPane, valuePane );

		ix_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "ix",         ix_field,         labelPane, valuePane );

		iy_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "iy",         iy_field,         labelPane, valuePane );

		factorySpace_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "FactorySpace",  factorySpace_field,         labelPane, valuePane );

		storeSpace_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "StoreSapce",    storeSpace_field,         labelPane, valuePane );
		
		JPanel cityPanel = new JPanel(new GridLayout(1,0));
		cityPanel.add( labelPane );
		cityPanel.add( valuePane );
		return cityPanel;
	}
	
	
	
	public CityView() {
        super(new GridLayout(1,0));
		
		tabbedPane = new JTabbedPane();
		
		JPanel cityPane = makeCityPanel( );
		tabbedPane.addTab("City", null, cityPane, "Does nothing");
		
		comodityModel = new ComodityTableModel();
        comodityTable = new JTable( comodityModel );
        comodityTable.setPreferredScrollableViewportSize( new Dimension(150, 200) );
        comodityTable.setFillsViewportHeight( true );
		comodityScroll = new JScrollPane( comodityTable );
        tabbedPane.addTab("Comodities", null, comodityScroll, "Does nothing");
        //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		//add( comodityScroll );
		
		factoryModel   = new FactoryTableModel();
        factoryTable   = new JTable( factoryModel );
        factoryTable.setPreferredScrollableViewportSize( new Dimension(150, 200) );
        factoryTable.setFillsViewportHeight( true );
		factoryScroll  = new JScrollPane( factoryTable );
		tabbedPane.addTab("Factories", null, factoryScroll, "Does nothing");
        //tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		//add( factoryScroll );
        
		add(tabbedPane);
		//tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        frame = new JFrame("CityView");
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
