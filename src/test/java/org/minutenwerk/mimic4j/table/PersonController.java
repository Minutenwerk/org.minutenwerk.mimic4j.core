package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.composite.MmRoot;

public class PersonController {

  private final TeamAccessor teamAccessor = new TeamAccessor();

  public final MmTabTeam tabTeam = new MmTabTeam(new MmRoot(), team());

  /** root accessor. */
  public TeamAccessor team() {
    return teamAccessor;
  }

}
