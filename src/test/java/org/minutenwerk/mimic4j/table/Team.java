package org.minutenwerk.mimic4j.table;

import java.util.List;

public class Team {

  private String name;

  private List<Person> personen;

  public Team() {
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Person> getPersonen() {
    return personen;
  }

  public void setPersonen(final List<Person> personen) {
    this.personen = personen;
  }

  public void addPerson(final Person person) {
    this.personen.add(person);
  }
}
