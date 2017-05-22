/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import unobest.model.Customer;
import unobest.model.Route;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class ListOfRoutes {
    
    private static ListOfRoutes instance=null;
    public synchronized  static ListOfRoutes getInstance(){
        if (instance==null){
            instance=  new ListOfRoutes();
        }
        return instance;
    }

    public void load() {
//        System.out.println("loading routes and fascades");
//        ListOfRoutes i = ListOfRoutes.getInstance();
//        for (Route r : Route.getAllRoutes()) {
//            i.addRoute(r);
//        }
        
        //Hardcode Routes
        //i.addRoute(new Route("636","Luton"));
        //i.addRoute(new Route("602","Stanmore"));
        
        Map<String,Route> allRoute = null;
        
       
        try { 
          //Read routes from file
          FileInputStream fileIn = new FileInputStream("routes.ser");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          allRoute = (Map<String,Route>) in.readObject(); 
          in.close();
          fileIn.close();
            System.out.println("Routes in file");
          for (Route rr: allRoute.values()){
              addRoute(rr);
              System.out.println(rr.getName());
          }
            
          
      
            
        }
        
        catch (Exception e){
            System.out.println("Error");
            e.printStackTrace(); 
        }
     }
    private ListOfRoutes() {
        routes= new TreeMap<String,Route>();
        fascades = new HashMap<Route,RouteFascade>();
    }
    
    Map<String,Route> routes;
    Map<Route,RouteFascade> fascades;

    
    public Route getRoute(String rid){
        return routes.get(rid);
    }
    public void addRoute(Route r){
        routes.put(r.getName(), r);
        try {
            fascades.put(r, (RouteFascade) new RouteImpR(r));
            updateFile();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
    
    public List<RouteFascade> getAllFascades() {
     ArrayList<RouteFascade> lfascades = new ArrayList<RouteFascade>();
     for (Route r:routes.values()){
         RouteFascade f=fascades.get(r);
         try {
             if (f == null) {
                 f = new RouteImpR(r);
                 fascades.put(r, f);
             }
             lfascades.add(f);
         } catch (RemoteException re) {
             re.printStackTrace();
         }
     }
     return lfascades;
    }

    public RouteFascade getRouteFascade(Route r) {
        RouteFascade fac=fascades.get(r);
        try {
            if (fac == null) {
                routes.put(r.getName(), r);
                fac = new RouteImpR(r);
                fascades.put(r, fac);
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return fac;
      }
    
    
    
    
    public void removeRoute(String rid){
    
       if (routes.containsKey(rid))
       {
    routes.remove(rid);
    updateFile();
       }
    }
private void updateFile()
{
        try {
            FileOutputStream fileOut= new FileOutputStream("routes.ser");
            ObjectOutputStream out= new ObjectOutputStream(fileOut);
            
                out.writeObject(routes);
                
            
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListOfRoutes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListOfRoutes.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
