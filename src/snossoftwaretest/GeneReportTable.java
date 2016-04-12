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
 * GeneReportTable.java
 *
 * Created on October 21, 2010, 9:44 AM
 */


import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
/**
 *This prepares and initializes the necessary SWING GUI components 
 * needed to view and properly report retrieved SMS alerts and their 
 * details (date, sender, etc.). It also defines and implements the 
 * corresponding methods or/and functions.
 * @author Afam
 * @see ReportTableModel.java, Date_Report.java,Action_show.java
 * @version 1.0 
 */
public class GeneReportTable extends javax.swing.JInternalFrame {
   private boolean firsttime = true;
   private Container c;
   private JScrollPane jspane;
   private ReportTableModel qtbl;
   private JTable jtbl;
   private String str="";
   private JTextField eTF;
   private  SmsMain show;
   private Date dt;
   private String dat;
   private JPanel bottomPanel;

    /** Creates new form GeneReportTable */
    public GeneReportTable() {
        dt=new Date(); dat=dt.toGMTString(); 
          initComponents();
        this.setIconifiable(false);
         this.setClosable(false);
         this.setMaximizable(false);
       
    }
   
    public GeneReportTable( SmsMain Admin,Vector v)
    { 
           
            
        initComponents(); 
        initComponents1(v);
         this.show = Admin;
        qtbl=new ReportTableModel(v);
        jtbl = new JTable( qtbl );
        //jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         //jtbl.setForeground(Color.BLUE);
       qtbl.setVector();
        qtbl.fire();
	TableColumn tcol = jtbl.getColumnModel().getColumn(0);
	tcol.setPreferredWidth(125);
        show=new SmsMain();int m=0;
        
       
      
            
          
      
       
       
    }
     
    private void initComponents1 (Vector v ) {
      //  Set up GUI environment
        qtbl=new ReportTableModel(v);
	if ( firsttime ) {
 	  c = getLayeredPane();
          c.setLayout( new BorderLayout() );
          c.setBackground (Color.white);
        qtbl.setVector();
        
 	 jtbl = new JTable( qtbl );
        // jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         //jtbl.setForeground(Color.BLUE);
      
	TableColumn tcol = jtbl.getColumnModel().getColumn(2);
	//tcol.setPreferredWidth(50);
	jspane = new JScrollPane( jtbl );
	c.add( jspane, BorderLayout.CENTER ); 
        //c.add( pTF, BorderLayout.SOUTH );
         	firsttime = false;
	}else{
	   qtbl.setVector();
           qtbl.fire();
	   TableColumn tcol = jtbl.getColumnModel().getColumn(2);
	   tcol.setPreferredWidth(125);
    }
	setVisible(true);
    }
    public GeneReportTable( SmsMain Admin)
    { 
        initComponents(); 
        initComponents1();
        this.show = Admin;
        qtbl=ReadMessages1.getObject();
       // jtbl = new JTable( qtbl );
        //jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
      
        qtbl.setVector();
        qtbl.fire();
        TableColumn tcol = jtbl.getColumnModel().getColumn(0);
        tcol.setPreferredWidth(125);
        show=new SmsMain();int m=0;

       
    
            
          
      }
     private void initComponents1 () {
      //  Set up GUI environment

       
         qtbl=ReadMessages1.getObject();
         //qtbl=new ReportTableModel();
	if ( firsttime ) {
 	  c = getLayeredPane();
          c.setLayout( new BorderLayout() );
          c.setBackground (Color.white);
        qtbl.setVector();
        
 	 jtbl = new JTable( qtbl );
        jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
	TableColumn tcol = jtbl.getColumnModel().getColumn(2);
         jtbl.getSelectionModel().addListSelectionListener(new SelectionHandler());
       jtbl.getColumnModel().getSelectionModel().addListSelectionListener(new SelectionHandler());
       jtbl.setDefaultRenderer(Object.class,new FlasingCellMess());

	//tcol.setPreferredWidth(50);
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
         	firsttime = false;
	}else{
	   qtbl.setVector();
           qtbl.fire();
	   TableColumn tcol = jtbl.getColumnModel().getColumn(2);
	   tcol.setPreferredWidth(125);
    }
	setVisible(true);
    }
     
     
     
     
     
     class SelectionHandler implements ListSelectionListener{
       
       public void valueChanged(ListSelectionEvent e){
           int col=jtbl.getSelectedColumn();
           int row=jtbl.getSelectedRow();
           
           for(int y=0;y<=jtbl.getRowCount();y++)
           {
               if(row==y){
                   jtbl.setValueAt("Read", y, 0);
               }
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
          JButton but=new JButton("Print");
          but.setBounds(150, 180, 70, 20);
         
          JButton Infor=new JButton("More Information");
           Infor.setBounds(250, 180, 150, 20);
           
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
            final String sensor=(String)jtbl.getValueAt(row, col);
            Reconsoft bee =new Reconsoft();
            UserInforGet data= bee.SNOSUSER(sensor);
            nam1.setText(data.getClient());
             add1.setText(data.getLoc()); 
             fone1.setText(data.getFone());
             cont1.setText(data.getContact());
             
              but.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
             //PrintClientInfor b= new PrintClientInfor(bottomPanel);
                   PrintClientInfor.printComponent(bottomPanel); 
         }
          });
              
              
              
              Infor.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
              Detailed_Infor b= new Detailed_Infor(sensor);
              b.setResizable(false);
              b.setLocationRelativeTo(null);
              b.setVisible(true);
             }
          });
              
                bottomPanel.add(bis);
                //adding Jbuttons
                bottomPanel.add(but);
                bottomPanel.add(Infor);
                //adding database information
                bottomPanel.add(nam1);bottomPanel.add(add1);
                bottomPanel.add(fone1); bottomPanel.add(cont1);
                
                
               bottomPanel.revalidate();
           }
           
       }
   }
private void printBActionPerformed(java.awt.event.ActionEvent evt) {                                       
        //Detailed_Infor b= new Detailed_Infor(sensor);
        
         //b.setResizable(false);
         //b.setLocationRelativeTo(null);
        // b.setVisible(true);
    }       
     
     
     public  class FlasingCellMess extends DefaultTableCellRenderer{
        private boolean isbore=true;
        
        public FlasingCellMess(){
            //this.isbore=isboredered;
            //setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column)
        {
            JLabel label =
                (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            String me= table.getModel().getValueAt(row, 1).toString();
          
               if (isSelected)
            {
                
                //if(isSelected){
                        label.setForeground(Color.RED);
                      label.setFont(new Font("Verdana",Font.BOLD,14));
                       table.setForeground(Color.RED);
                      // table.getSelectionModel().setValueIsAdjusting(true);
                     
                
          
                }else{ 
                
              // label.setBackground(Color.white);.
              //table.setForeground(Color.BLUE);//
              label.setForeground(Color.BLUE);
               label.setFont(new Font("Verdana",Font.BOLD,14));
            }
               
            return label;
        }
    
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    

    // Variables declaration - do not modify                     
    private javax.swing.JLayeredPane lp;
    // End of variables declaration                   



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 204, 51));
        setBorder(null);
        setClosable(true);
        setForeground(new java.awt.Color(0, 0, 255));
        setIconifiable(true);
        setMaximizable(true);
        setTitle("TELEDOM SOFTWARE ");
        setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 863, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
