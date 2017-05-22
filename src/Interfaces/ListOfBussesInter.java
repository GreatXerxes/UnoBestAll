/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.TreeMap;
import unobest.model.Bus;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.serverside.interfaces.BusFascade;

/**
 *
 * @author cm12aco
 */
public interface ListOfBussesInter extends Remote
{

    public void addBus(Bus b) throws RemoteException;
    
    public void addBus() throws RemoteException;
    
    BusFascade getBusFascade(Bus b) throws RemoteException;
    
    List<BusFascade> getAllBusFascades() throws RemoteException;
    
    TreeMap<String,Bus> getBusses() throws RemoteException;
    
    void busArrivedAtStop(String stop, String reg) throws RemoteException;
    
     public void saveBussesToFile() throws RemoteException;

}

