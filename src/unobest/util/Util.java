/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.util;

import unobest.model.Bus;
import unobest.model.Route;
import unobest.rmistuff.serverside.impl.ListOfBusses;
import unobest.rmistuff.serverside.impl.ListOfRoutes;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class Util {
    
    
    public static BusFascade getFascade(Bus bus){
        return ListOfBusses.getInstance().getFascade(bus);
    }
    
    public static RouteFascade getFascade(Route r){
        return ListOfRoutes.getInstance().getRouteFascade(r);
    }
    
}
