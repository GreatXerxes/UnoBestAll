/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import unobest.rmistuff.serverside.impl.ListOfRoutes;
import unobest.rmistuff.serverside.interfaces.WatchListFascade;

/**
 *
 * @author AkshayPopat
 */
public class ControllerLegTest {

    public ControllerLegTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ControllerLeg.
     */
    @Test
    public void testGetInstance() {
        System.out.println("Equal getInstance");
        ControllerLeg l1 = ControllerLeg.getInstance();
        ControllerLeg l2 = ControllerLeg.getInstance();

        assertEquals(l1, l2);
    }
    //Singleton object, checking to see if it's the same

    /**
     * Test of getParkedBusses method, of class ControllerLeg.
     */
    @Test
    public void testGetParkedBusses() {
        System.out.println("Return parked busses");
        Bus bus = new Bus();

        ControllerLeg instance = ControllerLeg.getInstance();
        instance.addBus(bus);

        assertTrue(instance.getParkedBusses().contains(bus));
    }

    /**
     * Test of assignBusToJourney method, of class ControllerLeg.
     */
    @Test
    public void testAssignBusToJourney() {
        System.out.println("Assign Bus to Journey");
        Bus bus = new Bus();
        Journey j1 = new Journey(new Route("636", "Here to there"), "Monday", "Monday 636");

        ControllerLeg instance = ControllerLeg.getNew();
        instance.addBus(bus);

        instance.assignBusToJourney(bus, j1);

        assertEquals(instance.getParkedBusses().size(), 0);

    }

    /**
     * Test of assignAvailableBusToJourney method, of class ControllerLeg.
     */
    @Test
    public void testAssignAvailableBusToJourney() {
        System.out.println("assignAvailableBusToJourney");

     
        Journey j1 = new Journey(new Route("179", "Chingford"), "Tuesday", "Tuesday 179");

        ControllerLeg instance = ControllerLeg.getInstance();
        
       

        instance.assignAvailableBusToJourney(j1);

     
        assertEquals(instance.getParkedBusses().size(), 0);

    }

    /**
     * Test of assignAvailableBusToJourney1 method, of class ControllerLeg.
     */
    @Test
    public void testAssignAvailableBusToJourney1() {
        HashSet<Bus> busses = new HashSet<Bus>();

        Bus bus = new Bus();

        Journey j1 = new Journey(new Route("179", "Chingford"), "Tuesday", "Tuesday 179");

        busses.add(bus);
        bus.setJourney(j1);

        assertTrue(busses.size() == 1);

    }

    /**
     * Test of assignAvailableBusToJourney2 method, of class ControllerLeg.
     */
    @Test
    public void testAssignAvailableBusToJourney2() {

        ControllerLeg instance = ControllerLeg.getInstance();
        Journey j1 = new Journey(new Route("179", "Chingford"), "Tuesday", "Tuesday 179");
        
        instance.assignAvailableBusToJourney(j1);
        assertTrue(true);

    }

    /**
     * Test of addBus method, of class ControllerLeg.
     *
     */
    @Test
    public void testAddBus1() {
        System.out.println("addBus");

        Bus bus = new Bus();
        Journey instance = new Journey(new Route("636", "Here to there"), "Monday", "Monday 636");
        instance.addBus(bus);
        assertTrue(instance.getBusses().contains(bus));

    }

}
