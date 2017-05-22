/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

import java.io.Serializable;
import unobest.rmistuff.serverside.interfaces.Watchable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import unobest.rmistuff.clientside.BusTrackingClient;

/**
 *
 * @author comqdhb
 */
public class Journey implements Watchable, Serializable{

    private static HashMap<String, Journey> map = new HashMap<String, Journey>();

    public static Journey getJourney(String name) {
        return map.get(name);
    }
    List<Leg> legs = new ArrayList<Leg>();
    Set<Bus> activeBusses = new HashSet<Bus>();
    Route route;
    String dow;
    String descr;

    public Journey(Route r, String dayOfWeek,String d) {
        route = r;
        r.addJourney(this);
        dow = dayOfWeek;
        descr=d;
    }
    
    public int legCount(){
        return legs.size();
    }
    
    public Leg getFirstLeg(){
        Leg l=null;
        if (legCount()>0){
            l=legs.get(0);
        }
        return l;
    }
    
    public Leg getLastLeg(){
        Leg l=null;
        if (legCount()>2){ //NB last leg will be to ControllerLeg
            l=legs.get(legCount()-2);
        }
        return l;
    }
    
    public Leg getLeg(int n){
        return legs.get(n);
    }
    
    public String getRouteName(){
        return route.getName();
    }

    @Override
    public String toString() {
        //System.out.println(route.getName() + "\t" + dow);
        for (Leg l : legs) {
            //System.out.println("\t" + l);
        }
        return route+" Journey{descr="+descr + ", legs=" + legs + ", route=" + route + ", dow=" + dow + '}';
    }

    public void addLeg(Leg l) {
        legs.add(l);
    }

    public boolean containsShelter(Shelter s) {
        boolean result = false;
        for (Leg l : legs) {
            if (l.getShelter().equals(s)) {
                result = true;
            }
        }
        return result;
    }

    public boolean containsStop(String txt) {
        boolean result = false;
        for (Leg l : legs) {
            Shelter s = l.getShelter();
            if (s != null) {
                Stop stop = s.stop;
                String n = stop.name.toLowerCase();
                if (n.contains(txt.toLowerCase())) {
                    result = true;
                }
            }
        }
        return result;
    }

    public String getDescr() {
    return descr;
    }

    public String getDOW() {
    return dow;
    }
    
    public void addBus(Bus bus){
        activeBusses.add(bus);
    }
    public void removeBus(Bus bus){
        activeBusses.remove(bus);
    }
    
    public Set<Bus> getBusses(){
        return activeBusses;
    }
    
    
    ////////////////////////////////////////////////

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
    
    ///////////////////////////////////////////

    String[] getStops() {
        String[] stops=new String[legs.size()];//String[] stops=new String[legs.size() - 1];// removed -1 cause god knows why it was there

        for (int i=0;i<stops.length;i++){
            Stop l = legs.get(i).getStop();
            if (l!=null){
              stops[i]=l.getName();
            } else {
                stops[i]=l.toString();
            }
        }
        return stops;
    }

    public Route getRoute() {
        return route;
    }
}
