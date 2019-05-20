package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

public class TeamAccessor extends MmRootAccessor<Team> {

  public MmAttributeAccessor<Team, String> name() {
    return new MmAttributeAccessor<>(this, Team::getName, Team::setName);
  }

  public PersonenAccessor personen() {
    return new PersonenAccessor(this);
  }

  public PersonAccessor person(final int index) {
    return new PersonAccessor(personen(), () -> index);
  }
}