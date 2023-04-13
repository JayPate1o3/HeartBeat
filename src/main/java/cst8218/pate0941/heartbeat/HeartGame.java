/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0941.heartbeat;

import cst8218.pate0941.heartbeat.entity.Heart;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Jay Patel
 */
@Singleton
@Startup
public class HeartGame {
    public static final double CHANGE_RATE = 10;
    
@EJB private cst8218.pate0941.heartbeat.HeartFacade ejbFacade2;


@PostConstruct
    public void go() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // the game runs indefinitely
                while (true) {
                    //update all the hearts and save changes to the database
                    Iterable<Heart> hearts = ejbFacade2.findAll();
                    for (Heart heart : hearts) {
                        heart.advanceOneTimeIncrement();
                        ejbFacade2.edit(heart);
                    }
                    //sleep while waiting to process the next frame of the animation
                    try {
                        
                        // wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long)(1.0/CHANGE_RATE*1000));                            
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
}

