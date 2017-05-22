/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import unobest.rmistuff.serverside.interfaces.Watchable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import unobest.rmi.util.AliveIterator;
import unobest.rmistuff.clientside.BusTrackingClient;

/**
 *
 * @author comqdhb
 */
public class WatchableHelper implements Serializable{
    
    HashSet<BusTrackingClient> clients = new HashSet<BusTrackingClient>();
    public void addClient(BusTrackingClient c){
        clients.add(c);
    }
    public void removeClient(BusTrackingClient c){
        clients.remove(c);
    }
    public boolean isWatchedBy(BusTrackingClient client){
        return clients.contains(client);
    }    

    public void notifyClients(Remote o) {
        AliveIterator<BusTrackingClient> it=new AliveIterator<BusTrackingClient>(clients);
        while (it.hasNext()){
            BusTrackingClient c=it.next();            
            try {           
                c.watchableUpdated(o);
            } catch (RemoteException ex) {
                Logger.getLogger(WatchableHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Set<BusTrackingClient> getClients(){
        return clients;
    }
    public void removeAll(){
        clients.clear();
    }
    
    public Set<BusTrackingClient> getWatchers(){
        return clients;
    }
    
    public void addAll(Set<BusTrackingClient> watchers) {
       clients.addAll(watchers);
    }
    
    /*
    copy the following code into Watchable
    /////////////////////////////////////////////////////////
    
    WatchableHelper helper = new WatchableHelper();

    @Override
    public boolean isWatchedBy(BusTrackingClient client)  {
    return helper.isWatchedBy(client);
    }

    @Override
    public void addWatcher(BusTrackingClient client) {
    helper.addClient(client);
    }
    

    @Override
    public void removeWatcher(BusTrackingClient client) {
    helper.removeClient(client);
    }
    
    @Override
    public void notifyWatchers(){
        helper.notifyClients(this);
    }
    ///////////////////////////////////////////////////////////
    */
    
}
