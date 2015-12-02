package baic.obd.protocol.model.body;

public class ETD {
	public static final String FLAG = "ETD";
	
	private String eventData;

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	@Override
    public String toString() {
	    return "ETD [eventData=" + eventData + "]";
    }
}
