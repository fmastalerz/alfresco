import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {
    @Test
    void checkIfOneEqualsOne(){
        assertEquals(1, 1, "expected should be 1");
    }

    @Test
    void checkIfOneIsNotEqualsToTwo(){
        assertNotEquals(2,1);
    }

}