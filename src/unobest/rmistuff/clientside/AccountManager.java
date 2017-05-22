/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.clientside;

import Interfaces.ListOfCustomersInter;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import unobest.model.Customer;
import unobest.rmistuff.serverside.impl.ListOfCustomers;
import unobest.rmistuff.serverside.interfaces.BusServer;
import unobest.rmistuff.serverside.interfaces.CustomerFascade;
import unobest.util.EncryptionFunctions;

/**
 *
 * @author comqdhb
 */
public class AccountManager extends javax.swing.JFrame {

    String thisCust;
        ListOfCustomersInter server2 = (ListOfCustomersInter) ClientSideServerInstance.getServer("127.0.0.1");


    /**
     * Creates new form AccountManager
     */
    public AccountManager() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Old");

        jLabel2.setText("New");

        jLabel3.setText("New");

        jPasswordField1.setText("jPasswordField1");

        jPasswordField2.setText("jPasswordField2");

        jPasswordField3.setText("jPasswordField3");

        jButton1.setText("Update Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("De Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jButton2)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jButton2)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        final AccountManager thisClass = this;
        SwingWorker worker12 = new SwingWorker(){

            @Override
            protected Object doInBackground() throws Exception {
                String currentPwd = EncryptionFunctions.encryptPassword(jPasswordField1.getText());

        if (jPasswordField2.getText().equals(jPasswordField3.getText())) //New passwords match
        {
            try {
                //Match
                //Check old password matches

                BusServer server = ClientSideServerInstance.getServer("127.0.0.1");
                CustomerFascade cust = server.getCustomer(thisCust, currentPwd, null);
                ListOfCustomersInter serverCust = (ListOfCustomersInter) server;

                System.out.println(cust);
                if (cust != null) //Old password matches
                {
                    //Replace old with new (remember encrpytion)
                    String newPassword = EncryptionFunctions.encryptPassword(jPasswordField2.getText());
                    serverCust.changePassword(thisCust, newPassword);
                    JOptionPane.showMessageDialog(rootPane, "Password Updated");

                    ClientLogin login = new ClientLogin();
                    login.setVisible(true);
                    thisClass.dispose();
                    server2.saveCustToFile();
                    //AccountManager ac = new AccountManager();
                    //ac.setVisible(false);

                } else {
                    //Old password incorrect
                    JOptionPane.showMessageDialog(rootPane, "Old Password Is Incorrect");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //new passwords do not match. Do something
            JOptionPane.showMessageDialog(rootPane, "New Passwords Do Not Match");

        }
        return null;
            }
            
            
        };
                    
            
            
            worker12.execute();
            
            
        

       


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        final AccountManager thisClass = this;
        SwingWorker worker13 = new SwingWorker(){
        
        

            @Override
            protected Object doInBackground() throws Exception {
                try {
            // TODO add your handling code here:

            String currentPwd = EncryptionFunctions.encryptPassword(jPasswordField1.getText());
            //Confirm password
            BusServer server = ClientSideServerInstance.getServer("127.0.0.1");
            CustomerFascade cust = server.getCustomer(thisCust, currentPwd, null);
            ListOfCustomersInter serverCust = (ListOfCustomersInter) server;

            System.out.println(cust);

            //Check if the old password is correct
            if (cust != null) {
                //Remove customer
                int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + thisCust + " form the system", "WARNING", JOptionPane.YES_NO_OPTION);

                if (dialogButton == JOptionPane.YES_OPTION) {
                    serverCust.removeCustomer(thisCust);
                    JOptionPane.showMessageDialog(rootPane, thisCust + " Removed");

                    ClientLogin login = new ClientLogin();
                    login.setVisible(true);
                    thisClass.dispose();
                }

            } else {
                //Old password incorrect
                JOptionPane.showMessageDialog(rootPane, "Old Password Is Incorrect, You Stupid?");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                return null;
            }
        
        };
        worker13.execute();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void setCustomer(String c) {
        thisCust = c;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    // End of variables declaration//GEN-END:variables
}