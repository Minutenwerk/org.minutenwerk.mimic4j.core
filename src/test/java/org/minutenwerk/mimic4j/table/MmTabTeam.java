package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTabTeam extends MmTab<Team> {

  @MmStringAnnotation(id = "tn")
  public final MmString name = new MmString(this) {
    @Override
    public MmAttributeAccessor<?,String> callbackMmGetAccessor(MmComponentAccessor<?, ?> pParentAccessor) {
      TeamAccessor team = (TeamAccessor) pParentAccessor;
      return team.name();
    }
  };
  
  @MmTableAnnotation(id = "pt")
  public final MmTablePersonen personenTable = new MmTablePersonen(this) {
    @Override
    public MmListAccessor<Team, List<Person>, Person> callbackMmGetAccessor(MmComponentAccessor<?, ?> pParentAccessor) {
      TeamAccessor team = (TeamAccessor) pParentAccessor;
      return team.personen();
    }
  };

  public MmTabTeam(MmBaseDeclaration<?, ?> pParent, MmRootAccessor<Team> pParentAccessor) {
    super(pParent, pParentAccessor);
  }
}
