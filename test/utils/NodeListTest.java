package utils;

import models.Ingredient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeListTest {
    NodeList<Ingredient> ingredients;
    Ingredient i1 = new Ingredient("Flour", "White", 20, "g");
    Ingredient i2 = new Ingredient("Sugar", "Sweet", 100, "g");
    Ingredient i3 = new Ingredient("Milk", "non-vegan", 50, "ml");

    @BeforeEach
    void setup(){
        ingredients = new NodeList<>();

        ingredients.addNode(i1);
        ingredients.addNode(i2);
    }

    @AfterEach
    void tearDown(){
        ingredients = null;
    }

    @Nested
    class Getter{
        @Test
        void testGetHead(){
            assertEquals(i2, ingredients.getHead().getContents());
            assertEquals(i1, ingredients.getHead().getNext().getContents());
        }

        @Test
        void testGetTemp(){
            assertEquals(i2, ingredients.getTemp().getContents());
            assertEquals(i1, ingredients.getTemp().getNext().getContents());
        }
    }

    @Nested
    class methods{
        @Test
        void testAdd(){
            assertEquals(i2, ingredients.getTemp().getContents());

            ingredients.addNode(i3);
            assertEquals(i3, ingredients.getTemp().getContents());
        }

        @Test
        void testReset(){
            ingredients.reset();
            assertNull(ingredients.getHead());
            assertNull(ingredients.getTemp());
        }

        @Test
        void testCount(){
            assertEquals(2, ingredients.count());

            ingredients.reset();
            assertEquals(0, ingredients.count());

            ingredients.addNode(i3);
            assertEquals(1, ingredients.count());
        }

        @Nested
        class testDeleteNodeItem{
            @Test
            void testDeleteHead(){
                assertEquals(i2, ingredients.getHead().getContents());
                ingredients.delete(i2);
                assertEquals(i1, ingredients.getHead().getContents());
            }

            @Test
            void testDeleteNotHead(){
                assertEquals(i1, ingredients.getTemp().getNext().getContents());
                ingredients.delete(i1);
                assertNull(ingredients.getTemp().getNext());
            }

            @Test
            void testDeleteEmptyNodeList(){
                ingredients.reset();
                assertNull(ingredients.delete(i1));
            }
        }
    }


}

