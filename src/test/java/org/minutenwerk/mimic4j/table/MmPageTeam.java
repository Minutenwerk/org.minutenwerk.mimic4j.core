package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.springframework.web.util.UriComponents;

public class MmPageTeam extends MmPage<Team> {

  @MmStringAnnotation(id = "tn")
  public final MmString name = new MmString(this) {
    @Override
    public MmAttributeAccessor<?,String> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      TeamAccessor team = (TeamAccessor) pParentAccessor;
      return team.name();
    }
  };
  
  @MmTableAnnotation(id = "pt")
  public final MmTablePersonen personenTable = new MmTablePersonen(this) {
    @Override
    public MmListAccessor<Team, Person> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      TeamAccessor team = (TeamAccessor) pParentAccessor;
      return team.personen();
    }
  };

  public MmPageTeam(MmBaseDeclaration<?, ?> pParent, MmRootAccessor<Team> pParentAccessor) {
    super(null, "pgTeam", pParentAccessor);
  }

  @Override
  public UriComponents getMmSelfReferencePath() {
    return null;
  }
}
