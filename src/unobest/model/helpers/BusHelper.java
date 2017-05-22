/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model.helpers;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import unobest.model.Bus;
import unobest.model.WatchableHelper;
import unobest.rmistuff.clientside.BusTrackingClient;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;
import unobest.util.Util;

public class BusHelper extends WatchableHelper {
        private Bus bus;
        private BusFascade fascade;

        public BusHelper(Bus bus) {
            this.bus=bus;
            fascade=Util.getFascade(bus);
        }
        public void updateBus()
        {
            for (BusTrackingClient c:getClients()){
                try {
                    c.updateBus(
                            Util.getFascade(bus.getJourney().getRoute()),
                            Util.getFascade(bus)
                    );
                } catch (RemoteException ex) {
                    Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }

        public void removeBusFromClients() {
            if (bus.getJourney()!=null){
            RouteFascade rf=Util.getFascade(bus.getJourney().getRoute());
            for (BusTrackingClient c:getClients()){
                try {
                    System.out.println("class name ------ "+c.getClass());
                    c.removeBus(rf, fascade);
                } catch (Exception ex) {
                    
                    Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
            }
            getClients().clear();
         }

    }
