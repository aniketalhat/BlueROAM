package main;

public class BTDevice {

	private String BTURL;
	private String name;
	
	public BTDevice(String name,String BTURL) {
		this.name=name;
		this.BTURL=BTURL;
	}
	
	public String getBTURL() {
		return BTURL;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
