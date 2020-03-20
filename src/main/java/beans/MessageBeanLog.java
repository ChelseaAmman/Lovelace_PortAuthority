package beans;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="jms/myLog" )
public class MessageBeanLog implements MessageListener {

    public MessageBeanLog() {}
    @Override
    public void onMessage(Message msg) {
        if(msg instanceof TextMessage) {
            TextMessage tm = (TextMessage) msg;
            String text;
            try {
                text = tm.getText();
                System.out.println("Message received: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}