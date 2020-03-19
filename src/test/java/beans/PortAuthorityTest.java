package beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortAuthorityTest {

    @Test
    public void noaToStringTest() {
        NoticeOfArrival testNOA = new NoticeOfArrival("hey", 2, 3);
        String testString = testNOA.toJsonString();
        assertEquals("{\"name\":\"hey\",\"mmsi\":2,\"client\":3}", testString);
    }

    @Test
    public void deserializeTest(){
        PortAuthority test = new PortAuthority();
        NoticeOfArrival NOAobj = new NoticeOfArrival("hey", 2, 3);
        NoticeOfArrival NOAgenerated = test.deserialize("{\"name\": \"hey\",\"mmsi\":2,\"client\":3}");
        assertEquals(NOAobj.getName(), NOAgenerated.getName());
        assertEquals(NOAobj.getClient(), NOAgenerated.getClient());
        assertEquals(NOAobj.getMmsi(), NOAgenerated.getMmsi());
    }
}
