package org.minutenwerk.mimic4j.impl.javahelper;

public class MmHelperAdresse {

	private String street;

	private String city;

	public MmHelperAdresse() {
	}

	public String getStreet() {
		return this.street;
	}

	public String getCity() {
		return this.city;
	}

	public MmHelperAdresse setStreet(final String pStreet) {
		this.street = pStreet;
		return this;
	}

	public MmHelperAdresse setCity(final String pCity) {
		this.city = pCity;
		return this;
	}
}
