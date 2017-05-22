/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import unobest.model.Bus;
import unobest.model.Journey;
import unobest.model.Route;

/**
 *
 * @author IzaakBacchus
 */
public class WhatItFiles {
    public static void main(String[] args) {
        WhatItFiles w = new WhatItFiles();
        w.showRoutes();
    }
    
    
    private void showRoutes()
    {
        Map<String,Route> allRoute = null;
        try { 
          //Read routes from file
          FileInputStream fileIn = new FileInputStream("routes.ser");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          allRoute = (Map<String,Route>) in.readObject(); 
          in.close();
          fileIn.close();
          if(allRoute.isEmpty())
          {
              System.out.println("No Routes");
          }
          for (Route r : allRoute.values())
          {
              System.out.println(r);
              System.out.println("Journeys....");
              for (Journey j : r.getJourneys())
              {
                  System.out.println(j);
                  System.out.println("Busses...");
                  for (Bus b : j.getBusses())
                  {
                      System.out.println(b);
                  }
              }
          }
        }
        
        catch (Exception e){
            System.out.println("Error");
            e.printStackTrace(); 
        }
    }
}
