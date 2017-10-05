/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RiskAddPlayerUI.java
 *
 * Created on Nov 15, 2010, 12:39:35 PM
 */

package Risk.Controller;
import Risk.Model.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author cube
 */
public class RiskAddPlayerController extends java.awt.Frame {
    /** Creates new form RiskAddPlayerUI */
    public RiskAddPlayerController() {
        initComponents();
        setLocationRelativeTo(null); 
    }
    boolean added;
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Integer[] numbers = { 3,4,5,6};
      //Create the combo box, select item at index 4.
      //Indices start at 0, so 4 specifies the pig.
      JComboBox comboBox = new JComboBox(numbers);
//        JComboBox<String> jComboBox1 = new javax.swing.JComboBox();
//        jComboBox1.addItem("3");
//        jComboBox1.addItem("4");
//        jComboBox1.addItem("5");
//        jComboBox1.addItem("6");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 1, 1));
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setText("Ok");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        JLabel lblEnterTheNumber = new JLabel();
        lblEnterTheNumber.setText("Enter the number of players");
        lblEnterTheNumber.setName("jLabel1");
        lblEnterTheNumber.setForeground(new Color(254, 254, 254));
        
        //JComboBox comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		JComboBox cb1=(JComboBox)event.getSource();
        		Integer numPlayers=(Integer)cb1.getSelectedItem();
        		added=false;
        		if(numPlayers==3){
        			RiskGameModel.addPlayer("Player1");
        			RiskGameModel.addPlayer("Player2");
        			RiskGameModel.addPlayer("Player3");
        		}
        		else if(numPlayers==4)  {
        			RiskGameModel.addPlayer("Player1");
        			RiskGameModel.addPlayer("Player2");
        			RiskGameModel.addPlayer("Player3");
        			RiskGameModel.addPlayer("Player4");
        		}
        		else if(numPlayers==5) {
        			RiskGameModel.addPlayer("Player1");
        			RiskGameModel.addPlayer("Player2");
        			RiskGameModel.addPlayer("Player3");
        			RiskGameModel.addPlayer("Player4");
        			RiskGameModel.addPlayer("Player5");
        		}
        		else if(numPlayers==6) {
        			RiskGameModel.addPlayer("Player1");
        			RiskGameModel.addPlayer("Player2");
        			RiskGameModel.addPlayer("Player3");
        			RiskGameModel.addPlayer("Player4");
        			RiskGameModel.addPlayer("Player5");
        			RiskGameModel.addPlayer("Player6");
        		}
        		 added=true;
        		}
        
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(202, Short.MAX_VALUE)
        			.addComponent(lblEnterTheNumber, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
        			.addGap(114))
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(377, Short.MAX_VALUE)
        			.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        			.addGap(12))
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(239)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(243, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblEnterTheNumber)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(12)
        			.addComponent(jButton1)
        			.addGap(20))
        );
        jPanel1.setLayout(jPanel1Layout);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        setVisible(false);
    }//GEN-LAST:event_exitForm

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           if(added == true) {
               System.out.println(" added.");
               setVisible(false);
                RiskStartGameController.jButton1.setEnabled(true);
                    }
        }
   //GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox comboBox;
}
