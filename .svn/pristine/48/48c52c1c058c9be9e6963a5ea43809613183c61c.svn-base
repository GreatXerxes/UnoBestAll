/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author comqdhb
 */
public class Leg implements Serializable{
    Shelter destination;
    Time eta,delta;
    Leg nextLeg;
    
    public Leg(Shelter e,Time te,Time dt){
        destination=e;
        eta=te;
        delta=dt;
    }

    @Override
    public String toString() {
        String n="";
        Leg nl=nextLeg();
        if (nl!=null){
            Shelter s=nl.getShelter();
            if (s!=null){
            n=s.stop.name+" "+s.name;
            }
        }
        return "Leg{" + "to=" + destination + ", end=" + eta + ", delta=" + delta + ", nextLeg=" + n + '}';
    }
    
    public boolean nextLegIsReady(){
        return true;
    }
    
    public Leg nextLeg(){
        return nextLeg;
    }

    Shelter getShelter() {
    return destination;
    }

    public void setNextLeg(Leg instance) {
    nextLeg=instance;
    }

    public String getShelterName() {
        String result="";
        if (destination!=null){
            result=destination.getFullName();
        }
        return result;
    }

    public String getEta() {
        String result="";
        if (eta!=null){
            result=eta.toString();
        }
        return result;
    }

    public Time getTime() {
    return eta;
    }

    
    public Stop getStop(){
        Stop s= null;
        if (destination!=null){
            s=destination.getStop();
        } else {
            try {
                //throw new Exception("hmm");
            } catch (Exception ex) {
                Logger.getLogger(Leg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }

    void addBus(Bus bus) {
    }
    
    
    
}
