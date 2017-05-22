/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.rmistuff.serverside.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import unobest.model.Bus;
import unobest.model.Route;
import unobest.rmistuff.serverside.interfaces.BusFascade;

/**
 *
 * @author comqdhb
 */
public class ListOfBusses {

    private static ListOfBusses instance=null;
    public synchronized  static ListOfBusses getInstance(){
        if (instance==null){
            instance=  new ListOfBusses();
        }
        return instance;
    }
    private ListOfBusses() {
    }
    
    
    
    Map<String,Bus> busses= new TreeMap<String,Bus>();
    Map<String,BusFascade> fascades = new TreeMap<String,BusFascade>();
    
    
    public void addBus()
    {
     addBus(new Bus()); 
     
    }
    
    public void addBus(Bus r){
        
        busses.put(r.getReg(), r);
        try {
            fascades.put(r.getReg(), new BusImpR(r));
            saveBussesToFile();
        
        }catch (RemoteException re) {
            re.printStackTrace();
        }
        
    }
    
    public BusFascade getFascade(Bus bus){
        BusFascade f = fascades.get(bus.getReg());
        if (f==null){
            addBus(bus);
            f=fascades.get(bus.getReg());
        }
        return f;
    }
    
    public List<BusFascade> getAllFascades() {
     ArrayList<BusFascade> lfascades = new ArrayList<BusFascade>();
     for (Bus r:busses.values()){
         BusFascade f=fascades.get(r.getReg()); //David. Your code was wrong. Im not angry just dissapointed.
         try {
             if (f == null) {
                 f = new BusImpR(r);
                 fascades.put(r.getReg(), f);
             }
             lfascades.add(f);
         } catch (RemoteException re) {
             re.printStackTrace();
         }
     }
     return lfascades;
    } 
    
    public void busArrivedAtStop(String stop,String reg){
        Bus b = busses.get(reg);
        b.arrivedAtStop(stop);
    }
    
    public void load()
    {
        TreeMap<String,Bus> allBusses;
           try { 
          //Read routes from file
          FileInputStream fileIn = new FileInputStream("busses.ser");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          allBusses = (TreeMap<String,Bus>) in.readObject(); 
          in.close();
          fileIn.close();
               System.out.println("Busses in file");
          for (Bus b: allBusses.values()){
              addBus(b);
              System.out.println(b.getReg());
          }
        }
        
        catch (Exception e){
            System.out.println("Error");
            e.printStackTrace(); 
        }
    }
    
     public void saveBussesToFile()
    {
        try
      {
         FileOutputStream fileOut =  new FileOutputStream("busses.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(busses);
         out.close();
         fileOut.close();
      }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    
}
