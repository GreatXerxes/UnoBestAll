/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

import java.io.Serializable;
import unobest.rmistuff.serverside.interfaces.Watchable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import unobest.rmistuff.clientside.BusTrackingClient;

/**
 *
 * @author comqdhb
 */
public class Route implements Watchable, Serializable {
    
    private static HashMap<String,Route> map = new HashMap<String,Route>();
    
    public static Route getRoute(String rid){
        return map.get(rid);
    }

    public static Collection<Route> getAllRoutes() {
        return map.values();
    }
    
    Set<Journey> journeys = new HashSet<Journey>();
    String name;
    private String descr;
    
    
    public Route(String rid,String descr) {
        System.out.println("adding "+rid+"->"+descr);
        name=rid;
        map.put(rid,this);
        this.descr=descr;
    }
    
    public String getName() {
        return name;
    }

    void addJourney(Journey aThis) {
    journeys.add(aThis);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Route other = (Route) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Set<Journey> getJourneys() {
        return journeys;
    }

    @Override
    public String toString() {
        return "Route{" + "name=" + name + " - " + descr +'}';
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
    
    /////////////////////////////////////////////

    public Set<BusTrackingClient> getWatchers(){
        return helper.getWatchers();
    }
    
    public String[] getStops() {
        Journey j = journeys.iterator().next();
        return j.getStops();
    }
    
}
