package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;

public class AdressAccessor extends MmComponentAccessor<Person, Adress> {

  public AdressAccessor(final PersonAccessor testModelAccessor) {
    super(testModelAccessor, Person::getAdresse, Person::setAdresse);
  }

  public MmAttributeAccessor<Adress, String> street() {
    return new MmAttributeAccessor<>(this, Adress::getStreet, Adress::setStreet);
  }

  public MmAttributeAccessor<Adress, String> city() {
    return new MmAttributeAccessor<>(this, Adress::getCity, Adress::setCity);
  }
}
