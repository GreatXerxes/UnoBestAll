/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import unobest.model.Customer;
import unobest.model.Journey;
import unobest.model.Leg;
import unobest.model.Route;
import unobest.model.Stop;
import unobest.model.Bus;
import unobest.model.Shelter;
import unobest.rmistuff.serverside.impl.ListOfBusses;
/**
 *
 * @author Samsung
 */
public class HardcodeData implements Serializable
{
    private TreeMap<String,Customer> allCustomers = new TreeMap<String, Customer>();
    private TreeMap<String,Route> allRoutes = new TreeMap<String,Route>();
    private TreeMap<String,Bus> allBusses = new TreeMap<String,Bus>();
    
    public static void main(String[] args) {
        HardcodeData test = new HardcodeData();
        test.run();
    }
    
    public void run()
    {
        allCustomers.put("David",new Customer("David", "password"));
        allCustomers.put("Josh",new Customer("Josh", "password"));
        allCustomers.put("Ian",new Customer("Ian", "password"));
        allCustomers.put("Frankies",new Customer("Frankies", "password"));
        allCustomers.put("God", new Customer("God","1", true));
        allCustomers.put("Me", new Customer("Me","1", false,true));
        //Customer n = new Customer ("ko","1");
        //n.addRouteToWatchList("666");
        //allCustomers.put(n.getName(), n);

        Stop.HARDCODEDSTUFFTHATSGONNABEREMOVED();
        
        Leg leg1 = new Leg(Stop.getShelterFromFullName("LRCEntrance"), new unobest.model.Time(12, 00), new unobest.model.Time(11, 10));
        Leg leg2 = new Leg(Stop.getShelterFromFullName("QA_Dingi"), new unobest.model.Time(13, 00), new unobest.model.Time(12, 00));
        Leg leg3 = new Leg(Stop.getShelterFromFullName("Reichstag"), new unobest.model.Time(14, 00), new unobest.model.Time(13, 00));
        Leg leg4 = new Leg(Stop.getShelterFromFullName("DLV"), new unobest.model.Time(15, 00), new unobest.model.Time(14, 00));
        Leg leg5 = new Leg(Stop.getShelterFromFullName("Burning_Swan_Lake"), new unobest.model.Time(16, 00), new unobest.model.Time(15, 00));
        Leg leg6 = new Leg(Stop.getShelterFromFullName("Lucy_Arse"), new unobest.model.Time(17, 00), new unobest.model.Time(16, 00));
        
        Leg leg7 = new Leg(Stop.getShelterFromFullName("Chocolate Road"), new unobest.model.Time(12, 00), new unobest.model.Time(11, 10));
        Leg leg8 = new Leg(Stop.getShelterFromFullName("Puppy Stop"), new unobest.model.Time(13, 00), new unobest.model.Time(12, 00));
        Leg leg9 = new Leg(Stop.getShelterFromFullName("Big Booty Boulevard"), new unobest.model.Time(14, 00), new unobest.model.Time(13, 00));
        Leg leg10 = new Leg(Stop.getShelterFromFullName("Cloud Lane"), new unobest.model.Time(15, 00), new unobest.model.Time(14, 00));
        Leg leg11 = new Leg(Stop.getShelterFromFullName("Pearl Gates"), new unobest.model.Time(16, 00), new unobest.model.Time(15, 00));
        
        Route r = new Route("666", "Stairway to hell");
        Journey j = new Journey(r,"Monday","This is journey desc");
        j.addLeg(leg1);
        j.addLeg(leg2);
        j.addLeg(leg3);
        j.addLeg(leg4);
        j.addLeg(leg5);
        j.addLeg(leg6);

        
        Bus killMeNow = new Bus();
        allBusses.put(killMeNow.getReg(),killMeNow);
        killMeNow.setJourney(j);
        
        j.addBus(killMeNow);
        
        //Route 2 stuff
        Route r2 = new Route("111", "Stairway to heaven");
        Journey j2 = new Journey(r2,"Tuesday","This is journey desc");
        j2.addLeg(leg7);
        j2.addLeg(leg8);
        j2.addLeg(leg9);
        j2.addLeg(leg10);
        j2.addLeg(leg11);
        
        Bus killMeNow2 = new Bus();
                allBusses.put(killMeNow2.getReg(),killMeNow2);

        killMeNow2.setJourney(j);
        
        j.addBus(killMeNow2);
        
        Route r3 = new Route("999", "Road to Cthulhu");
        Route r4 = new Route("N7", "Road To the Reapers");
        Route r5 = new Route("S4", "Sea to Davy Jones");
        Route r6 = new Route("980", "Way of the Samurai");
        Route r7 = new Route("217", "Return of the Horde");
        Route r8 = new Route("617", "Return of the Bynantium");
        Route r9 = new Route("W6", "Path to Carthage");
        Route r10 = new Route("9I33", "Road to iPhone");
        Route r11 = new Route("457", "Skip to big titty lane");
        
        allRoutes.put("666",r);
        allRoutes.put("111",r2);
        allRoutes.put("999",r3);
        allRoutes.put("N7",r4);
        allRoutes.put("S4",r5);
        allRoutes.put("980",r6);
        allRoutes.put("217",r7);
        allRoutes.put("617",r8);
        allRoutes.put("W6",r9);
        allRoutes.put("9I33",r10);
        allRoutes.put("457", r11);

        
        writeCustomerFile();
        writeRoutesFile();
        writeBusFile();
    }
    
    public void writeBusFile()
    {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("busses.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(allBusses);
         out.close();
         fileOut.close();
          System.out.println("Saved Busses ");
      }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    public void writeCustomerFile()
    {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("customers.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(allCustomers);
         out.close();
         fileOut.close();
          System.out.println("Saved Customers ");
      }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void writeRoutesFile()
    {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("routes.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(allRoutes);
         out.close();
         fileOut.close();
          System.out.println("Saved Routes ");
      }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
