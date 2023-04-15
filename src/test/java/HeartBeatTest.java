/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cst8218.pate0941.heartbeat.AppUser;
import cst8218.pate0941.heartbeat.entity.Heart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jay Patel
 */
public class HeartBeatTest {
    
    public HeartBeatTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testUserID() {
       Heart heart = new Heart();
       heart.setX(100);
       assertEquals(100, heart.getX());
       heart.setY(200);
       assertEquals(200, heart.getY());
       heart.setSize(300);
       assertEquals(300, heart.getSize());
       heart.setContractedSize(400);
       assertEquals(400, heart.getContractedSize());
       heart.setBeatCount(500);
       assertEquals(500, heart.getBeatCount());
       
    }
}
