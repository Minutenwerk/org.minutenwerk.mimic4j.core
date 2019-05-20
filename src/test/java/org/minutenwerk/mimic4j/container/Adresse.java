package org.minutenwerk.mimic4j.container;

public class Adresse {

  private String street;

  private String city;

  public Adresse() {
  }

  public String getStreet() {
    return this.street;
  }

  public String getCity() {
    return this.city;
  }

  public Adresse setStreet(final String pStreet) {
    this.street = pStreet;
    return this;
  }

  public Adresse setCity(final String pCity) {
    this.city = pCity;
    return this;
  }
}
