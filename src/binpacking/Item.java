/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacking;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author remy.fischer
 */

// classe d'item correspondant aux caisses qui seront rentrées dans l'avion
// les items sont considérés à deux dimensions 

public class Item {
    
    private int tailleX;
    private int tailleY;
    private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private final int id;
    
    public Item(){
        
        tailleX = 0;
        tailleY = 0;
        id = ID_FACTORY.getAndIncrement();
        
    }
    
    public Item(int _x, int _y){
        
        tailleX = _x;
        tailleY = _y;
        id = ID_FACTORY.getAndIncrement();
        
    }
    
    public int getTailleX(){
        
        return tailleX;
        
    }
    
    public int getTailleY(){
        
        return tailleY;
        
    }
    
    public int getID(){
        
        return id;
        
    }
    
    public void setTailleX(int _x){
        
        tailleX = _x;
        
    }
    
    public void setTailleY(int _y){
        
        tailleY = _y;
        
    }
    
    
    
}
