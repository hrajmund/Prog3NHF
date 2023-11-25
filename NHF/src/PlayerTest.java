import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    public Player P1;

    public PlayerTest(){
        P1 = new Player("TestA",1);
        P1.setX(1);
        P1.setY(1);
    }
    @Test
    void getName() {
        assertEquals("TestA", P1.getName());
    }

    @Test
    void setName() {
        P1.setName("TestB");
        assertEquals("TestB", P1.getName());
    }

    @Test
    void setCoordinates() {
        P1.setCoordinates(2,3);
        assertEquals(2, P1.getX());
        assertEquals(3, P1.getY());
    }

    @Test
    void getNumberOfThePlayer() {
        assertEquals(1, P1.getNumberOfThePlayer());
    }

    @Test
    void setNumberOfThePlayer() {
        P1.setNumberOfThePlayer(2);
        assertEquals(2, P1.getNumberOfThePlayer());
    }
}