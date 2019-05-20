package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

public class MmTabAdresse extends MmTab<Adresse> {

  @MmStringAnnotation(id = "st")
  public final MmString street = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      PersonAccessor person = (PersonAccessor) pRootAccessor;
      return person.adresse().street();
    }
  };

  @MmStringAnnotation(id = "ci")
  public final MmString city = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      PersonAccessor person = (PersonAccessor) pRootAccessor;
      return person.adresse().city();
    }
  };

  public MmTabAdresse(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }
}
