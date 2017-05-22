/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import unobest.rmistuff.serverside.impl.ListOfRoutes;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;

/**
 *
 * @author IzaakBacchus
 */
public class WatchListTest {
    
    public WatchListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getFascade method, of class WatchList.
     */
    @Test
    public void testGetFascade() {
        System.out.println("getFascade");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        
        WatchListFascade fascade = instance.getFascade();
        
        assertTrue(fascade != null);

    }
    
    
    /**
     * Test of getFascade method, of class WatchList.
     */
    @Test
    public void testGetFascade1() {
        System.out.println("getFascade");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        
        WatchListFascade fascade1 = instance.getFascade();
        WatchListFascade fascade2 = instance.getFascade();
        
        assertEquals(fascade1,fascade2);

    }
        /**
     * Test of getFascade method, of class WatchList.
     */
    @Test
        (expected=RemoteException.class)
        public void testGetFascade2() { //Trying to testing the try catch clause (and failing)
            WatchList mockList = Mockito.mock(WatchList.class);
            doThrow(RemoteException.class).when(mockList).getFascade();
            mockList.getFascade();
            verify(mockList,atLeastOnce()).getFascade(); 
            when(mockList.getFascade()).thenThrow(new RemoteException());
    }
  
        

    /**
     * Test of getRoutes method, of class WatchList.
     */
    @Test
    public void testGetRoutes() {
        System.out.println("getRoutes");
         Route r1 = new Route("616","Here to there");
       Route r2 = new Route("654","there to over there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        Set<Route> expResult = new HashSet<Route>();
        expResult.add(r2);
        expResult.add(r1);
        instance.addRoute(r2);
        instance.addRoute(r1);
        Set<Route> result = instance.getRoutes();
        assertEquals(expResult, result);

    }

    /**
     * Test of addRoute method, of class WatchList.
     */
    @Test
    public void testAddRoute_String() {
        System.out.println("addRoute");
         Route r = new Route("616","Here to there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        ListOfRoutes.getInstance().addRoute(r);
        
        instance.addRoute(r.getName());
        
        assertTrue(instance.getRoutes().contains(r));
        
    }

    /**
     * Test of removeRoute method, of class WatchList.
     */
    @Test
    public void testRemoveRoute_String() {
        System.out.println("removeRoute");
        Route r = new Route("616","Here to there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        ListOfRoutes.getInstance().addRoute(r);
        instance.addRoute(r);
        
        instance.removeRoute(r.getName());
        
        assertFalse(instance.getRoutes().contains(r));
        
      

    }

    /**
     * Test of addRoute method, of class WatchList.
     */
    @Test
    public void testAddRoute_Route() {
        System.out.println("addRoute");
        Route r = new Route("616","Here to there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        instance.addRoute(r);
        assertTrue(instance.getRoutes().contains(r));
    }
    
      /**
     * Test of addRoute method, of class WatchList.
     */
    @Test
    public void testAddRoute_Route1() {
        System.out.println("addRoute");
        Route r = null;
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        instance.addRoute(r);
        assertTrue(instance.getRoutes().contains(r));
    }
    

    /**
     * Test of removeRoute method, of class WatchList.
     */
    @Test
    public void testRemoveRoute_Route() {
        System.out.println("removeRoute");
        Route r = new Route("616","Here to there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        instance.addRoute(r);
        instance.removeRoute(r);
        assertFalse(instance.getRoutes().contains(r));
    }
       /**
     * Test of removeRoute method, of class WatchList.
     */
    @Test
        public void testRemoveRoute_Route1() {
        System.out.println("removeRoute");
        Route r = new Route("616","Here to there");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        instance.removeRoute(r);
        assertFalse(instance.getRoutes().contains(r));
    }

    /**
     * Test of getCustomer method, of class WatchList.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        Customer cust = new Customer("test","test");
        WatchList instance = new WatchList(cust);
        Customer result = instance.getCustomer();
        assertEquals(cust, result);
    }
    
        /**
     * Test of getCustomer method, of class WatchList.
     */
    @Test
    public void testGetCustomer1() {
        System.out.println("getCustomer");
        Customer cust = null;
        WatchList instance = new WatchList(cust);
        Customer result = instance.getCustomer();
        assertEquals(cust, result);
    }
    
    
}
