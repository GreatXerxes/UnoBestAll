/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.clientside;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.SwingWorker;
import unobest.rmistuff.serverside.impl.Server;
import unobest.rmistuff.serverside.interfaces.BusServer;

/**
 *
 * @author IzaakBacchus
 */
public class ClientSideServerInstance {

    private static Registry registry;
    private static BusServer server = null;

    private static void connect(final String ip) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    registry = LocateRegistry.getRegistry(ip);
                    //get the server stub from the registry
                    server = (BusServer) registry.lookup("UnoBest");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        worker.execute();
        //Waiting until worker is done
        while (!worker.isDone()) {
        }
    }


    public static BusServer getServer(String ip) {
        if (server == null) {
            connect(ip);
        }
        return server;
    }

}
