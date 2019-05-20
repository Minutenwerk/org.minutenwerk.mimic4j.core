package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.container.PersonAccessor;
import org.minutenwerk.mimic4j.table.Person;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

@MmTabAnnotation(id = "tab")
public class MmTabTeam extends MmTab<Team> {

  public final MmTablePersonen testTable = new MmTablePersonen(this) {
	@Override
	public MmListAccessor<Team, List<Person>, Person> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.personen();
	}  
  };

  public MmTabTeam(MmBaseDeclaration<?, ?> pParent, MmRootAccessor<Team> pRootAccessor) {
    super(pParent, pRootAccessor);
  }
}
