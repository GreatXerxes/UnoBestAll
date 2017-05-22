/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.clientside;

import Interfaces.ListOfRoutesInter;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import unobest.model.Bus;
import unobest.model.Journey;
import unobest.model.Leg;
import unobest.model.Route;
import unobest.rmistuff.serverside.interfaces.BusFascade;
import unobest.rmistuff.serverside.interfaces.RouteFascade;

/**
 *
 * @author comqdhb
 */
public class RoutePanel extends javax.swing.JPanel {

    RouteFascade route;
    JLabel[] stopsL;
    JLabel[] stopTimesL;
    private HashSet<BusFascade> busses = new HashSet<BusFascade>();
    private ImageIcon busIcon;
    private ImageIcon blankIcon;
    private ImageIcon busStopIcon;
    private TitledBorder tb;

    private static List<RouteFascade> fas = new ArrayList<RouteFascade>();

    /**
     * Creates new form RoutePanel
     */
    public RoutePanel(final RouteFascade r) throws RemoteException {

        //SwingWorker worker = new SwingWorker() {

            //@Override
            //protected Object doInBackground() throws Exception {
             //   try {

                ListOfRoutesInter server = (ListOfRoutesInter) ClientSideServerInstance.getServer("127.0.0.1");

                    
                    java.net.URL imageURL = RoutePanel.class.getResource("bus.png");

                    if (imageURL != null) {
                        busIcon = new ImageIcon(imageURL);
                    }
                    imageURL = RoutePanel.class.getResource("blank.png");

                    if (imageURL != null) {
                        blankIcon = new ImageIcon(imageURL);
                    }

                    imageURL = RoutePanel.class.getResource("stop.png");
                    if (imageURL != null) {
                        busStopIcon = new ImageIcon(imageURL);
                    }
                    String[] stops = r.getStops();
                    
                    int legsNum = 0;
                    String[] stopTimes = new String[0];
                    Route rou = server.getRoute(r.getName());
                    Set<Journey> jour = rou.getJourneys();
                    for (Journey j : jour)
                    {
                        stopTimes = new String[j.legCount() + legsNum];
                        legsNum = legsNum + j.legCount();
                        for (int i = 0; i < j.legCount(); i++)
                        {
                            //Date now= new Date();
                            
                            //System.out.println(now.getHours() +" "+ now.getMinutes());
                            Leg legDay = j.getLeg(i);
                            stopTimes[i] = legDay.getEta();
                        }
                    }
                      

                    //System.out.println("Number of stops: " + stops.length);
                    int n = 0;
                    stopsL = new JLabel[stops.length];
                    for (int i = 0; i < stops.length; i++)
                    {
                       
                            stopsL[i] = new JLabel(stops[i]);
                            stopsL[i].setUI(new JVLabel(true));
                            add(stopsL[i]);
                            stopsL[i].setBounds((16 + 24 * n)+ (n*40), 20, 80, 450);
                        
                        //stopsL[i] = new JLabel(stops[i]);
                        //stopsL[i].setUI(new JVLabel(true));
                        //add(stopsL[i]);
                        //stopsL[i].setBounds((16 + 24 * n)+ (n*40), 20, 80, 450);

                        //stopsL[i].setIcon(blankIcon);
                        stopsL[i].setIcon(busStopIcon);

                        n++;
                    }
                    
                    stopTimesL = new JLabel[stopTimes.length];
                    int m = 0;
                    for (int i = 0; i < stopTimes.length; i++)
                    {
                       
                            stopTimesL[i] = new JLabel(stopTimes[i]);
                            add(stopTimesL[i]);
                            stopTimesL[i].setBounds((16 + 24 * m)+ (m*40), 20, 80, 450);
                       
                        //stopTimesL[i] = new JLabel(stopTimes[i]);
                        //add(stopTimesL[i]);
                        //stopTimesL[i].setBounds((16 + 24 * m)+ (m*40), 20, 80, 450);
                        //stopTimesL[i].setLocation(16 + 24 * m, 25);
                        m++;
                    } 
                    
                    /*System.out.println("Num of stops: " + stops.length + "     Num of stopTimes: " + stopTimes.length);
                    setLayout(null);//Stops the auto resizing

                    BusStopComponent[] stopInfo = new BusStopComponent[stopTimes.length];
                    if (stopTimes.length == stops.length)
                    {
                        System.out.println("Im running");
                        for (int t = 0; t < stopTimes.length; t++)
                        {
                            System.out.println("For loop running");
                            BusStopComponent stop = new BusStopComponent(stops[t], stopTimes[t], (16 + 65 * t)+ 100, 25 , 60, 140);
                            System.out.println("Stop created at: (" + (16 + 70 * t) + "," + 25 +") Stop: " + stops[t] + " Time: " + stopTimes[t]);
                            stop.setBounds((16 + 65 * t)+ 100 , 25, 100, 140);
                            add(stop);
                            System.out.println("Size" + stop.getSize());
                            /*stopInfo[t] = new BusStopComponent(stops[t], stopTimes[t], (16 + 100 * t), 20);
                            add(stopInfo[t]);
                            stopInfo[t].setBounds((16 + 100 * t), 20, 100, 140);*/
                       // }
                   // }*/
                    
                    Border blackline = BorderFactory.createLineBorder(Color.black);
                    
                    tb = BorderFactory.createTitledBorder(blackline, r.getName());
                    initComponents();
                    
                    setBorder(tb);
                    //setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
                    setLayout(null);//Stops the auto resizing

                //} catch (Exception ne) {
                //    ne.printStackTrace();
                //}

                //return null;

            //}

            //@Override
            //public void done() {

              //  setBorder(tb);
            //}
        //};
    }

    //NEW
    RoutePanel() {

        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                return null;
            }

        };

        worker.execute();

    }



    public void addBus(final BusFascade bus) {

        try {

            SwingWorker worker = new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    try {
                        busses.add(bus);
                        String stop = bus.getStop();

                    } catch (RemoteException ex) {
                        Logger.getLogger(RoutePanel.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                    return null;
                }
            };
            worker.execute();
        } catch (Exception ne) {
            ne.printStackTrace();
        }

    }

    public void removeBus(BusFascade bus) {
        busses.remove(bus);
    }

    public boolean containsBus(BusFascade bus) {
        return busses.contains(bus);
    }

    //Untested
    public void updateBusPosition(final BusFascade bus) throws RemoteException {
        try {
            SwingWorker worker = new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    addBus(bus);
                    String s = bus.getStop();
                    updateLabels(s);
                    return null;
                }
            };
            worker.execute();
        } catch (Exception ne) {
            ne.printStackTrace();
        }
    }

    //Untested 
    public void updateLabels(String s) {
        final HashSet<String> stopNames = new HashSet<String>();

        try {
            SwingWorker worker6 = new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    for (BusFascade bus : busses) {
                        try {
                            stopNames.add(bus.getStop());
                        } catch (RemoteException r) {
                            r.printStackTrace();
                        }
                    }
                    for (String sn : stopNames) {
                        System.out.println("" + sn);
                    }
                    for (JLabel l : stopsL) {
                        if (stopNames.contains(l.getText())) {
                            l.setIcon(busIcon);
                        } else {
                            l.setIcon(blankIcon);
                        }
                    }
                    return null;
                }
            };

            worker6.execute();
        } catch (Exception ne) {
            ne.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Hmm"));
        setMaximumSize(new java.awt.Dimension(32767, 240));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
