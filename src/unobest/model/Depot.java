/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unobest.model;

/**
 *
 * @author comqdhb
 */
public class Depot extends Shelter {

    private static Depot instance=null;
    public static Depot getInstance(){
        if (instance==null){
            instance=new Depot();
        }
        return instance;
    }
    
    private Depot(){
        this(new Stop("Depot","Depot"),"Depot");
    }
    
    private Depot(Stop aThis, String name) {
        super(aThis, name);        
    }
    
    
    
}
