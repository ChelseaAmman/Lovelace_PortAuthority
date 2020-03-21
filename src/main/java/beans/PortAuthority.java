package beans;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.json.*;
import java.io.StringReader;

@MessageDriven(mappedName = "jms/myLog")
public class PortAuthority implements MessageListener {

    @Resource(mappedName = "jms/NoAClientOne")
    private Queue topic1;
    @Resource(mappedName = "jms/NoAClientTwo")
    private Queue topic2;

    public PortAuthority(){}

    NoticeManager notice = new NoticeManager();

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof TextMessage) {
            TextMessage tm = (TextMessage) msg;
            try {
                String text = tm.getText();
                NoticeOfArrival noa = deserialize(text);
                if (noa.getClient() == 1 | noa.getClient() == 3){
                    notice.sendMesg(text);
                }
                else if (noa.getClient() == 2){
                    notice.sendMesg(text);
                }
            } catch (Exception e) {
                System.out.println("Message bean error: " + e);
            }
        }
    }

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
