/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.serverside.impl;

import Interfaces.ListOfBussesInter;
import Interfaces.ListOfCustomersInter;
import Interfaces.ListOfRoutesInter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import unobest.model.Bus;

import unobest.model.CurrentTimeGenerator;
import unobest.model.Customer;
import unobest.model.Route;
import unobest.model.Time;
import unobest.rmi.util.RegistryLoader;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.BusServer;
import unobest.rmistuff.serverside.interfaces.CustomerFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */public class Server extends UnicastRemoteObject implements BusServer, ListOfRoutesInter, ListOfCustomersInter, ListOfBussesInter {

    ListOfCustomers customers;
    //ListOfStops stops;
    ListOfRoutes routes;
    ListOfBusses busses;
    CurrentTimeGenerator time;

    public Server() throws RemoteException {
        //TT.load();
        loadAllJourneys();
        routes = ListOfRoutes.getInstance();
        routes.load();
        customers = ListOfCustomers.getInstance();
        customers.load();
        busses = ListOfBusses.getInstance();
        busses.load();
        time = new CurrentTimeGenerator();
        //stops=ListOfStops.getInstance();
        RegistryLoader.Load();
        String myName = "UnoBest";
        try {
            String myIP = java.net.InetAddress.getLocalHost().getHostAddress();
            String myURL = "rmi://" + myIP + "/" + myName;
            System.out.println(myURL);
            // register RMI object
            BusServer serverStub = (BusServer) this;
            //NB rebind will replace any stub with the given name 'ChatterServer'
            Naming.rebind(myName, serverStub);
            System.out.println("Server started");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public ListOfRoutes getListOfRoutes()
            {
                return routes;
            }

    @Override
    public CustomerFascade getCustomer(String uname, String pwd, BusTrackingClient client) throws RemoteException {
        return customers.getCustomerFascade(uname, pwd, client);
    }

    @Override
    public List<RouteFascade> getAllRoutes() throws RemoteException {
        return routes.getAllFascades();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        
    }

    @Override
    public Time getTime() throws RemoteException {
        return time.currentTime();
    }

    public void setTimeGenerator(CurrentTimeGenerator ftg) {
        time = ftg;
    }

    @Override
    public CustomerFascade createCustomer(String uname, String pwd, BusTrackingClient client) throws RemoteException {
        Customer c = new Customer(uname,pwd);
        if(ListOfCustomers.getInstance().addCustomer(c)){ //Checking if a customer exists or not, if they already exists return null. 
          ListOfCustomers.getInstance().saveCustToFile();
           return new CustomerImpR(c);//This may break things.
            //return getCustomer(uname,pwd,client); //Replaced this with the line above because this seems to always return null. Not sure why.
            
            
        }
       
        return null;
    }
  
    private void loadAllJourneys() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void saveAllJourneys() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void loadAllCustomers() {//*NEVER CALLED?//
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void saveAllCustomers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //List of routes stuff
    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Route getRoute(String rid) {
        return routes.getRoute(rid);
    }

    @Override
    public void addRoute(Route r) {
        routes.addRoute(r);
    }

    @Override
    public List<RouteFascade> getAllRouteFascades() {
        return routes.getAllFascades();
    }

    @Override
    public RouteFascade getRouteFascade(Route r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeRoute(String rid) {
        routes.removeRoute(rid);
    }

    @Override
    public void updateFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addCustomer(Customer c) {
        return customers.addCustomer(c);
    }

    
    //List of customers stuff
    @Override
    public CustomerFascade getCustomerFascade(String uname, String pwd, BusTrackingClient client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCustomer(String username) throws RemoteException{
        customers.removeCustomer(username);
    }

    @Override
    public void saveCustToFile() {
        customers.saveCustToFile();
    }

    @Override
    public boolean customerExist(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void changePassword(String username, String newPassword)
    {
        customers.changePassword(username,newPassword);
    }
    
    @Override
    public boolean isAdmin(String u)
    {
        return customers.isAdmin(u);
    }
    
    
     @Override
    public boolean isSuperAdmin(String u)
    {
        return customers.isSuperAdmin(u);
    }
     @Override
    public Map<String,Customer> getAllCustomers()
    {
        return customers.getAllCustomers();
    }
    
    @Override
    public void makeAdmin(String username)
    {
        customers.makeAdmin(username);
    }
    
      @Override
    public void revertAdmin(String username)
    {
        customers.revertAdmin(username);
    }
    
    @Override
    public void addRouteToWatchList(String username,String route)
    {
        customers.addRouteToWatchList(username, route);
    }
    
    //Bus Stuff
    @Override
    public void addBus(Bus b) throws RemoteException {
           busses.addBus(b);

    }
    
    @Override
    public void addBus() throws RemoteException {
        busses.addBus();
    }

    @Override
    public BusFascade getBusFascade(Bus b) throws RemoteException 
    {
        return busses.getFascade(b); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BusFascade> getAllBusFascades() throws RemoteException {
        return busses.getAllFascades();
    }
    
    @Override
    public TreeMap<String,Bus> getBusses() throws RemoteException
    {
        return (TreeMap) busses.busses;
    }

    @Override
    public void busArrivedAtStop(String stop, String reg) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer getCustomer(String username) throws RemoteException{
        return customers.getCustomer(username);
    }

    @Override
    public void saveBussesToFile() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


    
}
