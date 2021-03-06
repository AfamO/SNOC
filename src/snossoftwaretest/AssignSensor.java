/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.util.Properties;
import javax.swing.*;
/**
 * This prepares and initializes the necessary SWING GUI components needed to assign sensors 
 * to a particular SNOS number belonging to a particular client. 
 * It also defines and implements the corresponding methods or/and functions.
 * @author Afam;
 * @see AddSnos.java,AddSensor.java
 * @version 1.0 
 */
public class AssignSensor extends JFrame {
 private static Properties snosprop=new Properties();
private Reconsoft bis=new Reconsoft();
    /**
     * Creates new form AssignSensor
     */
    public AssignSensor() {
       
        initComponents();
        this.RegisteredSnosRecord();
         snosprop= bis.getALLSNOS();
        this.RegisteredSnos();
        
    }
    
    
    public void RegisteredSnos(){
       idCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
   }
    
    public void SetInformationNull()
    {
        
        sensorTF.setText("");
        idCB.setSelectedIndex(0);
        sensorCB.setSelectedIndex(0);
                
    }
    
    
   
    
    
   
    public void SendSensorTodb(){
        String snos_id=(String)idCB.getSelectedItem();
        String sensor_text= sensorTF.getText();
        String sensor=(String)sensorCB.getSelectedItem();
     
        if(new PatternCheck().checkSensorField(sensor_text, "Sensor Name", "URGENT ASSISTANCE or ALARM CLOSED\nDoor Open or DOOR CLOSED"))
       if(new Reconsoft().CheckIfItemExist("sensor_table","sensor", sensor_text)) 
       {
            display("The Sensor name is already registered,please enter a NEW sensor name\nThank You.","INVALID Sensor name");
       }
        
       else
       {
            new Reconsoft().AddSensor(snos_id,sensor_text,sensor);
            SetInformationNull();
            display("Information Submitted Successfully","SUCCESSFUL INSERTION");
       }
       
      
    }

    
    public void RegisteredSnosRecord(){
       //snosCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofRegisteredSnos()));
       sensorCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofSensors()));
   }
    private void display (String msg,String label){
       JOptionPane.showMessageDialog(this,msg,label.toUpperCase(),2);
    }
    public boolean ValidateEmptyFields()
   {
       
     if(idCB.getSelectedIndex()==0)
       {
            
            JOptionPane.showMessageDialog (this,"The Client field is in 'choose one',please select Sensor's SNOS type\nThank You.","EMPTY CLIENT FIELD",2);
            return false;
            
       }else if(sensorTF.getText().equals(""))
       {
         
           display("The Sensor name field is empty,please enter Sensor's name\nThank You.","EMPTY Sensor name FIELD");
            return false;
       }else if(sensorCB.getSelectedIndex()==0)
       {
            
            JOptionPane.showMessageDialog (this,"The Sensor field is in 'choose one',please select Sensor's type\nThank You.","EMPTY SENSOR TYPE FIELD",2);
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
        jLabel3 = new javax.swing.JLabel();
        bB = new javax.swing.JButton();
        pB = new javax.swing.JButton();
        qB = new javax.swing.JButton();
        sensorTF = new javax.swing.JTextField();
        idCB = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sensorCB = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sensor Information");

        conPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Enter Sensor Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 102))); // NOI18N
        conPane.setForeground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sensor Text:");

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

        idCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one" }));
        idCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idCBItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Sensor Type:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Client");

        sensorCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sensorCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Door Contact", "Panic Button", "Motion Sensor", "Smoke Detector", "Curtain IR Detector", "Gas Detector", "Door Contact", "Microphone", "Perimeter" }));
        sensorCB.setEnabled(false);
        sensorCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sensorCBItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout conPaneLayout = new javax.swing.GroupLayout(conPane);
        conPane.setLayout(conPaneLayout);
        conPaneLayout.setHorizontalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conPaneLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(bB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(qB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(conPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(conPaneLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conPaneLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(conPaneLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sensorCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        conPaneLayout.setVerticalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conPaneLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bB)
                    .addComponent(pB)
                    .addComponent(qB))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPLayout = new javax.swing.GroupLayout(mainP);
        mainP.setLayout(mainPLayout);
        mainPLayout.setHorizontalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                        .addComponent(conPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        mainPLayout.setVerticalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(conPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        conPane.getAccessibleContext().setAccessibleName("Register Sensors");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pBActionPerformed
        // TODO add your handling code here:
        if(this.ValidateEmptyFields())
        {
            SendSensorTodb();
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

    private void idCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idCBItemStateChanged
        // TODO add your handling code here:
        if(idCB.getSelectedIndex()==0){
            
        }
        else
        {
            sensorCB.setEnabled(true);
        }
    }//GEN-LAST:event_idCBItemStateChanged

    private void sensorCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sensorCBItemStateChanged
        // TODO add your handling code here:
        /*
         if(idCB.getSelectedIndex()==0){
            sensorCB.setEnabled(false);
        }
        else
        {
            
        }
        */
    }//GEN-LAST:event_sensorCBItemStateChanged

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bB;
    private javax.swing.JPanel conPane;
    private javax.swing.JComboBox idCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel mainP;
    private javax.swing.JButton pB;
    private javax.swing.JButton qB;
    private javax.swing.JComboBox sensorCB;
    private javax.swing.JTextField sensorTF;
    // End of variables declaration//GEN-END:variables
}
