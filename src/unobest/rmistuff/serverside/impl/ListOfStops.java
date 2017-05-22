/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import unobest.model.Stop;
import unobest.rmistuff.serverside.interfaces.StopFascade;

/**
 *
 * @author comqdhb
 */
public class ListOfStops {
    private static ListOfStops instance=null;
    public synchronized  static ListOfStops getInstance(){
        if (instance==null){
            instance=  new ListOfStops();
        }
        return instance;
    }
    private ListOfStops() {
    }
    
    Map<String,Stop> stops = new HashMap<String,Stop>();
    Map<Stop,StopFascade> fascades = new HashMap<Stop,StopFascade>();
    
    public void addStop(Stop s){
        stops.put(s.getName(), s);
        try {
            fascades.put(s, new StopImpR(s));
        } catch (RemoteException remoteException) {
        }
    }
    
}
