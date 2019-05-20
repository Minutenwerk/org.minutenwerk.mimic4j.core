package org.minutenwerk.mimic4j.container;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.minutenwerk.mimic4j.container.Person.Gender;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

public class PersonAccessor extends MmRootAccessor<Person> {

  public MmAttributeAccessor<Person, String> vorname() {
    return new MmAttributeAccessor<>(this, Person::getVorname, Person::setVorname);
  }

  public MmAttributeAccessor<Person, String> nachname() {
    return new MmAttributeAccessor<>(this, Person::getNachname, Person::setNachname);
  }

  public MmAttributeAccessor<Person, Boolean> isMember() {
    return new MmAttributeAccessor<>(this, Person::isMember, Person::setMember);
  }

  public MmAttributeAccessor<Person, LocalDate> birthday() {
    return new MmAttributeAccessor<>(this, Person::getBirthday, Person::setBirthday);
  }

  public MmAttributeAccessor<Person, Gender> gender() {
    return new MmAttributeAccessor<>(this, Person::getGender, Person::setGender);
  }

  public AdresseAccessor adresse() {
    return new AdresseAccessor(this);
  }

  public MmAttributeAccessor<Person, Instant> instant() {
    return new MmAttributeAccessor<>(this, Person::getInstant, Person::setInstant);
  }

  public MmAttributeAccessor<Person, LocalTime> localTime() {
    return new MmAttributeAccessor<>(this, Person::getLocalTime, Person::setLocalTime);
  }

  public MmAttributeAccessor<Person, LocalDateTime> localDateTime() {
    return new MmAttributeAccessor<>(this, Person::getLocalDateTime, Person::setLocalDateTime);
  }

  public MmAttributeAccessor<Person, ZonedDateTime> zonedDateTime() {
    return new MmAttributeAccessor<>(this, Person::getZonedDateTime, Person::setZonedDateTime);
  }
}
