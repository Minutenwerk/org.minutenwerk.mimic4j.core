package org.minutenwerk.mimic4j.container;

public class PersonController {

  private final PersonAccessor personAccessor = new PersonAccessor();

  public final MmPagePerson pagePerson = new MmPagePerson(person());

  /** root accessor. */
  public PersonAccessor person() {
    return personAccessor;
  }

}
