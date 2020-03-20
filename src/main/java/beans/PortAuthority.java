package beans;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.json.*;
import java.io.StringReader;

@MessageDriven(mappedName = "jms/myQueue")
public class PortAuthority implements MessageListener {

    @Resource(mappedName = "jms/NoAClientOne")
    private Queue topic1;
    @Resource(mappedName = "jms/NoAClientTwo")
    private Queue topic2;

    public PortAuthority(){}

    private void sendMessage(String msg){
        //TODO: whoever implements the factory will also need to implement sendMessage. Please send a message to topic1 and topic in their respective mappings
        //look at BookManagementFacadeBean.java in Renet's examples for reference
    }

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof TextMessage) {
            TextMessage tm = (TextMessage) msg;
            try {
                String text = tm.getText();
                EntityManager em = new EntityManager();
                NoticeOfArrival noa = deserialize(text);
                if (noa.getClient() == 1 | noa.getClient() == 3){
                    //sendMessage(topic1, blah blah blah)
                }
                else if (noa.getClient() == 2){
                    //sendMessage(topic2, blah blah blah)
                }
                em.persist(noa);

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
