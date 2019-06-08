package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

public class TeamAccessor extends MmRootAccessor<Team> {

  public MmAttributeAccessor<Team, String> name() {
    return new MmAttributeAccessor<>(this, Team::getName, Team::setName);
  }

  public MmListAccessor<Team, List<Person>, Person> personen() {
    return new MmListAccessor<>(this, Team::getPersonen, Team::setPersonen, Team::addPerson);
  }

  public PersonAccessor person(final int index) {
    return new PersonAccessor(personen(), () -> index);
  }
}