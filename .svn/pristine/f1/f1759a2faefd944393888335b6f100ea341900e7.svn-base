/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Stop;
import unobest.rmistuff.serverside.interfaces.StopFascade;

/**
 *
 * @author comqdhb
 */
public class StopImpR extends UnicastRemoteObject implements StopFascade {

    Stop stop;
    public StopImpR(Stop s) throws RemoteException {        
        stop=s;
    }
    
    @Override
    public String getName() throws RemoteException {
    return stop.getName();
    }

    @Override
    public String[] getShelterNames() throws RemoteException {
        return stop.getShelterNames();
    }
    
}
