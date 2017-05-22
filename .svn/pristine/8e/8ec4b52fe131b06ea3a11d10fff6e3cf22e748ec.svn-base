/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Bus;
import unobest.rmistuff.serverside.interfaces.BusStopServer;

/**
 *
 * @author comqdhb
 */
public class BusStopServerImpR extends UnicastRemoteObject implements BusStopServer {

    public BusStopServerImpR() throws RemoteException{
        
    }
    
    @Override
    public void busArrived(String stop, String busreg) throws RemoteException {
        Bus b=Bus.getBus(busreg);
        b.arrivedAtStop(stop);
    }
    
}
