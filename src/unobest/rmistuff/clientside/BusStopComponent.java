/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.rmistuff.clientside;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class BusStopComponent extends JComponent
{
    private String busStop;
    private String etaTime;
    private int stopX;
    private int stopY;
    private int stopWidth;
    private int stopHeight;
    
    public BusStopComponent(String stop, String time, int x, int y, int width, int height)
    {
        busStop = stop;
        etaTime = time;
        stopX = x;
        stopY = y;
        stopWidth = width;
        stopHeight = height;
        
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(stopWidth, stopHeight);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        int diameter = 20;
        g.setColor(getBackground());
        g.setColor(Color.red);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        
        //g2.drawOval(stopX, stopY, 20, 20);
        //g2.fillOval(stopX, stopY, 19, 19);
        g2.setPaint(new Color(102, 0, 51));
        Ellipse2D.Double circle = new Ellipse2D.Double(stopX, stopY, diameter, diameter);
        g2.fill(circle);
        g2.setPaint(Color.black);

        g2.drawLine(stopX+ 10, stopY + 20, stopX+ 10, stopY + 60);
        g2.drawLine(stopX, stopY + 60, stopX+ 20, stopY + 60);
        
        g2.drawString(busStop, stopX - (int)(busStop.length()*2) , stopY + 72);
        g2.drawString(etaTime, stopX, stopY + 82);
        System.out.println("Stop length:" + ((int)busStop.length()/2));
        
        /*JLabel stopie = new JLabel(busStop);
        JLabel timie = new JLabel(etaTime);
        
        String stopieTxt = stopie.getText();
        Icon stopIcon = (stopie.isEnabled()) ? stopie.getIcon() : stopie.getDisabledIcon();
 
        if ((stopIcon == null) && (stopieTxt == null)) {
            return;
        }
        String timieTxt = timie.getText();
        Icon timeIcon = (timie.isEnabled()) ? timie.getIcon() : timie.getDisabledIcon();
 
        if ((timeIcon == null) && (timieTxt == null)) {
            return;
        }
        
        //Doesn't Work yet
        stopie.setLocation(stopX+ 10, stopY + 62);
        stopie.setLocation(stopX+ 10, stopY + 67);*/
        //g2.drawLine(stopX, stopY, 8, 2);

    }
}
