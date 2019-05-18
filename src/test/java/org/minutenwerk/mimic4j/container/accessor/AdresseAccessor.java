package org.minutenwerk.mimic4j.container.accessor;

import org.minutenwerk.mimic4j.container.model.Adresse;
import org.minutenwerk.mimic4j.container.model.Person;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;

public class AdresseAccessor extends MmComponentAccessor<Person, Adresse> {

  public AdresseAccessor(final PersonAccessor testModelAccessor) {
    super(testModelAccessor, Person::getAdresse, Person::setAdresse);
  }

  public MmAttributeAccessor<Adresse, String> street() {
    return new MmAttributeAccessor<>(this, Adresse::getStreet, Adresse::setStreet);
  }

  public MmAttributeAccessor<Adresse, String> city() {
    return new MmAttributeAccessor<>(this, Adresse::getCity, Adresse::setCity);
  }
}
