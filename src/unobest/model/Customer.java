/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import unobest.rmi.util.AliveRemoteSet;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.clientside.impl.BusTrackingClientImpR;
import unobest.util.EncryptionFunctions;


/**
 *
 * @author comqdhb
 */
public class Customer implements Serializable {
    AliveRemoteSet<BusTrackingClient> clients=new AliveRemoteSet<BusTrackingClient>();
    WatchList watchList;
    String uname,pwd;
     private boolean isAdmin= false;
    private boolean superAdmin=false;
 
    public void makeAdmin()
    {
        isAdmin = true;
    }
    public void revertAdmin()
    {
        isAdmin = false;
    }
    public boolean isAdmin()
    {
        return isAdmin;
    }
    
    public boolean isSuperAdmin()
    {
        return superAdmin;
    }
    
    
    
    public Customer(String u, String p, boolean a, boolean sa)
    {
        uname=u;
        pwd= EncryptionFunctions.encryptPassword(p);
        watchList=new WatchList(this);
        isAdmin= a;
        superAdmin= sa;
        
    }
    public Customer(String u, String p, boolean a)
    {
        uname=u;
        pwd= EncryptionFunctions.encryptPassword(p);
        watchList=new WatchList(this);
        isAdmin= a;
        
    }
    public Customer(String u,String p){
        uname=u;
        pwd= EncryptionFunctions.encryptPassword(p);
        watchList=new WatchList(this);
       
    }

    public WatchList getWatchList() {
    return watchList;
    }

    public String getPassword() {
    return pwd;
    }

    public String getName() {
    return uname;
    }

    public void addClient(BusTrackingClient client) {
        clients.add(client);
    }
    
    public void removeClient(BusTrackingClient client){
        clients.remove(client);
    }
    
    public Set<BusTrackingClient> getClients(){
        return clients;
    }

    public WatchList getWatchList(Customer c) {
        return watchList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void changePassword(String newPassword) {
        pwd = newPassword;
    }
    
    public void addRouteToWatchList(String route)
    {
        watchList.addRoute(route);
    }
    
}
