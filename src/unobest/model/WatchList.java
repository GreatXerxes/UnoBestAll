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
import unobest.rmistuff.serverside.impl.ListOfRoutes;
import unobest.rmistuff.serverside.impl.WatchListImpR;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;

/**
 *
 * @author comqdhb
 */
public class WatchList implements Serializable {

    Customer customer;
    WatchListFascade fascade;
    static ListOfRoutes lor = ListOfRoutes.getInstance();

    WatchList(Customer aThis) {
    customer=aThis;
    }

    public WatchListFascade getFascade() {
        if (fascade == null) {
            try {
                fascade = new WatchListImpR(this);
            } catch (RemoteException r) {
                r.printStackTrace();
            }
        }
        return fascade;
    }

    HashSet<Route> routes = new HashSet<Route>();

    public Set<Route> getRoutes() {
        return routes;
    }

    public void addRoute(String routeid) {
        addRoute(lor.getRoute(routeid));
    }

    public void removeRoute(String routeid) {
        removeRoute(lor.getRoute(routeid));
    }

    public void addRoute(Route r) {
        routes.add(r);        
    }

    public void removeRoute(Route r) {
        routes.remove(r);        
    }

    public Customer getCustomer() {
    return customer;
    }

    

}
