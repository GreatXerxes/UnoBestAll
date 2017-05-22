/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.clientside;

import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;
import unobest.rmistuff.serverside.interfaces.RouteFascade;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;

/**
 *
 * @author comqdhb
 */
public class RouteFascadeRenderer extends JLabel implements ListCellRenderer {
    private final WatchListFascade watchList;

    public RouteFascadeRenderer(WatchListFascade wl){
        watchList=wl;
    }
    @Override
    public Component getListCellRendererComponent(JList list, final Object value, int index, final boolean isSelected, boolean cellHasFocus) {
        
        
        final RouteFascadeRenderer thisClass = this;
        SwingWorker worker14 = new SwingWorker() {
        

            @Override
            protected Object doInBackground() throws Exception {
                if (value instanceof RouteFascade){
            RouteFascade route=(RouteFascade) value;
            try {
                setText(route.getName() + " "+route.getDescription());
            } catch (RemoteException remoteException) {
            }
            Color bg=Color.white,fg=Color.black;
            thisClass.setOpaque(true);
            if (isSelected){
                bg=Color.yellow;
            }
            try {
                if (watchList.getRoutes().contains(route)) {
                    fg = Color.RED;
                }
            } catch (RemoteException remoteException) {
            }
            thisClass.setBackground(bg);
            thisClass.setForeground(fg);
            
        }
                return thisClass;
            }
            
            @Override
            public void done() {
                        
                     
                        
                    }
        
    };
        worker14.execute();
        while (!worker14.isDone()){}
        return this;
        
    
 
    
}
}
