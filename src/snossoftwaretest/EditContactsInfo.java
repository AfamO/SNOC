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
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 This class prepares and initializes the necessary SWING GUI components needed 
 * to modify and edit the clients’ contacts registered information. 
 * It also defines and implements the corresponding methods or/and functions.
 * @author Afam
 * @see EditClientInfo.java,EditDeviceInfo.java
 * @version 1.0 
 */
public class EditContactsInfo extends JFrame{

    /**
     * Creates new form AddContacts
     */
 private static Properties snosprop=new Properties();
 private Reconsoft bis=new Reconsoft();
 private Vector row;
 private String RowId="";
 private int rowCounter=0;
    public EditContactsInfo(String id) {
       // super(parent, modal);
        initComponents();
         snosprop= bis.getALLSNOS();
         SmsMain.snos_id=id;
          GetContactsInfo(id);
         rowCounter=0;
         
        this.DisplayContactsInfo(rowCounter);
        if(row.size()>1)
         {
             nextB.setEnabled(true);
             prevB.setEnabled(true);
         }
        //this.RegisteredSnos();
    }

    
    public void RegisteredSnos(){
      // idCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
   }
    
    
    
 public void myClear_data()
 {
     nameTF.setText(null);
     mobTF.setText(null);
     mailTF.setText(null);
     relaCB.setSelectedIndex(0);
    
     addTA.setText(null);
     mformatTA.setText(null);
     sformatTA.setText(null);
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
    
   public void GetContactsInfo(String id)
    {
      row=new Vector();

                    row=bis.getClientContactsDetails(id, "clients_contacts_tab");
    }
    public void DisplayContactsInfo(int counter)
    {
      //row=new Vector();
        String value[]=(String[])row.get(counter);
        String rela[] = this.BuildItems(relaCB);
        nameTF.setText(value[0]);
        relaCB.setSelectedIndex(this.SearchObjectsPosition(rela, value[1])); 
        addTA.setText(value[2]);
        mailTF.setText(value[3]);
        mobTF.setText(value[4]);
        sformatTA.setText(value[5]);
        mformatTA.setText(value[6]);
        RowId=value[7];
                     
    }
    
    public void UpdateContactsInfo()
    {
        
      PatternCheck pc=new PatternCheck();
        String nam =nameTF.getText();
        String fone =mobTF.getText();
        String mail=mailTF.getText();
        String rela=(String)relaCB.getSelectedItem();
      
        String add=addTA.getText();
        String mailf=mformatTA.getText();
        String smsf=sformatTA.getText();
        Reconsoft data = new Reconsoft();
        String   ident=SmsMain.snos_id;
         if(this.Check_EmptyFields()==true)
        {
            
        
        if( pc.checkUserName(nameTF.getText(), "Contact Person's Name", "Surname FirstName LastName")&& pc.checkLocation(addTA.getText(), "Contact Person's Address","5 Sule Abuka Crescent,Opebi,Ikeja")&& pc.checkGsm(fone, "Contact Person's Mobile Number", "234xxxxxxxxxx or 080xxxxxxxx")  
              && pc.checkArbitraryField(mformatTA.getText(),"E-Mail Format" , "Urgent Fire Services Needed")&& pc.checkArbitraryField(sformatTA.getText(), "SMS Format","Urgent Fire Services Needed") && pc.checkEmail(mailTF.getText(),"Client's Email","newuser@snos.com")
              )
        {
         
        UserInforGet get = new  UserInforGet(ident,nam,rela,add,mail,fone);
         
         
        if(bis.UpdateClientsContactsDetails(ident, get, RowId,smsf,  mailf)==1)
        {
            this.myClear_data();
         JOptionPane.showMessageDialog(null,"Information Updated Successfully");
         this.GetContactsInfo(ident);
         DisplayContactsInfo(rowCounter);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Information could not be updated successfully, Contact the Administrator\n Or Ensure that your Database Server is running");
        }
        
        
        
             
        }
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
    
   public static void main(String [] args)
{
    
EditContactsInfo m=new  EditContactsInfo("SNOS3");
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

        mainP = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nextB = new javax.swing.JButton();
        prevB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mformatTA = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sformatTA = new javax.swing.JTextArea();
        relaCB = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        mailTF = new javax.swing.JTextField();
        mobTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("E-Mail Format:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("SMS Format:");

        nextB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nextB.setText("Next >>");
        nextB.setEnabled(false);
        nextB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBActionPerformed(evt);
            }
        });

        prevB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        prevB.setText("<< Previous");
        prevB.setEnabled(false);
        prevB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBActionPerformed(evt);
            }
        });

        mformatTA.setColumns(20);
        mformatTA.setRows(5);
        jScrollPane1.setViewportView(mformatTA);

        sformatTA.setColumns(20);
        sformatTA.setRows(5);
        jScrollPane2.setViewportView(sformatTA);

        relaCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        relaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Brother", "Sister", "Mother", "Father", "Wife", "Son", "In-Law", "Agency", "Chief Security Officer", "Security Agent", "Uncle", "Others" }));

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane3.setViewportView(addTA);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Address:");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Save Changes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Quit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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
                            .addComponent(mobTF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prevB, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextB, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9))
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7))
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8))
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevB)
                    .addComponent(nextB)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
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

    private void nextBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBActionPerformed
        // TODO add your handling code here:
        
        
         if (row.size()>rowCounter+1)
        {
            rowCounter++;
            prevB.setEnabled(true);
        this.DisplayContactsInfo(rowCounter);
        
        }
         if((row.size()==rowCounter+1))
        {
            nextB.setEnabled(false);
             prevB.setEnabled(true);
        }
         else if(row.size()>1)
         {
             //nextB.setEnabled(true);
             prevB.setEnabled(true);
         }
     
    }//GEN-LAST:event_nextBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.myClear_data();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        UpdateContactsInfo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void prevBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBActionPerformed
        // TODO add your handling code here:
        if(row.size()>rowCounter)
        {
            rowCounter--;
        this.DisplayContactsInfo(rowCounter);
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

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addTA;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JButton nextB;
    private javax.swing.JButton prevB;
    private javax.swing.JComboBox relaCB;
    private javax.swing.JTextArea sformatTA;
    // End of variables declaration//GEN-END:variables
}
