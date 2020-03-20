package beans;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/NoAClientOne")
public class Agent1 implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof TextMessage) {
            TextMessage tm = (TextMessage) msg;
            try {
                String text = tm.getText();
                System.out.println("A1: Message received: " + text);
            } catch (Exception e) {
                System.out.println("A1: Message bean error: " + e);
            }
        }
    }
}
