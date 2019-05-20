package org.minutenwerk.mimic4j.table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Supplier;

import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.table.Person.Gender;

public class PersonAccessor extends MmListEntryAccessor<List<Person>, Person> {

  public PersonAccessor(final MmListAccessor<?, List<Person>, Person> personAccessor, final Supplier<Integer> indexSupplier) {
    super(personAccessor, indexSupplier);
  }
	  
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
