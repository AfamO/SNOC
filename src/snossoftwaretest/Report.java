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
/**
 * This sets up the needed GUI for searching SMS by date or by SNOS type.
 * @author Charles;
 * @see GeneReportTable.java,Action_show.java,Date_Report.java
 * @version 1.0 
 */
public class Report extends JFrame{

 private JLabel datL;  
    
    public Report() {
        //super(parent, modal);
        initComponents();
        datP.setVisible(false);
        snosP.setVisible(false);
       // ParameterInfor();
    }

    public void ParameterInfor(){
      if(dateCB.isSelected()){
        datP.setVisible(true);
        snosP.setVisible(false); 
        snosCB.setEnabled(false);
      }else {
             snosCB.setEnabled(true);
             datP.setVisible(false);
        if(snosCB.isSelected()){
          datP.setVisible(false);
         snosP.setVisible(true);
         dateCB.setEnabled(false);
        }else{
            dateCB.setEnabled(true); 
            snosP.setVisible(false);
        }
      } 
    }
    
    public void dateDatabaseInformation(){
     String datpara =datTF.getText(); 
     Reconsoft date1= new Reconsoft();
     UserInforGet data=date1.dateMessageInfor(datpara);
     
     datL=new JLabel();
     datL.setText(data.getSensor());
     String dpara=datL.getText();
     DateReport record = new DateReport(dpara);  
     System.out.println(dpara);
    }
    
    
    
    public void SnosTypeDatabaseInformation(){
        String para=snosTF.getText();
        Snos_Report record = new Snos_Report(para);
           }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReportMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        conPane = new javax.swing.JPanel();
        dateCB = new javax.swing.JCheckBox();
        snosCB = new javax.swing.JCheckBox();
        datP = new javax.swing.JPanel();
        datTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        datB = new javax.swing.JButton();
        snosP = new javax.swing.JPanel();
        snosTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        snosB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Message Statistics");

        conPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Check any of the Parameters to have you report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 3, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        dateCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dateCB.setForeground(new java.awt.Color(0, 51, 102));
        dateCB.setText("Search With Date");
        dateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateCBActionPerformed(evt);
            }
        });

        snosCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        snosCB.setForeground(new java.awt.Color(0, 51, 102));
        snosCB.setText("Search with SNOS Type");
        snosCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snosCBActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Date:");

        datB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        datB.setForeground(new java.awt.Color(0, 51, 153));
        datB.setText("Find");
        datB.setToolTipText("search text messages");
        datB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datPLayout = new javax.swing.GroupLayout(datP);
        datP.setLayout(datPLayout);
        datPLayout.setHorizontalGroup(
            datPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(datTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        datPLayout.setVerticalGroup(
            datPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(datPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datTF)
                    .addComponent(datB))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("SNOS Type:");

        snosB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snosB.setForeground(new java.awt.Color(0, 51, 153));
        snosB.setText("Find");
        snosB.setToolTipText("search text messages");
        snosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snosBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout snosPLayout = new javax.swing.GroupLayout(snosP);
        snosP.setLayout(snosPLayout);
        snosPLayout.setHorizontalGroup(
            snosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, snosPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(snosTF, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(snosB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        snosPLayout.setVerticalGroup(
            snosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, snosPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(snosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(snosTF)
                    .addComponent(snosB))
                .addContainerGap())
        );

        javax.swing.GroupLayout conPaneLayout = new javax.swing.GroupLayout(conPane);
        conPane.setLayout(conPaneLayout);
        conPaneLayout.setHorizontalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conPaneLayout.createSequentialGroup()
                .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(conPaneLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(snosCB, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateCB, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(conPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(snosP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        conPaneLayout.setVerticalGroup(
            conPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateCB)
                .addGap(18, 18, 18)
                .addComponent(snosCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(snosP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ReportMainLayout = new javax.swing.GroupLayout(ReportMain);
        ReportMain.setLayout(ReportMainLayout);
        ReportMainLayout.setHorizontalGroup(
            ReportMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportMainLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(ReportMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(conPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        ReportMainLayout.setVerticalGroup(
            ReportMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportMainLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(conPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReportMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ReportMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateCBActionPerformed
        // TODO add your handling code here:
        ParameterInfor();
    }//GEN-LAST:event_dateCBActionPerformed

    private void snosCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snosCBActionPerformed
        // TODO add your handling code here:
        ParameterInfor();
    }//GEN-LAST:event_snosCBActionPerformed

    private void snosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snosBActionPerformed
        // TODO add your handling code here:
        SnosTypeDatabaseInformation();
    }//GEN-LAST:event_snosBActionPerformed

    private void datBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datBActionPerformed
        // TODO add your handling code here:
        this.dateDatabaseInformation();
    }//GEN-LAST:event_datBActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ReportMain;
    private javax.swing.JPanel conPane;
    private javax.swing.JButton datB;
    private javax.swing.JPanel datP;
    private javax.swing.JTextField datTF;
    private javax.swing.JCheckBox dateCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton snosB;
    private javax.swing.JCheckBox snosCB;
    private javax.swing.JPanel snosP;
    private javax.swing.JTextField snosTF;
    // End of variables declaration//GEN-END:variables
}
