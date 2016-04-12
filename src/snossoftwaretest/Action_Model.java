/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Tele
 */
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/**
 * This encapsulates and represents the table model for SMS action taken details.
 * @author Charles;
 * @see Action_show.java,SnosReportModel.java,ReportTableModel.java
 * @version 1.0 
 */


public class Action_Model extends AbstractTableModel {
    private Vector totalrows;
    private String dept;
    private String sec;
    private Reconsoft mr;
   
    private boolean DEBUG = true;
   
    
    public Action_Model () {
        //this.dept = dept1; 
        totalrows = new Vector ( );
    }
    
     String [ ] heading = { "S/N","Customer's ID","Sensor Message","Action Type","Message Sent","Date Taken"};
               
	public String getColumnName (int i ) {
                     return heading [i];
        }

	public int getColumnCount ( ) {
		     return heading.length;
        }

	public int getRowCount ( ) {

		return totalrows.size ( );
	}

    @Override
	public Object getValueAt (int row, int col) {

		return ((String[ ])totalrows.elementAt (row)) [col];
	}

    @Override
	public boolean isCellEditable (int row, int col) {
		
		return false;
	}
	
	public void fire () {
            fireTableChanged(null);
	}

	public void setVector ( ) {
		Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
              pro.queryActionTaken_List();
                
              totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
        
        
        public void setVectorElemnt () {
		//Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
           //  pro.querysmsList();
                
             // totalrows = pro.getRows();
                // totalrows=mr.getRows();
                
              
        }
        
    
}
