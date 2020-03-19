package beans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.Serializable;
import java.io.StringWriter;

// For JPA persistency
// @Entity
// @Table(name="NoticeOfArrival")
public class NoticeOfArrival implements Serializable {

	private static final long serialVersionUID = 1L;

	// Uncomment if you want to use this bean as a transport object for JPA (from PortAuthority component)
	// The extra annotations allow for using an auto-increment key in MySQL (see description of NoticeOfArrival table)
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String name;
	private int mmsi;
	private int client;


	public NoticeOfArrival(String name, int mmsi, int client) {
		this.mmsi = mmsi;
		this.client = client;
		this.name = name;
	}

	public NoticeOfArrival(){}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getClient() {
		return client;
	}

	public int getMmsi() {
		return mmsi;
	}

	public Long getId() {
		return id;
	}

	public String toJsonString(){
		StringWriter sw = new StringWriter();
		JsonWriter jw = Json.createWriter( sw );
		jw.writeObject( toJsonObject() );
		jw.close();
		return sw.toString();
	}

	public JsonObject toJsonObject(){
		JsonObject model = Json.createObjectBuilder()
				.add("name", name)
				.add("mmsi", mmsi)
				.add("client", client)
				.build();
		return model;
	}


}
