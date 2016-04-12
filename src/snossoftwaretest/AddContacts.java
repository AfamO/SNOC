/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Tele
 */
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * It prepares and initializes the necessary SWING GUI components needed for the client contacts registration. It also defines and implements their corresponding methods or/and functions.
 * @author Afam;
 * @see AddContacts.java,AddDeviceInfo.java
 * @version 1.0 
 * @author Afam;
 * @see AddClient.java,AddDeviceInfo.java
 * @version 1.0 
 */
public class AddContacts extends JFrame{

    /**
     * Creates new form AddContacts
     */
 private static Properties snosprop=new Properties();
private Reconsoft bis=new Reconsoft();

    public AddContacts() {
       // super(parent, modal);
        initComponents();
         snosprop= bis.getALLSNOS();
        this.RegisteredSnos();
    }

    
    public void RegisteredSnos(){
       idCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
   }
    
    
    
 public void myClear_data()
 {
     nameTF.setText(null);
     mobTF.setText(null);
     mailTF.setText(null);
     relaCB.setSelectedIndex(0);
     idCB.setSelectedIndex(0);
     addTA.setText(null);
     mformatTA.setText(null);
     sformatTA.setText(null);
 }
    
    
    public void Register_data()
    {
      PatternCheck pc=new PatternCheck();
        String nam =nameTF.getText();
        String fone =mobTF.getText();
        String mail=mailTF.getText();
        String rela=(String)relaCB.getSelectedItem();
        String ident=(String)idCB.getSelectedItem();
        String add=addTA.getText();
        String mailf=mformatTA.getText();
        String smsf=sformatTA.getText();
        Reconsoft data = new Reconsoft();
        if( pc.checkUserName(nameTF.getText(), "Contact Person's Name", "Surname FirstName LastName")&& pc.checkLocation(addTA.getText(), "Contact Person's Address","5 Sule Abuka Crescent,Opebi,Ikeja")&& pc.checkGsm(fone, "Contact Person's Mobile Number", "234xxxxxxxxxx or 080xxxxxxxx")  
              && pc.checkArbitraryField(mformatTA.getText(),"E-Mail Format" , "Urgent Fire Services Needed")&& pc.checkArbitraryField(sformatTA.getText(), "SMS Format","Urgent Fire Services Needed")  && pc.checkEmail(mailTF.getText(),"Client's Email","newuser@snos.com") 
              )
        {
           
        data.AddClient_Contacts(ident, nam, rela, add, mail, fone, smsf, mailf); 
        this.myClear_data();
        JOptionPane.showMessageDialog(null," Client Contact Person's Registration Completed Successfully", null,2);
             
        }
    }
    
    
    
    
    public boolean Check_EmptyFields()
    {
        if(nameTF.getText().equals(""))
       {
            
            JOptionPane.showMessageDialog (this,"The name field is empty,please enter Contact Person's name\nThank You.","EMPTY NAME FIELD",2);
           return false;
       }
       
       else if(addTA.getText().equals(""))
       {
            
           JOptionPane.showMessageDialog (this,"The Address Field is empty,please enter Contact Person's Address\nThank You.","EMPTY ADDRESS FIELD ALERT",2);
           return false;
       }
       else if(mformatTA.getText().equals(""))
       {
            
            JOptionPane.showMessageDialog (this,"The E-Mail Format field is empty,please Enter Contact Person's E-Mail Format\nThank You.","EMPTY E-MAIL FORMAT ALERT",2);
            return false;
       }
        else if(sformatTA.getText().equals(""))
       {
            
            JOptionPane.showMessageDialog (this,"The SMS Format field is empty,please Enter Contact Person's SMS Format\nThank You.","EMPTY SMS FORMAT ALERT",2);
            return false;
        }
       else if(relaCB.getSelectedIndex()==0)
       {
           
            JOptionPane.showMessageDialog (this,"The Relation field is in 'choose one',please select Contact Person's  Relation\nThank You.","EMPTY RELATION FIELD",2);
            return false;
            
       }
       else if(idCB.getSelectedIndex()==0)
       {
            
            JOptionPane.showMessageDialog (this,"The Identity field is in 'choose one',please select Contact Person's SNOS type\nThank You.","EMPTY NAME FIELD",2);
            return false;
            
       }
       else if(mobTF.getText().equals(""))
       {
           
           JOptionPane.showMessageDialog (this,"The Mobile Number field is empty,please enter Contact Person's Mobile Number\nThank You.","EMPTY MOBILE NUMBER FIELD",2);
            return false;
       }
       else if(mailTF.getText().equals(""))
       {
            
            JOptionPane.showMessageDialog (this,"The Contact Person's Email field is iempty,please enter Contact Person's email\nThank You.","EMPTY  EMAIL FIELD",2);
            return false;
            
       }
       else
       {
           return true;
       } 
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainP = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        finishCB = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mformatTA = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sformatTA = new javax.swing.JTextArea();
        idCB = new javax.swing.JComboBox();
        relaCB = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        mailTF = new javax.swing.JTextField();
        mobTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("CONTACT PERSON INFORMATION");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mobile Number:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("E-Mail:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Relationship:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Identity:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("E-Mail Format:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("SMS Format:");

        finishCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        finishCB.setText("Finish");
        finishCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishCBActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("<< Back");

        mformatTA.setColumns(20);
        mformatTA.setRows(5);
        jScrollPane1.setViewportView(mformatTA);

        sformatTA.setColumns(20);
        sformatTA.setRows(5);
        jScrollPane2.setViewportView(sformatTA);

        idCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one" }));

        relaCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        relaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Brother", "Sister", "Mother", "Father", "Son", "In-Law", "Agency", "Security Agent", "Uncle", "Others" }));

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane3.setViewportView(addTA);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Address:");

        javax.swing.GroupLayout mainPLayout = new javax.swing.GroupLayout(mainP);
        mainP.setLayout(mainPLayout);
        mainPLayout.setHorizontalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                                    .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(mainPLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63)))
                            .addGroup(mainPLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)))
                        .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(mailTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(relaCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mobTF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton2)
                        .addGap(70, 70, 70)
                        .addComponent(finishCB, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        mainPLayout.setVerticalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(relaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel9))
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel8)
                        .addGap(45, 45, 45))
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(finishCB))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finishCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishCBActionPerformed
        // TODO add your handling code here:
         if(this.Check_EmptyFields()==true)
        {
             this.Register_data();
        }
     
    }//GEN-LAST:event_finishCBActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addTA;
    private javax.swing.JButton finishCB;
    private javax.swing.JComboBox idCB;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JTextField mailTF;
    private javax.swing.JPanel mainP;
    private javax.swing.JTextArea mformatTA;
    private javax.swing.JTextField mobTF;
    private javax.swing.JTextField nameTF;
    private javax.swing.JComboBox relaCB;
    private javax.swing.JTextArea sformatTA;
    // End of variables declaration//GEN-END:variables
}
