package beans;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;


@SessionScoped
@Named("noticeMgr")
public class NoticeManager implements Serializable {



	private static final long serialVersionUID = 1L;

	private NoticeOfArrival notice = new NoticeOfArrival();
	@Resource(mappedName = "jms/myLog")
	private Queue logMessages;
	@Resource(mappedName = "jms/TopicOne")
	private Topic TopicOne;
	@Resource(mappedName = "jms/TopicTwo")
	private Topic TopicTwo;
	@Resource(mappedName = "jms/myMessageFactory")
	private ConnectionFactory logFactory;


	
	public void setNotice( NoticeOfArrival notice ) {
		this.notice = notice;
	}
	
	
	public NoticeOfArrival getNotice() { return notice ; }

	
	public void mesg() {
		System.out.println("NoticeManager: mesg()");
		try (
				JMSContext context = logFactory.createContext()) {
				JMSProducer mp = context.createProducer();
				Message tm = context.createTextMessage();
				mp.send(logMessages, tm);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}
}
