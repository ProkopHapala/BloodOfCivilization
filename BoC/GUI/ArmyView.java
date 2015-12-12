
package BoC.GUI;

import BoC.Engine.Military.Army;
import java.awt.Color;
import java.awt.Component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;

public class ArmyView extends JPanel {
	
	public Army               army;
	
	public ComodityTableModel comodityModel;
	public BrigadeTableModel  brigadeModel;
	//ComodityTableModel factoryModel;
	
	public JTable      comodityTable;
	public JTable      brigadeTable;
	public JScrollPane comodityScroll;
	public JScrollPane factoryScroll;
	public JTabbedPane tabbedPane;
	public JFrame      frame;
	
	public JTextField name_field;
	public JFormattedTextField x_field;
	public JFormattedTextField y_field;
	public JFormattedTextField organized_field;
			
	public void setArmy( Army army_ ){
		//if( ( city_ != null ) && ( city_ != city ) ){
		if( army_ != null ){
			army  = army_;
			comodityModel.setData( army.comodities.values() );
			brigadeModel.setData( army.brigades.values() );
			
			name_field.setText( army.name   );
			x_field.setValue ( army.x );
			y_field.setValue ( army.y );
			organized_field.setValue( army.organized );
		}
	}
	
	public void forceSize( int szx, int szy, JComponent comp ){
		comp.setPreferredSize( new Dimension( szx, szy ) );
		comp.setMaximumSize  ( new Dimension( szx, szy ) );
		comp.setMinimumSize  ( new Dimension( szx, szy ) );
	};
	
	public void addLabeledBox( String label_str, JTextField valField, JPanel labelPane, JPanel valuePane ){
		JLabel label  = new JLabel( label_str );
		label.setLabelFor( valField );
		labelPane.add( label    );
		forceSize( 100, 20, label );
		forceSize( 100, 20, valField );
		valuePane.add( valField );
	}
		
	public JComponent makeCityPanel( ){

		//JPanel labelPane = new JPanel( new GridLayout(0,1) );
	    //JPanel valuePane = new JPanel( new GridLayout(0,1) );
		
		JPanel labelPane = new JPanel( ); labelPane.setLayout( new BoxLayout( labelPane, BoxLayout.Y_AXIS )  );
	    JPanel valuePane = new JPanel( ); valuePane.setLayout( new BoxLayout( valuePane, BoxLayout.Y_AXIS )  );
		
		//panel.setLayout(new GridBagLayout());
		//panel.setLayout(new GridBagLayout());
		
		name_field = new JTextField( );
		addLabeledBox( "name",       name_field,         labelPane, valuePane );

		x_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "ix",         x_field,         labelPane, valuePane);

		y_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "iy",         y_field,         labelPane, valuePane);

		organized_field = new JFormattedTextField( NumberFormat.getNumberInstance() );
		addLabeledBox( "organized",  organized_field,         labelPane, valuePane);
		
		//JScrollPane cityPanel = new  JScrollPane( ); 
		JPanel cityPanel = new JPanel(new GridLayout(1,0));
		cityPanel.add( labelPane );
		cityPanel.add( valuePane );
		cityPanel.setSize( 100 , 100 );
		
		return cityPanel;
	}
	
	
	
	public ArmyView() {
        super(new GridLayout(1,0));
		
		tabbedPane = new JTabbedPane();
		
		JComponent cityPane = makeCityPanel( );
		tabbedPane.addTab("Army", null, cityPane, "Does nothing");
		
		comodityModel = new ComodityTableModel();
        comodityTable = new JTable( comodityModel );
        comodityTable.setPreferredScrollableViewportSize( new Dimension(150, 200) );
        comodityTable.setFillsViewportHeight( true );
		comodityScroll = new JScrollPane( comodityTable );
        tabbedPane.addTab("Comodities", null, comodityScroll, "Does nothing");
        //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		//add( comodityScroll );
		
		brigadeModel   = new BrigadeTableModel();
        brigadeTable   = new JTable( brigadeModel );
        brigadeTable.setPreferredScrollableViewportSize( new Dimension(150, 200) );
        brigadeTable.setFillsViewportHeight( true );
		factoryScroll  = new JScrollPane( brigadeTable );
		tabbedPane.addTab("brigades", null, factoryScroll, "Does nothing");
        //tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		//add( factoryScroll );
        
		add(tabbedPane);
		//tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        frame = new JFrame("ArmyView");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
