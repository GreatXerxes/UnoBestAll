/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import unobest.model.Customer;
import unobest.model.WatchList;
import unobest.rmistuff.serverside.interfaces.CustomerFascade;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;

/**
 *
 * @author comqdhb
 */
public class CustomerImpR extends UnicastRemoteObject implements  CustomerFascade {

    
    public CustomerImpR(Customer c) throws RemoteException {
        customer = c;
    } 
    
    Customer customer;
    @Override
    public WatchListFascade getWatchList() throws RemoteException {
        WatchList wl = customer.getWatchList();
        WatchListFascade fascade = wl.getFascade();
        return fascade;
    }
    
}
