/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author lIONSEAL
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainScreen extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private static MainScreen splashScreen;
    private static int count = 1, TIMER_PAUSE = 25,PROGBAR_MAX=150;
    private static Timer progressBarTimer;
    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            //System.out.println(count);
            if (PROGBAR_MAX == count) {
                splashScreen.dispose();//dispose of splashscreen
                progressBarTimer.stop();//stop the timer
                createAndShowFrame();
            }
            count++;//increase counter

        }
    };

    public MainScreen() {
        createSplash();
    }

    private void createSplash() {
        Container container = getContentPane();

        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        container.add(panel, BorderLayout.CENTER);

       // final ImageIcon icon = new ImageIcon(getClass().getResource("/SNOSSoftwareTest/home.jpg"));
        JLabel lab1 = new JLabel("AUTHENTICATING MODEM...!");
        lab1.setFont(new Font("Verdana", Font.BOLD, 14));
        lab1.setForeground(Color.BLUE);
        panel.add(lab1);
         JLabel la = new JLabel("Please wait...");
        la.setFont(new Font("Verdana", Font.ITALIC, 14));
        la.setForeground(Color.GREEN);
        panel.add(la);

        progressBar.setMaximum(PROGBAR_MAX);
        container.add(progressBar, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startProgressBar();
    }

    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    private void createAndShowFrame() {
        SmsMain sm = new SmsMain();
        sm.setSize(1090, 720);
        sm.setLocationRelativeTo(null);
        sm.setResizable(false);
        sm.setVisible(true);
       sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   // public static void main(String[] args) {
       
    //}
}
