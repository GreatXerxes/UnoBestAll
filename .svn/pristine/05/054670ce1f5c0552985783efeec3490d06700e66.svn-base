/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.clientside.impl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Time;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.clientside.Ticker;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.BusServer;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class BusTrackingClientImpR extends UnicastRemoteObject implements BusTrackingClient {

    BusServer server;
    public BusTrackingClientImpR() throws RemoteException{
        
    }
    
    private Ticker ticker;
    public void setRoutePanel(Ticker t){
        ticker=t;
    }   
    
    @Override
    public void updateBus(RouteFascade route,BusFascade bus) throws RemoteException {
        ticker.updateBus(route,bus);
        Time t = server.getTime();
        ticker.setTime(t);
        System.out.println(t.toString());
    }

    @Override
    public void addRoute(RouteFascade route) throws RemoteException {
        ticker.addRoute(route);
    }
    
    @Override
    public void removeBus(RouteFascade route,BusFascade bus) throws RemoteException {
        ticker.removeBusFromRoute(route, bus);
    }

    @Override
    public void removeRoute(RouteFascade route) throws RemoteException {
        ticker.removeRoute(route);
    }

    @Override
    public void watchableUpdated(Remote o) throws RemoteException {
        System.out.println(""+o);
        if (o instanceof BusFascade){
            System.out.println("it is a bus!");
            BusFascade bus=(BusFascade) o;
            RouteFascade route=bus.getCurrentRoute();
            System.out.println(route.getName()+ " "+bus.getReg()+" "+bus.getStop());
            updateBus(route,bus);
        }
    }

    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }

    public void setServer(BusServer server) {
        this.server=server;
    }
    
    
    
}
