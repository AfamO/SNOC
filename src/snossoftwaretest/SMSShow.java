/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
/**
 * This prepares the GUI needed by SMSModel object to display database SMS details.
 * @author Charles;
 * @see GeneReportTable.java,ReportTableModel.java,Action_show.java
 * @version 1.0 
 */
public class SMSShow extends JInternalFrame{
   private boolean firsttime = true;
   private Container c;
   private JScrollPane jspane;
   private SMSModel qtbl;
   private JTable jtbl;
  // private String dept;
   //private String sec;
   
    /** Creates a new instance of NameResult */
    public SMSShow() {
        //this.dept= dept1; 
         initComponents ( );
    }
    
   
   
   private void initComponents () {

		
      //  Set up GUI environment
	if ( firsttime ) {
 	  c = getContentPane();
          c.setLayout( new BorderLayout() );
         c.setBackground (Color.white);
        // c.setFont("Verdana",Font.BOLD,14);
         //c.setForeground(Color.blue);
	   qtbl = new SMSModel();
        qtbl.setVector();
 	 jtbl = new JTable( qtbl );
         jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
	TableColumn tcol = jtbl.getColumnModel().getColumn(0);
	tcol.setPreferredWidth(150);
	jspane = new JScrollPane( jtbl );
	c.add( jspane, BorderLayout.CENTER );
         	firsttime = false;
	setBounds (80,0,600,500);
	//setResizable(true);
	//setClosable(true);
	//setMaximizable(true);
	//setIconifiable(true);
	setTitle("TELEDOM SNOS SOFTWARE");
	
	}else{
	   qtbl.setVector();
           qtbl.fire();
	   TableColumn tcol = jtbl.getColumnModel().getColumn(0);
	   tcol.setPreferredWidth(125);
      	}
	setVisible(true);

    }
}
