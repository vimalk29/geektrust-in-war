package geektrust.in.war.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattalionTest {
    @Test
    void shouldSetExhaustTrueIfUnitDeployedMoreThanMax(){
        Battalion battalion = new Battalion(100, 201);
        assertEquals(true, battalion.getExhausted());
    }

    @Test
    void shouldSetExhaustFalseIfUnitDeployedLessThanMax(){
        Battalion battalion = new Battalion(100, 200);
        assertEquals(false, battalion.getExhausted());
    }

}
