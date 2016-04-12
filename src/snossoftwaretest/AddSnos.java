/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This prepares and initializes the necessary SWING GUI components needed for the registration of the available SNOS Numbers OR CLIENT IDENTIFICATION KEYS. It also defines and implements their corresponding methods or/and functions.
 * @author Afam;
 * @see AddSensor.java,AssignSensor.java
 * @version 1.0 
 */
public class AddSnos extends JFrame {

    /**
     * Creates new form AddSensor
     */
    public AddSnos() {
        //super(parent, modal);
        initComponents();
        
    }
    
    public void SetInformationNull(){
        senTF.setText(null);
        foneTF.setText(null);
    }
    
    public void MyCaller(){
        String snos=senTF.getText();
        String fone=foneTF.getText();
        //if()
        PatternCheck pc=new PatternCheck();
        UserInforGet get = new UserInforGet(snos,fone);
         Reconsoft check = new Reconsoft();
         
         if(pc.checkSnosLabel(snos, "Snos Name Field", "SNOS1 or SNOS 2 or snos30") && pc.checkGsm(fone, "Snos Gsm Field", "234xxxxxxxxxx or 080xxxxxxxx"))
       if(check.CheckExistingSNOS(snos)||check.CheckIfItemExist("snos_client","snos_type", snos)){
           
     JOptionPane.showMessageDialog (this,"This particular SNOS  already exists,please register another\nThank You.","SNOS TYPE ALREADY EXIST ALERT",2);
                                       
      }
       else{
                                    //check.AddSnos(get); 
                                     SetInformationNull();
          JOptionPane.showMessageDialog(null,"Information Submitted Successfully");
  
       }
                  
    }
    private void display (String msg,String label){
       JOptionPane.showMessageDialog(this,msg,"EMPTY "+label.toUpperCase(),2);
   }
    
    public boolean ValidateEmptyFields()
   {
       
       if(senTF.getText().equals(""))
       {
            //conTF.setEditable(false);
           display("The Snos Name field is empty,please enter Snos's name\nThank You.","SNOS NAME FIELD");
           return false;
       }
       else if(foneTF.getText().equals(""))
       {
            //locTA.setEditable(false);
           display("The Snos GSM Number field is empty,please enter Snos's Gsm Number\nThank You.","SNOS gsm number FIELD");
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
        jLabel1 = new javax.swing.JLabel();
        conPane = new javax.swing.JPanel();
        senTF = new javax.swing.JTextField();
        foneTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bB = new javax.swing.JButton();
        pB = new javax.swing.JButton();
        qB = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SNOS Information");

        conPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Enter Snos Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 102))); // NOI18N
        conPane.setForeground(new java.awt.Color(0, 51, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("SNOS Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Phone Number:");

        bB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bB.setForeground(new java.awt.Color(0, 0, 102));
        bB.setText("<<Back");
        bB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBActionPerformed(evt);
            }
        });

        pB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pB.setForeground(new java.awt.Color(0, 0, 102));
        pB.setText("Process");
        pB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pBActionPerformed(evt);
            }
        });

        qB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qB.setForeground(new java.awt.Color(0, 0, 102));
        qB.setText("Quit");
        qB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout conPaneLayout = new javax.swing.GroupLayout(conPane);
        conPane.setLayout(conPaneLayout);
        conPaneLayout.setHorizontalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conPaneLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(senTF, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(conPaneLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(bB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(qB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        conPaneLayout.setVerticalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pB)
                    .addComponent(qB))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout mainPLayout = new javax.swing.GroupLayout(mainP);
        mainP.setLayout(mainPLayout);
        mainPLayout.setHorizontalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(conPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 69, Short.MAX_VALUE))
        );
        mainPLayout.setVerticalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(conPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pBActionPerformed
        // TODO add your handling code here:
         if(this.ValidateEmptyFields()==true)
        {
            this.MyCaller();
        }
        
    }//GEN-LAST:event_pBActionPerformed

    private void qBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qBActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_qBActionPerformed

    private void bBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBActionPerformed
        // TODO add your handling code here:
          this.setVisible(false);
    }//GEN-LAST:event_bBActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bB;
    private javax.swing.JPanel conPane;
    private javax.swing.JTextField foneTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel mainP;
    private javax.swing.JButton pB;
    private javax.swing.JButton qB;
    private javax.swing.JTextField senTF;
    // End of variables declaration//GEN-END:variables
    
    
}
