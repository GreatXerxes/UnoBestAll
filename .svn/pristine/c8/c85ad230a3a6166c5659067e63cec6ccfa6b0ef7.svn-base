/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

import java.io.Serializable;
import unobest.rmistuff.serverside.interfaces.Watchable;
import java.rmi.RemoteException;
import java.util.Objects;
import unobest.rmistuff.clientside.BusTrackingClient;

/**
 *
 * @author comqdhb
 */
public class Shelter implements Watchable, Serializable{

    Stop stop;
    String name;
    LatLong position;
    
    public String getFullName(){
        return stop.getName()+name;
    }

    @Override
    public String toString() {
        return "Shelter{" + "stop=" + stop + ", name=" + name + ", position=" + position + '}';
    }

    Shelter(Stop aThis, String name) {
        stop = aThis;
        if (stop != null) {
            stop.addShelter(this);
        }
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.stop);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shelter other = (Shelter) obj;
        if (!Objects.equals(this.stop, other.stop)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public Stop getStop() {
    return stop;
    }
    
    
    /////////////////////////////////////////////////////

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
    
    
    /////////////////////////////////////////

}
