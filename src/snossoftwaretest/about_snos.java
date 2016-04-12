/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tele
 */
public class about_snos extends JFrame {

    private JPanel Main_pan;
    /**
     * Creates new form about_snos
     */
    public about_snos() {
      
        initComponents();
        aboutTA.setEditable(false);
     setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage()); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content_pan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        aboutTA = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(53, 109, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ABOUT SNOS FORM");

        aboutTA.setColumns(20);
        aboutTA.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        aboutTA.setRows(5);
        aboutTA.setText("        SNOS……real-time monitoring and surveillance, with SMS and\n e-mail alerts, of your treasures wherever they may be located\n from wherever you are in Nigeria and in the world, whenever\n you want…….. …….you may need a second or third person or\n your security company or guard or the nearest Police post/station/patrol\n team or whoever you designate to also receive the alerts for rapid \nresponse intervention………. \nThese designated persons must necessarily be physically close to your\n Location of Treasure (LoT) for the purpose of rapid response intervention.\n ……your LoT may be your home where you live or your country/village home\n where you visit infrequently or office or factory or bank or warehouse or \nevent centre/place or project/construction site or plot of land or restaurant or shop. \nIt can be your Church or Mosque compound. Your Object of Treasure (OoT)\ncan be your car or truck or yacht or ship or aircraft. In your home or office building,\n your Point of Treasure (PoT) may be your cash/gold/diamond/certificates/landed\n property document vault or a particular room or garage or main gate or\n parking lot or sitting room or kitchen or corridor or veranda or\n balcony or lobby or particular doors or windows or cupboards or\n lockers or drawers or shelves or any of the 4 segments of your home/ building\n perimeter fence, etc………\nYou can designate wherever you want as your point of treasure! \n……”had I known” or “had we known” is always too late and it has cost implications:\n the cost may be financial or material or tragic loss of human life or lives…… \n……if you think providing security is too expensive, please try insecurity………\n\n\n");
        aboutTA.setWrapStyleWord(true);
        jScrollPane1.setViewportView(aboutTA);

        final ImageIcon icon = new ImageIcon(getClass().getResource("/snossoftwaretest/setup.png"));
        content_pan = new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(icon.getImage(), 0,0, null);
                super.paintComponent(g);
                //super.paintComponent(g);

            }
        };
        content_pan.setLayout(null);

        content_pan.setOpaque(false);

        Main_pan = new JPanel();
        Main_pan.setLayout(new BorderLayout());
        getContentPane().add(Main_pan);
        Main_pan.add(content_pan, BorderLayout.CENTER );

        javax.swing.GroupLayout content_panLayout = new javax.swing.GroupLayout(content_pan);
        content_pan.setLayout(content_panLayout);
        content_panLayout.setHorizontalGroup(
            content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_panLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        content_panLayout.setVerticalGroup(
            content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_panLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aboutTA;
    private javax.swing.JPanel content_pan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}