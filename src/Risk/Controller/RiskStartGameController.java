/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RiskStartGame.java
 *
 * Created on Nov 15, 2010, 11:13:31 AM
 */

package Risk.Controller;

import java.io.IOException;
import javax.sound.midi.*;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author cube
 */
public class RiskStartGameController extends java.awt.Frame {
    /** Creates new form RiskStartGame */

    public RiskStartGameController() {
       initComponents();
        jButton1.setEnabled(false);
        setLocationRelativeTo(null);

        try{
        Sequence song = MidiSystem.getSequence(getClass().getResourceAsStream("resources/song.mid"));
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();

        sequencer.setSequence(song);
        sequencer.setLoopCount(2);
        //sequencer.start();

        }catch (IOException e) {}
        catch (MidiUnavailableException e) {}
        catch (InvalidMidiDataException e) {}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(1, 1, 1));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 1, 1));
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setText("Start Game");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Exit");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Add RiskPlayer");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Risk/resources/risk-logo.jpg"))); // NOI18N
        jLabel1.setName("jLabel1");
        
        JButton btnChooseMap = new JButton();
        btnChooseMap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnChooseMap.setText("Choose Map");
        btnChooseMap.setName("jButton1");
        btnChooseMap.setEnabled(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel1)
        					.addContainerGap(43, Short.MAX_VALUE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(109)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
        						.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        						.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        						.addComponent(btnChooseMap, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
        					.addGap(87))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnChooseMap)
        			.addGap(12)
        			.addComponent(jButton1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jButton2)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton3)
        			.addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            RiskAddPlayerController add = new RiskAddPlayerController();
            add.setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

             RiskController.ShowGUI();
             setVisible(false);
        
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed



    /**
    * @param args the command line arguments
    */



    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiskStartGameController().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
