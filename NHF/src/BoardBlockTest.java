import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardBlockTest {
    BoardBlock boardBlock;
    public BoardBlockTest() {
        boardBlock = new BoardBlock("E");
        boardBlock.setRow(1);
        boardBlock.setColumn(1);
    }
    @Test
    void setRow() {
        boardBlock.setRow(2);
        assertEquals(2,boardBlock.getRow());
    }

    @Test
    void setColumn() {
        boardBlock.setColumn(2);
        assertEquals(2, boardBlock.getColumn());
    }

    @Test
    void getRow() {
        assertEquals(1, boardBlock.getRow());
    }

    @Test
    void getColumn() {
        assertEquals(1, boardBlock.getColumn());
    }

    @Test
    void getState() {
        assertEquals("E",boardBlock.getState());
    }

    @Test
    void setState() {
        boardBlock.setState("B");
        assertEquals("B", boardBlock.getState());
    }
}