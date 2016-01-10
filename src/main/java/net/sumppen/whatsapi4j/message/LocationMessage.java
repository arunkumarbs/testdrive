package net.sumppen.whatsapi4j.message;

import net.sumppen.whatsapi4j.ProtocolNode;

public class LocationMessage extends BasicMessage {

	private String longitude;
	private String latitude;
	
	public LocationMessage(ProtocolNode node, String from, String group) {
		super(MessageType.LOCATION,node, from, group);
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
