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
import javax.swing.event.*;
import java.util.Vector;

/**
 * This prepares and initializes the necessary SWING GUI components 
 * needed by BackUpModel.java .It also defines and implements the 
 * corresponding methods or/and functions.
 * @author Charles
 * @see Action_show.java,SMSShow.java,GeneReportTable.java
 * @version 1.0 
 */


public class backupShow  extends JInternalFrame{
   private boolean firsttime = true;
   private Container c;
   private JScrollPane jspane;
   private backUpmodel qtbl;
   private JTable jtbl;
   private JPanel bottomPanel;
   private int y;
   private boolean me = true;
    
  // private String dept;
   //private String sec;
   
    /** Creates a new instance of NameResult */
    public backupShow () {
        //this.dept= dept1; 
         initComponents ( );
    }
    
   
   
   private void initComponents () {

		
      //  Set up GUI environment
	if ( firsttime ) {
 	  c = getContentPane();
          c.setLayout( new BorderLayout() );
         c.setBackground (Color.white);
	  qtbl = new backUpmodel();
         qtbl.setVector();
        //qtbl.setChange();
 	 jtbl = new JTable( qtbl );
         jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
	TableColumn tcol = jtbl.getColumnModel().getColumn(0);
	tcol.setPreferredWidth(150);
        TableModelListener tl= new TableModelListener(){
  public void tableChanged(TableModelEvent e){

    jtbl.setValueAt("world",0,3);
  }
};

jtbl.getModel().addTableModelListener(tl);
            
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              // int row =jtbl.getSelectedRow();
                //int col =jtbl.getSelectedRow();
                //jtbl.isCellSelected(row, y);
      // boolean test=  jtbl.isRowSelected(row);
          jtbl.getSelectionModel().addListSelectionListener(new SelectionHandler());
         jtbl.setDefaultRenderer(Object.class,new FlasingCellMess(true));
       // jtbl.getColumnModel().getSelectionModel().addListSelectionListener(new SelectionHandler());
	      
        
            }
        });
        
        //jspane = new JScrollPane( jtbl );
	//c.add( jspane, BorderLayout.CENTER );
        JSplitPane pan = new JSplitPane();
        pan.setOrientation(SwingConstants.HORIZONTAL);
        pan.setLeftComponent(new JScrollPane(jtbl));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        pan.setRightComponent(bottomPanel);
        pan.setDividerLocation(350);
        c.add(pan,BorderLayout.CENTER);
       // ListSelectionListener listen = new ListSelectionListener();
        
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
   
   
  
   
  
   class SelectionHandler implements ListSelectionListener{
       
       @Override
       public void valueChanged(ListSelectionEvent e){
           int col=jtbl.getSelectedColumn();
           int row=jtbl.getSelectedRow();
           
           qtbl.setVectorElemnt();
           if(row==col){
                 // jtbl.setValueAt("Read", row, 3);
                 // qtbl.setValueAt("Read", row,3);
                 
               }
           
           
           
         JLabel b = new JLabel("Here is the detailed Information about the Text Message\n");
          JLabel nam = new JLabel("Name:");
          nam.setBounds(20, 60, 150, 30);
          nam.setForeground(Color.red);
        nam.setFont(new Font("Verdana",Font.BOLD,14));
          JLabel nam1 = new JLabel();
          nam1.setBounds(80, 60, 250, 30);
          nam1.setForeground(Color.red);
         nam1.setFont(new Font("Verdana",Font.BOLD,14));
          
         //adding Jlabel for address 
         JLabel add= new JLabel("Address:");
           add.setBounds(20, 80, 150, 30);
          add.setForeground(Color.red);
          add.setFont(new Font("Verdana",Font.BOLD,14));
          JLabel add1= new JLabel();
           add1.setBounds(120, 80, 500, 30);
          add1.setForeground(Color.red);
          add1.setFont(new Font("Verdana",Font.BOLD,14));
          
           
           JLabel fone = new JLabel("Client Phone:");
           fone.setBounds(20, 100, 150, 30);
           fone.setForeground(Color.red);
          fone.setFont(new Font("Verdana",Font.BOLD,14));
          JLabel fone1 = new JLabel();
           fone1.setBounds(150, 100, 200, 30);
           fone1.setForeground(Color.red);
          fone1.setFont(new Font("Verdana",Font.BOLD,14));
          
          
          JLabel cont = new JLabel("Contact Person:");
           cont.setBounds(20, 120, 150, 30);
           cont.setForeground(Color.red);
          cont.setFont(new Font("Verdana",Font.BOLD,14));
          JLabel cont1 = new JLabel();
           cont1.setBounds(150, 120, 200, 30);
           cont1.setForeground(Color.red);
          cont1.setFont(new Font("Verdana",Font.BOLD,14));
          
          
          //JLabel v = new JLabel(" Message");
          JButton printB=new JButton("Print");
          printB.setBounds(150, 180, 70, 20);
          
          JButton InforB=new JButton("More Information");
           InforB.setBounds(250, 180, 150, 20);
           
           if(row != -1 && col != -1){
               
               bottomPanel.removeAll();
              // bottomPanel.setForeground(Color.GREEN);
           // bottomPanel.setFont(new Font("Verdana",Font.BOLD,14));
               b.setBounds(100, 10, 350, 30);
               b.setForeground(Color.BLUE);
                bottomPanel.add(b);
               JLabel bis =new JLabel("Sensor Type: "+jtbl.getValueAt(row, col));
               bis.setBounds(20, 40, 200, 30);
               bis.setForeground(Color.red);
               bis.setFont(new Font("Verdana",Font.BOLD,14));
               bottomPanel.add(nam);bottomPanel.add(add);
               bottomPanel.add(fone); bottomPanel.add(cont);
               //database manipulation
            String sensor=(String)jtbl.getValueAt(row, col);
            Reconsoft bee =new Reconsoft();
            UserInforGet data= bee.SNOSUSER(sensor);
            nam1.setText(data.getClient());
             add1.setText(data.getLoc()); 
             fone1.setText(data.getFone());
             cont1.setText(data.getContact());
               
                bottomPanel.add(bis);
                //adding Jbuttons
                bottomPanel.add( printB);
                bottomPanel.add( InforB);
                //adding database information
                bottomPanel.add(nam1);bottomPanel.add(add1);
                bottomPanel.add(fone1); bottomPanel.add(cont1);
                
                
                
                printB.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                       PrintClientInfor.printComponent(bottomPanel); 
                    }
                });
                
                 InforB.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int col=jtbl.getSelectedColumn();
                        int row=jtbl.getSelectedRow();
           
                        String para =jtbl.getValueAt(row, col).toString();
                       
         Detailed_Infor userd= new Detailed_Infor(para);
         userd.setResizable(false);
        userd.setLocationRelativeTo(null);
        userd.setVisible(true);
                    }
                });
               bottomPanel.revalidate();
           }
           
       }
   }
    public  class FlasingCellMess extends DefaultTableCellRenderer{
        private boolean isbore;
        
        public FlasingCellMess( boolean isboredered){
          this.isbore=isboredered;
            //setOpaque(true);
        }
        
        
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column)
        {
            JLabel label =
                (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            String me= table.getModel().getValueAt(row, 3).toString();
             
          //table.setValueAt("read", 0, 3);
            
               //if (me.equals("Read"))
           /// {
                
                if(isSelected){
                     if(isbore){
                        label.setForeground(Color.RED);
                      label.setFont(new Font("Verdana",Font.BOLD,14));
                       table.setForeground(Color.RED);
                      // table.getSelectionModel().setValueIsAdjusting(true);
                        //table.setValueAt(value, 0, 3);
                     
                
          
               }
                      }
              else{ 
                
              // label.setBackground(Color.white);.
             // table.setForeground(Color.BLUE);//
              label.setForeground(Color.BLUE);
              label.setFont(new Font("Verdana",Font.BOLD,14));
            }
               
            return label;
        }
    
}
    
    
     
}

