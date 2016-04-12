/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JFrame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This prepares and initializes the necessary SWING GUI components needed for the administrators/SNOC software super users to login and access the application. It also defines and implements the corresponding methods or/and functions.
 * @author Afam;
 * @see Operator.java,AddSensor.java,AssignSensor.java,
 * @version 1.0 
 */
public class AdminLogin extends javax.swing.JDialog {

    /**
     * Creates new form AdminLogin
     */
    private SmsMain m;
    private String sta;
     private String myid;
    private String InfoLogged="";
    public static String getBiodata[]=new String[3];
    public static String student_staffid="";
    public static boolean IsCSO=false;
    public AdminLogin(JFrame parent,SmsMain m2) {
       // super(parent);
        initComponents();
        this.m=m2;
         Rectangle ParentB = parent.getBounds();
        Dimension size = getSize();
        int x = Math.max(0,ParentB.x + (ParentB.width- size.width)/2);
        int y = Math.max(0, ParentB.y+(ParentB.height-size.height)/2);
        setLocation(new Point(x,y));
        

    }

    
    
    
    
 public void LoginCaller()
 {
     String user =useridTF.getText();
     String pass = passTF.getText();
     String status=(String)staCB.getSelectedItem();
     if(user.equals("")||user==null)
     {
         JOptionPane.showMessageDialog(null,"User id Should not be Empty");
     }else if(pass.equals("")||pass==null)
     {
         JOptionPane.showMessageDialog(null, "Password Should not be Empty");
     }else if(staCB.getSelectedIndex()==0)
     {
         JOptionPane.showMessageDialog(null, "Status is Not Selected");
     }else
     {
       
         Reconsoft data=new Reconsoft();
         if(data.CheckregisteredID(user, pass, status))
         {
              String id =data.myID(user, pass, status);
              
              SetStatus(status);
              SmsMain.loginButton.setEnabled(false);//disable login button
              SmsMain.exitButton.setEnabled(false);//disable exit button
              SmsMain.mbar.setVisible(true);
               this.setVisible(false);
              //m.EnablePage(id,this.getStatus());
             
              getBiodata[1]=id;
              getBiodata[2]=status;
              if(status.equalsIgnoreCase("super Admin")&&SmsMain.IsSuperAdminLoggedIn)
              {
                  
                  IsCSO=true;
                  SmsMain.IsSuperAdminLoggedIn=false;
                  this.setVisible(false);
                  
                       myid=JOptionPane.showInputDialog("Please Kindly enter the Client's Identification Key");
         if(myid.equals(""))
         {
             JOptionPane.showMessageDialog(null,"Please Kindly enter the Client's Identification Key and not empty value\nThank you.");
         }
         else if(!data.CheckIfItemExist("snos_client","snos_type", myid))
         {
             JOptionPane.showMessageDialog(null,"We are sorry to inform you that\n this Client's Identification Key does not exist in our database.\nThank you.");
         }
         else
         {
             if(SmsMain.edit_type.equals(""))
             {
                 
             }
             else if(SmsMain.edit_type.equals("client"))
             {
                  EditClientInfo b= new EditClientInfo(myid);
                  b.setResizable(false);
                  b.toFront();
                  b.setLocationRelativeTo(null);
                  b.setVisible(true);
             }
             else if(SmsMain.edit_type.equals("contacts"))
             {
                   EditContactsInfo b= new EditContactsInfo(myid);
                   b.setResizable(false);
                   b.toFront();
                   b.setLocationRelativeTo(null);
                   b.setVisible(true);
             }
             else if(SmsMain.edit_type.equals("device"))
             {
                  EditDeviceInfo b= new EditDeviceInfo(myid);
                   b.setResizable(false);
                   b.toFront();
                   b.setLocationRelativeTo(null);
                   b.setVisible(true);
             }
             else if(SmsMain.edit_type.equals("snosid"))
             {
                  EditSnos b=new  EditSnos(myid); 
                   b.setResizable(false);
                   b.toFront();
                   b.setLocationRelativeTo(null);
                   b.setVisible(true);
             }
        
          }
            
             
              } 
            else if(!status.equalsIgnoreCase("super Admin") && SmsMain.IsSuperAdminLoggedIn){
                this.setVisible(false);
                  JOptionPane.showMessageDialog(null, "Please it is ONLY the Super Admin that is authorized to carry out this operation.\nPlease  do kindly logout and re-login.\nThank you", "UNAUTHORIZED USER", 2); 
                  SmsMain.IsSuperAdminLoggedIn=false;
                   IsCSO=false;
                  
                   
              }
              else
              {
                 
              }
            
                   }
         else
         {
             JOptionPane.showMessageDialog(null, "Invalid user Name Or Password,\nCheck your data Or Alert Administrator");
             //JOptionPane.showMessageDialog(null, "Wrong user or password or status\nplease check and "
                //     + "try again");
             
         }
             // this.setVisible(false);
       //  PrintID b= new PrintID(reg); "2013/4444"
              
             
           //new BiometricMain().EnablePage(); 
          // System.out.println("it is true");
         
     }
      
      
     
 }
 
    private void ClearInputs(){
        
        useridTF.setText("");
     passTF.setText("");
   staCB.setSelectedIndex(0);
    }
   public static void setStudent_StaffId(String id)
   {
       student_staffid=id;
   }
    
  public void ResetB()
  {
      useridTF.setText(null);
      passTF.setText(null);
      staCB.setSelectedIndex(0);
  }  
    
  
    
  public void SetStatus(String s)
  {
      this.sta=s;
  }
  
  public String getStatus()
  {
      return sta;
  }
  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        useridTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        staCB = new javax.swing.JComboBox();
        proB = new javax.swing.JButton();
        reB = new javax.swing.JButton();
        passTF = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AUTHENTICATION FORM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("USER ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("PASSWORD:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("STATUS:");

        staCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Admin", "super Admin", "Data Entry Specialist", "CSO", "Registrar", "VC", "others" }));

        proB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        proB.setText("Process");
        proB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proBActionPerformed(evt);
            }
        });

        reB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        reB.setText("Exit");
        reB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reBActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(proB)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reB)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(useridTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(staCB, 0, 1, Short.MAX_VALUE)
                            .addComponent(passTF, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(useridTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(staCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reB)
                    .addComponent(jButton1)
                    .addComponent(proB))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proBActionPerformed
        // TODO add your handling code here:
        LoginCaller();
    }//GEN-LAST:event_proBActionPerformed

    private void reBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reBActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_reBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ClearInputs();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passTF;
    private javax.swing.JButton proB;
    private javax.swing.JButton reB;
    private javax.swing.JComboBox staCB;
    private javax.swing.JTextField useridTF;
    // End of variables declaration//GEN-END:variables
}
