package org.minutenwerk.mimic4j.container;


public class TestAdresse {

  private String street;

  private String city;
  
  public TestAdresse() {
  }

  public String getStreet() {
    return this.street;
  }

  public String getCity() {
    return this.city;
  }

  public TestAdresse setStreet(String pStreet) {
    this.street = pStreet;
    return this;
  }

  public TestAdresse setCity(String pCity) {
    this.city = pCity;
    return this;
  }
}
