package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.container.accessor.PersonAccessor;
import org.minutenwerk.mimic4j.container.mimic.MmTabPerson;

public class PersonController {

  private final PersonAccessor personAccessor = new PersonAccessor();

  public final MmTabPerson tabPerson = new MmTabPerson(new MmRoot(), person());

  /** root accessor. */
  public PersonAccessor person() {
    return personAccessor;
  }

}
