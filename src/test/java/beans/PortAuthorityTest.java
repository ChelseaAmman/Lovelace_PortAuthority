package beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortAuthorityTest {

    @Test
    public void returnsNullOnEmptyNOA(){
        PortAuthority test = new PortAuthority();
        assertEquals(null, test.deserialize("").getName());
    }
}
