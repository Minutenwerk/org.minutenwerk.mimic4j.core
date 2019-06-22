package org.minutenwerk.mimic4j.container;

public class Adress {

  private String street;

  private String city;

  public Adress() {
  }

  public String getStreet() {
    return this.street;
  }

  public String getCity() {
    return this.city;
  }

  public Adress setStreet(final String pStreet) {
    this.street = pStreet;
    return this;
  }

  public Adress setCity(final String pCity) {
    this.city = pCity;
    return this;
  }
}
