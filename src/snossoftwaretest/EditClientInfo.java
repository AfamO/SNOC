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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 This class prepares and initializes the necessary SWING GUI components 
 * needed to modify and edit the clients’ registered information. 
 * It also defines and implements the corresponding methods or/and functions.
 * @author Afam
 * @see EditContactsInfo.java,EditDeviceInfo.java
 * @version 1.0 
 */
public class EditClientInfo extends JFrame {
private static Properties snosprop=new Properties();
private Reconsoft bis=new Reconsoft();
private String old_id="";
    /**
     * Creates new form AddClient
     */
    public EditClientInfo(String id) {
        //super(parent, modal);
        initComponents();
         RegisteredSnos();
         SmsMain.snos_id=id;
        snosprop= bis.getALLSNOS();
       this.DisplayClientInfo(id);
      
         foneTF.setEditable(false);
    }
    private void display (String msg,String label){
       JOptionPane.showMessageDialog(this,msg,"INVALID INPUT",2);
   }

    public void ClearMyScreen(){
        surnameTF.setText("");
        foneTF.setText("");
        firstnameTF.setText("");
        locTA.setText(" ");
        idCB.setSelectedIndex(0);
        lgaCB.setSelectedIndex(0);
        stateCB.setSelectedIndex(0);
       
        //passTF.setText(null);
        cemailTF.setText(null);
        otherTF.setText(null);
        
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
    public void DisplayClientInfo(String id)
    {
        UserInforGet get=bis.getClientDetails1(id, "snos_client");
        if(get!=null)
        {
        String state[] = this.BuildItems(stateCB);
        String snos[] = this.BuildItems(idCB);
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofLga(get.getState())));
        String lga[] = this.BuildItems(lgaCB);
        surnameTF.setText(get.getsurname());
        foneTF.setText(get.getFone());
        firstnameTF.setText(get.getFname());
        locTA.setText(get.getContact());
        idCB.setSelectedIndex(this.SearchObjectsPosition(snos, get.getSensor())); 
        stateCB.setSelectedIndex(this.SearchObjectsPosition(state, get.getState())); 
        lgaCB.setSelectedIndex(this.SearchObjectsPosition(lga,get.getLga())); 
        old_id = (String)idCB.getSelectedItem();
       
        cemailTF.setText(get.getClientEmail());
        otherTF.setText(get.getOname()); 
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Client's Information could not be retrieved successfully, Contact the Administrator\n Or Ensure that your Database Server is running");
        }
    }
    public void RegisteredSnos(){
       idCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
   }
    public void UpdateClientInfo(){
      PatternCheck pc=new PatternCheck();
      String sname=surnameTF.getText();
      String fname=firstnameTF.getText();
      String other=otherTF.getText();
      String number=foneTF.getText();
      String location= locTA.getText();
     
      String state=(String)stateCB.getSelectedItem();
      String lga=(String)lgaCB.getSelectedItem();
      
      String ident=(String)idCB.getSelectedItem();
     
      String client_email=cemailTF.getText();
      
      if( pc.checkUserName(surnameTF.getText(), "Client Name", "Okolo or Ugwu")
              && pc.checkUserName(firstnameTF.getText(), "First Name", "Mike or John") && pc.checkUserName(otherTF.getText(), "Other Name", "Kelvin or Andrew") && pc.checkLocation(locTA.getText(), "Client's Location","5 Sule Abuka Crescent,Opebi,Ikeja") 
               && pc.checkEmail(cemailTF.getText(),"Client's Email","newuser@snos.com") 
              )
      {
          //if(this.validateLength(client,"Name", 6) && this.validateLength(contact,"Contact", 8) && this.validateLength(location,"Location", 10) && this.validateLength(area,"Area", 5) )
      //{
      
         UserInforGet get = new UserInforGet(sname,fname,other,number,location,lga,state,ident,"",client_email);
         
               
         if(bis.UpdateClientDetails(old_id, get)==1)
         {
            ClearMyScreen();
             
            JOptionPane.showMessageDialog(null,"Information Updated Successfully.\nGet ready to see the the changes you have made reflected immediately");
            this.DisplayClientInfo(ident);
         
           
         }
         else
         {
             JOptionPane.showMessageDialog(null,"Information could not be updated successfully, Contact the Administrator\n Or Ensure that your Database Server is running");
             
         }
         
         
       
      //}
      
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
    
  
   
   
   
   
   public boolean ValidateEmptyFields()
   {
       firstnameTF.setEditable(true);
       locTA.setEditable(true);
      
       cemailTF.setEditable(true);
       otherTF.setEditable(true);
       if(surnameTF.getText().equals(""))
       {
            //conTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The surname field is empty,please enter client's surname\nThank You.","EMPTY NAME FIELD",2);
           return false;
       }
       else if(firstnameTF.getText().equals(""))
       {
            //locTA.setEditable(false);
           JOptionPane.showMessageDialog (this,"The firstname field is empty,please type in client's firstname\nThank You.","EMPTY FIRST NAME FIELD",2);
            return false;
       }
       
       else if(locTA.getText().equals(""))
       {
            //AreaTA.setEditable(false);
           JOptionPane.showMessageDialog (this,"The Address Field is empty,please enter client's Address\nThank You.","EMPTY LOCATION FIELD ALERT",2);
           return false;
       }
       else if(idCB.getSelectedIndex()==0)
       {
            //idCB.setEditable(false);
           JOptionPane.showMessageDialog (this,"The Identity field is in 'choose one',please select client's identity\nThank You.","EMPTY NAME FIELD",2);
            return false;
       }
      
       else if(stateCB.getSelectedIndex()==0)
       {
            //idCB.setEditable(false);
           JOptionPane.showMessageDialog (this,"The State field is in 'choose one',please select client's state\nThank You.","EMPTY NAME FIELD",2);
            return false;
       }
      
       else if(lgaCB.getSelectedIndex()==0)
       {
            //stateTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The LGA field is in 'choose one',please select client's LGA\nThank You.","EMPTY NAME FIELD",2);
            return false;
            
       }
       
       else if(cemailTF.getText().equals(""))
       {
            //contemailTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The Client's Email field is iempty,please enter Client's email\nThank You.","EMPTY CLIENT EMAIL FIELD",2);
            return false;
            
       }
       else if (otherTF.getText().equals(""))
       {
            
           JOptionPane.showMessageDialog (this,"The Othername field is empty,please enter Client's Othername\nThank You.","EMPTY CONTACT EMAIL FIELD",2);
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
        foneTF = new javax.swing.JTextField();
        firstnameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        subB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        cB = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cemailTF = new javax.swing.JTextField();
        otherTF = new javax.swing.JTextField();
        stateCB = new javax.swing.JComboBox();
        lgaCB = new javax.swing.JComboBox();
        surnameTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        locTA = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        idCB = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENT INFORMATION FORM VIEW");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "View/Modify Client Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 153))); // NOI18N

        firstnameTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                firstnameTFMouseExited(evt);
            }
        });
        firstnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameTFActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Phone Number:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("First Name:");

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Other Names:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 102));
        jLabel13.setText(" E-mail:");

        stateCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stateCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Anambra State", "Akwa Ibom State", "Adamawa State", "Abia State", "Bauchi State", "Bayelsa State", "Benue State", "Borno State", "Cross River State", "Delta State", "Ebonyi State", "Edo State", "Enugu State", "Ekiti State", "Gombe State", "Imo State", "Jigawa State", "Kaduna State", "Kano State", "Katsina State", "Kebbi State", "Kogi State", "Kwara State", "Lagos State", "Nasarawa State", "Niger State", "Ogun State", "Ondo State", "Osun State", "Oyo State", "Plateau State", "Rivers State", "Sokoto State", "Taraba State", "Yobe State", "Zamfara State" }));
        stateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateCBActionPerformed(evt);
            }
        });

        lgaCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one" }));

        surnameTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                surnameTFMouseExited(evt);
            }
        });
        surnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surnameTFActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 102));
        jLabel14.setText("Address");

        locTA.setColumns(20);
        locTA.setRows(5);
        jScrollPane1.setViewportView(locTA);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Identity:");

        idCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "SNOS1", "SNOS2" }));
        idCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idCBItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(168, 168, 168))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(201, 201, 201))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(209, 209, 209))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(surnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(cemailTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(otherTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(firstnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(30, 30, 30))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(subB, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(surnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(firstnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(otherTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cemailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cB)
                    .addComponent(subB)
                    .addComponent(exitB))
                .addGap(201, 201, 201))
        );

        javax.swing.GroupLayout clientPaneLayout = new javax.swing.GroupLayout(clientPane);
        clientPane.setLayout(clientPaneLayout);
        clientPaneLayout.setHorizontalGroup(
            clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPaneLayout.createSequentialGroup()
                .addGroup(clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPaneLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1))
                    .addGroup(clientPaneLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        clientPaneLayout.setVerticalGroup(
            clientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clientPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientPane, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBActionPerformed
        // TODO add your handling code here:
        if(this.ValidateEmptyFields()==true)
        {
            UpdateClientInfo();
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

    private void firstnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameTFActionPerformed
        // TODO add your handling code here:
        //this.ValidateEmptyFields();
    }//GEN-LAST:event_firstnameTFActionPerformed

    private void firstnameTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstnameTFMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_firstnameTFMouseExited

    private void stateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateCBActionPerformed
        // TODO add your handling code here:
        getlgaSelectedState();
    }//GEN-LAST:event_stateCBActionPerformed

    private void surnameTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_surnameTFMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_surnameTFMouseExited

    private void surnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surnameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surnameTFActionPerformed

    private void idCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idCBItemStateChanged
        // TODO add your handling code here:
        foneTF.setText(bis.RetrieveItem("snos_table", "snos_type", (String)idCB.getSelectedItem(),"fone"));
        foneTF.setEditable(false);
    }//GEN-LAST:event_idCBItemStateChanged

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cB;
    private javax.swing.JTextField cemailTF;
    private javax.swing.JPanel clientPane;
    private javax.swing.JButton exitB;
    private javax.swing.JTextField firstnameTF;
    private javax.swing.JTextField foneTF;
    private javax.swing.JComboBox idCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox lgaCB;
    private javax.swing.JTextArea locTA;
    private javax.swing.JTextField otherTF;
    private javax.swing.JComboBox stateCB;
    private javax.swing.JButton subB;
    private javax.swing.JTextField surnameTF;
    // End of variables declaration//GEN-END:variables
}
