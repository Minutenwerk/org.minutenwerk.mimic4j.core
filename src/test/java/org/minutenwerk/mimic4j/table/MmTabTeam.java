package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

@MmTabAnnotation(id = "tab")
public class MmTabTeam extends MmTab<Team> {

  @MmStringAnnotation(id = "tn")
  public final MmString name = new MmString(this) {
    public MmAttributeAccessor<?,String> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.name();
    }
  };
  
  @MmTableAnnotation(id = "pt")
  public final MmTablePersonen personenTable = new MmTablePersonen(this) {
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
