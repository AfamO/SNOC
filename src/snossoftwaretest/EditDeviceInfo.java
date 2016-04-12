/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 This class prepares and initializes the necessary SWING GUI components 
 * needed to modify and edit the clients’ device(s)/equipment’s registered information. 
 * It also defines and implements the corresponding methods or/and functions.
 * @author Afam
 * @see EditContactsInfo.java,EditClientInfo.java
 * @version 1.0 
 */
public class EditDeviceInfo extends JFrame {
private static Properties snosprop=new Properties();
private Reconsoft bis=new Reconsoft();
private Vector row;
private String RowId="";
private int rowCounter=0;
    /**
     * Creates new form AddClient
     */
    public EditDeviceInfo(String id) {
        //super(parent, modal);
        initComponents();
        snosprop= bis.getALLSNOS();
        SmsMain.snos_id=id;
        GetDeviceInfo(id);
         rowCounter=0;
         
        this.DisplayDeviceInfo(rowCounter);
        if(row.size()>1)
         {
             nextB.setEnabled(true);
             prevB.setEnabled(true);
         }
    }
    private void display (String msg,String label){
       JOptionPane.showMessageDialog(this,msg,"INVALID INPUT",2);
   }

    public void ClearMyScreen(){
       
        locTA.setText("");
        devNameTF.setText("");
        desTA.setText(" ");
        addTA.setText(" ");
        house_typeTF.setText(" ");
        lgaCB.setSelectedIndex(0);
        stateCB.setSelectedIndex(0);
        //idCB.setSelectedIndex(0);
       
        
         }
    public void UpdateDeviceInfo(){
      PatternCheck pc=new PatternCheck();
     
      String devname=devNameTF.getText();
      String descr=desTA.getText();
      String loc= locTA.getText();
      String add=addTA.getText();
      String house_type=house_typeTF.getText();
      String state=(String)stateCB.getSelectedItem();
      String lga=(String)lgaCB.getSelectedItem();
      
     
      
      if(pc.checkUserName(devname, "Device Field", "Mercedez Benz")
              && pc.checkLocation(descr, "Description Field", "Cool car") && pc.checkUserName(house_type, "Housing Type Field", "Bungallow or Duplex") && pc.checkLocation(loc,"Device Location","Inside Bedroom or Back of the House")
              && pc.checkLocation(add, "Device's Address","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkUserName(state,"State Field" , "Enugu or Anambra or Lagos") )
      {
          
     
      
     
          String ident=SmsMain.snos_id;
                       
         UserInforGet get = new  UserInforGet(ident,devname,descr,house_type,loc,add,lga, state);
         
         
        if(bis.UpdateDeviceDetails(ident, get, RowId)==1)
        {
             ClearMyScreen();
         JOptionPane.showMessageDialog(null,"Information Updated Successfully");
         this.GetDeviceInfo(ident);
         DisplayDeviceInfo(rowCounter);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Information could not be updated successfully, Contact the Administrator\n Or Ensure that your Database Server is running");
        }
        
         
         
       
     
          }
      
    }
    public boolean validateLength(String field,String label,int lent)
    {
        if(field.length()<lent)
        {
            JOptionPane.showMessageDialog (this,"The number of values  "+label+" Field is too small,please enter atleast "+lent+" characters\nThank You.","INCOMPLETE VALUES",2);
            return false;
        }
        return true;
    }
    
  
   public void InitializeIdentityCB()
    {
     Collection enu=ReadMessages1.getSnosProp().values();
     Object[] values;
     values=enu.toArray();
     for(int i=0;i<values.length;i++)
     {
         //idCB.addItem(values[i]);
     }
    }
   public final int SearchObjectsPosition(Object[] grp,String item){
        int pos=0;
        for(int i=0;i<grp.length;i++)
        {
            if(grp[i].equals(item))
            {
                pos=i;
                break;
            }
        }
        return pos;
    }
   public final String[] BuildItems(JComboBox list)
    {
        int n =list.getItemCount();
        String[] vals=new String[n];
        for(int i=0;i<list.getItemCount();i++)
        {
            vals[i]=(String)list.getItemAt(i);
        }
        return vals;
    }
  
   public void GetDeviceInfo(String id)
    {
      row=new Vector();

                    row=bis.getClientDeviceDetails(id, "clients_device_tab");
    }
    public void DisplayDeviceInfo(int counter)
    {
      //row=new Vector();
        String value[]=(String[])row.get(counter);
        String state[] = this.BuildItems(stateCB);
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofLga(value[6])));
        String lga[] = this.BuildItems(lgaCB);
                   
                    
        locTA.setText(value[3]);
        devNameTF.setText(value[0]);
        desTA.setText(value[1]);
        addTA.setText(value[4]);
        house_typeTF.setText(value[2]);
        stateCB.setSelectedIndex(this.SearchObjectsPosition(state, value[6])); 
        lgaCB.setSelectedIndex(this.SearchObjectsPosition(lga, value[5])); 
        RowId=value[7];
                    
    }
   
   
   public boolean ValidateEmptyFields()
   {
       devNameTF.setEditable(true);
       desTA.setEditable(true);
       locTA.setEditable(true);
       addTA.setEditable(true);
       house_typeTF.setEditable(true);
      // idCB.setEditable(true);
      
       if(devNameTF.getText().equals(""))
       {
            //conTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The name field is empty,please enter Device's name\nThank You.","EMPTY Device NAME FIELD",2);
           return false;
       }
       else if(desTA.getText().equals(""))
       {
            //locTA.setEditable(false);
           JOptionPane.showMessageDialog (this,"The descrption Field is empty,please type in device's Descriptiont\nThank You.","EMPTY CONTACT NAME FIELD",2);
            return false;
       }
       else if(locTA.getText().equals(""))
       {
            //AreaTA.setEditable(false);
           JOptionPane.showMessageDialog (this,"The Location Field is empty,please enter Device's location in the house\nThank You.","EMPTY LOCATION FIELD ALERT",2);
           return false;
       }
       else if(addTA.getText().equals(""))
       {
            //buTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The Address field is empty,please write in device's area\nThank You.","EMPTY AREA FIELD",2);
            return false;
       }
       else if(house_typeTF.getText().equals(""))
       {
            //lgaTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The Housing_type field is empty,please enter device's Housing_type\nThank You.","EMPTY BUILDING FIELD",2);
            return false;
            
       }
       else if(lgaCB.getSelectedIndex()==0)
       {
            //stateTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The LGA field is in 'choose one',please select device's LGA\nThank You.","EMPTY NAME FIELD",2);
            return false;
            
       }
       else if(stateCB.getSelectedIndex()==0)
       {
            //idCB.setEditable(false);
           JOptionPane.showMessageDialog (this,"The State field is in 'choose one',please select client's state\nThank You.","EMPTY NAME FIELD",2);
            return false;
       }
      
       
       else
       {
           return true;
       }
   }
    
   
   
   public void getlgaSelectedState()
   {
       String para =(String)stateCB.getSelectedItem();
       lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofLga(para)));
       
  }
    
   
 
  
public void Next_Step()
{
  this.setVisible(false);
 AddContacts b= new AddContacts();
   b.setResizable(false);
   b.setLocationRelativeTo(null);
   b.setVisible(true);
   b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
   
 


 public static void main(String [] args)
{
    
EditDeviceInfo m=new  EditDeviceInfo("SNOS7");
m.setVisible(true);
m.setLocationRelativeTo(null);

}
   
   
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        devNameTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        desTA = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        house_typeTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        subB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        cB = new javax.swing.JButton();
        stateCB = new javax.swing.JComboBox();
        lgaCB = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        locTA = new javax.swing.JTextArea();
        nextB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        prevB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DEVICE APPLICATION FORM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "View/Modify Device/Equipment Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 153))); // NOI18N

        devNameTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                devNameTFMouseExited(evt);
            }
        });
        devNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devNameTFActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Name");

        desTA.setColumns(20);
        desTA.setRows(5);
        desTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                desTAMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(desTA);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Location(In the House):");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("Type of House:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("LGA:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 102));
        jLabel9.setText("State:");

        subB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        subB.setForeground(new java.awt.Color(0, 0, 153));
        subB.setText("Save Changes");
        subB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 255), null));
        subB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBActionPerformed(evt);
            }
        });

        exitB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exitB.setForeground(new java.awt.Color(0, 0, 153));
        exitB.setText("Quit");
        exitB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 255), null));
        exitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });

        cB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cB.setForeground(new java.awt.Color(0, 0, 153));
        cB.setText("Clear");
        cB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 255), null));
        cB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBActionPerformed(evt);
            }
        });

        stateCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stateCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Anambra State", "Akwa Ibom State", "Adamawa State", "Abia State", "Bauchi State", "Bayelsa State", "Benue State", "Borno State", "Cross River State", "Delta State", "Ebonyi State", "Edo State", "Enugu State", "Ekiti State", "Gombe State", "Imo State", "Jigawa State", "Kaduna State", "Kano State", "Katsina State", "Kebbi State", "Kogi State", "Kwara State", "Lagos State", "Nasarawa State", "Niger State", "Ogun State", "Ondo State", "Osun State", "Oyo State", "Plateau State", "Rivers State", "Sokoto State", "Taraba State", "Yobe State", "Zamfara State" }));
        stateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateCBActionPerformed(evt);
            }
        });

        lgaCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Description");

        locTA.setColumns(20);
        locTA.setRows(5);
        locTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                locTAMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(locTA);

        nextB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nextB.setForeground(new java.awt.Color(0, 0, 153));
        nextB.setText("NEXT >>");
        nextB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 255), null));
        nextB.setEnabled(false);
        nextB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Address:");

        addTA.setColumns(20);
        addTA.setRows(5);
        addTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addTAMouseExited(evt);
            }
        });
        jScrollPane4.setViewportView(addTA);

        prevB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prevB.setForeground(new java.awt.Color(0, 0, 153));
        prevB.setText("<<PREVIOUS");
        prevB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 255), null));
        prevB.setEnabled(false);
        prevB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(devNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(house_typeTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stateCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(prevB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextB, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(devNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(house_typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevB)
                    .addComponent(cB)
                    .addComponent(exitB)
                    .addComponent(subB)
                    .addComponent(nextB))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout clientPaneLayout = new javax.swing.GroupLayout(clientPane);
        clientPane.setLayout(clientPaneLayout);
        clientPaneLayout.setHorizontalGroup(
            clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPaneLayout.createSequentialGroup()
                .addGroup(clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPaneLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(clientPaneLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        clientPaneLayout.setVerticalGroup(
            clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientPane, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBActionPerformed
        // TODO add your handling code here:
        if(this.ValidateEmptyFields()==true)
        {
            this.UpdateDeviceInfo();
        }
        
    }//GEN-LAST:event_subBActionPerformed

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_exitBActionPerformed

    private void cBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBActionPerformed
        // TODO add your handling code here:
        this.ClearMyScreen();
    }//GEN-LAST:event_cBActionPerformed

    private void desTAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desTAMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_desTAMouseExited

    private void stateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateCBActionPerformed
        // TODO add your handling code here:
        getlgaSelectedState();
    }//GEN-LAST:event_stateCBActionPerformed

    private void devNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devNameTFActionPerformed
        // TODO add your handling code here:
        //this.ValidateEmptyFields();
    }//GEN-LAST:event_devNameTFActionPerformed

    private void devNameTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_devNameTFMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_devNameTFMouseExited

    private void locTAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locTAMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_locTAMouseExited

    private void nextBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBActionPerformed
        // TODO add your handling code here:
         
         if (row.size()>rowCounter+1)
        {
            rowCounter++;
            prevB.setEnabled(true);
        this.DisplayDeviceInfo(rowCounter);
        }
         if((row.size()==rowCounter+1))
        {
            nextB.setEnabled(false);
             prevB.setEnabled(true);
        }
         else if(row.size()>1)
         {
             nextB.setEnabled(true);
             prevB.setEnabled(true);
         }
        
        
       
    }//GEN-LAST:event_nextBActionPerformed

    private void addTAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTAMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_addTAMouseExited

    private void prevBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBActionPerformed
        // TODO add your handling code here:
        if(row.size()>rowCounter)
        {
            rowCounter--;
        this.DisplayDeviceInfo(rowCounter);
        }
        if((0==rowCounter))
        {
            nextB.setEnabled(true);
            prevB.setEnabled(false);
        }
        
        else if(row.size()>1)
         {
             nextB.setEnabled(true);
             prevB.setEnabled(true);
         }
    }//GEN-LAST:event_prevBActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addTA;
    private javax.swing.JButton cB;
    private javax.swing.JPanel clientPane;
    private javax.swing.JTextArea desTA;
    private javax.swing.JTextField devNameTF;
    private javax.swing.JButton exitB;
    private javax.swing.JTextField house_typeTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox lgaCB;
    private javax.swing.JTextArea locTA;
    private javax.swing.JButton nextB;
    private javax.swing.JButton prevB;
    private javax.swing.JComboBox stateCB;
    private javax.swing.JButton subB;
    // End of variables declaration//GEN-END:variables
}
