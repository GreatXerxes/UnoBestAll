/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

import java.io.Serializable;
import unobest.rmistuff.serverside.interfaces.Watchable;
import java.rmi.RemoteException;
import java.util.Date;
import unobest.rmistuff.clientside.BusTrackingClient;


/**
 *
 * @author comqdhb
 */
public class Travelling implements Watchable, Serializable {
    Bus bus;
    Journey journey;
    Date date;
    boolean active=false;
    LatLong position;
    String driver;
    Leg currentLeg;
    Time timeStarted;
    
    
    public Travelling(Bus b, Journey j){
        bus=b;
        journey=j;
        currentLeg=j.getFirstLeg();
    }
    
    public Travelling(Bus b,Leg l){
        bus=b;
        currentLeg=l;
    }
    
    void arrivedAtStop(Leg nextLeg){
        
    }
    
    void arrivedAtStop(String s) {
        //System.out.println("arrived at "+s);
        //System.out.println("bus "+bus.reg+" arrived at "+s);
        try {
        if (currentLeg.getShelter().getStop().getName().toLowerCase().contains(s.toLowerCase())){
            if (currentLeg.nextLegIsReady()){
                currentLeg=currentLeg.nextLeg(); 
                currentLeg.addBus(this.bus);
                helper.notifyClients(this);
            }
        } else {
            System.out.println("Bus "+bus.getReg()+" has gone off piste!");
        }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    

    public Bus getBus(){
        return bus;
    }

    
    ////////////////////////////////////////
    
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
    
    ////////////////////////////////////////////
}
