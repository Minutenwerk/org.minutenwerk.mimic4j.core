package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;

public class TeamAccessor extends MmRootAccessor<Team> {

  public MmAttributeAccessor<Team, String> name() {
    return new MmAttributeAccessor<>(this, Team::getName, Team::setName);
  }

  public MmListAccessor<Team, Person> personen() {
    return new MmListAccessor<>(this, Team::getPersonen, Team::setPersonen, Team::addPerson);
  }
}