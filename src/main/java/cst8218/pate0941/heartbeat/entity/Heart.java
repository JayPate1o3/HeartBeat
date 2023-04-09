/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0941.heartbeat.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jay Patel
 */
@Entity
@XmlRootElement
public class Heart implements Serializable {
    public static final int INITIAL_SIZE = 50;         // constant for fix value of a variable
    public static final int BEATS_TO_EXHAUSTION = 200;     // maximum beat
    public static final int CONTRACTED_MAX = 300;           //maximum value for contraction
    public static final int CONTRACTION_DECREMENT = 1;      // contraction decrement value
    public static final int BEAT_INCREMENT = 20;            // beat increment value
    public static final int SHRINK_DECREMENT = 1;           // beat decrement value
    public static final int STOP_SIZE = 10;                 // value of heart size to stop
    public static final int X_MAX = 200;                    // x-coordinate value
    public static final int Y_MAX = 200;                    // y-coordinate value
    public static final int SIZE_MAX = 300;                 // maximum size of heart

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;                                        // id variable with autogeneration constraint

    @Min(1) @Max(SIZE_MAX)
    private Integer size = INITIAL_SIZE;                // size with max(SIZE_MAX) and min constraint

    @Min(0) @Max(X_MAX)
    private Integer x;                  // x variable for x-coordinate

    @Min(0) @Max(Y_MAX)
    private Integer y;              // y variable for y-coordinate

    @Min(1) @Max(CONTRACTED_MAX)
    private Integer contractedSize = INITIAL_SIZE;      //contractedSize variable

    @Min(0) @Max(BEATS_TO_EXHAUSTION)
    private Integer beatCount;                  // number of beat 

    public void advanceOneTimeIncrement() {
        if (stillBeating()){                    //if still beating
            if (finishedCurrentBeat()){            //if size has decreased to contracted size
                newBeat();                             //suddenly increase size to begin new beat
                setBeatCount(getBeatCount() + 1);      //increment beat count
                if (exhausted()){                      //if beat count has reached exhausted level
                    shrink();                               //descrease contracted size
                    setBeatCount(0);                        //now not exhausted - reset beat count
                }
            } else {                               //else 
                continueContracting();                 //continue the contracting phase of a beat
            }
        }

    }

    private boolean stillBeating() {             // method to check the condition to check size of heart with minimum value
        return size > STOP_SIZE;
    }

    private boolean finishedCurrentBeat() {     // method to check whether a beat is completed or not
        return size <= contractedSize;
    }

    private void newBeat() {            // method to increment size by BEAT_INCREMENT
        size += BEAT_INCREMENT;
    }

    private void continueContracting() {
        size -= CONTRACTION_DECREMENT;      // method to decrement size by CONTRACTION_DECREMENT
    }

    private boolean exhausted() {
        return beatCount >= BEATS_TO_EXHAUSTION;    // method to check beat exhaustion with beatCount
    }

    private void shrink() {                             // logic to shrink the size of the HEART
        contractedSize -= SHRINK_DECREMENT;         
        if (contractedSize < STOP_SIZE) {       
            contractedSize = STOP_SIZE;
        }
    }

    // TODO: add getters and setters for all properties


    public void setBeatCount(Integer i) {  // set method for beatCount
        this.beatCount=i;
    }
    
    public Integer getBeatCount()   // get method for beatCount
    {
        return beatCount;
    }
    
    public Integer getContractedSize() {  // get method contractedSize
        return contractedSize;
    }

    /**
     * @return the x
     */
    public Integer getX() {     // get method contractedSize
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {   // set method for X
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public Integer getY() {    // get method for Y
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {    // set method for Y
        this.y = y;
    }

    /**
     * @return the size
     */
    public Integer getSize() {    // get method for size
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {   // set method for size
        this.size = size;
    }

    /**
     * @param contractedSize the contractedSize to set
     */
    public void setContractedSize(Integer contractedSize) {    // set method for contractedSize
        this.contractedSize = contractedSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {  //get method for id
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    public boolean validate(){
//        if (getX() == null){
//            //setX(some default)
//        }else{
//        if (getX() < 0){
//            return false;
//        }
//        }
//    }

    public void updates(Heart oldHeart){   // update method for updating entity's attributes
        if(getX() != null){
            oldHeart.setX(getX());
        }
              
        if(getY() != null){
            oldHeart.setY(getY());
        }
        
        if(getContractedSize() != null){
            oldHeart.setContractedSize(getContractedSize());
        }
        
        if(getSize() != null){
            oldHeart.setSize(getSize());
        }
               
        if(getBeatCount() != null){
            oldHeart.setBeatCount(getBeatCount());
        }
    
    }

    public void edit(Heart oldHeart) {   /// edit method for PUT request
        
        if(getX() != null){
            oldHeart.setX(getX());
        }
        else
        {
            oldHeart.setX(null);
        }
        
        if(getY() != null){
            oldHeart.setY(getY());
        }
        else
        {
            oldHeart.setY(null);
        }
        
        if(getContractedSize() != null){
            oldHeart.setContractedSize(getContractedSize());
        }
        else
        {
            oldHeart.setContractedSize(null);
        }
            
        if(getSize() != null){
            oldHeart.setSize(getSize());
        }
        else
        {
            oldHeart.setSize(null);
        }
               
        if(getBeatCount() != null){
            oldHeart.setBeatCount(getBeatCount());
        }
        else
        {
            oldHeart.setBeatCount(null);
        }
            
    }

    public void defaultInitialization() { 
         if(getSize() == null){
            setSize(10);
        }
    }

   
}

