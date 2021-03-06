/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import unobest.model.Route;
import unobest.rmistuff.serverside.impl.ListOfRoutes;
import unobest.rmistuff.serverside.impl.RouteImpR;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author Ian
 */
public interface ListOfRoutesInter extends Remote {
 
    public void load() throws RemoteException;

    public Route getRoute(String rid)throws RemoteException;

    public void addRoute(Route r)throws RemoteException;

    public List<RouteFascade> getAllRouteFascades()throws RemoteException;

    public RouteFascade getRouteFascade(Route r)throws RemoteException;

    public void removeRoute(String rid)throws RemoteException;

    public void updateFile()throws RemoteException;
}
