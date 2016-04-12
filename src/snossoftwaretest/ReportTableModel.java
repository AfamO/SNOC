/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author lIONSEAL
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import org.smslib.InboundMessage;
/**
 * This encapsulates the table model needed to report and display clients SMS details in a table form by GeneReportTable .java class.
 * @author Afam;
 * @see GeneReportTable.java,SMSModel.java,Action_Model.java
 * @version 1.0 
 */
public class ReportTableModel extends AbstractTableModel {
     private Vector totalrows;
     private Vector rows;
     private Vector rows1;
    
    /** Creates a new instance of NameTableModel */
     public ReportTableModel(Vector v) {
          
       
        totalrows = new Vector ( );
        rows=v;
        rows1=v;
    }
     public ReportTableModel() {
          
       
        totalrows = new Vector ( );
        
    }
    //"Security Status","Analysis","Conclusion"
    String [ ] heading = { "SENDER/SNOS TYPE","MESSAGE TEXT","DATE SENT"};
    
               
	public String getColumnName (int i ) {
                     return heading [i];
        }

	public int getColumnCount ( ) {
		     return heading.length;
        }

	public int getRowCount ( ) {

		return totalrows.size ( );
	}

	public Object getValueAt (int row, int col) {

		return ((String[ ])totalrows.elementAt (row)) [col];
	}

	public boolean isCellEditable (int row, int col) {
		
		return false;
	}
	
	public void fire ( ) {
            fireTableChanged(null);
	}

	public void setVector () {
		
                
		//get all the rows and store in totolrows
                 totalrows=this.rows1;
        }
               
       
                   
               
             
}

