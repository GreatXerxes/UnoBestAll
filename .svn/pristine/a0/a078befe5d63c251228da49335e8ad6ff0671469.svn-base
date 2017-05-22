/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Bus;
import unobest.model.Route;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;
import unobest.util.Util;

/**
 *
 * @author comqdhb
 */
public class BusImpR extends UnicastRemoteObject implements BusFascade {
    Bus bus;
    
    public BusImpR(Bus b) throws RemoteException{
        bus=b;
    }

    @Override
    public void arrivedAtStop(String s) throws RemoteException {
        bus.arrivedAtStop(s);
    }

    @Override
    public String getStop() throws RemoteException {
        String result="";
        if (bus.getLeg().getStop()!=null){
        result=bus.getLeg().getStop().getName();
        }
        return result;
    }

    @Override
    public String getReg() throws RemoteException {
        return bus.getReg();
    }

    @Override
    public RouteFascade getCurrentRoute() throws RemoteException {
        RouteFascade f=null;
        Route r= bus.getCurrentRoute();
        if (r!=null){
            f=Util.getFascade(r);
        }
        return f;
    }
}
