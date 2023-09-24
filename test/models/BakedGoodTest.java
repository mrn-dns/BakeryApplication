package models;

import models.Ingredient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.NodeList;

import static org.junit.jupiter.api.Assertions.*;

public class BakedGoodTest {
    BakedGood b1, b2, b3;
    Recipe r1, r2;

    @BeforeEach
    void setup(){
        b1 = new BakedGood("Cookies", "Ireland", "Sweet", "pic");
        b2 = new BakedGood("Brownie", "America", "Sour", "pic");
        b3 = new BakedGood("", "", "", "");

        r1 = r2 = new Recipe(new NodeList<>(), new NodeList<>());
    }

    @AfterEach
    void tearDown(){
        b1 = b2 = b3 = null;
        r1 = r2 = null;
    }

   @Nested
    class Getters{
        @Test
        void testGetBakedName(){
            assertEquals(b1.getBakedName(), "Cookies");
            assertEquals(b2.getBakedName(), "Brownie");
        }

        //@Test
       //void testGet
    }
}
