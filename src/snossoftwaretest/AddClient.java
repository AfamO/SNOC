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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * It prepares and initializes the necessary SWING GUI components needed for the client registration. It also defines and implements their corresponding methods or/and functions.
 * @author Afam;
 * @see AddContacts.java,AddDeviceInfo.java
 * @version 1.0 
 */
public class AddClient extends JFrame {
private static Properties snosprop=new Properties();

private Reconsoft bis=new Reconsoft();
    /**
     * Creates new form AddClient
     */
    public AddClient() {
        //super(parent, modal);
        initComponents();
        snosprop= bis.getALLSNOS();
        this.RegisteredSnos();
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
       
        lgaCB.setSelectedIndex(0);
        stateCB.setSelectedIndex(0);
        idCB.setSelectedIndex(0);
        passTF.setText(null);
        cemailTF.setText(null);
        otherTF.setText(null);
        
         }
    public void MydatabaseSender(){
      PatternCheck pc=new PatternCheck();
      String sname=surnameTF.getText();
      String fname=firstnameTF.getText();
      String other=otherTF.getText();
      String number=foneTF.getText();
      String location= locTA.getText();
     
      String state=(String)stateCB.getSelectedItem();
      String lga=(String)lgaCB.getSelectedItem();
      
      String ident=(String)idCB.getSelectedItem();
      String pass =passTF.getText();
      String client_email=cemailTF.getText();
      
      if( pc.checkUserName(surnameTF.getText(), "Client Name", "Okolo or Ugwu")
              && pc.checkUserName(firstnameTF.getText(), "First Name", "Mike or John") && pc.checkUserName(otherTF.getText(), "Other Name", "Kelvin or Andrew") && pc.checkLocation(locTA.getText(), "Client's Location","5 Sule Abuka Crescent,Opebi,Ikeja") 
              && pc.checkArbitraryField(pass,"Building Field" , "Bungallow or Duplex or One-Bedroom Flat") && pc.checkEmail(cemailTF.getText(),"Client's Email","newuser@snos.com") 
              )
      {
          //if(this.validateLength(client,"Name", 6) && this.validateLength(contact,"Contact", 8) && this.validateLength(location,"Location", 10) && this.validateLength(area,"Area", 5) )
      //{
      
         UserInforGet get = new UserInforGet(sname,fname,other,number,location,lga,state,ident,pass,client_email);
         SmsMain.snos_id=ident;
         if(new Reconsoft().AddClientInfo(get)==1)
         {
            ClearMyScreen();
            JOptionPane.showMessageDialog(null,"Information Submitted Successfully");
         
            this.Next_Step();
         }
         else
         {
             JOptionPane.showMessageDialog(null,"Information could not be submitted successfully, Contact the Administrator\n Or Ensure that your Database Server is running");
             
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
    
   public void RegisteredSnos(){
       idCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
   }
   public void InitializeIdentityCB()
    {
     Collection enu=ReadMessages1.getSnosProp().values();
     Object[] values;
     values=enu.toArray();
     for(int i=0;i<values.length;i++)
     {
         idCB.addItem(values[i]);
     }
    }
   
   
   
   public boolean ValidateEmptyFields()
   {
       firstnameTF.setEditable(true);
       locTA.setEditable(true);
       idCB.setEditable(true);
       passTF.setEditable(true);
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
       
       else if(lgaCB.getSelectedIndex()==0)
       {
            //stateTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The LGA field is in 'choose one',please select client's LGA\nThank You.","EMPTY NAME FIELD",2);
            return false;
            
       }
       else if(stateCB.getSelectedIndex()==0)
       {
            //idCB.setEditable(false);
           JOptionPane.showMessageDialog (this,"The State field is in 'choose one',please select client's state\nThank You.","EMPTY NAME FIELD",2);
            return false;
       }
       else if(idCB.getSelectedIndex()==0)
       {
            //foneTF.setEditable(false);
            JOptionPane.showMessageDialog (this,"The Identity field is in 'choose one',please select client's snos type\nThank You.","EMPTY NAME FIELD",2);
            return false;
            
       }
       else if(passTF.getText().equals(""))
       {
            //idCB.setEditable(false);
           JOptionPane.showMessageDialog (this,"The password field is empty,please enter client's default password\nThank You.","EMPTY NAME FIELD",2);
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
    
   
 
  
public void Next_Step()
{
   this.setVisible(false);
   AddDeviceInfo b= new AddDeviceInfo();
   b.setResizable(false);
   b.setLocationRelativeTo(null);
   b.setVisible(true);
   b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
   
 


 public static void main(String [] args)
{
    
AddClient m=new  AddClient();
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
        foneTF = new javax.swing.JTextField();
        firstnameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        idCB = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        subB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        cB = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        passTF = new javax.swing.JPasswordField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENT APPLICATION FORM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Fill in Client Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 153))); // NOI18N

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

        idCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "SNOS1", "SNOS2" }));
        idCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idCBItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Identity:");

        subB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        subB.setForeground(new java.awt.Color(0, 0, 153));
        subB.setText("NEXT >>");
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

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Password:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(cB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(subB, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cemailTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(otherTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firstnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(81, 81, 81))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(87, 87, 87))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(84, 84, 84))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(76, 76, 76)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passTF)
                            .addComponent(idCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lgaCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stateCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foneTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(surnameTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(44, 44, 44)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(passTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subB)
                    .addComponent(exitB)
                    .addComponent(cB))
                .addGap(35, 35, 35))
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
                        .addGap(96, 96, 96)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
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
            .addComponent(clientPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clientPane, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBActionPerformed
        // TODO add your handling code here:
        if(this.ValidateEmptyFields()==true)
        {
            this.MydatabaseSender();
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

    private void idCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idCBItemStateChanged
        // TODO add your handling code here:
        foneTF.setText(bis.RetrieveItem("snos_table", "snos_type", (String)idCB.getSelectedItem(),"fone"));
        foneTF.setEditable(false);
    }//GEN-LAST:event_idCBItemStateChanged

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
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPasswordField passTF;
    private javax.swing.JComboBox stateCB;
    private javax.swing.JButton subB;
    private javax.swing.JTextField surnameTF;
    // End of variables declaration//GEN-END:variables
}
