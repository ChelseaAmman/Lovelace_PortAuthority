package beans;

import javax.json.*;
import java.io.StringReader;

public class PortAuthority {

    public NoticeOfArrival deserialize(String noa){
        JsonValue jsonVal = Json.createReader( new StringReader( noa )).read();
        JsonObject obj = (JsonObject) jsonVal;
        return createNOA(obj);
        }

    private NoticeOfArrival createNOA(JsonObject obj){
        JsonString nameObj = (JsonString) obj.get("name");
        String name = nameObj.getString();
        name = name.replace("\"", "");
        int mmsi = new Integer(obj.get("mmsi").toString());
        int client = new Integer(obj.get("client").toString());
        return new NoticeOfArrival(name, mmsi, client);
    }




}
