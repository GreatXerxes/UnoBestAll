/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Route;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class RouteImpR extends UnicastRemoteObject implements RouteFascade{

    Route route;

    public RouteImpR(Route aThis) throws RemoteException {
        route=aThis;
    }
    @Override
    public String getName() throws RemoteException {
        return route.getName();
    }

    @Override
    public String[] getStops() throws RemoteException {
        return route.getStops();
    }

    @Override
    public String getDescription() throws RemoteException {
        return route.getDescr();
    }
    
}
