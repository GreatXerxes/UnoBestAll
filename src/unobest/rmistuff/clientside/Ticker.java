/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.clientside;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import unobest.model.Time;
import unobest.rmistuff.clientside.ClientLogin;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class Ticker extends javax.swing.JFrame {
    private ClientLogin login;

    /**
     * Creates new form Ticker
     */
    public Ticker() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Ticker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ticker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ticker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ticker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ticker().setVisible(true);
            }
        });
    }
    
    public void addRoute(RouteFascade route){
        try {
            RoutePanel rp = new RoutePanel(route);
            routePanels.put(route, rp);
            this.getContentPane().add(rp);
            revalidate();
            repaint();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
    
    public void addBusToRoute(RouteFascade r,BusFascade b){
        RoutePanel rp = routePanels.get(r);
        rp.addBus(b);
    }
    
    public void removeBusFromRoute(RouteFascade r,BusFascade b){
        RoutePanel rp = routePanels.get(r);
        rp.removeBus(b);
    }
    
    public void removeRoute(RouteFascade route){
        
            RoutePanel rp = routePanels.get(route);
            routePanels.remove(route);
            this.getContentPane().remove(rp);
            revalidate();
            repaint();        
    }
    
    HashMap<RouteFascade,RoutePanel> routePanels= new HashMap<RouteFascade,RoutePanel>();
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   

    public void updateBus(final RouteFascade route, final BusFascade bus) {
        try {
            final RoutePanel rp = new RoutePanel(); 
            final RouteFascade rr = route;
            final Logger ex = null;
            SwingWorker worker2 = new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    return null;
        } 
                {
            Logger.getLogger(Ticker.class.getName()).log(Level.SEVERE, null, ex);
        }
        RoutePanel rp = routePanels.get(route);
                };
            
            worker2.execute();
            
        
            rp.updateBusPosition(bus);  
            
        } catch (RemoteException r) {
            r.printStackTrace();
        }
       
    }

    public void setLogin(ClientLogin aThis) {
        login=aThis;
    }

    public void setTime(Time t){
        this.setTitle("h="+t.getHours() + " m="+t.getMins());
    }
}
