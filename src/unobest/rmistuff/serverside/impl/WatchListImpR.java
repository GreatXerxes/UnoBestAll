/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import unobest.model.Customer;
import unobest.model.Route;
import unobest.model.WatchList;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.serverside.interfaces.RouteFascade;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;
import unobest.util.Util;

/**
 *
 * @author comqdhb
 */
public class WatchListImpR extends UnicastRemoteObject implements WatchListFascade {

    WatchList watchList;

    public WatchListImpR(WatchList aThis) throws RemoteException {
        watchList = aThis;
    }

    @Override
    public List<RouteFascade> getRoutes() throws RemoteException {
        ArrayList<RouteFascade> result = new ArrayList<RouteFascade>();
        ListOfRoutes lor = ListOfRoutes.getInstance();
        Set<Route> rl = watchList.getRoutes();
        for (Route r : rl) {
            result.add(lor.getRouteFascade(r));
        }
        return result;
    }

    @Override
    public void addRoute(String routeid) throws RemoteException {
        System.out.println("adding route at watchlistImpR " + routeid);
        Route route = ListOfRoutes.getInstance().getRoute(routeid);
        watchList.addRoute(route);
        Customer c = watchList.getCustomer();
        System.out.println("What this?" + c);
        
        
        Set<BusTrackingClient> cl = c.getClients();
       
        
        for (BusTrackingClient cli : cl) {
            if(cli==null){continue;}
            
            cli.addRoute(Util.getFascade(route));
            
            route.addWatcher(cli);
        }
    }

    @Override
    public void removeRoute(String routeid) throws RemoteException {
        watchList.removeRoute(routeid);
        Route route = ListOfRoutes.getInstance().getRoute(routeid);
        Customer c = watchList.getCustomer();
        Set<BusTrackingClient> cl = c.getClients();

        for (BusTrackingClient cli : cl) {
            cli.removeRoute(Util.getFascade(route));
            route.removeWatcher(cli);
        }
    }

    @Override
    public void watchRoutes(BusTrackingClient client) throws RemoteException {
        for (Route r : watchList.getRoutes()) {
            System.out.println(r);
            System.out.println(watchList.getRoutes().size());
            
            r.addWatcher(client);
        }
    }

}
