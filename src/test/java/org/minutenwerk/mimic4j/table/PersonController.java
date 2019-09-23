package org.minutenwerk.mimic4j.table;

public class PersonController {

  private final TeamAccessor teamAccessor = new TeamAccessor();

  public final MmTabTeam tabTeam = new MmTabTeam(null, team());

  /** root accessor. */
  public TeamAccessor team() {
    return teamAccessor;
  }

}
