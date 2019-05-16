package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;

public class MmTabAdresse extends MmTab<TestAdresse> {

  @MmStringAnnotation(id = "st")
  public final MmString street = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmComponentAccessor<?, ?> pRootAccessor) {
      return ((TestModelAccessor)pRootAccessor).adresse().street();
    }
  };

  @MmStringAnnotation(id = "ci")
  public final MmString city = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmComponentAccessor<?, ?> pRootAccessor) {
      return ((TestModelAccessor)pRootAccessor).adresse().city();
    }
  };
  
  public MmTabAdresse(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }
}
